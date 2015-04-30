package taichitech.jblog.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTester {
	static Logger LOG = LoggerFactory.getLogger(ThreadTester.class);
	private static long START = System.currentTimeMillis();

	static void showTime() {
		LOG.debug("花費 {}", (System.currentTimeMillis() - START));
		START = System.currentTimeMillis();
	}

	public static void main(String[] args) {
		ThreadTester tt = new ThreadTester();
		tt.test1();

	}

	Logger logger = LoggerFactory.getLogger(getClass());

	private void test1() {
		int threadNum = 5;
		Summer s = new Summer(threadNum);
		s.startCalculate();
//		for (long i = 0; i < Calculator.LIMIT * threadNum; i++) {
//
//		}
//		showTime();
	}

}
