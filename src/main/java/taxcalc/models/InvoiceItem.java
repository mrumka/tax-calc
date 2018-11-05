package taxcalc.models;

import com.fasterxml.jackson.annotation.*;

public class InvoiceItem {
    private final int id;
    private final String description;
    private final String taxCategory;
    private final double pretaxAmount;
    private double totalAmount;

    @JsonCreator
    public InvoiceItem(@JsonProperty("ID")int id,
                       @JsonProperty("description")String description,
                       @JsonProperty("pretaxAmount")double pretaxAmount,
                       @JsonProperty("taxCategory")String taxCategory)
    {
        this.id = id;
        this.description = description;
        this.pretaxAmount = pretaxAmount;
        this.taxCategory = taxCategory;
        this.totalAmount = pretaxAmount;
    }

    @JsonProperty("ID")
    public int getId() {
        return this.id;
    }

    @JsonProperty("description")
    public String getDescription() {
        return this.description;
    }

    @JsonProperty("taxCategory")
    public String getTaxCategory() {
        return this.taxCategory;
    }

    @JsonProperty("pretaxAmount")
    public double getPretaxAmount() {
        return this.pretaxAmount;
    }

    @JsonProperty("totalAmount")
    public double getTotalAmount() {
        return this.totalAmount;
    }

    @JsonProperty("totalAmount")
    public void setTotalAmount(double value) {
        this.totalAmount = value;
    }
}
