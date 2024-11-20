package org.example.gymkhanabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.gymkhanabackend.entity.Members;
import org.example.gymkhanabackend.pojo.MembersPojo;
import org.example.gymkhanabackend.service.MembersService;
import org.example.gymkhanabackend.shared.pojo.GlobalApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor

@Validated
public class MembersController {
    private final MembersService membersService;

    //get mapping
    @GetMapping("/get")
    public GlobalApiResponse<List<Members>> getData() {
        List<Members> list = membersService.getAll();

        return GlobalApiResponse.
                <List<Members>>builder()
                .data(list)
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }



  //save
    @PostMapping("/save")
    public GlobalApiResponse<String> save(@Validated @RequestBody MembersPojo membersPojo) {
        this.membersService.saveData(membersPojo);
        return GlobalApiResponse.
                <String>builder()
                .statusCode(200)
                .data("ok")
                .message("Member saved Successfully!")
                .build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GlobalApiResponse<Optional<Members>>> getData(@PathVariable Integer id) {
        Optional<Members> list = membersService.findById(id);

        if (list.isEmpty()) {
            return ResponseEntity.status(404).body(
                    GlobalApiResponse.<Optional<Members>>builder()
                            .data(list)
                            .statusCode(404)
                            .message("Data not found!")
                            .build()
            );
        }

        return ResponseEntity.ok(
                GlobalApiResponse.<Optional<Members>>builder()
                        .data(list)
                        .statusCode(200)
                        .message("Data retrieved successfully!")
                        .build()
        );
    }



    @DeleteMapping("/delete/{id}")
    public GlobalApiResponse<String> deleteMember(@PathVariable Integer id) {
        this.membersService.deleteMemberAndDue(id);
        return GlobalApiResponse.
                <String>builder()
                .statusCode(200)
                .data("ok")
                .message("Data deleted successfully")
                .build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GlobalApiResponse<String>> updateData(@PathVariable("id") Integer id, @RequestBody MembersPojo membersPojo) {
        Optional<Members> existingMember = membersService.findById(id);

        if (existingMember.isPresent()) {
            membersService.update(id, membersPojo);
            return ResponseEntity.ok(
                    GlobalApiResponse.<String>builder()
                            .data("updated data")
                            .statusCode(200)
                            .message("Data updated successfully!")
                            .build()
            );
        } else {
            return ResponseEntity.status(404).body(
                    GlobalApiResponse.<String>builder()
                            .data("Members not found with id: " + id)
                            .statusCode(404)
                            .message("Update failed: Data not found")
                            .build()
            );
        }
    }

    @GetMapping("/count/basic")
    public ResponseEntity<GlobalApiResponse<Long>> countBasicMembers() {
        Long count = membersService.countBasicMembers();

        return ResponseEntity.ok(
                GlobalApiResponse.<Long>builder()
                        .data(count)
                        .statusCode(200)
                        .message("Count retrieved successfully!")
                        .build()
        );
    }

    @GetMapping("/count/premium")
    public ResponseEntity<GlobalApiResponse<Long>> countPremiumMembers() {
        Long count = membersService.countPremiumMembers();

        return ResponseEntity.ok(
                GlobalApiResponse.<Long>builder()
                        .data(count)
                        .statusCode(200)
                        .message("Count retrieved successfully!")
                        .build()
        );
    }
    @GetMapping("/count/standard")
    public ResponseEntity<GlobalApiResponse<Long>> countStandardMembers() {
        Long count = membersService.countStandardMembers();

        return ResponseEntity.ok(
                GlobalApiResponse.<Long>builder()
                        .data(count)
                        .statusCode(200)
                        .message("Count retrieved successfully!")
                        .build()
        );
    }

    @GetMapping("/total-price")
    public GlobalApiResponse<Double> getTotalMembershipPrice() {
        double totalPrice = membersService.getTotalMembershipPrice();
        return GlobalApiResponse.<Double>builder()
                .data(totalPrice)
                .statusCode(200)
                .message("Total price retrieved successfully!")
                .build();
    }



}
