package utils;

import org.apache.log4j.Logger;


public class CustomLogger {

    static final Logger logger = Logger.getLogger(CustomLogger.class);

    public static void info(String msg){
        logger.info(msg);
    }

    public static void error(String msg){
        logger.error(msg);
    }

    public static void debug(String msg){
        logger.debug(msg);
    }
}
