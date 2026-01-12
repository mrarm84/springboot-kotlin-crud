package com.example.demo.controller

import com.example.demo.model.CustomException
import com.example.demo.model.Equipment
import com.example.demo.service.EquipmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


/**
 * Controller for Equipment
 */
@RestController
class EquipmentController(@Autowired private val equipmentService : EquipmentService) {

    //gets all equipment
    @GetMapping("/equipment")
    fun getAllEquipment() : ResponseEntity<List<Equipment>> =
            ResponseEntity.status(HttpStatus.OK)
                    .body(equipmentService.getAllEquipment())


    //gets the requested equipment
    @GetMapping("equipment/{id}")
    fun getEquipmentById(@PathVariable id : Long) : Equipment =
            equipmentService.getEquipmentById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This equipment does not exist")

    //gets equipment with multiple providers
    @GetMapping("equipment/hasMultipleProvider")
    fun hasMultipleProvider() : ResponseEntity<List<Equipment>> {
        val e : List<Equipment> = equipmentService.hasMultipleProvider()
        if(e.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND,
                "Equipment with multiple providers does not exist")
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(e)
    }

    //creates new equipment
    @PostMapping("/equipment")
    fun saveEquipment(@RequestBody equipment : Equipment) : ResponseEntity<Equipment>   {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(equipmentService.saveEquipment(equipment))
        }catch (e : CustomException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Input is not in valid format")
        }

    }

    //updates existing equipment
    @PutMapping("/equipment/{id}")
    fun updateEquipment(@PathVariable id : Long, @RequestBody equipment : Equipment): ResponseEntity<Equipment> {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(equipmentService.updateEquipment(id,equipment))

        }catch (e : CustomException){
            if(e.message.equals("Equipment does not exist"))
                throw ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Equipment Not Found")
            else
                throw ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Input is not in valid format")
        }

    }

    // deletes existing equipment
    @DeleteMapping("/equipment/{id}")
    fun deleteEquipment(@PathVariable id : Long) : ResponseEntity<Any>{
        try {
            equipmentService.deleteEquipment(id)
        }
        catch (e: Exception) {
            // can handle it by adding to logger
        }
            return ResponseEntity.noContent().build()

    }
}
