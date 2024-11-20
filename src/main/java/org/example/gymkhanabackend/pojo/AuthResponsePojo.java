package org.example.gymkhanabackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor

@Data
public class AuthResponsePojo {


    private String accessToken;
    private Integer userId;
    private List<String> roles;
}