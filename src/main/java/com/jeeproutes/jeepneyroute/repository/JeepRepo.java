package com.jeeproutes.jeepneyroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeeproutes.jeepneyroute.model.Jeep;

public interface JeepRepo extends JpaRepository<Jeep,String>{
    
}
