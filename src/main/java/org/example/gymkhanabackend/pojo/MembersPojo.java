package org.example.gymkhanabackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gymkhanabackend.entity.MembershipType;
import org.example.gymkhanabackend.entity.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MembersPojo {
    private Integer memberId;
    private String username;
    private String password;
    private List<Role> roles = new ArrayList<>();
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;
    private MembershipType membershipType;


}
