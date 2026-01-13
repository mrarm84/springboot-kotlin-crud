package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "vendor")
class Vendor(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long = 0,
        val name : String = "",
        val contactEmail : String = "",
        val phoneNumber : String = "",
        val serviceType : String = ""
)
