package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Alumno {

    private double rating;

    private int age;

    private String name;

    private String email;

    private String phone;

    private String higher_grade;
}
