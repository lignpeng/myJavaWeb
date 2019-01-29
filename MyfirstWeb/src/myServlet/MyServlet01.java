package myServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet01
 */
@WebServlet("/MyServlet01")
public class MyServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //把i定义成全局变量，当多个线程并发访问变量i时，就会存在线程安全问题了
	int i = 1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet01() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter outPrintWriter  = response.getWriter();
		outPrintWriter.println("<!DOCTYPE HTML PUBLIC \\\"-//W3C//DTD HTML 4.01 Transitional//EN\\\">");
		outPrintWriter.println("<html>");
		outPrintWriter.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		outPrintWriter.println("  <BODY>");
		outPrintWriter.print("    This is ");
		outPrintWriter.print(this.getClass());
		outPrintWriter.println(", using the GET method\n");
		/**
		* 当多线程并发访问这个方法里面的代码时，会存在线程安全问题吗
		* i变量被多个线程并发访问，但是没有线程安全问题，因为i是doGet方法里面的局部变量，
		* 当有多个线程并发访问doGet方法时，每一个线程里面都有自己的i变量，
		* 各个线程操作的都是自己的i变量，所以不存在线程安全问题
		* 多线程并发访问某一个方法的时候，如果在方法内部定义了一些资源(变量，集合等)
		* 那么每一个线程都有这些东西，所以就不存在线程安全问题了
		*/
//		int i= 1;
//		i++;
		/**
		* 加了synchronized后，并发访问i时就不存在线程安全问题了，
		* 为什么加了synchronized后就没有线程安全问题了呢？
		* 假如现在有一个线程访问Servlet对象，那么它就先拿到了Servlet对象的那把锁
		* 等到它执行完之后才会把锁还给Servlet对象，由于是它先拿到了Servlet对象的那把锁，
		* 所以当有别的线程来访问这个Servlet对象时，由于锁已经被之前的线程拿走了，后面的线程只能排队等候了
		* 在java中，每一个对象都有一把锁，这里的this指的就是Servlet对象
		*/
		synchronized (this) {
			i++;
			response.getWriter().append("i = " + i);
		}
		
		
//		outPrintWriter.println("i = " + i);
		outPrintWriter.println("  </BODY>");
		outPrintWriter.println("</HTML>");
		outPrintWriter.flush();
		outPrintWriter.close();

				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter outPrintWriter  = response.getWriter();
		outPrintWriter.println("<!DOCTYPE HTML PUBLIC \\\"-//W3C//DTD HTML 4.01 Transitional//EN\\\">");
		outPrintWriter.println("<html>");
		outPrintWriter.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		outPrintWriter.println("  <BODY>");
		outPrintWriter.print("    This is ");
		outPrintWriter.print(this.getClass());
		outPrintWriter.println(", using the GET method");
		outPrintWriter.println("  </BODY>");
		outPrintWriter.println("</HTML>");
		outPrintWriter.flush();
		outPrintWriter.close();
	}

}
