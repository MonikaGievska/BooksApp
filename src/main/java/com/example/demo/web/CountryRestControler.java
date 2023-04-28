package com.example.demo.web;

import com.example.demo.DTO.CountryDTO;
import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class CountryRestControler {
    private final CountryService countryService;

    public CountryRestControler(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/")
    public List<Country> findAll(){
        return countryService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDTO countryDTO) {
        return this.countryService.save(countryDTO)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteByName(@PathVariable Long id) {
        this.countryService.deleteById(id);
        if(this.countryService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}