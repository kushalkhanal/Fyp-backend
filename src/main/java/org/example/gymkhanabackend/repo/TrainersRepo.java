package org.example.gymkhanabackend.repo;

import org.example.gymkhanabackend.entity.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainersRepo extends JpaRepository<Trainers, Long> {
    List<Trainers> findByType(String type);
}