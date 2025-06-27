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

    /**     * Creates a new contact.
     *
     * @param contactDto the data transfer object containing contact details
     * @return the created contact response
     */
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

        contact = contactRepository.save(contact);

        return mapToResponse(contact);
    }

    /**     * Retrieves all contacts.
     *
     * This method returns a list of all contacts in the system.
     * It is useful for displaying all contacts on a page or in a list.
     * @return a list of ContactResponse objects representing all contacts
     */
    public List<ContactResponse> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();

        return mapToResponseList(contacts);
    }

    /**     * Retrieves a contact by its ID.
     *
     * @param id the ID of the contact to retrieve
     * @return the ContactResponse DTO
     * @throws IllegalArgumentException if no contact is found with the given ID
     */
    public ContactResponse getContactById(Long id) {
        Contact contact = findEntityById(id);
        return mapToResponse(contact);
    }

    public Contact findEntityById(Long id) {
        // todo check if this works
        return contactRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Contact not found with id: " + id));
    }

    /**     * Retrieves contacts associated with a sponsor by its ID.
     *
     * @param sponsorId the ID of the sponsor
     * @return a list of ContactResponse objects associated with the sponsor
     */
    public List<ContactResponse> getContactsBySponsorId(Long sponsorId) {
        List<Contact> contacts = contactRepository.findBySponsorId(sponsorId);

        return mapToResponseList(contacts);
    }

    /**     * Retrieves contacts by a partial name match.
     *
     * @param name the partial name to search for
     * @return a list of ContactResponse objects matching the partial name
     */
    public List<ContactResponse> getContactsByPartialName(String name) {
        List<Contact> contacts = contactRepository.findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(name, name);

        return mapToResponseList(contacts);
    }

    /**     * Deletes a contact by its ID.
     *
     * This method will throw an IllegalArgumentException if no contact is found with the given ID.
     * @param id the ID of the contact to delete
     */
    public void deleteContactById(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new IllegalArgumentException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }

    /**     * Maps a Contact entity to a ContactResponse DTO.
     *
     * @param contact the Contact entity to map
     * @return the ContactResponse DTO
     */
    private ContactResponse mapToResponse(Contact contact) {
        return new ContactResponse(
            contact.getId(),
            contact.getFname(),
            contact.getLname(),
            contact.getRole(),
            contact.getEmail(),
            contact.getPhone(),
            contact.getSponsor().getId()
        );
    }

    /**     * Maps a list of Contact entities to a list of ContactResponse DTOs.
     *
     * @param contacts the list of Contact entities to map
     * @return a list of ContactResponse DTOs
     */
    private List<ContactResponse> mapToResponseList(List<Contact> contacts) {
        return contacts.stream()
            .map(this::mapToResponse)
            .toList();
    }
}
