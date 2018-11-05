package taxcalc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import taxcalc.interfaces.*;

public class LoggingService implements ILoggingService {

    private final Logger logger;

    public LoggingService()
    {
        this.logger = LoggerFactory.getLogger(LoggingService.class);
    }

    public void error(String correlationId, String message){
        logger.error(String.format("%s: %s", correlationId, message));
    }

    public void info(String correlationId, String message){
        logger.info(String.format("%s: %s", correlationId, message));
    }

    public void debug(String correlationId, String message){
        logger.debug(String.format("%s: %s", correlationId, message));
    }

    public void warning(String correlationId, String message){
        logger.warn(String.format("%s: %s", correlationId, message));
    }

    public void trace(String correlationId, String message){
        logger.trace(String.format("%s: %s", correlationId, message));
    }
}
