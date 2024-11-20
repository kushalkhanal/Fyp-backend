package org.example.gymkhanabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class MembershipType {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "members_type_gen")
        @SequenceGenerator(name = "members_type_gen", sequenceName = "members_type", allocationSize = 1)
        private Integer membershipTypeId;
        private String typeName;
        private Integer duration;  // in months
        private Double price;


}
