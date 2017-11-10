package kz.kaznitu.su;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionCalculator extends HttpServlet {
    public static final String SESSION_MAP = "sessionMap";
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        Map<String, List> sessionMap = (Map<String, List>)req.getServletContext().getAttribute("sessionMap");

        if (sessionMap==null){
            sessionMap = new HashMap<String, List>();
        }
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String title = "Calculator";
        Double number1 = Double.parseDouble(req.getParameter("number1"));
        Double number2 = Double.parseDouble(req.getParameter("number2"));

        String oper = req.getParameter("oper");

        HttpSession session = req.getSession(true);
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
        ArrayList<String> listOperations;

        if (session.isNew()) {
            listOperations = new ArrayList<String>();
        } else {
            listOperations = (ArrayList<String>) session.getAttribute("formula");
        }
        String docType =
                "<!doctype html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n");

        out.println("</td>");
        out.println("<td style=\"vertical-align:top \">");
        listOperations.add(number1 + " " + operr + " " + number2 + " = " + oper);
        session.setAttribute("formula", listOperations);

//out.println("<h1>"+ session.getId() +"</h1>");

        for (String opera : listOperations) {
            out.println("<h3>" + opera + "</h3>");
        }

        sessionMap.put(session.getId(), listOperations);
        getServletContext().setAttribute(SESSION_MAP, sessionMap);




        for (Map.Entry<String, List> entry : sessionMap.entrySet()) {
            String sessionId = entry.getKey();
            List listOper = entry.getValue();

// out.println("<h1 style=\"color:red\">" + sessionId + "</h1>");

/*for (Object str : listOper) {
out.println("<h3>" + str + "</h3>");
}*/
            out.println( "</body>\n" +
                    "</html>"
            );

        }}}