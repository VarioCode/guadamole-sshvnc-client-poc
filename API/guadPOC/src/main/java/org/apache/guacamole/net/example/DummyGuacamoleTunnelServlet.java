/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.guacamole.net.example;

import javax.servlet.http.HttpServletRequest;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;

/**
 * Simple tunnel example with hard-coded configuration parameters.
 */
public class DummyGuacamoleTunnelServlet extends GuacamoleHTTPTunnelServlet {

    @Override
    protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {

        //get the parameters from the request
        String configHost = request.getHeader("hostname");
        String configProtocol = request.getHeader("protocol");
        int configPort = 0;

        // guacd connection information
        String hostname = "localhost";
        int port = 4822;

        GuacamoleConfiguration config = new GuacamoleConfiguration();


        // checks the protocol and sets the port accordingly
        if (configProtocol.equals("vnc")) {
            configPort = 5900;
            config.setParameter("password", "");
        } else if (configProtocol.equals("ssh")) {
            configPort = 22;
            config.setParameter("username", "");
            config.setParameter("password", "");
        }

        // VNC connection information
        config.setProtocol(configProtocol);
        config.setParameter("hostname", configHost);
        config.setParameter("port", String.valueOf(configPort));

        // Connect to guacd, proxying a connection to the VNC server above
        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(hostname, port),
                config
        );

        // Create tunnel from now-configured socket
        GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);
        return tunnel;

    }

}
