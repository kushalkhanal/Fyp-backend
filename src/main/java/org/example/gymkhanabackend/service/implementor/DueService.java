package org.example.gymkhanabackend.service.implementor;


import org.example.gymkhanabackend.entity.Due;
import org.example.gymkhanabackend.repo.DueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DueService {

    private final DueRepo dueRepo;

    @Autowired
    public DueService(DueRepo dueRepo) {
        this.dueRepo = dueRepo;
    }

    public List<Due> getAllDues() {
        return dueRepo.findAll();
    }
}