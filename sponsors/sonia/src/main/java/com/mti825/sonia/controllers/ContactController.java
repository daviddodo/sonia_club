package com.mti825.sonia.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.ContactDto;
import com.mti825.sonia.dto.ContactResponse;
import com.mti825.sonia.services.ContactService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse createContact(@RequestBody @Valid ContactDto contactDto) {
        return contactService.createContact(contactDto);
    }
    
    @GetMapping
    public List<ContactResponse> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping(value="/{id}")
    public ContactResponse getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @GetMapping("/search")
    public List<ContactResponse> getContactByPartialName(@RequestParam String name) {
        return contactService.getContactsByPartialName(name);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContactById(id);
    }
}
