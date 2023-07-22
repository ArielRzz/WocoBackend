package com.nocountry.woco.model.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data

public class ContactDTO {
    private int id;


    private String email;

    private String whatsapp;

    private String instagram;

    private String phone;

    private String website;


}
