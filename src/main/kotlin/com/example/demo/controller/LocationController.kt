package com.example.demo.controller

import com.example.demo.model.CustomException
import com.example.demo.model.Location
import com.example.demo.service.LocationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class LocationController(@Autowired private val locationService : LocationService) {

    @GetMapping("/locations")
    fun getAllLocations() : ResponseEntity<List<Location>> =
            ResponseEntity.status(HttpStatus.OK)
                    .body(locationService.getAllLocations())

    @GetMapping("/locations/{id}")
    fun getLocationById(@PathVariable id : Long) : Location =
            locationService.getLocationById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This location does not exist")

    @PostMapping("/locations")
    fun saveLocation(@RequestBody location : Location) : ResponseEntity<Location> {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(locationService.saveLocation(location))
        }catch (e : CustomException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PutMapping("/locations/{id}")
    fun updateLocation(@PathVariable id : Long, @RequestBody location : Location): ResponseEntity<Location> {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(locationService.updateLocation(id, location))

        }catch (e : CustomException){
            if(e.message.equals("Location does not exist"))
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Location Not Found")
            else
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @DeleteMapping("/locations/{id}")
    fun deleteLocation(@PathVariable id : Long) : ResponseEntity<Any>{
        try {
            locationService.deleteLocation(id)
        }
        catch (e: Exception) {
            // log
        }
        return ResponseEntity.noContent().build()
    }
}
