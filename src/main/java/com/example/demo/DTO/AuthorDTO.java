package com.example.demo.DTO;

import com.example.demo.model.Country;
import lombok.Data;

@Data
public class AuthorDTO {
    private String name;

    private String surname;

    private Long country;
}