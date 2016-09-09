package examples.servlets;

//import examples.utils.common.ExampleUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * 
 * このサーブレット サンプルでは、クッキーの作成および取得の方法と、
 * クッキーに有効期限を設定する方法を示します。
 *
 * <p> このサーブレットは、サーバが起動してからすべての
 * クライアントから何回アクセスされたかを表示し、また、
 * 各クライアントから 10 秒以内に続けてアクセスされた
 * 回数も表示します。
 *
 * <p><h3>サンプルのビルド</h3>  
 * <ol>
 * <li> 新しいコマンド シェルを開きます。
 * <p><li>「<a href=../examples.html#environment>サンプルのビルドおよび
 * 実行用環境のセットアップ</a>」で説明しているとおりに、この開発シェルを
 * セットアップします。
 * <p>
 * <li>サンプルのディレクトリに移動します:
 * <pre> prompt&gt;<b> cd %SAMPLES_HOME%\server\examples\src\examples\servlets</b></pre>
 * <p>
 * <li>以下のように、ant を使ってこのサーブレットをビルドします。
 * <pre>  prompt&gt;<b> ant CookieCounter</b></pre>
 * 
 * 
 * <p> <li><a href=../examples.html>サンプル用コンフィグレーション</a> を使って
 * WebLogic Server を起動します。<p> 
 *
 * </ol>
 * <p><h3>サーバのコンフィグレーション</h3>
 * <dl>
 * <dd><font face="Courier New" size=-1>examplesWebApp</font> が
 * <a href=../examples.html#webApp>サーバ上にデプロイ</a>
 * されていることを確認してください。
 * </dl>
 * <p><h3>サンプルの実行</h3>
 * <ol>
 * <li>Web ブラウザを使って、以下の URL をロードします。
 * 
 * <pre><b>http://localhost:7001/examplesWebApp/CookieCounter</b></pre>
 *
 * <li>ブラウザで再ロード用ボタンをクリックし（または、大半のブラウザでは
 * 〔Control〕+〔R〕を押す）、 再ロード用ボタンをクリックするたびにテキストの
 * どちらの行の数字もインクリメントする様子を観察します。
 *
 * <p><li>10 秒以上待ってから、ブラウザの再ロード用ボタンを再びクリックします。
 * 2 行目のテキストが変化することに注意してください。これは、10 秒後にクッキーの
 * 有効期限が切れたことを示しています。再ロード用ボタンを再びクリックすると、
 * 2 行目のカウントはまた 1 から始まります。
 *
 * </ol>
 * <h3>注意</h3>
 * ブラウザでクッキーを無効にしてある場合には、このサーブレットは動作しません。
 * 非クッキー対応の Web ブラウザを扱う絶対確実な方法については、
 * <a href="SessionServlet.html">SessionServlet</a> サンプルを参照してください。
 * <p>
 * HP プラットフォームでは、クッキーは 10 秒たっても有効期限が切れないことがあります。
 * </ol>
 *
 * <h3>補足事項</h3>
 *
 * サーブレットの詳細については、『<a href="http://edocs.beasys.co.jp/e-docs/wls/docs81/servlet/index.html">
 * WebLogic HTTP サーブレット プログラマーズ ガイド</a>』を参照してください。
 * <p>
 * @author Copyright (c) 1999-2003 by BEA Systems, Inc. All Rights Reserved.
 */
public class CookieCounterServlet extends HttpServlet {
  private int pageCount = 0;

 /**
  * サーブレットを初期化します。プロパティ「initial」を探して、
  * pageCount 変数に設定します。
  */
  public void init(ServletConfig config) throws ServletException  {
    super.init(config);
    String s = getInitParameter("initial");
    if (s == null) pageCount = 0;
    else pageCount = Integer.parseInt(s);
  }

 /**
  * サーブレットの service メソッドを実装します。
  */
  public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException
  {
    boolean cookieFound = false;
    Cookie thisCookie = null;

    // getWriter() を呼び出す前にコンテンツ タイプを設定する必要がある
    res.setContentType("text/html");
    // これで、getWriter() を呼び出せる
    PrintWriter out = res.getWriter();

    // リクエストからクッキーを取り出してみる
    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
      for(int i=0; i < cookies.length; i++) {
        thisCookie = cookies[i];
        if (thisCookie.getName().equals("CookieCount")) {
          cookieFound = true;
          break;
        }
      }
    }

    if (cookieFound == false) {
      // 新しい Cookie インスタンスを作成し、その有効期限を設定する
      thisCookie = new Cookie("CookieCount", "1");
      thisCookie.setMaxAge(60*1);
      // この新しいクッキーを応答に追加する
      res.addCookie(thisCookie);
    }

    out.println("<HTML><HEAD><TITLE>Cookie Counter</TITLE></HEAD><BODY>");
    pageCount++;
    out.println("<p>");
    out.println("<font color=blue size=+1>");
    out.println("<p><br><br><br>This page has been visited " + pageCount +
                (pageCount==1?" time":" times") +
                " before.\n");

    // クライアント固有のカウントの詳細を表示する
    if (cookieFound) {
      int cookieCount = Integer.parseInt(thisCookie.getValue());
      cookieCount++;
      // クッキーの新しい値を設定し、それを応答に追加する
      thisCookie.setValue(String.valueOf(cookieCount));
      thisCookie.setMaxAge(10);
      res.addCookie(thisCookie);

      out.println("<p>You have visited this page " +
                  thisCookie.getValue() +
                  (cookieCount==1?" time":" times") +
                  " within the past 10 seconds.\n");

    } else {
      out.println("<p>Either you haven't visited this page in the last 10 seconds, "+
                  "or your browser doesn't like cookies!\n");
    }
    out.println("</BODY></HTML>");
    // printWriter はクローズしない
  }
}

