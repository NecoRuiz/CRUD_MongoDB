package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Profesor {

    private double rating;

    private int age;

    private String name;

    private String gender;

    private String email;

    private String phone;

    @BsonIgnore
    private List<String> subjects;

    private String title;



}
