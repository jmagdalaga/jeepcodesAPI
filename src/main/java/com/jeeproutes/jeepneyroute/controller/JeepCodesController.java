package com.jeeproutes.jeepneyroute.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jeeproutes.jeepneyroute.service.JeepRouteService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
public class JeepCodesController {
    
    @Autowired
    private final JeepRouteService jeepRouteService;

    public JeepCodesController(JeepRouteService jeepRouteService) {
        this.jeepRouteService = jeepRouteService;
    }

    @PostMapping("/add-jeep-route")
    public int addJeepRoute(String jeepcode, String places) {
        return jeepRouteService.addJeepRoute(jeepcode, places);
    }

    @GetMapping("/all-routes")
    public Map<String, List<String>> getRoutesByJeepCode() {
        return jeepRouteService.getAllJeepRoutes();
    }

    @GetMapping("/{jeepcode}")
    public List<String> getJeepRoute(@PathVariable String jeepcode) {
        return jeepRouteService.getRouteByJeepCode(jeepcode);
    }

    @PutMapping("/update-route/{jeepcode}")
    public int changeJeepRoute(@PathVariable String jeepcode, String places) {
        return jeepRouteService.changeRoute(jeepcode, places);
    }

    @DeleteMapping("/delete-route/{jeepcode}")
    public int deleteJeepRoute(@PathVariable String jeepcode, String places) {
        return jeepRouteService.deleteJeepRoute(jeepcode, places);
    }
}
