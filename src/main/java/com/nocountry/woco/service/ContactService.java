package com.nocountry.woco.service;

import com.nocountry.woco.model.dto.ContactDTO;
import com.nocountry.woco.model.entity.Contact;
import com.nocountry.woco.model.exception.ResourceNotFoundException;

import java.util.List;

public interface ContactService {
    List<ContactDTO> getAll();
    ContactDTO getById(int id) throws ResourceNotFoundException;
    ContactDTO post(Contact contact);

    ContactDTO patch(int id, Contact constact) throws ResourceNotFoundException;

    ContactDTO delete(int id) throws ResourceNotFoundException;


}
