package taxcalc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class TaxCategory {

    @Id
    public String id;
    public String name;
    public double taxRate;

    public TaxCategory()
    {
    }

    public TaxCategory(@JsonProperty("name")String name, @JsonProperty("taxRate")double taxRate)
    {
        this.name = name;
        this.taxRate = taxRate;
    }

    @JsonProperty("id")
    public String getId()
    {
        return this.id;
    }

    @JsonProperty("id")
    public void setId(String value)
    {
        this.id = value;
    }

    @JsonProperty("name")
    public String getName()
    {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String value)
    {
        this.name = value;
    }

    @JsonProperty("taxRate")
    public double getTaxRate()
    {
        return this.taxRate;
    }

    @JsonProperty("taxRate")
    public void setTaxRate(double value)
    {
        this.taxRate = value;
    }
}
