package org.example.gymkhanabackend.controller;


import org.example.gymkhanabackend.entity.Due;
import org.example.gymkhanabackend.service.implementor.DueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/due")
public class DueController {

    private final DueService dueService;

    @Autowired
    public DueController(DueService dueService) {
        this.dueService = dueService;
    }

    @GetMapping
    public List<Due> getAllDues() {
        return dueService.getAllDues();
    }
}

