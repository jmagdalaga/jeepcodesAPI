package com.jeeproutes.jeepneyroute.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeproutes.jeepneyroute.model.BackupCodesRoute;
import com.jeeproutes.jeepneyroute.model.Jeep;
import com.jeeproutes.jeepneyroute.model.JeepPlace;
import com.jeeproutes.jeepneyroute.model.Places;
import com.jeeproutes.jeepneyroute.repository.JeepPlaceRepo;
import com.jeeproutes.jeepneyroute.repository.JeepRepo;
import com.jeeproutes.jeepneyroute.repository.PlacesRepo;

@Service
public class JeepRouteService {
    @Autowired
    private JeepPlaceRepo jeepRouteRepo;
    @Autowired
    private JeepRepo jeepRepo;
    @Autowired
    private PlacesRepo routeRepo;

    public Map<String,List<String>> getAllJeepRoutes() {
        List<JeepPlace> allRoutes = jeepRouteRepo.findAll();

        Map<String, List<String>> groupedByJeepCode = 
            allRoutes.stream().collect(Collectors.groupingBy(JeepPlace::getJeepcode,
                Collectors.mapping(JeepPlace::getRoutename, Collectors.toList())));
        return groupedByJeepCode;
    }

    public List<String> getRouteByJeepCode(String jeepcode) {
        List<JeepPlace> routes = jeepRouteRepo.findByJeepcode(jeepcode);

        List<String> routeByJeepcode =
            routes.stream().collect(Collectors.mapping(JeepPlace::getRoutename, Collectors.toList()));
        
        return routeByJeepcode;
    }

    public List<BackupCodesRoute> getMultipleJeepRoutes(String jeepcodes) {
        List<BackupCodesRoute> helperRoutes = new ArrayList<>();
        List<String> jeepList = Arrays.asList(jeepcodes.split(","));
        
        for (String jeep : jeepList) {
            if (addJeepCode(jeep) == 0) {
                List<String> routes = getRouteByJeepCode(jeep);
                BackupCodesRoute helperRoute = new BackupCodesRoute(jeep, routes);
                helperRoutes.add(helperRoute);
            }
        }
        
        return helperRoutes;
    }
    
    

    public int addJeepCode(String jeepcode) {
        try {
            jeepRepo.findById(jeepcode).get();
        } catch (Exception e) {
            jeepRepo.save(new Jeep(jeepcode));
            return 1;
        }
        return 0;
    }

    public int addJeepRoute(String jeepcode, String route) {
        addJeepCode(jeepcode);
        List<String> routeList = Arrays.asList(route.split(","));
        if(!getRouteByJeepCode(jeepcode).containsAll(routeList)) {
            for (String r : routeList) {
                if(jeepRouteRepo.findByJeepcodeAndRoutename(jeepcode, r).isEmpty()) {
                    addRoute(r);
                    jeepRouteRepo.save(new JeepPlace(jeepcode, r));
                }
            }    
            return 1;
        }
        return 0;
    }

    public int addRoute(String route) {
        try {
            routeRepo.findById(route).get();
        } catch (Exception e) {
            routeRepo.save(new Places(route));
            return 1;
        }
        return 0;
    }

    public int changeRoute(String jeepcode, String route) {
        if(addJeepCode(jeepcode)==0) {
            List<JeepPlace> existingRoute = jeepRouteRepo.findByJeepcode(jeepcode);
            jeepRouteRepo.deleteAllInBatch(existingRoute);
            addJeepRoute(jeepcode, route);
            return 1;
        }
        return 0;
    }

    public int deleteJeepRoute(String jeepcode, String route) {
        if(addJeepCode(jeepcode)==0) {
            List<String> routeList = Arrays.asList(route.split(","));
            for (String r : routeList) {
                JeepPlace jroute = (jeepRouteRepo.findByJeepcodeAndRoutename(jeepcode, r)==null) ? null 
                    : jeepRouteRepo.findByJeepcodeAndRoutename(jeepcode, r).get(0);
                jeepRouteRepo.delete(jroute);
            }
            return 1;
        }
        return 0;
    }
}
