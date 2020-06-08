package com.project.gelingeducation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "big_img")
    private String bigImg;
    @ManyToMany(mappedBy = "teachers",fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Course> courses = new HashSet<>();

}
