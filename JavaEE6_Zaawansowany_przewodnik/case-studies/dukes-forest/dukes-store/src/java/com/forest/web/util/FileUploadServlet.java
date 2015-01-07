/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package com.forest.web.util;

import com.forest.ejb.ProductBean;
import com.forest.entity.Product;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet(urlPatterns = "/fileUpload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 535323841419135818L;
    private static final Logger logger = Logger.getLogger(
                FileUploadServlet.class.getCanonicalName());
    private static final List<String> EXTENSIONS_ALLOWED = new ArrayList<String>();

    static {
        // images only
        EXTENSIONS_ALLOWED.add(".jpg");
        EXTENSIONS_ALLOWED.add(".bmp");
        EXTENSIONS_ALLOWED.add(".png");
        EXTENSIONS_ALLOWED.add(".gif");
    }

    Product product;
    @EJB
    ProductBean productBean;

    public FileUploadServlet() {
        super();
    }

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        InputStream is = null;

        try {
            if ((request.getParameter("productID") != null)
                    && (request.getParts()
                                   .size() > 0)) {
                for (Part part : request.getParts()) {
                    logger.info(part.getName());

                    is = request.getPart(part.getName())
                                .getInputStream();

                    int i = is.available();
                    byte[] b = new byte[i];
                    is.read(b);

                    logger.log(Level.INFO, "Length : {0}", b.length);

                    String fileName = getFileName(part);
                    logger.log(Level.INFO, "File name : {0}", fileName);

                    Integer id = Integer.parseInt(
                                request.getParameter("productID"));

                    product = productBean.find(id);

                    // generate *unique* filename 
                    final String extension = fileName.substring(
                                fileName.length() - 4);

                    if (!EXTENSIONS_ALLOWED.contains(extension)) {
                        logger.severe(
                                "User tried to upload file that's not an image. Upload canceled.");
                        response.sendRedirect(
                                "admin/product/List.xhtml?errMsg=Error trying to upload file");

                        return;
                    }

                    //final String newFilename = MD5Util.generateMD5(fileName) + extension;    //id + extension;
                    product.setImgSrc(b);
                    product.setImg(fileName);
                    productBean.edit(product);

                    response.sendRedirect("admin/product/Confirm.xhtml");
                }
            } else {
                // no img case
                response.sendRedirect("admin/product/Confirm.xhtml");
            }

            // very generic error threatment - just sample
        } catch (NumberFormatException nfe) {
            logger.log(
                    Level.SEVERE,
                    "Error to process product information - ",
                    nfe.getMessage());
        } catch (IOException ioe) {
            logger.log(
                    Level.SEVERE,
                    "Error during file upload - ",
                    ioe.getMessage());
        } catch (StringIndexOutOfBoundsException soe) {
            logger.log(
                    Level.SEVERE,
                    "Proceeding without an image",
                    soe.getMessage());

            response.sendRedirect("admin/product/Confirm.xhtml");
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private String getFileName(Part part) {
        String partHeader = part.getHeader("content-disposition");
        logger.log(Level.INFO, "Part Header = {0}", partHeader);

        for (String cd : part.getHeader("content-disposition")
                             .split(";")) {
            if (cd.trim()
                      .startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1)
                         .trim()
                         .replace("\"", "");
            }
        }

        return null;
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
