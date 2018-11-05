package taxcalc.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import taxcalc.interfaces.*;
import taxcalc.models.*;

public class TaxCalculatorServiceTest {

    @Mock
    private ILoggingService loggingService;

    @Mock
    private ITaxCategoryService taxCategoryService;

    @Before
    public void setuUp()
    {
        MockitoAnnotations.initMocks(this);

        Mockito.when(taxCategoryService.getCategory("Category #1")).
                thenReturn(new TaxCategory("Category #1", 1.1));
    }

    @Test
    public void calTest() throws Exception {
        InvoiceItem[] items = new InvoiceItem[] {
                new InvoiceItem(11, "none", 10, "Category #1" )
        };

        Invoice invoice = new Invoice(1, "2017-11-3 10:00 AM",  items);

        TaxCalculatorService service = new TaxCalculatorService(taxCategoryService, loggingService);

        service.calc("QWERTY", invoice);

        double aaa = invoice.getItems()[0].getTotalAmount();
        Assert.assertEquals((double)11.0, invoice.getItems()[0].getTotalAmount(),0.0);

    }
}
