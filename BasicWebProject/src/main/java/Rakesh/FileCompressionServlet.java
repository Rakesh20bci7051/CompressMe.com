package Rakesh;
/*
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileCompressionServlet")
@MultipartConfig
public class FileCompressionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Part filePart = request.getPart("fileToCompress");
            String fileName = getSubmittedFileName(filePart);

            if (fileName != null && fileName.toLowerCase().endsWith(".txt")) {
                String compressedFileName = fileName.substring(0, fileName.length() - 4) + ".zip";
                response.setContentType("application/zip");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + compressedFileName + "\"");

                try (InputStream fileInputStream = filePart.getInputStream();
                     ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
                    ZipEntry zipEntry = new ZipEntry(fileName);
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        zipOutputStream.write(buffer, 0, bytesRead);
                    }

                    zipOutputStream.closeEntry();
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please upload a .txt file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}*/
//package Rakesh;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileCompressionServlet")
@MultipartConfig
public class FileCompressionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Part filePart = request.getPart("fileToCompress");
            String fileName = getSubmittedFileName(filePart);

            if (fileName != null) {
                String fileExtension = getFileExtension(fileName);
                if (fileExtension != null) {
                    String compressedFileName = fileName.substring(0, fileName.length() - fileExtension.length()) + ".zip";
                    response.setContentType("application/zip");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + compressedFileName + "\"");

                    try (InputStream fileInputStream = filePart.getInputStream();
                         ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
                        ZipEntry zipEntry = new ZipEntry(fileName);
                        zipOutputStream.putNextEntry(zipEntry);

                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                            zipOutputStream.write(buffer, 0, bytesRead);
                        }

                        zipOutputStream.closeEntry();
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported file type. Please upload a .txt, .pdf, or .doc file.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please upload a file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex >= 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1).toLowerCase();
        }
        return null;
    }
}

