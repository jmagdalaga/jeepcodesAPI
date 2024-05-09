package com.jeeproutes.jeepneyroute.model;

import java.io.Serializable;

public class JeepPlacesId implements Serializable {
    @SuppressWarnings("unused")
    private String jeepcode;
    @SuppressWarnings("unused")
    private String routename;
    public JeepPlacesId() {
    }
    public JeepPlacesId(String jeepcode, String routename) {
        this.jeepcode = jeepcode;
        this.routename = routename;
    }
}
