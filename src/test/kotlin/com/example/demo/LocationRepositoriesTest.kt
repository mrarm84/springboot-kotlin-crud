package com.example.demo

import com.example.demo.dao.LocationDao
import com.example.demo.model.Location
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.assertj.core.api.Assertions.assertThat

@DataJpaTest
class LocationRepositoriesTest @Autowired constructor(
        val locationDao: LocationDao) {

    @Test
    fun `When save Location then it is persisted`() {
        val loc = Location(0, "789 Broadway", "San Francisco", "USA", 200)
        locationDao.save(loc)
        
        val found = locationDao.findByCity("San Francisco")
        assertThat(found).isNotEmpty
        assertThat(found[0].address).isEqualTo("789 Broadway")
    }
}
