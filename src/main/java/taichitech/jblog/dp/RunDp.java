package taichitech.jblog.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunDp {
	Logger logger = LoggerFactory.getLogger(getClass());
	ApplicationContext context;

	public RunDp() {
		try {
			context = new ClassPathXmlApplicationContext(new String[] {
					"applicationContext.xml", "applicationContext-ap.xml" });
		} catch (Exception e) {
			logger.error("Init Spring fail~", e);
		}
	}

	

	public static void main(String[] args) {
		RunDp rd = new RunDp();

	}

}
