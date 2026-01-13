package com.example.demo.dao

import com.example.demo.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskDao : JpaRepository<Task, Long>
