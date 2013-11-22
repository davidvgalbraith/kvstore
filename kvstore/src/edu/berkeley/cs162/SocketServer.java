/**
 * Socket Server manages network connections
 *
 * @author Mosharaf Chowdhury (http://www.mosharaf.com)
 * @author Prashanth Mohan (http://www.cs.berkeley.edu/~prmohan)
 *
 * Copyright (c) 2012, University of California at Berkeley
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  * Neither the name of University of California, Berkeley nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package edu.berkeley.cs162;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is an generic class that should handle all TCP network connections
 * arriving on a given unique (host, port) tuple. Ensure that this class
 * remains generic by providing the connection handling logic in a NetworkHandler
 */
public class SocketServer {
    String hostname;
    int port;
    NetworkHandler handler;
    ServerSocket server;
    boolean client_request = true;
    private final int SO_TIMEOUT = 1000;
    
    public SocketServer(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Creates a new ServerSocket and binds it to an endpoint.
     */
    public void connect() throws IOException {
        server = new ServerSocket();
        server.bind(new InetSocketAddress(hostname, port));
    }

    /**
     * Accept requests and service them asynchronously.
     * @throws IOException if there is a network error (for instance if the
    *          socket is inadvertently closed)
     */
    public void run() throws IOException {
    	while (client_request) {
    		server.setSoTimeout(SO_TIMEOUT);
    		Socket client = server.accept();
    		handler.handle(client);
    	}
    	finalize();
    }

    /**
     * Add the network handler for the current socket server
     * @param handler is logic for servicing a network connection
     */
    public void addHandler(NetworkHandler handler) {
        this.handler = handler;
    }

    /**
     * Close the ServerSocket immediately.
     */
    public void closeSocket() {
    	try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}

    /**
     * Stop the ServerSocket cleanly (do not force an exception to be thrown).
     * A call to stop() may return before the ServerSocket is closed.
     */
    public void stop() {
        client_request = false;
    }

    protected void finalize(){
        closeSocket();
    }
}
