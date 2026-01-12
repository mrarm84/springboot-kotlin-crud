package com.example.demo.model

import javax.persistence.*

/**
 * Model Class for Equipment
 */
@Entity
@Table(name = "equipment")
class Equipment(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long = 0,
        val contactEmail : String = "",
        val name : String = "",
        val provider : String = ""){
}
