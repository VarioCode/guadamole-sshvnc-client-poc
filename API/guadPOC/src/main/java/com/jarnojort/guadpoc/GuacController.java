package com.jarnojort.guadpoc;

import org.apache.guacamole.net.GuacamoleTunnel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/guac")
public class GuacController {
    public GuacController() {
    }

    @GetMapping("/connect")
    public GuacamoleTunnel getGuac(@RequestBody HttpServletRequest request) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();        Guac guac = new Guac(protocol, hostname, port, username, password);
        return guac.doConnect(request)
    }
}
