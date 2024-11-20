package org.example.gymkhanabackend.service;

import org.example.gymkhanabackend.entity.Trainers;

import org.example.gymkhanabackend.pojo.TrainersPojo;


import java.util.List;
import java.util.Optional;


public interface TrainersService {

    List<Trainers> getAllTrainers();
    void saveData(TrainersPojo trainersPojo);
    void deleteById(Integer id);

    Optional<Trainers>findById(Integer id);

    void update(Integer id, TrainersPojo trainersPojo);

    double getTotalSalary();
}
