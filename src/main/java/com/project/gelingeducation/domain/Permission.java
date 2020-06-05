package com.project.gelingeducation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 6400268759155522604L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 24)
    private String name;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "remark")
    private String remark;

    @Column(name = "url")
    private String url;

    /**
     * 权限标识
     */
    @Column(name = "perms",length = 12)
    private String perms;

    @ManyToMany(targetEntity = Role.class)
    @JsonIgnore
    @JoinTable(
            name = "t_role_permission",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}
