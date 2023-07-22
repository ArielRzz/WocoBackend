package com.nocountry.woco.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",length = 2048, nullable = false)
    @NotNull
    @Email
    private String email;

    @Column(name = "whatsapp",length = 2048, nullable = false)
    @NotNull
    private String whatsapp;

    @Column(name = "instagram",length = 2048, nullable = false)
    @NotNull
    private String instagram;

    @Column(name = "phone",length = 350, nullable = false)
    @NotNull
    private String phone;

    @Column(name = "website",length = 2048, nullable = false)
    @NotNull
    private String website;


}
