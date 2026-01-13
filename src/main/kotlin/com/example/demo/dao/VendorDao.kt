package com.example.demo.dao

import com.example.demo.model.Vendor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendorDao : JpaRepository<Vendor, Long>
