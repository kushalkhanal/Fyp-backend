package org.example.gymkhanabackend.controller;


import lombok.RequiredArgsConstructor;
import org.example.gymkhanabackend.entity.MembershipType;
import org.example.gymkhanabackend.pojo.MembershipTypePojo;
import org.example.gymkhanabackend.service.MembershipTypeService;
import org.example.gymkhanabackend.shared.pojo.GlobalApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/membershipTypes")
@RequiredArgsConstructor

public class MembershipTypeController {
    @Autowired
    private final MembershipTypeService membershipTypeService;


    @GetMapping("/get")
    public GlobalApiResponse<List<MembershipType>> getData() {
        List<MembershipType> list = membershipTypeService.getAll();
        return GlobalApiResponse.
                <List<MembershipType>>builder()
                .data(list)
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }




    @PostMapping("/save")
    public void save(@RequestBody MembershipTypePojo membershipTypePojo) {
        this.membershipTypeService.saveData(membershipTypePojo);
    }


    @GetMapping("/get/{id}")
    public Optional<MembershipType> getData(@PathVariable Integer id) {
        return membershipTypeService.findById(id);
    }

    @DeleteMapping ("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        this.membershipTypeService.deleteById(id);
    }
}
