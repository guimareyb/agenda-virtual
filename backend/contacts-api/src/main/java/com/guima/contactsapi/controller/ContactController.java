package com.guima.contactsapi.controller;

import com.guima.contactsapi.dto.ContactDTO;
import com.guima.contactsapi.entity.Contact;
import com.guima.contactsapi.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/contacts")
@RestController
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public Page<Contact> List(Pageable pageable){
        return contactService.findAll(pageable);
    }

    @GetMapping("/search")
    public Page<Contact> searchContacts( @RequestParam(required = false) String search,Pageable pageable) {
        if (search == null) {
            return contactService.findAll(pageable);
        } else {
            return contactService.findByNameOrEmail(pageable, search);
        }
    }

    @GetMapping("{id}")
    public Contact get(@PathVariable Integer id){
        return contactService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contact create(@RequestBody @Validated ContactDTO contactDTO){
        return contactService.create(contactDTO);
    }

    @PutMapping("{id}")
    public Contact update(@PathVariable Integer id, @RequestBody @Validated ContactDTO contactDTO){
        return contactService.update(id, contactDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        contactService.delete(id);
    }
}
