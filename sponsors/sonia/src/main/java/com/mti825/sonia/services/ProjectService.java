package com.mti825.sonia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mti825.sonia.dto.ProjectDto;
import com.mti825.sonia.dto.ProjectResponse;
import com.mti825.sonia.models.Project;
import com.mti825.sonia.repository.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    /**     * Creates a new project.
     *
     * @param projectDto the data transfer object containing project details
     * @return the created project response
     */
    public ProjectResponse createProject(ProjectDto projectDto) {
        Project project = new Project(
            projectDto.getName(),
            projectDto.getDescription());
        
        project = projectRepository.save(project);

        return mapToResponse(project);
    }

    /**     * Retrieves all projects.
     *
     * This method returns a list of all projects in the system.
     * It is useful for displaying all projects on a page or in a list.
     * @return a list of ProjectResponse objects representing all projects
     */
    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return mapToResponse(projects);
    }

    /**     * Retrieves a project its ID.
     *
     * @param id the ID of the project
     * @return a list of ProjectResponse associated with the ID
     * @throws IllegalArgumentException if no project is found with the given ID
     */
    public ProjectResponse getProjectById(Long id) {
        Project project = getEntityById(id);
        return mapToResponse(project);
    }

    /**     * Retrieves a project its ID.
     *
     * @param id the ID of the project
     * @return a list of Project object associated with the ID
     * @throws IllegalArgumentException if no project is found with the given ID
     */
    public Project getEntityById(Long id) {
        return projectRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
    }

    /**     * Retrieves projects by a partial name match.
     *
     * @param name the partial name to search for
     * @return a list of ProjectResponse objects matching the partial name
     */
    public List<ProjectResponse> getProjectByPartialName(String name) {
        List<Project> projects = projectRepository.findByNameContainingIgnoreCase(name);

        return mapToResponse(projects);
    }
    
    /**     * Deletes a project by its ID.
     *
     * This method will throw an IllegalArgumentException if no contact is found with the given ID.
     * @param id the ID of the project to delete
     */
    public void deleteProjectById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new IllegalArgumentException("Contact not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }

    /**     * Maps a Project entity to a ProjectResponse DTO.
     *
     * @param project the Project entity to map
     * @return the ProjectResponse DTO
     */
    private ProjectResponse mapToResponse(Project project) {
        return new ProjectResponse(
            project.getId(),
            project.getName(),
            project.getDescription()
        );
    }

    /**     * Maps a list of Project entities to a list of ProjectResponse DTOs.
     *
     * @param projects the list of Project entities to map
     * @return a list of ProjectResponse DTOs
     */
    private List<ProjectResponse> mapToResponse(List<Project> projects) {
        return projects.stream()
            .map(this::mapToResponse)
            .toList();
    }
}
