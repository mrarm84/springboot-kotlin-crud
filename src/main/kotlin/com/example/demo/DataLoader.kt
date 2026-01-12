package com.example.demo

import com.example.demo.dao.EquipmentDao
import com.example.demo.model.Equipment
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class DataLoader(private val equipmentDao: EquipmentDao) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val equipment1 = Equipment(0, "support@cat.com", "Excavator", "Caterpillar")
        val equipment2 = Equipment(0, "sales@johndeere.com", "Tractor", "John Deere")
        val equipment3 = Equipment(0, "info@bosch.com", "Drill", "Bosch")

        equipmentDao.save(equipment1)
        equipmentDao.save(equipment2)
        equipmentDao.save(equipment3)

        println("Equipment database populated.")
    }
}
