package kz.kaznitu.su;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Calculator extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String title = "Calculator";
        Double number1 = Double.parseDouble(req.getParameter("number1"));
        Double number2 = Double.parseDouble(req.getParameter("number2"));

        String oper = req.getParameter("oper");
        String operr = new String();
        switch(oper) {
            case "add":
                oper = (number1 + number2) + "";
                operr="+";
                break;
            case "sub":
                oper = (number1 - number2) + "";
                operr="-";
                break;
            case "mul":
                oper = (number1 * number2) + "";
                operr="*";
                break;
            case "div":
                oper = (number1 / number2) + "";
                operr="/";
                break;
        }
        String docType =
                "<!doctype html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                " <li><b>Result</b>: "+
                number1 + " " + operr + " "+ number2 + " = " + oper +"\n" +
                "</ul>\n" +
                "</body>\n" +
                "</html>"
        );

    }}