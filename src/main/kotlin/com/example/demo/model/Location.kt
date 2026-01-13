package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "location")
class Location(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long = 0,
        val address : String = "",
        val city : String = "",
        val country : String = "",
        val capacity : Int = 0
)
