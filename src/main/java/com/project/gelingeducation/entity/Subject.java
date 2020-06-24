package com.project.gelingeducation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subject")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Accessors(chain = true)
@Setter
@Getter
public class Subject implements Serializable {

    private static final long serialVersionUID = -4621129159591311477L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "big_img")
    private String bigImg;

    @ManyToMany(mappedBy = "teachers",
            fetch = FetchType.EAGER)
        @JsonBackReference
    private Set<Course> courses = new HashSet<>();

}
