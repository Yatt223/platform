package com.formation.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.platform.dto.CoursDto;
import com.formation.platform.service.CoursService;

@RestController
@RequestMapping("/api/cours")
@CrossOrigin
// @CrossOrigin(origins = "http://localhost:3000")
public class CoursController {
    @Autowired
    private CoursService coursService;

    @GetMapping
    public List<CoursDto> getAll() {
        return coursService.getAll();
    }
    @PostMapping
    public CoursDto create(@RequestBody CoursDto dto) {
        return coursService.create(dto);
    }
    @GetMapping("/{id}")
    public CoursDto getById(@PathVariable Long id) {
        return coursService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        coursService.delete(id);
    }
}
