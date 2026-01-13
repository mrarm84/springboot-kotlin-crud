package com.example.demo.dao

import com.example.demo.model.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentDao : JpaRepository<Department, Long> {
    fun findByName(name: String): List<Department>
}
