package com.example.VMSappdemo.Entity;

import com.example.VMSappdemo.Enums.VisitStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Enumerated(EnumType.STRING)
@Column(nullable = false)
    private VisitStatus status;

@Column(nullable = false)
    private String purpose;

    private Date inTime;

    private Date outTime;

    private String imageUrl;
@Column(nullable = false)
    private Integer noOfPeople;

@ManyToOne
@JoinColumn(name="visitor_id")
private Visitor visitor;

@ManyToOne
@JoinColumn(name="flat_id",unique = false)
private Flat flat;

@ManyToOne
    private User createdBy;

@ManyToOne
    private User approvedBy;

    @CreationTimestamp
    private Date createDate;

    @UpdateTimestamp
    private Date updateDate;




}
