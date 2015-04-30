package taichitech.jblog.jse;

import org.apache.commons.lang3.time.StopWatch;


public class StopWatchTest {
	public static void main(String[] args) {
		try {
			apachStopWatchTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void apachStopWatchTest() throws Exception {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		queryData();
		System.out.println("queryData:" + stopWatch.toString());
		buildUI();
		System.out.println("buildUI:" + stopWatch.toString());
		stopWatch.stop();
	}

	public static void traditionalStopWatchTest() throws Exception {
		long start = System.currentTimeMillis();
		queryData();
		System.out.println("queryData:" + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		buildUI();
		System.out.println("buildUI:" + (System.currentTimeMillis() - start));
	}

	// private static void springStopWatchTest() throws Exception {
	// StopWatch sw = new StopWatch("Spring Stop Watch Demostraction");
	// sw.start("queryData");
	// queryData();
	// sw.stop();
	// sw.start("buildUI");
	// buildUI();
	// sw.stop();
	// System.out.println(sw.prettyPrint());
	// }

	private static void buildUI() throws InterruptedException {
		Thread.sleep(3000);

	}

	private static void queryData() throws InterruptedException {
		Thread.sleep(4000);

	}

}
