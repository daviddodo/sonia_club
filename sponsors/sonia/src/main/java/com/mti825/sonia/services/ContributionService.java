package com.mti825.sonia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mti825.sonia.dto.ContributionDto;
import com.mti825.sonia.dto.ContributionResponse;
import com.mti825.sonia.models.ClubRep;
import com.mti825.sonia.models.Contact;
import com.mti825.sonia.models.Contribution;
import com.mti825.sonia.models.Project;
import com.mti825.sonia.repository.ContributionRepository;

@Service
public class ContributionService {
    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ClubRepService clubRepService;

    @Autowired
    private ProjectService projectService;

    /**     * Creates a new contribution.
     *
     * @param contributionDto the data transfer object containing contribution details
     * @return the created contribution response
     */
    public ContributionResponse createContribution(ContributionDto contributionDto) {
        Contribution contribution = createFromDto(contributionDto);
        contribution = contributionRepository.save(contribution);
        
        return new ContributionResponse(contribution);
    }

    /**     * Retrieves all contributions.
     *
     * This method returns a list of all contributions in the system.
     * It is useful for displaying all contributions on a page or in a list.
     * @return a list of ContributionResponse objects representing all contributions
     */
    public List<ContributionResponse> getAllContributions() {
        List<Contribution> contributions = contributionRepository.findAll();

        return mapToResponseList(contributions);
    }

    /**     * Retrieves a contribution by its ID.
     *
     * @param id the ID of the contribution to retrieve
     * @return the ContributionResponse DTO
     * @throws IllegalArgumentException if no contribution is found with the given ID
     */
    public ContributionResponse getContributionById(Long id) {
        Contribution contribution = getEntityById(id);
        return new ContributionResponse(contribution);
    }

    /**     * Retrieves a contribution by its ID.
     *
     * @param id the ID of the contribution to retrieve
     * @return the Contribution entity
     * @throws IllegalArgumentException if no contribution is found with the given ID
     */
    public Contribution getEntityById(Long id) {
        return contributionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Contribution not found with id " + id));
    }

    /**     * Deletes a contribution by its ID.
     *
     * This method will throw an IllegalArgumentException if no contribution is found with the given ID.
     * @param id the ID of the contribution to delete
     */
    public void deleteContributionById(Long id) {
        if (!contributionRepository.existsById(id)) {
            throw new IllegalArgumentException("Contribution not found with id: " + id);
        }
        contributionRepository.deleteById(id);
    }

    /**     * Retrieves contributions associated with a contact by its ID.
     *
     * @param contactId the ID of the contact
     * @return a list of ContributionResponse objects associated with the contact
     */
    public List<ContributionResponse> getContributionsByContactId(Long contactId) {
        List<Contribution> contributions = contributionRepository.findByContactId(contactId);
        return mapToResponseList(contributions);
    }

    /**     * Retrieves contributions associated with many contacts by their ID.
     *
     * @param contactIds the list of ID of many contacts
     * @return a list of ContributionResponse objects associated with the contact
     */
    public List<ContributionResponse> getContributionsByContactIds(List<Long> contactIds) {
        List<Contribution> contributions = contributionRepository.findByContactIdIn(contactIds);
        return mapToResponseList(contributions);
    }

    /**     * Retrieves contributions associated with a club rep by its ID.
     *
     * @param contactId the ID of the club rep
     * @return a list of ContributionResponse objects associated with the club rep
     */
    public List<ContributionResponse> getContributionsByClubRepId(Long clubRepId) {
        List<Contribution> contributions = contributionRepository.findByClubRepId(clubRepId);
        return mapToResponseList(contributions);
    }

    /**     * Retrieves contributions associated with a project by its ID.
     *
     * @param contactId the ID of the project
     * @return a list of ContributionResponse objects associated with the project
     */
    public List<ContributionResponse> getContributionsByProjectId(Long projectId) {
        List<Contribution> contributions = contributionRepository.findByProjectId(projectId);
        return mapToResponseList(contributions);
    }

    /**
     * Maps a ContributionDto to a Contribution entity.
     *
     * This method is used to convert the ContributionDto into a Contribution entity 
     * that can be saved in the database.
     * @param contributionDto the DTO to convert
     * @return Contribution entity for saving into the database
     */
    private Contribution createFromDto(ContributionDto contributionDto) {
        Contribution contribution = new Contribution(contributionDto);

        Contact contact = contactService.getEntityById(contributionDto.getContactId());
        contribution.setContact(contact);

        if (contributionDto.getClubRepId() != null) {
            ClubRep clubRep = clubRepService.getEntityById(contributionDto.getClubRepId());
            contribution.setClubRep(clubRep);
        }

        if (contributionDto.getProjectId() != null) {
            Project project = projectService.getEntityById(contributionDto.getProjectId());
            contribution.setProject(project);
        }

        return contribution;
    }

    /**     * Maps a list of Contact entities to a list of ContactResponse DTOs.
     *
     * @param contacts the list of Contact entities to map
     * @return a list of ContactResponse DTOs
     */
    private List<ContributionResponse> mapToResponseList(List<Contribution> contributions) {
        return contributions.stream()
            .map(ContributionResponse::new)
            .toList();
    }
}
