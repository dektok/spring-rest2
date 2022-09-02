package com.example.springrest.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String firstName;
    private String secondName;
    private String position;
    private String department;
}
