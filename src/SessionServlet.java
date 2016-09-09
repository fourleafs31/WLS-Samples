package examples.servlets;
/*
 * @(#)SessionServlet.java	1.21 97/05/22
 * 
 * Copyright (c) 1996-1997 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * 本ソフトウェアは、米国 Sun Microsystems, Inc. の秘密情報
 * （以下「秘密情報」という）で構成されます。お客様は、秘密
 * 情報を第三者に開示してはならず、またお客様がサンと締結
 * された使用許諾契約の条項に基づいてのみ、それらを使用する
 * ものとします。
 * 
 * サンは本ソフトウェアの適合性について、商品性、特定目的への
 * 適合性、および第三者の権利に対する非侵害の黙示の保証を含み
 * それに限定されない、明示的または黙示的な、いかなる表明も
 * 保証も行いません。サンは、本ソフトウェアまたはその派生物の
 * 使用、改変または頒布に起因してお客様が蒙ったいかなる損害に
 * ついても、責任を負いません。
 * 
 * 著作権 第 1.0 版
 */


//import examples.utils.common.ExampleUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import weblogic.management.MBeanHome;

/**
 * 
 * これは HTTP サーブレットの簡単なサンプルで、クライアントが
 * このサーブレットにアクセスした回数を HttpSession クラスを
 * 使って追跡するものです。セッション トラッキングを利用する
 * ことで、クライアント ブラウザがクッキーをサポートしない場合
 * でも、クライアントを追跡したりクライアント固有のデータを保存
 * することができます。これを実現するには、トラッキング ID を
 * コード化して、クライアントに返す HTML ページのハイパーリンクの 
 * URL に入れます。
 *
 * <p><h3>サンプルのビルド</h3>  
 * <ol>
 * <li> 新しいコマンド シェルを開きます。
 * <p><li>「<a href=../examples.html#environment>サンプルのビルドおよび
 * 実行用環境のセットアップ</a>」で説明されているとおりに、この開発シェルを
 * セットアップします。
 * <p>
 * <li>サンプル ディレクトリに移動します:
 * <pre> prompt&gt;<b> cd %SAMPLES_HOME%\server\examples\src\examples\servlets</b></pre>
 * <p>
 * <li>以下のように、ant を使ってこのサーブレットをビルドします。
 * <pre>  prompt&gt;<b> ant SessionServlet</b></pre>
 * 
 * 
 * <p> <li><a href=../examples.html>サンプル用コンフィグレーション</a> を使って
 * WebLogic Server を起動します。<p> 
 *
 * </ol>
 * <p><h3>サーバのコンフィグレーション</h3>
 * <ol>
 * <li><font face="Courier New" size=-1>examplesWebApp</font> が <a href=../examples.html#webApp>サーバ上にデプロイ</a>されていることを確認してください。
 * 
 * <p><li>ブラウザでクッキーを無効にしたまま、このサンプルをテスト
 * したい場合には、URL 書き換えによるセッション トラッキングを 
 * <font face="Courier New" size=-1>examplesWebApp</font> に必ず
 * コンフィグレーションしてください。デフォルトでは、URL 書き換えは
 * 無効になっています。
 *
 * </ol>
 * <p><h3>サンプルの実行</h3>
 * <ol>
 * <li>Web ブラウザを使って、以下の URL をロードします。
 * 
 * <pre><b>http://localhost:7001/examplesWebApp/SessionServlet</b></pre>
 * <p><li>ブラウザでクッキーを無効にしてから、サンプル内のリンクを
 * クリックして URL 書き換えが必ず使われるようにすると、今度は
 * ブラウザの URL アドレス バーの表示にセッション ID が含まれている
 * のがわかります。

 * </ol>
 * <h3>補足事項</h3>
 *
 * HTTP サーブレットの詳細については、『<a href="http://edocs.beasys.co.jp/e-docs/wls/docs70/servlet/index.html">
 * WebLogic HTTP サーブレット プログラマーズ ガイド</a>』を参照してください。
 * <p>
 * @author Copyright (c) 1996-1998 by WebLogic, Inc. All Rights Reserved.
 * @author Copyright (c) 1999-2003 by BEA Systems, Inc. All Rights Reserved.  */


