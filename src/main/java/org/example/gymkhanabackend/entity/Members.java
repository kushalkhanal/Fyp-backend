package org.example.gymkhanabackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "members_table")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "members_seq_gen")
    @SequenceGenerator(name = "members_seq_gen", sequenceName = "members_seq", allocationSize = 1)
    private Integer memberId;

    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "memberId"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();

    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @Column(name = "phone_number", nullable = true, length = 100)
    private String phoneNumber;

    @Column(name = "address", nullable = true, length = 100)
    private String address;

    @Column(name = "date_of_birth", nullable = true)
    private LocalDate dateOfBirth;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "membership_start_date", nullable = true)
    private LocalDate membershipStartDate;

    @Column(name = "membership_end_date", nullable = true)
    private LocalDate membershipEndDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membership_type_id")
    private MembershipType membershipType;
}
