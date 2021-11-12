package org.example.model;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sold",
        "string",
        "pending",
        "available"
})
@Generated("jsonschema2pojo")
@Data
public class Inventory {

    @JsonProperty("sold")
    public Integer sold;
    @JsonProperty("string")
    public Integer string;
    @JsonProperty("pending")
    public Integer pending;
    @JsonProperty("available")
    public Integer available;


}