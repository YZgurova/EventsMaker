package com.EventsMaker.v1.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class StripeCharge {

    public final int songId;
    private final long amount;
    private final String source;
    private final String currency;
    private final String songName;

    public StripeCharge(@JsonProperty("songId") int songId, @JsonProperty("amount") long amount, @JsonProperty("token") String token, @JsonProperty("songName") String songName) {
        this.songId=songId;
        this.amount = amount;
        this.source = token;
        this.currency = "usd";
        this.songName=songName;
    }

    public Map<String, Object> getCharge() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("amount", this.amount);
        params.put("currency", this.currency);
        params.put("source", this.source);
        params.put(
                "description",
                "Bought "+songName
        );
        return params;
    }
}
