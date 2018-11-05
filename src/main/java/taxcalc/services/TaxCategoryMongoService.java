package taxcalc.services;

import taxcalc.interfaces.*;
import taxcalc.models.*;
import taxcalc.repositories.*;


import java.util.HashMap;
import java.util.List;

public class TaxCategoryMongoService implements ITaxCategoryService {

    private final TaxCategoryRepository repository;
    private final ILoggingService loggingService;
    private final HashMap<String, TaxCategory> categories;

    public TaxCategoryMongoService(TaxCategoryRepository repository, ILoggingService loggingService) {
        if (repository == null) throw new IllegalArgumentException("repository is null");
        if (loggingService == null) throw new IllegalArgumentException("loggingService is null");

        this.repository = repository;
        this.loggingService = loggingService;
        this.categories = new HashMap<String, TaxCategory>();
        this.initialize();
    }

    private void initialize()
    {
        List<TaxCategory> items = this.repository.findAll();
        for (TaxCategory item : items) {
            this.categories.put(item.getName().toLowerCase(), item);
        }
    }

    public TaxCategory[] getCategories() {

        return (TaxCategory[])this.categories.values().toArray();
    }

    public TaxCategory getCategory(String name){
        if(name == null || name.isEmpty()) {
            return null;
        }
        name = name.toLowerCase();

        return this.categories.get(name);
    }
}
