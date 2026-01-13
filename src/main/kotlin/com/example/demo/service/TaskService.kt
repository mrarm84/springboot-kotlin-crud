package com.example.demo.service

import com.example.demo.dao.TaskDao
import com.example.demo.model.CustomException
import com.example.demo.model.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Service
class TaskService(@Autowired private val taskDao : TaskDao) {

    fun getAllTasks(): List<Task> =
            taskDao.findAll()

    fun getTaskById(@PathVariable id : Long) : Task? =
            taskDao.findById(id).orElse(null)

    fun saveTask(@RequestBody task : Task) : Task {
        if(task.title.isNotEmpty()) {
            return taskDao.save(task)
        }
        else throw CustomException("Task title cannot be empty")
    }

    fun updateTask(@PathVariable id : Long, @RequestBody task : Task): Task {
        if(task.title.isNotEmpty()) {
            taskDao.findById(id).takeIf { it.isPresent }?.let {
                return taskDao.save(Task(id, task.title, task.description, task.assigneeId, task.projectId, task.status))
            } ?: throw CustomException("Task does not exist")
        } else throw CustomException("Task title cannot be empty")
    }

    fun deleteTask(@PathVariable id : Long) {
        try {
            taskDao.deleteById(id)
        }catch(e : EmptyResultDataAccessException){
            throw CustomException("Task does not exist")
        }
    }
}
