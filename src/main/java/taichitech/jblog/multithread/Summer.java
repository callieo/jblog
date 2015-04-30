package taichitech.jblog.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Summer {
	public Summer(int scope) {
		this.scope = scope;
		this.remain = this.scope;
	}

	private final int scope;

	public void startCalculate() {
		for (int i = 0; i < scope; i++) {
			String calculatorName = "mytherad" + i;
			Calculator c = new Calculator(this, calculatorName);
			Thread t = new Thread(c);
			t.setName(calculatorName);
			logger.debug("Thread {} start", t.getName());
			t.start();
		}
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
	private int total;
	private int remain;

	public boolean isLast() {
		return remain == 0;
	}

	public int setResult(Calculator c, int result) {
		this.total = this.total + result;
		this.remain--;
		return total;
	}

	public void doReturn() {
		if (isLast()) {
			logger.debug("計算結果 {}", this.total);
			ThreadTester.showTime();
		}
	}
}
