package org.example.gymkhanabackend.service.implementor;

import org.example.gymkhanabackend.entity.Due;
import org.example.gymkhanabackend.entity.Members;
import org.example.gymkhanabackend.pojo.MembersPojo;
import org.example.gymkhanabackend.repo.DueRepo;
import org.example.gymkhanabackend.repo.MembersRepo;
import org.example.gymkhanabackend.service.MembersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MembersImpl implements MembersService {
    private final MembersRepo membersRepo;
    @Autowired
    private DueRepo dueRepository;
    @Override
    public void saveData(MembersPojo membersPojo) {
        Members members = new Members();
        members.setMemberId(membersPojo.getMemberId());
        members.setUsername(membersPojo.getUsername());
        members.setPassword(membersPojo.getPassword());
        members.setName(membersPojo.getName());
        members.setEmail(membersPojo.getEmail());
        members.setPhoneNumber(membersPojo.getPhoneNumber());
        members.setAddress(membersPojo.getAddress());
        members.setDateOfBirth(membersPojo.getDateOfBirth());
        members.setMembershipStartDate(membersPojo.getMembershipStartDate());
        members.setMembershipEndDate(membersPojo.getMembershipEndDate());
        members.setCreatedAt(LocalDateTime.now());
        members.setMembershipType(membersPojo.getMembershipType());


        membersRepo.save(members);
    }
    @Override
    public List<Members>getAll() {
        return membersRepo.findAll();
    }


    public void update(Integer id, MembersPojo membersPojo) {
        Optional<Members> optionalMembers = membersRepo.findById(Long.valueOf(id));

        if (optionalMembers.isPresent()) {
            Members existingMembers = optionalMembers.get();

            existingMembers.setName(membersPojo.getName());
            existingMembers.setUsername(membersPojo.getUsername());
            existingMembers.setPassword(membersPojo.getPassword());
            existingMembers.setEmail(membersPojo.getEmail());
            existingMembers.setDateOfBirth(membersPojo.getDateOfBirth());
            existingMembers.setPhoneNumber(membersPojo.getPhoneNumber());
            existingMembers.setAddress(membersPojo.getAddress());
            existingMembers.setMembershipStartDate(membersPojo.getMembershipStartDate());
            existingMembers.setMembershipEndDate(membersPojo.getMembershipEndDate());
            existingMembers.setMembershipType(membersPojo.getMembershipType());

            membersRepo.save(existingMembers);

        }

    }
    @Override
    public Optional<Members> findById(Integer id) {
        return membersRepo.findById(Long.valueOf(id));
    }

    @Override
    public String getMembershipTypeName(Integer memberId) {
        Optional<Members> optionalMembers = membersRepo.findById(Long.valueOf(memberId));
        if (optionalMembers.isPresent()) {
            Members members = optionalMembers.get();
            return members.getMembershipType().getTypeName();
        } else {
            return "Member not found";
        }
    }
    @Override
    public Long countBasicMembers() {
        return membersRepo.countByMembershipType_MembershipTypeId(1);
    }
    @Override
    public Long countStandardMembers() {
        return membersRepo.countByMembershipType_MembershipTypeId(3);
    }

    @Override
    public Long countPremiumMembers() {
        return membersRepo.countByMembershipType_MembershipTypeId(2);
    }
    public void copyMembersToDue() {
        LocalDate today = LocalDate.now();
        List<Members> membersWithDue = membersRepo.findByMembershipEndDateBefore(today);

        for (Members member : membersWithDue) {
            // Check if the member is already in the Due table
            if (!dueRepository.existsByMemberId(member.getMemberId())) {
                Due due = new Due();
                due.setMemberId(member.getMemberId());
                due.setName(member.getName());
                due.setEmail(member.getEmail());
                due.setPhoneNumber(member.getPhoneNumber());
                due.setAddress(member.getAddress());
                due.setDateOfBirth(member.getDateOfBirth());
//
                due.setMembershipStartDate(member.getMembershipStartDate());
                due.setMembershipEndDate(member.getMembershipEndDate());
                due.setMembershipTypeId(member.getMembershipType().getMembershipTypeId());

                dueRepository.save(due);
            }
        }
    }



    @Scheduled(initialDelay = 30000, fixedRate = 86400000) // 30 seconds initial delay, then repeat every 24 hours
    public void scheduleCopyMembersToDue() {
        copyMembersToDue();
    }
    @Transactional
    @Override
    public void deleteMemberAndDue(Integer memberId) {
        dueRepository.deleteByMemberId(memberId);
        membersRepo.deleteById(Long.valueOf(memberId));
    }
    public double getTotalMembershipPrice() {
        List<Members> members = membersRepo.findAll();
        members.forEach(member -> {
            System.out.println("Member: " + member.getName() + ", Membership Type: " + member.getMembershipType().getTypeName() + ", Price: " + member.getMembershipType().getPrice());
        });
        return members.stream()
                .mapToDouble(member -> member.getMembershipType().getPrice())
                .sum();
    }



}
