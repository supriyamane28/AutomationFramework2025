package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    public static final Logger loggerobject= LogManager.getLogger();

    public static void info(String message)
    {
        loggerobject.info(message);
    }
    public static void warn(String message)
    {
        loggerobject.warn(message);
    }

    public static void error(String message)
    {
        loggerobject.error(message);
    }

    public static void debug(String message)
    {
        loggerobject.debug(message);
    }



}
