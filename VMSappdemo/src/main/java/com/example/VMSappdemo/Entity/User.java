package com.example.VMSappdemo.Entity;

import com.example.VMSappdemo.Enums.Role;
import com.example.VMSappdemo.Enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

   @Column(nullable = false,unique = true)
    private String email;
@Column(nullable = false,unique = true)
    private String phone;
@Column(nullable = false,unique = true)
    private String idNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;
    @Column(nullable = false)
   @Enumerated(EnumType.STRING)
    private Role role;

@ManyToOne
    private Flat flat;
@Column(nullable = false)
@Enumerated(EnumType.STRING)
    private UserStatus status;

@CreationTimestamp
    private Date CreateDate;

@UpdateTimestamp
    private Date UpdateDate;







}
