package com.example.demo.service

import com.example.demo.dao.VendorDao
import com.example.demo.model.CustomException
import com.example.demo.model.Vendor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Service
class VendorService(@Autowired private val vendorDao : VendorDao,
                    @Autowired private val validateEmailClient : ValidateEmailClient) {

    fun getAllVendors(): List<Vendor> =
            vendorDao.findAll()

    fun getVendorById(@PathVariable id : Long) : Vendor? =
            vendorDao.findById(id).orElse(null)

    fun saveVendor(@RequestBody vendor : Vendor) : Vendor {
        if(vendor.name.isNotEmpty()) {
             if(validateEmailClient.validateEmail(vendor.contactEmail).format_valid)
                return vendorDao.save(vendor)
             else throw CustomException("Email is not valid")
        }
        else throw CustomException("Vendor name cannot be empty")
    }

    fun updateVendor(@PathVariable id : Long, @RequestBody vendor : Vendor): Vendor {
        if(vendor.name.isNotEmpty()) {
             if(validateEmailClient.validateEmail(vendor.contactEmail).format_valid) {
                 vendorDao.findById(id).takeIf { it.isPresent }?.let {
                    return vendorDao.save(Vendor(id, vendor.name, vendor.contactEmail, vendor.phoneNumber, vendor.serviceType))
                 } ?: throw CustomException("Vendor does not exist")
             } else throw CustomException("Email is not valid")
        } else throw CustomException("Vendor name cannot be empty")
    }

    fun deleteVendor(@PathVariable id : Long) {
        try {
            vendorDao.deleteById(id)
        }catch(e : EmptyResultDataAccessException){
            throw CustomException("Vendor does not exist")
        }
    }
}
