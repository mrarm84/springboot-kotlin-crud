package com.example.demo.service

import com.example.demo.dao.ProjectDao
import com.example.demo.model.CustomException
import com.example.demo.model.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.time.LocalDate

@Service
class ProjectService(@Autowired private val projectDao : ProjectDao) {

    fun getAllProjects(): List<Project> =
            projectDao.findAll()

    fun getProjectById(@PathVariable id : Long) : Project? =
            projectDao.findById(id).orElse(null)

    fun saveProject(@RequestBody project : Project) : Project {
        validateProject(project)
        return projectDao.save(project)
    }

    fun updateProject(@PathVariable id : Long, @RequestBody project : Project): Project {
        validateProject(project)
        projectDao.findById(id).takeIf { it.isPresent }?.let {
            return projectDao.save(Project(id, project.name, project.startDate, project.endDate, project.status))
        } ?: throw CustomException("Project does not exist")
    }

    fun deleteProject(@PathVariable id : Long) {
        try {
            projectDao.deleteById(id)
        }catch(e : EmptyResultDataAccessException){
            throw CustomException("Project does not exist")
        }
    }

    private fun validateProject(project: Project) {
        if (project.name.isEmpty()) throw CustomException("Project name cannot be empty")
        if (project.endDate.isBefore(project.startDate)) throw CustomException("End date cannot be before start date")
    }
}
