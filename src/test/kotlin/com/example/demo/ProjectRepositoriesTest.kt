package com.example.demo

import com.example.demo.dao.ProjectDao
import com.example.demo.model.Project
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.assertj.core.api.Assertions.assertThat
import java.time.LocalDate

@DataJpaTest
class ProjectRepositoriesTest @Autowired constructor(
        val projectDao: ProjectDao) {

    @Test
    fun `When save Project then it is persisted`() {
        val proj = Project(0, "Migration", LocalDate.now(), LocalDate.now().plusDays(10), "Pending")
        projectDao.save(proj)
        
        val found = projectDao.findById(proj.id)
        assertThat(found).isPresent
        assertThat(found.get().name).isEqualTo("Migration")
    }
}
