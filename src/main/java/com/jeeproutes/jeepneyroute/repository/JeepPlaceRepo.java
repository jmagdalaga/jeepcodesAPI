package com.jeeproutes.jeepneyroute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeeproutes.jeepneyroute.model.JeepPlace;
import com.jeeproutes.jeepneyroute.model.JeepPlacesId;

public interface JeepPlaceRepo extends JpaRepository<JeepPlace, JeepPlacesId>{
    List<JeepPlace> findByJeepcodeAndRoutename(String jeepcode, String routename);
    List<JeepPlace> findByJeepcode(String jeepcode);
}
