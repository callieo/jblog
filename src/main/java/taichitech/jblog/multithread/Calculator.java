package taichitech.jblog.multithread;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator implements Runnable {
	Logger logger = LoggerFactory.getLogger(getClass());
	private static final int SCALE = 1000;
	private static final int BASE = 1;
	private static final int RANGE = 15;
	private int result;
	private String name;
	public static final long LIMIT = 100000l * 1000000l;

	@Override
	public void run() {
		try {
			// 需要很長的時間做的事
			for (long i = 0; i < LIMIT; i++) {
			}
			logger.debug("{} 執行完畢", this.name);
			controler.setResult(this, result);
			controler.doReturn();

		} catch (Exception e) {
			logger.error("失敗", e);
		}
	}

	public String getName() {
		return name;
	}

	public Calculator(Summer controler, String calculatorName) {
		this.controler = controler;
		this.name = calculatorName;
		this.result = RandomUtils.nextInt(BASE * SCALE, (BASE + RANGE) * SCALE);
		logger.debug("預計需要花費{}", result);
	}

	private Summer controler;

}
