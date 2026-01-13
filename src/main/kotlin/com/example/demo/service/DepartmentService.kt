package com.example.demo.service

import com.example.demo.dao.DepartmentDao
import com.example.demo.model.CustomException
import com.example.demo.model.Department
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Service
class DepartmentService(@Autowired private val departmentDao : DepartmentDao) {

    fun getAllDepartments(): List<Department> =
            departmentDao.findAll()

    fun getDepartmentById(@PathVariable id : Long) : Department? =
            departmentDao.findById(id).orElse(null)

    fun saveDepartment(@RequestBody department : Department) : Department {
        if(department.name.isNotEmpty()) {
            return departmentDao.save(department)
        }
        else throw CustomException("Department name cannot be empty")
    }

    fun updateDepartment(@PathVariable id : Long, @RequestBody department : Department): Department {
        if(department.name.isNotEmpty()) {
            departmentDao.findById(id).takeIf { it.isPresent }?.let {
                return departmentDao.save(Department(id, department.name, department.costCenter, department.managerId))
            } ?: throw CustomException("Department does not exist")
        } else throw CustomException("Department name cannot be empty")
    }

    fun deleteDepartment(@PathVariable id : Long) {
        try {
            departmentDao.deleteById(id)
        }catch(e : EmptyResultDataAccessException){
            throw CustomException("Department does not exist")
        }
    }
}
