package org.example.gymkhanabackend.service.implementor;

import lombok.RequiredArgsConstructor;
import org.example.gymkhanabackend.entity.Trainers;

import org.example.gymkhanabackend.pojo.TrainersPojo;
import org.example.gymkhanabackend.repo.TrainersRepo;
import org.example.gymkhanabackend.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainersImpl implements TrainersService {


    @Autowired
    private TrainersRepo trainersRepo;

    @Override
    public List<Trainers> getAllTrainers() {
        return trainersRepo.findAll();
    }

    @Override
    public void saveData(TrainersPojo trainersPojo) {
        Trainers trainers = new Trainers();
        trainers.setName(trainersPojo.getName());
        trainers.setType(trainersPojo.getType());
        trainers.setSalary(trainersPojo.getSalary());

        trainersRepo.save(trainers);

        Trainers savedTrainers = trainersRepo.save(trainers);

        System.out.println("Generated ID: " + savedTrainers.getId());


    }

    @Override
    public void deleteById(Integer id) {
        trainersRepo.deleteById(Long.valueOf(id));

    }

    @Override
    public Optional<Trainers> findById(Integer id) {
        return trainersRepo.findById(Long.valueOf(id));
    }

    @Override
    public void update(Integer id, TrainersPojo trainersPojo) {
        Optional<Trainers> optionalTrainers = trainersRepo.findById(Long.valueOf(id));

        if (optionalTrainers.isPresent()){
            Trainers existingTrainers = optionalTrainers.get();

            existingTrainers.setName(trainersPojo.getName());
            existingTrainers.setType(trainersPojo.getType());
            existingTrainers.setSalary(trainersPojo.getSalary());

            trainersRepo.save(existingTrainers);
        }



    }

    public double getTotalSalary() {
        return trainersRepo.findAll()
                .stream()
                .mapToDouble(Trainers::getSalary)
                .sum();
    }
}
