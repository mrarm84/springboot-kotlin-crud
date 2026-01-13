package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "task")
class Task(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long = 0,
        val title : String = "",
        val description : String = "",
        val assigneeId : Long = 0,
        val projectId : Long = 0,
        val status : String = "Pending"
)
