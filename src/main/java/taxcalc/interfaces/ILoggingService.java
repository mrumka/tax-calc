package taxcalc.interfaces;

public interface ILoggingService {
    void error(String correlationId, String message);
    void info(String correlationId, String message);
    void debug(String correlationId, String message);
    void warning(String correlationId, String message);
    void trace(String correlationId, String message);
}
