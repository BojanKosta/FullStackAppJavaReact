package com.bojankosta.ppmtool.web;


import com.bojankosta.ppmtool.domain.Project;
import com.bojankosta.ppmtool.services.ErrorValidationService;
import com.bojankosta.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ErrorValidationService errorValidationService;

    @PostMapping("")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> error = errorValidationService.errorValidationService(result);
        if (error != null) return error;

        Project project1 = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity <?> findProjectById (@PathVariable String projectId){
        Project project = projectService.findProject(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
}
