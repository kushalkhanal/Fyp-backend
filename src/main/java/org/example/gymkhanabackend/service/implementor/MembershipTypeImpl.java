package org.example.gymkhanabackend.service.implementor;

import lombok.RequiredArgsConstructor;
import org.example.gymkhanabackend.entity.MembershipType;
import org.example.gymkhanabackend.pojo.MembershipTypePojo;
import org.example.gymkhanabackend.repo.MembershipTypeRepo;
import org.example.gymkhanabackend.service.MembershipTypeService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembershipTypeImpl implements MembershipTypeService {

    private final MembershipTypeRepo membershipTypeRepo;
    @Override
    public void saveData(MembershipTypePojo membershipTypePojo) {
        MembershipType membershipType = new MembershipType();
        membershipType.setMembershipTypeId(membershipTypePojo.getMembershipTypeId());
        membershipType.setTypeName(membershipTypePojo.getTypeName());
        membershipType.setDuration(membershipTypePojo.getDuration());
        membershipType.setPrice(membershipTypePojo.getPrice());



        membershipTypeRepo.save(membershipType);
    }

    @Override
    public List<MembershipType> getAll() {
        return membershipTypeRepo.findAll();
    }
    @Override
    public void deleteById(Integer id) {
        membershipTypeRepo.deleteById(Long.valueOf(id));
    }
    @Override
    public Optional<MembershipType> findById(Integer id) {
        return membershipTypeRepo.findById(Long.valueOf(id));
    }

}


