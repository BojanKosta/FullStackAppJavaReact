package com.bojankosta.ppmtool.services;

import com.bojankosta.ppmtool.domain.Project;
import com.bojankosta.ppmtool.exceptions.ProjectIdException;
import com.bojankosta.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project) {

        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        try {
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Cant add project with same project identifier. "
                    + "Project with this projectId " + project.getProjectIdentifier() + " already exists.");
        }
    }

    public Project findProject(String projectId) {

        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null) {
            throw new ProjectIdException("Cant add project with same project identifier. "
                    + "Project with this projectId " + project.getProjectIdentifier() + " does not exists.");
        }

        return project;
    }
}
