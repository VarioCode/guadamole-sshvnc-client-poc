package com.jarnojort.guadpoc;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Guac extends GuacamoleHTTPTunnelServlet {
    private final String protocol;
    private final String hostname;
    private final int port;
    private final String username;
    private final String password;
    private final String guacdHost = "localhost"; // omzetten naar env var

    public Guac(String protocol, String hostname, int port, String username, String password) {
        super();
        this.protocol = protocol;
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    protected GuacamoleTunnel doConnect2() throws GuacamoleException {

        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol(protocol);
        config.setParameter("hostname", hostname);
        config.setParameter("port", String.valueOf(port));
        config.setParameter("username", username);
        config.setParameter("password", password);

        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(guacdHost, 4822), // omzetten naar env var
                config
        );
        return new SimpleGuacamoleTunnel(socket);
    }


    @Override
    protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {

        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol(protocol);
        config.setParameter("hostname", hostname);
        config.setParameter("port", String.valueOf(port));
        config.setParameter("username", username);
        config.setParameter("password", password);

        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(guacdHost, 4822), // omzetten naar env var
                config
        );
        return new SimpleGuacamoleTunnel(socket);
    }

    @Override
    protected void handleTunnelRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        super.handleTunnelRequest(request, response);
    }
}