public class SessionServlet extends HttpServlet { 
  
  public void doGet (HttpServletRequest req, HttpServletResponse res)
       throws ServletException, IOException
  {
    // セッション オブジェクトを取得する
    HttpSession session = req.getSession(true);

    // ローカル変数
    String serverName;
    String failoverMessage = "";
    String str = (String) session.getAttribute("simplesession.servername");
    PrintWriter out = res.getWriter();
    Integer ival;

    // まず、コンテンツ タイプなどの応答ヘッダー フィールドを設定する
    res.setContentType("text/html");
    
    // セッションからカウント値を取り出す
    ival = (Integer) session.getAttribute("sessiontest.counter");
    if (ival==null) ival = new Integer(1);
    else ival = new Integer(ival.intValue() + 1);
    
    session.setAttribute("sessiontest.counter", ival);

    out.println("<HTML><HEAD><TITLE>Session Servlet</TITLE></HEAD><BODY>");

    try {
      serverName = getServerName();
      if (str != null && str.equals(serverName)) failoverMessage = "";
      else failoverMessage = " (failing over from server" +str+ ")";
      session.setAttribute("simplesession.servername", serverName);

      // そのあと、応答のデータを書き込む
      out.println("You have hit this page <b>" + ival + "</b> times.<p>");
      out.println("The server currently hosting this session is <B>" +
        serverName + "</B>" + failoverMessage + "<p>");
      // ユーザが次の行のリンクをクリックした場合には、
      // SessionServlet が再び呼び出されるが、
      // 今度は URL 書き換えが有効になっている。
      out.println("Click <a href=" + res.encodeURL("SessionServlet") +
                  ">here</a>");
      out.println(" to ensure that session tracking is working even " +
                  "if cookies aren't supported.<br>");
      out.println("Note that by default URL rewriting is not enabled " +
                  "because of its expensive overhead");
      out.println("<p>");
      out.println("<h4>Request and Session Data:</h4>");
      out.println("Session ID in Request: " + req.getRequestedSessionId());
      out.println("<br>Session ID in Request from Cookie: " +
                  req.isRequestedSessionIdFromCookie());
      out.println("<br>Session ID in Request from URL: " +
                  req.isRequestedSessionIdFromURL());
      out.println("<br>Valid Session ID: " + req.isRequestedSessionIdValid());
      out.println("<h4>Session Data:</h4>");
      out.println("New Session: " + session.isNew());
      out.println("<br>Session ID: " + session.getId());
      out.println("<br>Creation Time: " + session.getCreationTime());
      out.println("<br>Last Accessed Time: " + session.getLastAccessedTime());
    } catch (Exception ex) {
      out.println("<p><b>!! Example Failed !!<br><br> The following exception occur:</b><br><br>");
      ex.printStackTrace(new PrintWriter(out));
      ex.printStackTrace();
    }
    finally {
      out.println("</BODY></HTML>");

    }
  }

  private String getServerName() throws Exception {
    String toReturn = null;

    Hashtable<String,String> h = new Hashtable<String,String>();
    h.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
    h.put(Context.SECURITY_PRINCIPAL, "weblogic");
    h.put(Context.SECURITY_CREDENTIALS, "weblogic1");
    h.put(Context.PROVIDER_URL, "t3://10.185.178.48:10305");

    Context myCtx = new InitialContext(h);
    MBeanHome mbeanHome = (MBeanHome)myCtx.lookup("weblogic.management.home.localhome");
    toReturn = mbeanHome.getMBeanServer().getServerName();
    if (toReturn == null) return "";
    else return toReturn;
  }
}
