package com.mti825.sonia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mti825.sonia.dto.ContactDto;
import com.mti825.sonia.dto.ContactResponse;
import com.mti825.sonia.models.Contact;
import com.mti825.sonia.models.Sponsor;
import com.mti825.sonia.repository.ContactRepository;
import com.mti825.sonia.repository.SponsorRepository;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private SponsorRepository sponsorRepository;

    public ContactResponse createContact(ContactDto contactDto) {
        Sponsor sponsor = sponsorRepository.findById(contactDto.getSponsorId())
            .orElseThrow(() -> new IllegalArgumentException("Sponsor not found"));

        Contact contact = new Contact(
            contactDto.getFname(),
            contactDto.getLname(),
            contactDto.getRole(),
            contactDto.getEmail(),
            contactDto.getPhone(),
            sponsor);

        Contact savedContact = contactRepository.save(contact);
        return new ContactResponse(
            savedContact.getId(),
            savedContact.getFname(),
            savedContact.getLname(),
            savedContact.getRole(),
            savedContact.getEmail(),
            savedContact.getPhone(),
            savedContact.getSponsor().getId()
        );
    }

    public List<ContactResponse> getAllContacts() {
        return contactRepository.findAll()
            .stream()
            .map(contact -> new ContactResponse(
                contact.getId(),
                contact.getFname(),
                contact.getLname(),
                contact.getRole(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getSponsor().getId()))
            .toList();
    }

    public List<ContactResponse> getContactsBySponsorId(Long sponsorId) {
        return contactRepository.findBySponsorId(sponsorId)
            .stream()
            .map(contact -> new ContactResponse(
                contact.getId(),
                contact.getFname(),
                contact.getLname(),
                contact.getRole(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getSponsor().getId()))
            .toList();
    }

    public void deleteContactById(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new IllegalArgumentException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }
}
