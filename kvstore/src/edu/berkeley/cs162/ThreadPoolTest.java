package edu.berkeley.cs162;

import static org.junit.Assert.*;

import org.junit.Test;

public class ThreadPoolTest {
	 public class TestRunnable implements Runnable {
	        public TestRunnable(int num) {
	            this.num = num;
	        }
	        public void run() {
	        }
	        private int num;
	    }

	  @Test 
	    public void moreJobThanBuff() {
		  System.out.println("test1");
	        ThreadPool tp = new ThreadPool(1);
	        int temp = 0;
			 System.out.println("before add");

	        try {
	            for (int i = 1; i < 8; i++) 
	                tp.addToQueue(new TestRunnable(i));
	        } catch (InterruptedException e) {
	           temp = 1;
	        }
			System.out.println("after add"+temp);

	        assertEquals(temp,0);
	    }
	    
	    @Test
	    public void check_inside() {
			System.out.println("test1");
	        ThreadPool tp = new ThreadPool(4);
	        assertEquals("NEW", tp.threads[0].getState().toString());
	    }
	    
	   

}
