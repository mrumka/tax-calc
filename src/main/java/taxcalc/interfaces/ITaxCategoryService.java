package taxcalc.interfaces;

import taxcalc.models.*;

public interface ITaxCategoryService {
    TaxCategory[] getCategories();
    TaxCategory getCategory(String name);
}
