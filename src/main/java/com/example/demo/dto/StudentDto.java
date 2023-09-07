package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
public class StudentDto  {
    private Long id;

    private String name;

    private Integer age;

    private String email;

    private String phone;

    private String address;


}