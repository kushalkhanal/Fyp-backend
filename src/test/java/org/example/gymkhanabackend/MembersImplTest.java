
package org.example.gymkhanabackend;

import org.example.gymkhanabackend.entity.Members;

import org.example.gymkhanabackend.entity.MembershipType;

import org.example.gymkhanabackend.pojo.MembersPojo;

import org.example.gymkhanabackend.repo.DueRepo;

import org.example.gymkhanabackend.repo.MembersRepo;

import org.example.gymkhanabackend.service.implementor.MembersImpl;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class MembersImplTest {

    @Mock

    private MembersRepo membersRepo;

    @Mock

    private DueRepo dueRepo;

    @InjectMocks

    private MembersImpl membersImpl;

    @Test

    public void testSaveData() {

        MembersPojo membersPojo = new MembersPojo();

        membersPojo.setMemberId(1);

        membersPojo.setUsername("testUser");

        membersPojo.setPassword("testPass");

        membersPojo.setName("Test Name");

        membersPojo.setEmail("test@example.com");

        membersPojo.setPhoneNumber("1234567890");

        membersPojo.setAddress("Test Address");

        membersPojo.setDateOfBirth(LocalDate.of(1990, 1, 1));

        membersPojo.setMembershipStartDate(LocalDate.of(2023, 1, 1));

        membersPojo.setMembershipEndDate(LocalDate.of(2024, 1, 1));

        MembershipType membershipType = new MembershipType();

        membershipType.setMembershipTypeId(1);

        membershipType.setTypeName("Basic");

        membershipType.setPrice(100.0);

        membersPojo.setMembershipType(membershipType);

        membersImpl.saveData(membersPojo);

        verify(membersRepo, times(1)).save(any(Members.class));

    }

    @Test

    public void testUpdate() {

        Members existingMember = new Members();

        existingMember.setMemberId(1);

        existingMember.setName("Old Name");

        MembersPojo updatedPojo = new MembersPojo();

        updatedPojo.setName("New Name");

        when(membersRepo.findById(anyLong())).thenReturn(Optional.of(existingMember));

        membersImpl.update(1, updatedPojo);

        verify(membersRepo, times(1)).save(existingMember);

        assertEquals("New Name", existingMember.getName());

    }

    @Test

    public void testFindById() {

        Members member = new Members();

        member.setMemberId(1);

        when(membersRepo.findById(anyLong())).thenReturn(Optional.of(member));

        Optional<Members> foundMember = membersImpl.findById(1);

        assertTrue(foundMember.isPresent());

        assertEquals(member, foundMember.get());

    }

    @Test

    public void testGetMembershipTypeName() {

        Members member = new Members();

        member.setMemberId(1);

        MembershipType membershipType = new MembershipType();

        membershipType.setMembershipTypeId(1);

        membershipType.setTypeName("Basic");

        membershipType.setPrice(100.0);

        member.setMembershipType(membershipType);

        when(membersRepo.findById(anyLong())).thenReturn(Optional.of(member));

        String typeName = membersImpl.getMembershipTypeName(1);

        assertEquals("Basic", typeName);

    }

    @Test

    public void testCountBasicMembers() {

        when(membersRepo.countByMembershipType_MembershipTypeId(1)).thenReturn(10L);

        Long count = membersImpl.countBasicMembers();

        assertEquals(10L, count);

    }

    @Test

    public void testCountStandardMembers() {

        when(membersRepo.countByMembershipType_MembershipTypeId(3)).thenReturn(15L);

        Long count = membersImpl.countStandardMembers();

        assertEquals(15L, count);

    }

    @Test

    public void testCountPremiumMembers() {

        when(membersRepo.countByMembershipType_MembershipTypeId(2)).thenReturn(5L);

        Long count = membersImpl.countPremiumMembers();

        assertEquals(5L, count);

    }



    @Test

    public void testGetTotalMembershipPrice() {

        Members member = new Members();

        MembershipType membershipType = new MembershipType();

        membershipType.setPrice(100.0);

        member.setMembershipType(membershipType);

        List<Members> members = new ArrayList<>();

        members.add(member);

        when(membersRepo.findAll()).thenReturn(members);

        double totalPrice = membersImpl.getTotalMembershipPrice();

        assertEquals(100.0, totalPrice);

    }

}

 