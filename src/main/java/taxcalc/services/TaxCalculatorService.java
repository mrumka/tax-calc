package taxcalc.services;

import taxcalc.controllers.TaxCalcController;
import taxcalc.models.*;
import taxcalc.interfaces.*;

//@Service
public class TaxCalculatorService implements ITaxCalculatorService {

    private final ILoggingService loggingService;
    private final ITaxCategoryService categoryService;

    //@Autowired
    public TaxCalculatorService(ITaxCategoryService categoryService, ILoggingService loggingService)
    {
        if (categoryService == null) throw new IllegalArgumentException("categoryService is null");
        if (loggingService == null) throw new IllegalArgumentException("loggingService is null");

        this.loggingService = loggingService;
        this.categoryService = categoryService;
    }

    public void calc(String correlationId, Invoice invoice){
        if (invoice == null)
        {
            throw new IllegalArgumentException("Invoice is empty");
        }

        loggingService.debug(correlationId, String.format("Invoice '%o' for date '%s' begin processing", invoice.getId(), invoice.getTimestamp()));

        for (InvoiceItem item : invoice.getItems()) {
            String taxCategoryName = item.getTaxCategory();
            TaxCategory taxCategory = this.categoryService.getCategory(taxCategoryName);

            if (taxCategory == null) {
                throw new IllegalArgumentException(String.format("Tax category \"%s\" cannot be found", taxCategoryName));
            }
            else {
                loggingService.debug(correlationId, String.format("Invoice item '%o' tax category '%s' processing for amount %f",
                        item.getId(), taxCategoryName, item.getPretaxAmount()));

                double totalAmount = item.getPretaxAmount() * taxCategory.getTaxRate();
                item.setTotalAmount(totalAmount);

                loggingService.debug(correlationId, String.format("Invoice item '%o' total amount %f calculated",
                        item.getId(), totalAmount));
            }
        }

        loggingService.debug(correlationId, String.format("Invoice '%o' for date '%s' end processing", invoice.getId(), invoice.getTimestamp()));
    }
}
