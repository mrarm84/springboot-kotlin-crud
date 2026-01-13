package com.example.demo.controller

import com.example.demo.model.CustomException
import com.example.demo.model.Project
import com.example.demo.service.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class ProjectController(@Autowired private val projectService : ProjectService) {

    @GetMapping("/projects")
    fun getAllProjects() : ResponseEntity<List<Project>> =
            ResponseEntity.status(HttpStatus.OK)
                    .body(projectService.getAllProjects())

    @GetMapping("/projects/{id}")
    fun getProjectById(@PathVariable id : Long) : Project =
            projectService.getProjectById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This project does not exist")

    @PostMapping("/projects")
    fun saveProject(@RequestBody project : Project) : ResponseEntity<Project> {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(projectService.saveProject(project))
        }catch (e : CustomException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PutMapping("/projects/{id}")
    fun updateProject(@PathVariable id : Long, @RequestBody project : Project): ResponseEntity<Project> {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(projectService.updateProject(id, project))

        }catch (e : CustomException){
             if(e.message.equals("Project does not exist"))
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Project Not Found")
            else
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @DeleteMapping("/projects/{id}")
    fun deleteProject(@PathVariable id : Long) : ResponseEntity<Any>{
        try {
            projectService.deleteProject(id)
        }
        catch (e: Exception) {
            // log
        }
        return ResponseEntity.noContent().build()
    }
}
