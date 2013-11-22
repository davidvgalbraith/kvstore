package edu.berkeley.cs162;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class SocketServerTest {

	@Test
	public void test() {
		
		SocketServer x = new SocketServer("localhost", 1234);
		try {
			x.connect();
		} catch (IOException e1) {
			//fail
			assertTrue(false);
		}
		try {
			x.run();
		} catch (IOException e) {
			//succeed (time out)
		}
		x.closeSocket();
	}	
	
	@Test
	public void test2() {
		
		SocketServer x = new SocketServer("localhost", 1234);
		try {
			x.connect();
		} catch (IOException e1) {
			//fail
			assertTrue(false);
		}
		try {
			x.run();
		} catch (IOException e) {
			//succeed (time out)
		}
		x.stop();
	}

}
