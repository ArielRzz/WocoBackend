package com.nocountry.woco.model.repository;


import com.nocountry.woco.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer > {

}

