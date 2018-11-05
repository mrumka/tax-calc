package taxcalc.interfaces;

import taxcalc.models.*;

public interface ITaxCalculatorService {

    void calc(String correlationId, Invoice incoice);

}
