package com.guima.contactsapi.repository;

import com.guima.contactsapi.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Page<Contact> findAll(Pageable pageable);

    Page<Contact> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(Pageable pageable, String name, String email);
}
