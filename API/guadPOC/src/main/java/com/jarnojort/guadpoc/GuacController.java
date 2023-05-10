package com.jarnojort.guadpoc;

import org.apache.guacamole.net.GuacamoleTunnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GuacamoleTunnel> getGuac(HttpServletRequest req, @RequestBody GuacModel items) {
        System.out.println(req.getRequestURI() + "hi im here");
        ResponseEntity<GuacamoleTunnel> guacReturn;
        Guac guac = new Guac(items.getProtocol(), items.getHostname(), items.getPort(), items.getUsername(), items.getPassword());
        try {
            guacReturn = new ResponseEntity<>(guac.doConnect(req), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            guacReturn = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return guacReturn;
    }
}
