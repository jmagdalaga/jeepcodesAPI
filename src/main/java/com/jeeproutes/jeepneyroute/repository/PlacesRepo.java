package com.jeeproutes.jeepneyroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeeproutes.jeepneyroute.model.Places;

public interface PlacesRepo extends JpaRepository<Places,String>{
    
}
