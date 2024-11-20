package org.example.gymkhanabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.gymkhanabackend.entity.Members;
import org.example.gymkhanabackend.entity.MembershipType;
import org.example.gymkhanabackend.entity.Role;
import org.example.gymkhanabackend.pojo.AuthResponsePojo;
import org.example.gymkhanabackend.pojo.MembersPojo;
import org.example.gymkhanabackend.repo.MembersRepo;

import org.example.gymkhanabackend.repo.MembershipTypeRepo;
import org.example.gymkhanabackend.repo.RoleRepository;
import org.example.gymkhanabackend.security.JwtGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final MembersRepo membersRepo;
    private final RoleRepository roleRepository;
    private final MembershipTypeRepo membershipTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<AuthResponsePojo> login(@RequestBody MembersPojo loginPojo) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginPojo.getUsername(), loginPojo.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(loginPojo.getUsername());

            Members user = membersRepo.findByUsername(loginPojo.getUsername())
                    .orElseThrow();
            List<String> roles = user.getRoles().stream()
                    .map(Role::getName)
                    .toList();

            AuthResponsePojo response = new AuthResponsePojo(token, user.getMemberId(), roles);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody MembersPojo registerPojo) {
        if (membersRepo.existsByUsername(registerPojo.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is taken");
        }

        Members member = buildMemberFromPojo(registerPojo);
        Optional<Role> role = roleRepository.findByName("USER");
        Optional<MembershipType> membershipType = membershipTypeRepository.findById(Long.valueOf(registerPojo.getMembershipType().getMembershipTypeId()));

        if (role.isPresent() && membershipType.isPresent()) {
            member.setRoles(Collections.singletonList(role.get()));
            member.setMembershipType(membershipType.get());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Role or Membership Type not found");
        }

        membersRepo.save(member);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody MembersPojo registerPojo) {
        if (membersRepo.existsByUsername(registerPojo.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is taken");
        }

        Members member = buildMemberFromPojo(registerPojo);
        Optional<Role> role = roleRepository.findByName("ADMIN");
        Optional<MembershipType> membershipType = membershipTypeRepository.findById(Long.valueOf(registerPojo.getMembershipType().getMembershipTypeId()));

        if (role.isPresent() && membershipType.isPresent()) {
            member.setRoles(Collections.singletonList(role.get()));
            member.setMembershipType(membershipType.get());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Role or Membership Type not found");
        }

        membersRepo.save(member);
        return ResponseEntity.ok("Admin registered successfully");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody MembersPojo request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            String token = jwtGenerator.generateToken(request.getUsername());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/secured")
    public ResponseEntity<String> securedEndpoint(@RequestHeader("Authorization") String token) {
        if (!jwtGenerator.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }

        String username = jwtGenerator.getUsernameFromJwt(token);
        return ResponseEntity.ok("Hello, " + username + "! This is a secured endpoint.");
    }

    private Members buildMemberFromPojo(MembersPojo registerPojo) {
        Members member = new Members();
        member.setUsername(registerPojo.getUsername());
        member.setPassword(passwordEncoder.encode(registerPojo.getPassword()));
        member.setName(registerPojo.getName());
        member.setEmail(registerPojo.getEmail());
        member.setPhoneNumber(registerPojo.getPhoneNumber());
        member.setAddress(registerPojo.getAddress());
        member.setDateOfBirth(registerPojo.getDateOfBirth());
        member.setMembershipStartDate(registerPojo.getMembershipStartDate());
        member.setMembershipEndDate(registerPojo.getMembershipEndDate());
        member.setCreatedAt(LocalDateTime.now());
        return member;
    }
}
