package com.example.VMSappdemo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flat {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
@Column(unique = true)
private String number;
@OneToMany(mappedBy = "flat")
private Set<User> Users;
@CreationTimestamp
private Date createDate;

@UpdateTimestamp
private Date updateData;




}
