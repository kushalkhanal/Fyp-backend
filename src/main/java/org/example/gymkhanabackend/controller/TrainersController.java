package org.example.gymkhanabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.gymkhanabackend.entity.Trainers;
import org.example.gymkhanabackend.pojo.TrainersPojo;
import org.example.gymkhanabackend.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trainers")
public class TrainersController {
    @Autowired
    private TrainersService trainersService;

    @GetMapping("/get")
    public List<Trainers> getAllTrainers() {
        return trainersService.getAllTrainers();
    }

    @PostMapping("/save")
    public void save(@RequestBody TrainersPojo trainersPojo) {
        this.trainersService.saveData(trainersPojo);
    }


    @GetMapping("/get/{id}")
    public Optional<Trainers> getData(@PathVariable Integer id) {
        return trainersService.findById(id);
    }



    @PutMapping("/update/{id}")
    public void update(@PathVariable Integer id, @RequestBody TrainersPojo trainersPojo) {
        this.trainersService.update(id, trainersPojo);
    }

    @DeleteMapping ("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        this.trainersService.deleteById(id);
    }

    @GetMapping("/total-salary")
    public double getTotalSalary() {
        return trainersService.getTotalSalary();
    }


}
