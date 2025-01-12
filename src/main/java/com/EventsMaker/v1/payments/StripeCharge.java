package com.EventsMaker.v1.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class StripeCharge {

    public final int eventId;
    private final long amount;
    private final String source;
    private final String currency;
    private final String eventTitle;

    public StripeCharge(@JsonProperty("eventId") int eventId, @JsonProperty("amount") long amount, @JsonProperty("token") String token, @JsonProperty("eventTitle") String eventTitle) {
        this.eventId=eventId;
        this.amount = amount;
        this.source = token;
        this.currency = "usd";
        this.eventTitle=eventTitle;
    }

    public Map<String, Object> getCharge() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("amount", this.amount*100);
        params.put("currency", this.currency);
        params.put("source", this.source);
        params.put(
                "description",
                "Bought "+ eventTitle
        );
        return params;
    }
}
