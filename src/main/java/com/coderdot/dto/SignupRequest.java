package com.coderdot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String firstName;
    private String lastName;
    private String phone;
    private String gender;
    private String email;
    private String password;
   
   
}
