package taxcalc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxcalc.models.*;
import taxcalc.interfaces.*;

import java.util.Arrays;
import java.util.Arrays.*;

public class TaxCategoryMemoryService implements ITaxCategoryService {

    private final ILoggingService loggingService;
    private TaxCategory[] categories;

    public TaxCategoryMemoryService(ILoggingService loggingService) {
        if (loggingService == null) throw new IllegalArgumentException("loggingService is null");

        this.loggingService = loggingService;
        this.categories = new TaxCategory[] {
                new TaxCategory("Category #1", 1.1),
                new TaxCategory("Category #2", 1.2),
                new TaxCategory("Category #3", 1.3)
        };
    }

    public TaxCategory[] getCategories() {
        return this.categories;
    }

    public TaxCategory getCategory(String name){
        for(int index = 0; index < this.categories.length; index++)
        {
            if (this.categories[index].getName().equals(name) == true) return this.categories[index];
        }
        return null;
    }
}
