package com.nocountry.woco.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;







@Entity
@Table(name = "coworks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cowork {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 350, nullable = false)
    @NotNull
    @Builder.Default
    private String name="Default name";

    @Column(name = "description", columnDefinition="TEXT", nullable = false)
    @Lob
    private String description;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cowork_id")
    @Builder.Default
    private Set<Photos> photos = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Services> services = new HashSet<>();


    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NotNull
    @Builder.Default
    private Location location=new Location();


    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NotNull
    @Builder.Default
    private Contact contact=new Contact();


    @Column(name = "address", length = 350)
    private String address;


    @Column(name = "zip_code")
    private String zipCode;

    private float latitude;

    private float longitude;

    @Column(name = "price")
    private double pricePerDay;

    private int capacity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cowork_id")
    @Builder.Default
    private Set<Rating> ratings = new HashSet<>();




}

