package org.example.gymkhanabackend.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipTypePojo {
    private Integer membershipTypeId;
    private String typeName;
    private Integer duration;
    private Double price;
}
