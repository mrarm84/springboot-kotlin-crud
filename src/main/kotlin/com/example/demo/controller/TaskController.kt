package com.example.demo.controller

import com.example.demo.model.CustomException
import com.example.demo.model.Task
import com.example.demo.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class TaskController(@Autowired private val taskService : TaskService) {

    @GetMapping("/tasks")
    fun getAllTasks() : ResponseEntity<List<Task>> =
            ResponseEntity.status(HttpStatus.OK)
                    .body(taskService.getAllTasks())

    @GetMapping("/tasks/{id}")
    fun getTaskById(@PathVariable id : Long) : Task =
            taskService.getTaskById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This task does not exist")

    @PostMapping("/tasks")
    fun saveTask(@RequestBody task : Task) : ResponseEntity<Task> {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(taskService.saveTask(task))
        }catch (e : CustomException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PutMapping("/tasks/{id}")
    fun updateTask(@PathVariable id : Long, @RequestBody task : Task): ResponseEntity<Task> {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(taskService.updateTask(id, task))

        }catch (e : CustomException){
            if(e.message.equals("Task does not exist"))
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found")
            else
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @DeleteMapping("/tasks/{id}")
    fun deleteTask(@PathVariable id : Long) : ResponseEntity<Any>{
        try {
            taskService.deleteTask(id)
        }
        catch (e: Exception) {
            // log
        }
        return ResponseEntity.noContent().build()
    }
}
