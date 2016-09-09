<%@ page session="false" contentType="text/html" import="java.net.*,weblogic.net.http.*"%>
<%!
// JMOD 2011/10/05
//private static String BODY="<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"><env:Header/><env:Body><m:sayHello xmlns:m=\"http://example.org\"><m:message>TEST</m:message></m:sayHello></env:Body></env:Envelope>";
private static String BODY="<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"><env:Header /><env:Body><sayHello xmlns=\"http://simple.jws_basic.webservices.examples\"><message>TEST</message></sayHello></env:Body></env:Envelope>";


private void doAsync(String urlStr) throws Exception {
   URL url = new URL(urlStr);
   weblogic.net.http.HttpsURLConnection conn = new weblogic.net.http.HttpsURLConnection(url);
   //weblogic.net.http.HttpURLConnection conn = new weblogic.net.http.HttpURLConnection(url);
   conn.setDoOutput(true);
   conn.setRequestMethod("POST");
   conn.setRequestProperty("Content-Type","text/xml; charset=utf-8");
   conn.connect();
   OutputStream out = conn.getOutputStream();
   out.write(BODY.getBytes());
   out.flush();
   
   final Object waiter = new Object();
   AsyncResponseCallback callback = new AsyncResponseCallback() {
      public void handleResponse(weblogic.net.http.HttpURLConnection connection) {
         try {
         System.out.println("Response Code:" + connection.getResponseCode());
         System.out.println("Response Headers:");
         java.util.Map<String, java.util.List<String>> headers = connection.getHeaderFields();
         java.util.Set<String> names = headers.keySet();
         for(String name : names) {
            System.out.println("\t"+ name + ":" + headers.get(name));
         }
         InputStream in = connection.getInputStream();
         BufferedReader bin = new BufferedReader(new InputStreamReader(in));
         String line = null;
         while((line = bin.readLine()) != null) {
            System.out.println(line);
         }
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            synchronized(waiter) {
               waiter.notifyAll();
            }
         }
      }
   };

   synchronized(waiter) {
      AsyncResponseHandler.getInstance().writeRequestAndRegister(conn, callback);
      waiter.wait();
   }
}
%>
<%
String url = request.getParameter("URL");
if (url != null) {
   try {
      doAsync(url);
   } catch (Exception e) {
      System.out.println("### Exception occurred!! : MSG: " + e.getMessage());
      e.printStackTrace();
   }
}
%>
<html>
<head><title>TEST</title></head>
<body>
OK
</body>
</html>
