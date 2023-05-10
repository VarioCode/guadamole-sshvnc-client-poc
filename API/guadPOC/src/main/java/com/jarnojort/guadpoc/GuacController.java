package com.jarnojort.guadpoc;

import com.jarnojort.guadpoc.Guac;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/guac")
public class GuacController extends GuacamoleHTTPTunnelServlet {

    public GuacController() {
    }

    @RequestMapping(value= "/connect", method= RequestMethod.POST)
    @Override
    protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {
        GuacamoleConfiguration config = new GuacamoleConfiguration();

//        Guac guac = new Guac(,, Integer.parseInt(request.getHeader("port")), request.getHeader("username"), request.getHeader("password"));


        config.setProtocol("vnc");
        config.setParameter("hostname",  "localhost");
        config.setParameter("port", "5900");
//        config.setParameter("username", "booq");
        config.setParameter("password", "booq");

        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket("localhost", 4822), // omzetten naar env var
                config
        );
        return new SimpleGuacamoleTunnel(socket);
    }

}
