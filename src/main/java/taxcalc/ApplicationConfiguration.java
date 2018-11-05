package taxcalc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import taxcalc.interfaces.*;
import taxcalc.services.*;
import taxcalc.repositories.*;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private TaxCategoryRepository repository;

    @Bean
    public ILoggingService getLoggingService() {
        return new LoggingService();
    }

    @Bean
    //@Profile("dev")
    public ITaxCategoryService getTaxCategoryServiceDev() {
        return new TaxCategoryMongoService(this.repository, this.getLoggingService());
        //return new TaxCategoryMemoryService(this.getLoggingService());
    }

    @Bean
    //@Profile("dev")
    public ITaxCalculatorService getTaxCalculatorServiceDev() {
        return new TaxCalculatorService(this.getTaxCategoryServiceDev(), this.getLoggingService());
    }
}
