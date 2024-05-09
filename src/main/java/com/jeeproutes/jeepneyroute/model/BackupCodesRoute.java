package com.jeeproutes.jeepneyroute.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class BackupCodesRoute {
    @JsonProperty("jeepcode")
    private String jeepCode;
    
    @JsonProperty("route")
    private List<String> routes;

    public BackupCodesRoute(String jeepCode, List<String> routes) {
        this.jeepCode = jeepCode;
        this.routes = routes;
    }

    @Override
    public String toString() {
        ObjectWriter mapper = (new ObjectMapper()).writerWithDefaultPrettyPrinter();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Transaction failed";
        }
    }
}
