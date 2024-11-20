
package org.example.gymkhanabackend;

import org.example.gymkhanabackend.entity.MembershipType;

import org.example.gymkhanabackend.pojo.MembershipTypePojo;

import org.example.gymkhanabackend.repo.MembershipTypeRepo;

import org.example.gymkhanabackend.service.implementor.MembershipTypeImpl;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class MembershipTypeImplTest {

    @Mock

    private MembershipTypeRepo membershipTypeRepo;

    @InjectMocks

    private MembershipTypeImpl membershipTypeImpl;

    @Test

    public void testSaveData() {

        MembershipTypePojo membershipTypePojo = new MembershipTypePojo();

        membershipTypePojo.setMembershipTypeId(1);

        membershipTypePojo.setTypeName("Basic");

        membershipTypePojo.setDuration(30); // example duration in days

        membershipTypePojo.setPrice(100.0);

        membershipTypeImpl.saveData(membershipTypePojo);

        verify(membershipTypeRepo, times(1)).save(any(MembershipType.class));

    }

    @Test

    public void testGetAll() {

        MembershipType membershipType1 = new MembershipType();

        membershipType1.setMembershipTypeId(1);

        membershipType1.setTypeName("Basic");

        MembershipType membershipType2 = new MembershipType();

        membershipType2.setMembershipTypeId(2);

        membershipType2.setTypeName("Premium");

        List<MembershipType> membershipTypes = new ArrayList<>();

        membershipTypes.add(membershipType1);

        membershipTypes.add(membershipType2);

        when(membershipTypeRepo.findAll()).thenReturn(membershipTypes);

        List<MembershipType> result = membershipTypeImpl.getAll();

        assertEquals(2, result.size());

        assertTrue(result.contains(membershipType1));

        assertTrue(result.contains(membershipType2));

    }

    @Test

    public void testDeleteById() {

        doNothing().when(membershipTypeRepo).deleteById(anyLong());

        membershipTypeImpl.deleteById(1);

        verify(membershipTypeRepo, times(1)).deleteById(1L);

    }

    @Test

    public void testFindById() {

        MembershipType membershipType = new MembershipType();

        membershipType.setMembershipTypeId(1);

        membershipType.setTypeName("Basic");

        when(membershipTypeRepo.findById(anyLong())).thenReturn(Optional.of(membershipType));

        Optional<MembershipType> result = membershipTypeImpl.findById(1);

        assertTrue(result.isPresent());

        assertEquals(membershipType, result.get());

    }

}

 