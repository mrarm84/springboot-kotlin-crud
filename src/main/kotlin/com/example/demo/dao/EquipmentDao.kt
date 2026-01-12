package com.example.demo.dao

import com.example.demo.model.Equipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EquipmentDao : JpaRepository<Equipment, Long> {
    fun findOneById(id: Long): Equipment?

    @Query("SELECT e FROM Equipment e WHERE e.provider LIKE '% %'")
    fun hasMultipleProvider(): List<Equipment>

}
