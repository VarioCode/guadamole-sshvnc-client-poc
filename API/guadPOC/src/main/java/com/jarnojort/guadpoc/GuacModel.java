package com.jarnojort.guadpoc;

public class GuacModel {
    private final String protocol;
    private final String hostname;
    private final int port;
    private final String username;
    private final String password;
    private final String guacdHost = "localhost"; // omzetten naar env var

    public GuacModel(String protocol, String hostname, int port, String username, String password) {
        this.protocol = protocol;
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getGuacdHost() {
        return guacdHost;
    }

    public String getHostname() {
        return hostname;
    }

    public String getPassword() {
        return password;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getUsername() {
        return username;
    }

    public int getPort() {
        return port;
    }
}
