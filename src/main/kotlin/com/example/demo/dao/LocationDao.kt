package com.example.demo.dao

import com.example.demo.model.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationDao : JpaRepository<Location, Long> {
    fun findByCity(city: String): List<Location>
}
