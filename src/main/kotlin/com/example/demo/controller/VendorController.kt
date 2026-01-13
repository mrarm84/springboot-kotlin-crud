package com.example.demo.controller

import com.example.demo.model.CustomException
import com.example.demo.model.Vendor
import com.example.demo.service.VendorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class VendorController(@Autowired private val vendorService : VendorService) {

    @GetMapping("/vendors")
    fun getAllVendors() : ResponseEntity<List<Vendor>> =
            ResponseEntity.status(HttpStatus.OK)
                    .body(vendorService.getAllVendors())

    @GetMapping("/vendors/{id}")
    fun getVendorById(@PathVariable id : Long) : Vendor =
            vendorService.getVendorById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This vendor does not exist")

    @PostMapping("/vendors")
    fun saveVendor(@RequestBody vendor : Vendor) : ResponseEntity<Vendor> {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(vendorService.saveVendor(vendor))
        }catch (e : CustomException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PutMapping("/vendors/{id}")
    fun updateVendor(@PathVariable id : Long, @RequestBody vendor : Vendor): ResponseEntity<Vendor> {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(vendorService.updateVendor(id, vendor))

        }catch (e : CustomException){
            if(e.message.equals("Vendor does not exist"))
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor Not Found")
            else
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @DeleteMapping("/vendors/{id}")
    fun deleteVendor(@PathVariable id : Long) : ResponseEntity<Any>{
        try {
            vendorService.deleteVendor(id)
        }
        catch (e: Exception) {
            // log
        }
        return ResponseEntity.noContent().build()
    }
}
