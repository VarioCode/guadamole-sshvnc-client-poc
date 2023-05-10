package com.jarnojort.guadpoc;

import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GuacamoleTunnel> getGuac(HttpServletRequest request, @RequestBody GuacModel items) {
        ResponseEntity<GuacamoleTunnel> guacReturn = null;
        Guac guac = new Guac(items.getProtocol(), items.getHostname(), items.getPort(), items.getUsername(), items.getPassword());
        try {
            guacReturn = guac.doConnect(request);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>();
    }
}
