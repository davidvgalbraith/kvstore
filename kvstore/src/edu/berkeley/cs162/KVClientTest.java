package edu.berkeley.cs162;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KVClientTest {
	Socket clientSock;
	ServerSocket mockServSock;
	@Before
	public void setUp() throws Exception {
		Thread runServer = new Thread () {
			public void run() {
				try {
					final ServerSocket mockServSock = new ServerSocket();
					mockServSock.setReuseAddress(true);
					mockServSock.bind(new InetSocketAddress("localhost", 8080));
					clientSock = mockServSock.accept();
					
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	    runServer.start();
	}

	@After
	public void tearDown() throws Exception {
		if (mockServSock != null) {
	        mockServSock.close();
	        mockServSock = null;
	            try {
	                Thread.sleep(5000);
	            } 
	            catch (InterruptedException e) {
	                // do nothing.
	            }
	    }
	}

	@Test
	public void putTest() {
		//checks that it creates a putMessage with the key value pair 
		KVClient kvc = new KVClient("localhost", 8080); 
		KVMessage putMessage = null;
		try { 
			//System.out.println("Putting key = 5, value = 10");
			kvc.put("5", "10");
			putMessage = new KVMessage (clientSock);
			assertEquals("Testing put's key", "5", putMessage.getKey()); 
			assertEquals("Testing put's value", "10", putMessage.getValue()); 
			assertEquals("Testing put's type", "putreq", putMessage.getMsgType()); 
		} catch (KVException e) { 
			e.printStackTrace();
		} catch (Exception e) { 
			e.printStackTrace();
		} 
	}
	
	@Test
	public void getTest() {
		//checks that it creates a putMessage with the key value pair 
		KVClient kvc = new KVClient("localhost", 8080); 
		KVMessage getMessage = null;
		try { 
			//System.out.println("Getting key = 5");
			kvc.get("5");
			getMessage = new KVMessage (clientSock);
			assertEquals("Testing get's key", "5", getMessage.getKey()); 
			assertEquals("Testing get's type", "getreq", getMessage.getMsgType()); 
		} catch (KVException e) { 
			e.printStackTrace();
		} catch (Exception e) { 
			e.printStackTrace();
		} 
	}
	
	@Test
	public void delTest() {
		//checks that it creates a putMessage with the key value pair 
		KVClient kvc = new KVClient("localhost", 8080); 
		KVMessage delMessage = null;
		try { 
			//System.out.println("Deleting key = 5");
			kvc.del("5");
			delMessage = new KVMessage (clientSock);
			assertEquals("Testing del's key", "5", delMessage.getKey()); 
			assertEquals("Testing del's type", "getreq", delMessage.getMsgType()); 
		} catch (KVException e) { 
			e.printStackTrace();
		} catch (Exception e) { 
			e.printStackTrace();
		} 
	}

}
