package taxcalc.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taxcalc.interfaces.ILoggingService;
import taxcalc.interfaces.ITaxCalculatorService;
import taxcalc.models.*;

@RestController
@RequestMapping("/api/v1")
public class TaxCalcController {

    private final ITaxCalculatorService taxCalculator;
    private final ILoggingService loggingService;

    @Autowired
    public TaxCalcController(ITaxCalculatorService taxCalculator, ILoggingService loggingService)
    {
        if (taxCalculator == null) throw new IllegalArgumentException("taxCalculator is null");
        if (loggingService == null) throw new IllegalArgumentException("loggingService is null");

        this.loggingService = loggingService;
        this.taxCalculator = taxCalculator;
    }

    @PostMapping("/calc")
    public Invoice calc(@RequestParam(value="correlationId")  String correlationId,
                        @RequestBody Invoice invoice, HttpServletRequest request, HttpServletResponse response)
    {
        if (correlationId == null || correlationId.isEmpty())
        {
            correlationId = UUID.randomUUID().toString();
        }

        try {
            //loggingService.trace(correlationId, String.format("Invoice '%s' payload received", getRequestPayload(request).toString()));
            this.taxCalculator.calc(correlationId, invoice);
        }
        catch (Exception ex) {
            loggingService.error(correlationId, ex.getMessage());
            throw ex;
        }
        return invoice;
    }
}
