package org.example.gymkhanabackend.service;

import org.example.gymkhanabackend.entity.MembershipType;
import org.example.gymkhanabackend.pojo.MembershipTypePojo;


import java.util.List;
import java.util.Optional;


public interface MembershipTypeService {
    void saveData(MembershipTypePojo membershipTypePojo);
    List<MembershipType> getAll();

    void deleteById(Integer id);
    Optional<MembershipType> findById(Integer id);

}
