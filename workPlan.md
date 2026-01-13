# Work Plan: Expanding CRUD Functionalities

This plan outlines the addition of 5 new distinct entities to the Spring Boot Kotlin application, mirroring the structure of the existing `Employee` and `Equipment` modules.

## Entities to Add
1. **Department** - To manage organizational units.
2. **Project** - To track ongoing initiatives.
3. **Task** - To manage specific work items within projects.
4. **Location** - To track physical office locations or sites.
5. **Vendor** - To manage suppliers for equipment/services.

---

## Detailed Implementation Steps

### 1. Department CRUD
- [ ] **Model**: Create `Department` entity (fields: id, name, costCenter, managerId).
- [ ] **Repository**: Create `DepartmentDao` with standard CRUD and a custom query (e.g., `findByName`).
- [ ] **Service**: Create `DepartmentService` with validation logic (e.g., unique name check).
- [ ] **Controller**: Create `DepartmentController` with endpoints:
    - GET `/departments`
    - GET `/departments/{id}`
    - POST `/departments`
    - PUT `/departments/{id}`
    - DELETE `/departments/{id}`
- [ ] **Tests**: Add unit tests for Service and Controller.
- [ ] **Data Seed**: Update `DataLoader` to add initial departments.

### 2. Project CRUD
- [ ] **Model**: Create `Project` entity (fields: id, name, startDate, endDate, status).
- [ ] **Repository**: Create `ProjectDao` with standard CRUD.
- [ ] **Service**: Create `ProjectService` with validation (e.g., endDate > startDate).
- [ ] **Controller**: Create `ProjectController` with standard CRUD endpoints.
- [ ] **Tests**: Add unit tests for Service and Controller.
- [ ] **Data Seed**: Update `DataLoader` to add sample projects.

### 3. Task CRUD
- [ ] **Model**: Create `Task` entity (fields: id, title, description, assigneeId, projectId, status).
- [ ] **Repository**: Create `TaskDao` with standard CRUD.
- [ ] **Service**: Create `TaskService` with validation.
- [ ] **Controller**: Create `TaskController` with standard CRUD endpoints.
- [ ] **Tests**: Add unit tests for Service and Controller.
- [ ] **Data Seed**: Update `DataLoader` to add sample tasks.

### 4. Location CRUD
- [ ] **Model**: Create `Location` entity (fields: id, address, city, country, capacity).
- [ ] **Repository**: Create `LocationDao` with regular CRUD and find by city.
- [ ] **Service**: Create `LocationService` with validation.
- [ ] **Controller**: Create `LocationController` with standard CRUD endpoints.
- [ ] **Tests**: Add unit tests for Service and Controller.
- [ ] **Data Seed**: Update `DataLoader` to add initial locations.

### 5. Vendor CRUD
- [ ] **Model**: Create `Vendor` entity (fields: id, name, contactEmail, phoneNumber, serviceType).
- [ ] **Repository**: Create `VendorDao` with standard CRUD.
- [ ] **Service**: Create `VendorService` with email validation (reusing `ValidateEmailClient` if applicable).
- [ ] **Controller**: Create `VendorController` with standard CRUD endpoints.
- [ ] **Tests**: Add unit tests for Service and Controller.
- [ ] **Data Seed**: Update `DataLoader` to add sample vendors.
