package org.example.gymkhanabackend.repo;

import org.example.gymkhanabackend.entity.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipTypeRepo  extends JpaRepository<MembershipType, Long> {
}
