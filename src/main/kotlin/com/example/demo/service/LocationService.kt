package com.example.demo.service

import com.example.demo.dao.LocationDao
import com.example.demo.model.CustomException
import com.example.demo.model.Location
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Service
class LocationService(@Autowired private val locationDao : LocationDao) {

    fun getAllLocations(): List<Location> =
            locationDao.findAll()

    fun getLocationById(@PathVariable id : Long) : Location? =
            locationDao.findById(id).orElse(null)

    fun saveLocation(@RequestBody location : Location) : Location {
        if(location.city.isNotEmpty()) {
            return locationDao.save(location)
        }
        else throw CustomException("City cannot be empty")
    }

    fun updateLocation(@PathVariable id : Long, @RequestBody location : Location): Location {
        if(location.city.isNotEmpty()) {
            locationDao.findById(id).takeIf { it.isPresent }?.let {
                return locationDao.save(Location(id, location.address, location.city, location.country, location.capacity))
            } ?: throw CustomException("Location does not exist")
        } else throw CustomException("City cannot be empty")
    }

    fun deleteLocation(@PathVariable id : Long) {
        try {
            locationDao.deleteById(id)
        }catch(e : EmptyResultDataAccessException){
            throw CustomException("Location does not exist")
        }
    }
}
