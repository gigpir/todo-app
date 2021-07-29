package it.polito.det.springTemplate.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class RouteController {
    @Autowired
    ServletContext context;

    @RequestMapping(value = {"", "/"})
    public String homePage() {
        return "index.html";
    }

    @RequestMapping(value = "/{path:[^\\.]*}/**")
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String staticPath = "static";
            String filename = staticPath +request.getRequestURI();
            ClassPathResource resource = new ClassPathResource(filename);
            if (resource.exists()) {
                InputStream in = resource.getInputStream();
                MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap(); // For this is necessary to be present the resources/META-INF/mime.types file
                String mimeType = fileTypeMap.getContentType(resource.getFilename());
                response.setContentType(mimeType);
                IOUtils.copy(in, response.getOutputStream());
            }
            else {
                RequestDispatcher dispatcher = context
                        .getRequestDispatcher("/");
                dispatcher.forward(request, response);
            }
        }
        catch (IOException e) {
            RequestDispatcher dispatcher = context
                    .getRequestDispatcher("/");
            dispatcher.forward(request, response);
        }
    }
}