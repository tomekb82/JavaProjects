/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package fileupload;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * Przykład serwletu dotyczącego przesyłania plików.
 */
@WebServlet(name = "FileUploadServlet", urlPatterns =  {
    "/upload"}
)
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(
                FileUploadServlet.class.getCanonicalName());

    /**
     * Przetwarza żądania HTTP dotyczące metod
     * <code>GET</code> i
     * <code>POST</code>.
     *
     * @param request żądanie serwletu
     * @param response odpowiedź serwletu
     * @throws ServletException, jeśli wystąpią błędy związane z serwletem
     * @throws IOException, jeśli wystąpią błędy dotyczące I/O
     */
    protected void processRequest(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Tworzy komponent ścieżki, by określić miejsce zapisu pliku.
        final String path = request.getParameter("destination");
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(
                        new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            writer.println("Nowy plik " + fileName + " utworzony w " + path);
            LOGGER.log(
                    Level.INFO,
                    "Plik {0} został przesłany do {1}",
                    new Object[] { fileName, path });
        } catch (FileNotFoundException fne) {
            writer.println(
                    "Albo nie wskazałeś pliku do przesłania, albo "
                    + "próbujesz go zapisać w nieistniejącej lub niedostępnej "
                    + "lokalizacji.");
            writer.println("<br/> BŁĄD: " + fne.getMessage());

            LOGGER.log(
                    Level.SEVERE,
                    "Problemy w trakcie przesyłu pliku. Błąd: {0}",
                    new Object[] { fne.getMessage() });
        } finally {
            if (out != null) {
                out.close();
            }

            if (filecontent != null) {
                filecontent.close();
            }

            if (writer != null) {
                writer.close();
            }
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Nagłówek części = {0}", partHeader);

        for (String content : part.getHeader("content-disposition")
                                  .split(";")) {
            if (content.trim()
                           .startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1)
                              .trim()
                              .replace("\"", "");
            }
        }

        return null;
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet that uploads files to a user-defined destination";
    }
}
