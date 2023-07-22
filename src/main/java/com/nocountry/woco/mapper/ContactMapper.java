package com.nocountry.woco.mapper;

import com.nocountry.woco.model.dto.ContactDTO;
import com.nocountry.woco.model.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );



    ContactDTO toContactDTO(Contact contact);
    List<ContactDTO> toContactsDTO(List<Contact> contacts);

    Contact toContact(ContactDTO DTO);
    List<Contact> toContacts(List<ContactDTO> contactDTO);

}

