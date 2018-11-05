package taxcalc.models;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

public class Invoice {
    private final int id;
    private final String timestamp;
    private final InvoiceItem[] items;

    @JsonCreator
    public Invoice(@JsonProperty("ID")int id,
                   @JsonProperty("timestamp") String timestamp,
                   @JsonProperty("items") InvoiceItem[] items)
    {
        this.id = id;
        this.timestamp = timestamp;
        this.items = items;
    }

    @JsonProperty("ID")
    public int getId()
    {
        return this.id;
    }

    @JsonProperty("timestamp")
    public String getTimestamp()
    {
        return this.timestamp;
    }

    @JsonProperty("items")
    public InvoiceItem[] getItems()
    {
        return this.items;
    }
}
