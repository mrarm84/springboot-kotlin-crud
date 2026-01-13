package com.example.demo

import com.example.demo.dao.VendorDao
import com.example.demo.model.Vendor
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.assertj.core.api.Assertions.assertThat

@DataJpaTest
class VendorRepositoriesTest @Autowired constructor(
        val vendorDao: VendorDao) {

    @Test
    fun `When save Vendor then it is persisted`() {
        val vend = Vendor(0, "QuickFix", "help@quickfix.com", "555-9999", "Repair")
        vendorDao.save(vend)
        
        val found = vendorDao.findById(vend.id)
        assertThat(found).isPresent
        assertThat(found.get().name).isEqualTo("QuickFix")
    }
}
