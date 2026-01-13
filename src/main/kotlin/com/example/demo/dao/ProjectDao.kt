package com.example.demo.dao

import com.example.demo.model.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectDao : JpaRepository<Project, Long>
