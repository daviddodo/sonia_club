package com.mti825.sonia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mti825.sonia.repository.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
}
