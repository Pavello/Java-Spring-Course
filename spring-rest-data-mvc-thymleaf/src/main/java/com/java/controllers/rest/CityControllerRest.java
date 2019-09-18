package com.java.controllers.rest;

import com.java.model.City;
import com.java.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/cities")
public class CityControllerRest {

    private CityRepository cityRepository;

    @Autowired
    public CityControllerRest(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> getCities(@RequestParam(defaultValue = "name") String orderBy) {
        List<City> cities = cityRepository.findAll();
        if ("name".equals(orderBy)) {
            cities.sort(Comparator.comparing(City::getName));
        }
        if ("population".equals(orderBy)) {
            cities.sort(Comparator.comparing(City::getPopulation));
        }
        return cities;
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable Long id) {
        return cityRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCity(@RequestBody City city){
        cityRepository.save(city);
    }


}
