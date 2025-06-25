package com.mti825.sonia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mti825.sonia.dto.ClubRepDto;
import com.mti825.sonia.dto.ClubRepResponse;
import com.mti825.sonia.models.ClubRep;
import com.mti825.sonia.repository.ClubRepRepository;

@Service
public class ClubRepService {
    @Autowired
    private ClubRepRepository clubRepRepository;

    /**     * Creates a new club rep.
     *
     * @param clubRepDto the data transfer object containing club rep details
     * @return the created club rep response
     */
    public ClubRepResponse createClubRep(ClubRepDto clubRepDto) {
        ClubRep clubRep = new ClubRep(
            clubRepDto.getFname(),
            clubRepDto.getLname(),
            clubRepDto.getEmail(),
            clubRepDto.getPhone()
        );

        clubRep = clubRepRepository.save(clubRep);

        return mapToResponse(clubRep);
    }

    /**     * Retrieves all club reps.
     *
     * This method returns a list of all club reps in the system.
     * It is useful for displaying all club reps on a page or in a list.
     * @return a list of ClubRepResponse objects representing all club reps
     */
    public List<ClubRepResponse> getAllClubReps() {
        List<ClubRep> clubReps = clubRepRepository.findAll();

        return mapToResponseList(clubReps);
    }

    /**     * Maps a ClubRep entity to a ClubRepResponse DTO.
     *
     * @param clubRep the ClubRep entity to map
     * @return the ClubRepResponse DTO
     */
    private ClubRepResponse mapToResponse(ClubRep clubRep) {
        return new ClubRepResponse(
            clubRep.getId(),
            clubRep.getFname(),
            clubRep.getLname(),
            clubRep.getEmail(),
            clubRep.getPhone()
        );
    }

    /**     * Maps a list of ClubRep entities to a list of ClubRepResponse DTOs.
     *
     * @param contacts the list of ClubRep entities to map
     * @return a list of ClubRepResponse DTOs
     */
    private List<ClubRepResponse> mapToResponseList(List<ClubRep> clubReps) {
        return clubReps.stream()
            .map(this::mapToResponse)
            .toList();
    }
}
