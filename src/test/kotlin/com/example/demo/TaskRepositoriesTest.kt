package com.example.demo

import com.example.demo.dao.TaskDao
import com.example.demo.model.Task
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.assertj.core.api.Assertions.assertThat

@DataJpaTest
class TaskRepositoriesTest @Autowired constructor(
        val taskDao: TaskDao) {

    @Test
    fun `When save Task then it is persisted`() {
        val task = Task(0, "Fix Bug", "Fix NPE in service", 3, 2, "Open")
        taskDao.save(task)
        
        val found = taskDao.findById(task.id)
        assertThat(found).isPresent
        assertThat(found.get().title).isEqualTo("Fix Bug")
    }
}
