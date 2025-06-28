package com.mti825.sonia.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.ContactDto;
import com.mti825.sonia.dto.ContactResponse;
import com.mti825.sonia.dto.ContributionResponse;
import com.mti825.sonia.services.ContactService;
import com.mti825.sonia.services.ContributionService;

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

    @Autowired
    private ContributionService contributionService;

    /**     * Creates a new contact.
     *
     * @param contactDto the data transfer object containing contact details
     * @return the created ContactResponse
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse createContact(@RequestBody @Valid ContactDto contactDto) {
        return contactService.createContact(contactDto);
    }
    
    /**     * Retrieves all contacts.
     *
     * This method returns a list of all contacts in the system.
     * It is useful for displaying all contacts on a page or in a list.
     * @return a list of ContactResponse objects representing all contacts
     */
    @GetMapping
    public List<ContactResponse> getAllContacts() {
        return contactService.getAllContacts();
    }

    /**     * Retrieves a contact by its ID.
     *
     * @param id the ID of the contact to retrieve
     * @return the contact response
     * @throws IllegalArgumentException if no contact is found with the given ID
     */
    @GetMapping(value="/{id}")
    public ContactResponse getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    /**     * Retrieves contacts by a partial name match.
     *
     * @param name the partial name to search for
     * @return a list of ContactResponse objects matching the partial name
     */
    @GetMapping("/search")
    public List<ContactResponse> getContactByPartialName(@RequestParam String name) {
        return contactService.getContactsByPartialName(name);
    }
    
    /**     * Deletes a contact by its ID.
     *
     * @param id the ID of the contact to delete
     * @throws IllegalArgumentException if no contact is found with the given ID
     */
    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactById(@PathVariable Long id) {
        contactService.deleteContactById(id);
    }

    /**     * Retrieves contributions associated with a contact by its ID.
     *
     * @param id the ID of the contact
     * @return a list of ContributionResponse objects associated with the contact
     */
    @GetMapping(value="/{id}/contributions")
    public List<ContributionResponse> getContributionsByContactId(@PathVariable Long id) {
        return contributionService.getContributionsByContactId(id);
    }
}
