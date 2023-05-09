package com.jarnojort.guadpoc;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;

import javax.servlet.http.HttpServletRequest;

public class Guad extends GuacamoleHTTPTunnelServlet {
    private final String protocol;
    private final String hostname;
    private final int port;
    private final String username;
    private final String password;
    private final String guacdHost = "guacd"; // omzetten naar env var

    public Guad(String protocol, String hostname, int port, String username, String password) {
        super();
        this.protocol = protocol;
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
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
}