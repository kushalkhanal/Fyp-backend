package org.example.gymkhanabackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "due_table")
public class Due {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "due_seq_gen")
    @SequenceGenerator(name = "due_seq_gen", sequenceName = "due_seq", allocationSize = 1)
    private Integer dueId;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 100)
    private String phoneNumber;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;


    @Column(name = "membership_start_date", nullable = false)
    private LocalDate membershipStartDate;

    @Column(name = "membership_end_date", nullable = false)
    private LocalDate membershipEndDate;

    @Column(name = "membership_type_id", nullable = false)
    private Integer membershipTypeId;
}