package com.example.demo

import com.example.demo.dao.EquipmentDao
import com.example.demo.model.Equipment
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.assertj.core.api.Assertions.assertThat

/**
 * Test for EquipmentDao
 */
@DataJpaTest
class EquipmentRepositoriesTest @Autowired constructor(
        val equipmentRepository: EquipmentDao) {

    @Test
    fun `When findOneById then return Equipment`() {

        val equipment = Equipment(0, "contact@provider.com", "Drill", "Best Tools")
        val savedEquipment = equipmentRepository.save(equipment)
        println("loaded equipment $savedEquipment")
        val found = equipmentRepository.findOneById(savedEquipment.id)
        println("found equipment $found")
        assertThat(found?.contactEmail).isEqualTo(equipment.contactEmail)
        assertThat(found?.name).isEqualTo(equipment.name)
        assertThat(found?.provider).isEqualTo(equipment.provider)
    }

}
