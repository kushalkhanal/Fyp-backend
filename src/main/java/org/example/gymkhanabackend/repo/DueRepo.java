package org.example.gymkhanabackend.repo;


import org.example.gymkhanabackend.entity.Due;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DueRepo extends JpaRepository<Due, Integer> {
    boolean existsByMemberId(Integer memberId);  //use to check if the member is already in due?
    void deleteByMemberId(Integer memberId);

}