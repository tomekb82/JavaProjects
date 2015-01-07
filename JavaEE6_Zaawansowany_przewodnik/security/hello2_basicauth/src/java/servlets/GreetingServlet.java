/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "GreetingServlet", urlPatterns =  {
    "/greeting"}
)
@ServletSecurity(@HttpConstraint(transportGuarantee = TransportGuarantee.CONFIDENTIAL, rolesAllowed =  {
    "TutorialUser"}
)
)
public class GreetingServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setBufferSize(8192);

        PrintWriter out = response.getWriter();

        // then write the data of the response
        out.println("<html>" + "<head><title>Czeœæ</title></head>");

        // then write the data of the response
        out.println(
                "<body  bgcolor=\"#ffffff\">"
                + "<img src=\"duke.waving.gif\" alt=\"Duke waving\">"
                + "<h2>Czeœæ, mam na imiê Duke, a ty?</h2>"
                + "<form method=\"get\">"
                + "<input type=\"text\" name=\"username\" size=\"25\">"
                + "<p></p>" + "<input type=\"submit\" value=\"Submit\">"
                + "<input type=\"reset\" value=\"Reset\">" + "</form>");

        String username = request.getParameter("username");

        if ((username != null) && (username.length() > 0)) {
            RequestDispatcher dispatcher = getServletContext()
                                               .getRequestDispatcher(
                        "/response");

            if (dispatcher != null) {
                dispatcher.include(request, response);
            }
        }

        out.println("</body></html>");
        out.close();
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Serwlet Hello wita siê z uwierzytelnionym u¿ytkownikiem.";
    }
}
