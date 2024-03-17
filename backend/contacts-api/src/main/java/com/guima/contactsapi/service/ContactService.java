package com.guima.contactsapi.service;

import com.guima.contactsapi.dto.ContactDTO;
import com.guima.contactsapi.entity.Contact;
import com.guima.contactsapi.exception.ResourceNotFoundException;
import com.guima.contactsapi.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper mapper;

    public Page<Contact> findAll(Pageable pageable){
        return contactRepository.findAll(pageable);
    }

    public Page<Contact> findByNameOrEmail(Pageable pageable, String search){
        return contactRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(pageable, search, search);
    }

    public Contact findById(Integer id){
        return contactRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Contact create(ContactDTO contactDTO){
        Contact contact = mapper.map(contactDTO, Contact.class);
        contact.setCreateAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    public Contact update(Integer id, ContactDTO contactDTO){
        Contact contactFromDb = findById(id);
        mapper.map(contactDTO, contactFromDb);
        return contactRepository.save(contactFromDb);
    }

    public void delete(Integer id){
        Contact contactFromDb = findById(id);
        contactRepository.delete(contactFromDb);
    }
}
