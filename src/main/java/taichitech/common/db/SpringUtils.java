package taichitech.common.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
    private static SpringUtils INSTANCE;
    private Logger logger = LoggerFactory.getLogger(SpringUtils.class);
    private ConfigurableApplicationContext context;

    private SpringUtils() {
        try {
            context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml",
                    "applicationContext-ap.xml" });

        } catch (Exception e) {
            logger.error("Init Spring fail~", e);
        }
    }

    public static void closeSpring() {
        INSTANCE.logger.debug("Run Finalize");
        INSTANCE.context.close();
    }

    public static <T> T getBean(Class<T> T) {
        init();
        return INSTANCE.context.getBean(T);
    }

    public static void init() {
        if (INSTANCE == null) {
            INSTANCE = new SpringUtils();
        }
    }
}
