package edu.berkeley.cs162;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KVServerTest {

	KVServer x;
	@Before
	public void setUp() throws Exception {
		x = new KVServer(1, 2);
		x.put("hello", "goodbye");
	}
	@Test
	public void test() throws Exception {

		assertEquals(x.get("hello"), "goodbye");
	}
	@Test
	public void delTest() throws Exception {
		x.del("hello");
		try  {
			String error = x.get("hello");
		}
		catch (KVException e) {
			assertTrue(true);
		}
	}

}
