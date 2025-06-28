package com.mti825.sonia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mti825.sonia.dto.FollowupDto;
import com.mti825.sonia.dto.FollowupResponse;
import com.mti825.sonia.models.Followup;
import com.mti825.sonia.repository.FollowupRepository;

@Service
public class FollowupService {
    @Autowired
    private FollowupRepository followupRepository;

    /**     * Creates a new followup.
     *
     * @param followupDto the data transfer object containing followup details
     * @return the created followup response
     */
    public FollowupResponse createFollowup(FollowupDto followupDto) {
        Followup followup = createFromDto(followupDto);
        followup = followupRepository.save(followup);

        return new FollowupResponse(followup);
    }

    /**     * Retrieves all followups.
     *
     * This method returns a list of all followups in the system.
     * It is useful for displaying all followups on a page or in a list.
     * @return a list of FollowupResponse objects representing all followups
     */
    public List<FollowupResponse> getAllFollowups() {
        List<Followup> followups = followupRepository.findAll();
        return mapToResponseList(followups);
    }

    /**     * Retrieves a followup by its ID.
     *
     * @param id the ID of the followup to retrieve
     * @return the FollowupResponse DTO
     * @throws IllegalArgumentException if no followup is found with the given ID
     */
    public FollowupResponse getFollowupById(Long id) {
        Followup followup = followupRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Followup not found with id: " + id));

        return new FollowupResponse(followup);
    }

    /**     * Deletes a followup by its ID.
     *
     * This method will throw an IllegalArgumentException if no followup is found with the given ID.
     * @param id the ID of the followup to delete
     */
    public void deleteFollowupById(Long id) {
        if (!followupRepository.existsById(id)) {
            throw new IllegalArgumentException("Followup not found with id: " + id);
        }
        followupRepository.deleteById(id);
    }


    /**
     * Maps a FollowupDto to a Followup entity.
     *
     * This method is used to convert the FollowupDto into a Followup entity 
     * that can be saved in the database.
     * @param followupDto the DTO to convert
     * @return Followup entity for saving into the database
     */
    private Followup createFromDto(FollowupDto followupDto) {
        return new Followup(followupDto);
    }

    private List<FollowupResponse> mapToResponseList(List<Followup> followups) {
        return followups.stream()
            .map(FollowupResponse::new)
            .toList();
    }
}
