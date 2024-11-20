package org.example.gymkhanabackend.repo;

import org.example.gymkhanabackend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Event, Long>{
}
