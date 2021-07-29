package it.polito.det.springTemplate.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiController {

    @RequestMapping(value = "/api{path:[^\\.]*}/**")
    public String Api404(HttpServletRequest request)
    {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endpoint "+request.getRequestURI()+" not found");
    }
}
