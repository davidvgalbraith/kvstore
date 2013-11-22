package edu.berkeley.cs162;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class KVStoreTest {
	private KVStore kvMap;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		kvMap = new KVStore();
	}

	@After
	public void tearDown() throws Exception {
		kvMap = null;
	}
	
	@Test
	public void storeTest() throws Exception{
		kvMap.put("k1", "v1");
		kvMap.put("k2", "v2");
		kvMap.put("k3", "v3");
		kvMap.put("k4", "v4");
		kvMap.put("k5", "v5");
		String v1 = kvMap.get("k1");
		String v2 = kvMap.get("k2");
		String v3 = kvMap.get("k3");
		String v4 = kvMap.get("k4");
		String v5 = kvMap.get("k5");
		assertEquals(v1, "v1");
		assertEquals(v2, "v2");
		assertEquals(v3, "v3");
		assertEquals(v4, "v4");
		assertEquals(v5, "v5");
	}
	
	@Test
	public void testToXML() throws Exception{
		kvMap.put("k1", "v1");
		kvMap.put("k2", "v2");
		kvMap.put("k3", "v3");
		kvMap.put("k4", "v4");
		kvMap.put("k5", "v5");
		
		String str = kvMap.toXML();
		assertNotNull(str);
		assertTrue(str.contains("v1"));
		assertTrue(str.contains("v2"));
		assertTrue(str.contains("v3"));
		assertTrue(str.contains("v4"));
		assertTrue(str.contains("v5"));
	}
	
	@Test
	public void testDumpToFileandRestoreFromFile() throws Exception{
		KVStore tmp = new KVStore();
		kvMap.put("k1", "v1");
		kvMap.put("k2", "v2");
		kvMap.put("k3", "v3");
		kvMap.put("k4", "v4");
		kvMap.put("k5", "v5");
		kvMap.dumpToFile("data");
		tmp.restoreFromFile("data");
		assertEquals("v1", tmp.get("k1"));
		assertEquals("v2", tmp.get("k2"));
		assertEquals("v3", tmp.get("k3"));
		assertEquals("v4", tmp.get("k4"));
		assertEquals("v5", tmp.get("k5"));
	}
}
