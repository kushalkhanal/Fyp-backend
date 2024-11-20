package org.example.gymkhanabackend.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainersPojo {
    private Long id;
    private String name;
    private String type; // "skilled" or "experienced"
    private Double salary;
}
