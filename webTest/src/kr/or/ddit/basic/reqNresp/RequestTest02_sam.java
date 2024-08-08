//package kr.or.ddit.basic.reqNresp;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.catalina.startup.SetContextPropertiesRule;
//
//
//@WebServlet("/requestTest02.do")
//public class RequestTst02 extends HttpServlet {
//   private static final long serialVersionUID = 1L;
//       
//   
//   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      request.setCharacterEncoding("utf-8");
//      
//      response.setCharacterEncoding("utf-8");
//      response.setContentType("text/html; charset=utf-8");
//      
//      PrintWriter out = response.getWriter();
//      
//      // 클라이언트가 보낸 데이터 받기
//      String strNum1 = request.getParameter("num1");
//      String op = request.getParameter("op");
//      String strNum2 = request.getParameter("num2");
//      
//      int num1 = Integer.parseInt(strNum1);
//      int num2 = Integer.parseInt(strNum2);
//      
//      double result = 0; // 계산 결과가 저장될 변수
//      boolean calcOk = true; // 계산 성공 여부가 저장될 변수(계산 실패시 false)
//      
//      switch(op) {
//          case "+" : result = num1 + num2; break;
//          case "-" : result = num1 - num2; break;
//          case "*" : result = num1 * num2; break;
//          case "/" : 
//             if(num2 != 0) {
//                result = (double)num1 / num2; 
//             }else {
//                calcOk = false;
//             }
//             break;
//          
//          case "%" : 
//             if(num2 != 0) {
//                result = num1 % num2; 
//             }else {
//                calcOk = false;
//             }
//             break;
//      }
//      
//      out.println("<html>");
//      out.println("<head><meta charset='utf-8'>"
//            + "<title>Request 객체 연습 </title></head>");
//      out.println("<body>");
//      out.println("<h3>계산결과</h3><hr>");
//      out.printf("%d %s %d =", num1, op,num2);
//      
//      if(calcOk) {
//         out.println(result + "<br>");
//         
//      }else {
//         out.println("계산 불능(0으로 나누기)<br>");
//      }
//      out.println("</body></html>");
//      
//   }
//
//
//   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      // TODO Auto-generated method stub
//      doGet(request, response);
//   }
//
//}
