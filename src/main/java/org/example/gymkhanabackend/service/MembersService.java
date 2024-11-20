package org.example.gymkhanabackend.service;

import org.example.gymkhanabackend.entity.Members;
import org.example.gymkhanabackend.pojo.MembersPojo;


import java.util.List;
import java.util.Optional;


public interface MembersService {
    void saveData(MembersPojo membersPojo);
    List<Members> getAll();

//    void deleteById(Integer id);
    void deleteMemberAndDue(Integer memberId);

    Optional<Members> findById(Integer id);

    void update(Integer id, MembersPojo membersPojo);

    void copyMembersToDue();

    String getMembershipTypeName(Integer memberId);

    Long countBasicMembers();
    Long countPremiumMembers();
    Long countStandardMembers();
    double getTotalMembershipPrice();

}
