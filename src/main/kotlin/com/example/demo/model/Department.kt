package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "department")
class Department(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long = 0,
        val name : String = "",
        val costCenter : String = "",
        val managerId : Long = 0
)
