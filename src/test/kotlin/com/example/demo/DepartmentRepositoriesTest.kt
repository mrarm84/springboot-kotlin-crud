package com.example.demo

import com.example.demo.dao.DepartmentDao
import com.example.demo.model.Department
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.assertj.core.api.Assertions.assertThat

@DataJpaTest
class DepartmentRepositoriesTest @Autowired constructor(
        val departmentDao: DepartmentDao) {

    @Test
    fun `When findByName then return Department list`() {
        val dept = Department(0, "Marketing", "MKT-001", 200)
        departmentDao.save(dept)
        
        val found = departmentDao.findByName("Marketing")
        assertThat(found).isNotEmpty
        assertThat(found[0].costCenter).isEqualTo("MKT-001")
    }
}
