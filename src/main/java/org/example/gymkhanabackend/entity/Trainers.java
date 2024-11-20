package org.example.gymkhanabackend.entity;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "trainers_table")
public class Trainers {
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="trainers_seq_gen")
    @SequenceGenerator(name="trainers_seq_gen",sequenceName="trainers_seq",allocationSize=1)

    @Id
    private long id;

    @Column(name="name" ,nullable=false, length=100)
    private String name;

    @Column(name="type" ,nullable=false, length=100)
    private String type;


    @Column(name="salary" ,nullable=false, length=100)
    private Double salary;
}
