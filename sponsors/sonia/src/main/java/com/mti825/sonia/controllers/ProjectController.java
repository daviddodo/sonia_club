package com.mti825.sonia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.ContributionResponse;
import com.mti825.sonia.dto.ProjectDto;
import com.mti825.sonia.dto.ProjectResponse;
import com.mti825.sonia.services.ContributionService;
import com.mti825.sonia.services.ProjectService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ContributionService contributionService;

    /**     * Creates a new project.
     *
     * @param projectDto the data transfer object containing project details
     * @return the created ProjectResponse
     */
    @PostMapping
    public ProjectResponse createProject(@RequestBody @Valid ProjectDto projectDto) {
        return projectService.createProject(projectDto);
    }

    /**     * Retrieves all projects.
     *
     * This method returns a list of all projects in the system.
     * It is useful for displaying all projects on a page or in a list.
     * @return a list of ProjectResponse objects representing all projects
     */
    @GetMapping
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    /**     * Retrieves a project by its ID.
     *
     * @param id the ID of the project to retrieve
     * @return the ProjectResponse
     * @throws IllegalArgumentException if no contact is found with the given ID
     */
    @GetMapping(value="/{id}")
    public ProjectResponse getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }
    
    /**     * Retrieves projects by a partial name match.
     *
     * @param name the partial name to search for
     * @return a list of ProjectResponse objects matching the partial name
     */
    @GetMapping("/search")
    public List<ProjectResponse> getMethodName(@RequestParam String name) {
        return projectService.getProjectByPartialName(name);
    }

    /**     * Deletes a project by its ID.
     *
     * @param id the ID of the project to delete
     * @throws IllegalArgumentException if no project is found with the given ID
     */
    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
    }

    /**     * Retrieves contributions associated with a project by its ID.
     *
     * @param id the ID of the project
     * @return a list of ContributionResponse objects associated with the project
     */
    @GetMapping(value="/{id}/contributions")
    public List<ContributionResponse> getContributionsByProjectId(@PathVariable Long id) {
        return contributionService.getContributionsByProjectId(id);
    }
    
}
