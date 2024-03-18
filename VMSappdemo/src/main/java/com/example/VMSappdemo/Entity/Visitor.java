package com.example.VMSappdemo.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(nullable = false)
    private  String name;
@Column(nullable = false,unique = true)
    private String phone;
@Column(nullable = false,unique = true)
    private String email;


@ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;
@Column(nullable = false,unique = true)
    private String idNumber;

@OneToMany(mappedBy = "visitor")
    private Set<Visit> vistSet;

@CreationTimestamp
    private Date creteDate;

@UpdateTimestamp
    private Date updateDate;
}
