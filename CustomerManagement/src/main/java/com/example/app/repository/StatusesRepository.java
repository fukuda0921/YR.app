package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entity.Statuses;



public interface StatusesRepository extends JpaRepository<Statuses,Integer>{

}
