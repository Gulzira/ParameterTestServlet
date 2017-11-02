package kz.kaznitu.su;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class SessionTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());

        String title = "Welcome Back to my website";
        Integer visitCount = new Integer(0);
        String visitCountKey = new String("visitCount");
        String userIDKey = new String("userID");
        String userID = new String("ABCD");
        if (session.isNew()) {
            title = "Welcome to my website";
            session.setAttribute(userIDKey, userID);
            session.setAttribute(visitCountKey, visitCount);
        }
        else {
            visitCount = (Integer)session.getAttribute(visitCountKey);
            visitCount = visitCount + 1;
            userID =(String)session.getAttribute(userIDKey);
        }
        session.setAttribute(visitCountKey, visitCount);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String docType = "<!doctype html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<h2 align = \"center\"> Session Information</h2>\n" +
                "<table border = \"1\" align = \"center\">\n" +
                "<tr bgcolor = \"#949494\">\n" +
                "<th>Session info</th><th>value</th>" +
                "</th>\n" +

                "<tr>\n" +
                " <td>id</td>\n" +
                " <td>" + session.getId()+"</td>"+
                "</tr>\n" +
                "<tr>\n" +
                " <td> Creation Time</td>\n" +
                " <td>"+ createTime + "</td>" +
                "</tr>\n" +

                "<tr>\n" +
                " <td>Time of Last Access</td>\n" +
                " <td>" +lastAccessTime + "</td>" +
                "</tr>\n" +

                "<tr>\n" +
                " <td>User ID</td>\n" +
                " <td>" + userID + "</td>" +
                "</tr>\n" +

                "<tr>\n" +
                " <td>Number of visits</td>\n" +
                " <td>" +visitCount + "</td>" +
                "</tr>\n" +
                "</table>\n" +
                "</body>\n"+
                "</html>"

        );
    }
}
