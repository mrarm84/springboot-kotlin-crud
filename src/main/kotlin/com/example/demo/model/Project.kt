package com.example.demo.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "project")
class Project(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long = 0,
        val name : String = "",
        val startDate : LocalDate = LocalDate.now(),
        val endDate : LocalDate = LocalDate.now(),
        val status : String = ""
)
