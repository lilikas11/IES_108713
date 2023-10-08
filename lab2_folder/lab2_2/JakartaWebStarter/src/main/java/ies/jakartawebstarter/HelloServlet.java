package ies.jakartawebstarter;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if(request.getParameter("msg") != null) {
            out.println("<h1>Hello " + request.getParameter("msg") + "</h1>");
        } else {
            out.println("<h1>Hello World!</h1>");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}