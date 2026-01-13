package com.example.demo

import com.example.demo.dao.EquipmentDao
import com.example.demo.dao.DepartmentDao
import com.example.demo.model.Equipment
import com.example.demo.model.Department
import com.example.demo.dao.ProjectDao
import com.example.demo.model.Project
import com.example.demo.dao.TaskDao
import com.example.demo.model.Task
import com.example.demo.dao.LocationDao
import com.example.demo.model.Location
import com.example.demo.dao.VendorDao
import com.example.demo.model.Vendor
import java.time.LocalDate
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val equipmentDao: EquipmentDao,
    private val departmentDao: DepartmentDao,
    private val projectDao: ProjectDao,
    private val taskDao: TaskDao,
    private val locationDao: LocationDao,
    private val vendorDao: VendorDao
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val equipment1 = Equipment(0, "support@cat.com", "Excavator", "Caterpillar")
        val equipment2 = Equipment(0, "sales@johndeere.com", "Tractor", "John Deere")
        val equipment3 = Equipment(0, "info@bosch.com", "Drill", "Bosch")

        equipmentDao.save(equipment1)
        equipmentDao.save(equipment2)
        equipmentDao.save(equipment3)

        println("Equipment database populated.")

        val dept1 = Department(0, "Engineering", "ENG-001", 101)
        val dept2 = Department(0, "Human Resources", "HR-002", 102)
        val dept3 = Department(0, "Sales", "SAL-003", 103)

        departmentDao.save(dept1)
        departmentDao.save(dept2)
        departmentDao.save(dept3)

        println("Department database populated.")

        val proj1 = Project(0, "Website Redesign", LocalDate.now(), LocalDate.now().plusMonths(3), "In Progress")
        val proj2 = Project(0, "Mobile App", LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(6), "Planned")
        
        projectDao.save(proj1)
        projectDao.save(proj2)

        println("Project database populated.")

        val task1 = Task(0, "Design Mockups", "Create UI mockups for new site", 1, 1, "In Progress")
        val task2 = Task(0, "DB Migrations", "Write Liquibase scripts", 2, 1, "Pending")
        
        taskDao.save(task1)
        taskDao.save(task2)

        println("Task database populated.")

        val loc1 = Location(0, "123 Main St", "New York", "USA", 100)
        val loc2 = Location(0, "456 High St", "London", "UK", 50)
        
        locationDao.save(loc1)
        locationDao.save(loc2)

        println("Location database populated.")

        val vend1 = Vendor(0, "ABC Supply", "contact@abc.com", "555-0101", "Construction")
        val vend2 = Vendor(0, "Tech Solutions", "sales@techsol.com", "555-0202", "IT")
        
        vendorDao.save(vend1)
        vendorDao.save(vend2)

        println("Vendor database populated.")
    }
}
