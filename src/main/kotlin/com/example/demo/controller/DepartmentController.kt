package com.example.demo.controller

import com.example.demo.model.CustomException
import com.example.demo.model.Department
import com.example.demo.service.DepartmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class DepartmentController(@Autowired private val departmentService : DepartmentService) {

    @GetMapping("/departments")
    fun getAllDepartments() : ResponseEntity<List<Department>> =
            ResponseEntity.status(HttpStatus.OK)
                    .body(departmentService.getAllDepartments())

    @GetMapping("/departments/{id}")
    fun getDepartmentById(@PathVariable id : Long) : Department =
            departmentService.getDepartmentById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This department does not exist")

    @PostMapping("/departments")
    fun saveDepartment(@RequestBody department : Department) : ResponseEntity<Department> {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(departmentService.saveDepartment(department))
        }catch (e : CustomException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Input is not in valid format")
        }
    }

    @PutMapping("/departments/{id}")
    fun updateDepartment(@PathVariable id : Long, @RequestBody department : Department): ResponseEntity<Department> {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(departmentService.updateDepartment(id, department))

        }catch (e : CustomException){
            if(e.message.equals("Department does not exist"))
                throw ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Department Not Found")
            else
                throw ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Input is not in valid format")
        }
    }

    @DeleteMapping("/departments/{id}")
    fun deleteDepartment(@PathVariable id : Long) : ResponseEntity<Any>{
        try {
            departmentService.deleteDepartment(id)
        }
        catch (e: Exception) {
            // can handle it by adding to logger
        }
            return ResponseEntity.noContent().build()
    }
}
