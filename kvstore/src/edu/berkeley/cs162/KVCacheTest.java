package edu.berkeley.cs162;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KVCacheTest {
    public KVCache cache;
    
    @Before
    public void setup() {
        cache = new KVCache(1, 5);
    }
    
    /**
     * Simple test to check whether KV entries are stored, and a key replaces itself
     */
    @Test
    public void testPutGet() {
        cache.put("A", "first");
        cache.put("C", "second");
        cache.put("C", "third");
        
        assertEquals("first", cache.get("A"));
        assertEquals("third", cache.get("C"));
    }

    /**
     * Tests the second chance policy: recently accessed keys A and B should not be
     * replaced here / keys F and G should replace keys C and D
     */
    @Test
    public void testSecondChance() {
        cache.put("A", "first");
        cache.put("B", "second");
        cache.put("C", "third");
        cache.put("D", "fourth");
        cache.put("E", "fifth");
        cache.get("A");
        cache.get("B");
        cache.put("F", "sixth");
        cache.put("G", "seventh");
        
        assertEquals("first", cache.get("A"));
        assertEquals("second", cache.get("B"));
        assertNull(cache.get("C"));
    }
    /**
     * After filling up the set with keys A, B, C, D, and E, deleting A and B, and
     * putting in F and G, the keys that remain should be C, D, E, F, and G
     */
    @Test
    public void testPutDel() {
        cache.put("A", "first");
        cache.put("B", "second");
        cache.put("C", "third");
        cache.put("D", "fourth");
        cache.put("E", "fifth");
        cache.del("A");
        cache.del("B");
        cache.put("F", "sixth");
        cache.put("G", "seventh");

        assertEquals("third", cache.get("C"));
        assertEquals("fourth", cache.get("D"));
        assertEquals("fifth", cache.get("E"));
        assertEquals("sixth", cache.get("F"));
        assertEquals("seventh", cache.get("G"));
        assertNull(cache.get("A"));
    }
    
    @Test
    public void testToXML() {
		KVCache kvCache = new KVCache(5, 5);
		kvCache.put("k1", "v1");
		kvCache.put("k2", "v2");
		kvCache.put("k3", "v3");
		kvCache.put("k4", "v4");
		kvCache.put("k5", "v5");
		String str = kvCache.toXML();
		assertTrue(str.contains("k1") && str.contains("v1"));
		assertTrue(str.contains("k2") && str.contains("v2"));
		assertTrue(str.contains("k3") && str.contains("v3"));
		assertTrue(str.contains("k4") && str.contains("v4"));
		assertTrue(str.contains("k5") && str.contains("v5"));
	}
}        

