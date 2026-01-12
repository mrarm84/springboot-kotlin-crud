package com.example.demo.service

import com.example.demo.dao.EquipmentDao
import com.example.demo.model.CustomException
import com.example.demo.model.Equipment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.CacheEvict

/**
 * Service for Equipment
 */
@Service
class EquipmentService(@Autowired private val equipmentDao : EquipmentDao,
                       @Autowired private val validateEmailClient : ValidateEmailClient) {

    @Cacheable("all_equipment")
    fun getAllEquipment(): List<Equipment> =
            equipmentDao.findAll()

    @Cacheable(value = ["equipment"], key = "#id")
    fun getEquipmentById(@PathVariable id : Long) : Equipment? =
            equipmentDao.findOneById(id)

    fun hasMultipleProvider() : List<Equipment> =
            equipmentDao.hasMultipleProvider()

    // Evict all caches because list changes and potentially individual items exist
    @CacheEvict(value = ["all_equipment", "equipment"], allEntries = true)
    fun saveEquipment(@RequestBody equipment : Equipment) : Equipment  {
        if(isLetters(equipment.name) && isLetters(equipment.provider)){
            if(validateEmailClient.validateEmail(equipment.contactEmail).format_valid)
               return equipmentDao.save(equipment)
            else throw CustomException("Email is not valid")
        }
        else throw CustomException("Name cannot contains non alphabet characters")
    }

    @CacheEvict(value = ["all_equipment", "equipment"], allEntries = true)
    fun updateEquipment(@PathVariable id : Long, @RequestBody equipment : Equipment): Equipment {
        if(isLetters(equipment.name) && isLetters(equipment.provider)) {
            if(validateEmailClient.validateEmail(equipment.contactEmail).format_valid)
                equipmentDao.findOneById(id).takeIf { e -> e != null }?.let {
                    return equipmentDao.save(Equipment(id, equipment.contactEmail, equipment.name, equipment.provider))
                } ?: throw CustomException("Equipment does not exist")
            else throw CustomException("Email is not valid")
        }else throw CustomException("Name cannot contains non alphabet characters")

    }

    @CacheEvict(value = ["all_equipment", "equipment"], allEntries = true)
    fun deleteEquipment(@PathVariable id : Long)  {
        try {
            equipmentDao.deleteById(id)
        }catch(e : EmptyResultDataAccessException){
            throw CustomException("id does not exist")
        }
    }

    fun isLetters(str: String): Boolean {
        return str.all { it.isLetter() }
    }

}
