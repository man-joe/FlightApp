package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FlightsRepository extends CrudRepository<Flight, Long> {
    ArrayList<Flight> findByAirlineIgnoreCaseContaining(String search);
//    ArrayList<Flight> findByDateContaining(String month);
}
