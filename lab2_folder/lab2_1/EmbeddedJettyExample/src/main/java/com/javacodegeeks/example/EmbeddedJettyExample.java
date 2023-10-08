package com.javacodegeeks.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class EmbeddedJettyExample {

  public static void main(String[] args) throws Exception {

    Server server = new Server(8680);

    ServletHandler servletHandler = new ServletHandler();
    server.setHandler(servletHandler);

    servletHandler.addServletWithMapping(HelloServlet.class, "/");

    server.start();
    server.join();

  }

  public static class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      response.setContentType("text/html");
      response.setStatus(HttpServletResponse.SC_OK);

      String user = request.getParameter("msg");

      if (user != null) {
        response.getWriter().println("<h1>Hello "  + user.toString() + " </h1>");
      } else {
        response.getWriter().println("<h1>Hello World</h1>");
      }
    }
  }
}