# Registration of Employees and Departments (v. 1.0.1)
***
### REST API application that allows you to add, edit, delete and get *employee and department* data
***
## Setup Instructions
### Running Locally
1. Clone the repository:
   ```sh
   git clone https://github.com/yermaalexx/Departments-and-employees.git
   cd departments-and-employees
   ```
2. Configure environment variables in `.env` 
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
4. Access the application at `http://localhost:8080`

### Running with Docker
1. Build and run using Docker Compose:
   ```sh
   docker-compose up --build
   ```
2. The application will be available at `http://localhost:8080`

## CI/CD Pipeline

A CI/CD pipeline using GitHub Actions is implemented for automated testing and Docker image creation. The pipeline triggers on pushes and pull requests to the `master` branch and includes two jobs:

- **Build & Test**: Runs tests with Maven and caches dependencies.

- **Docker Image Build & Push**: Creates a Docker image and pushes it to Docker Hub.

***

### Entities:
#### Employee entity
- id - *automatically generated employee ID*, UUID, example: 1bf10eeb-7105-4102-9c50-00d9f880651e
- name - *employee name*, string, example: Musk Ilon
- birthDate - *employee birth date*, example: 1999-03-17
- employmentDate - *employment date of the employee*, example: 2020-04-15
- idOfDepartment - *ID of department of the employee*, UUID, example: 86c6ff27-a386-4479-8cec-cfde91cb9474
- jobTitle - *employee job title*, string, example: Manager

#### Department entity
- id - *automatically generated department ID*, UUID, example: 86c6ff27-a386-4479-8cec-cfde91cb9474
- name - *department name*, string, example: Development department
- description - *brief description of the department*, string, example: Department is responsible for the acquisition of funds
- additionalInformation - *some additional information about the department*, string, example: Location: NYC
***
### REST API:
******
******
- #### Add employee
##### Request: `POST /employees`

Add new employee, Request body with name of employee required, ID is generated automatically, birthDate must be over 18 years ago, idOfDepartment must match the existing department (otherwise it will be setted to null)

Request body:
`{"name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}`

##### Response: 

Status: 201 CREATED,    Response body: `{"id": "1bf10eeb-7105-4102-9c50-00d9f880651e", "name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}`

Status: 406 NOT ACCEPTABLE,  Response body: `{"violations": [{"fieldName": "addEmployee.employeeDTO.name", "message": "Name of employee must not be empty."}, {"fieldName": "addEmployee.employeeDTO.birthDate", "message": "Employee must be over minimum age."}]}`
******
- #### Edit employee
##### Request: `PUT /employees`

Edit an existing employee, Request body with ID of employee required, birthDate must be over 18 years ago, idOfDepartment must match the existing department (otherwise idOfDepartment will not be changed)

Request body:
`{"id": "1bf10eeb-7105-4102-9c50-00d9f880651e", "name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}`

##### Response: 

Status: 202 ACCEPTED,    Response body: `{"id": "1bf10eeb-7105-4102-9c50-00d9f880651e", "name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}`

Status: 404 NOT FOUND,  Response body: `{"message": "No employee with this ID."}`
******
- #### Set new ID of department for the employee
##### Request: `PATCH /employees/{employeeId}/{newDepartmentId}`

Set new idOfDepartment for existing employee, ID's of employee and department in path required, if newDepartmentId = 0-0-0-0-0, then null will be setted

##### Response:

Status: 202 ACCEPTED,    Response body: `{"id": "1bf10eeb-7105-4102-9c50-00d9f880651e", "name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}`

Status: 404 NOT FOUND,  Response body: `{"message": "No employee with this ID."}`

Status: 404 NOT FOUND,  Response body: `{"message": "No department with this ID."}`
******
- #### Delete employee
##### Request: `DELETE /employees/{employeeId}`

Delete an existing employee, ID of employee in path required

##### Response:

Status: 204 NO CONTENT

Status: 404 NOT FOUND,  Response body: `{"message": "No employee with this ID."}`
******
- #### Get employee
##### Request: `GET /employees/{employeeId}`

Get an existing employee, ID of employee in path required

##### Response:

Status: 200 OK,    Response body: `{"id": "1bf10eeb-7105-4102-9c50-00d9f880651e", "name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}`

Status: 404 NOT FOUND,  Response body: `{"message": "No employee with this ID."}`
******
- #### Get all employees
##### Request: `GET /employees/all`

Get list of all employees

##### Response:

Status: 200 OK,    Response body: `[{"id": "1bf10eeb-7105-4102-9c50-00d9f880651e", "name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}]`

******
******
- #### Add department
##### Request: `POST /departments`

Add new department, Request body with name of department required, ID is generated automatically

Request body:
`{"name": "Development department", "description": "Department is responsible for the acquisition of funds", "additionalInformation": "Location: NYC"}`

##### Response:

Status: 201 CREATED,    Response body: `{"id": "86c6ff27-a386-4479-8cec-cfde91cb9474", "name": "Development department", "description": "Department is responsible for the acquisition of funds", "additionalInformation": "Location: NYC"}`

Status: 406 NOT ACCEPTABLE,  Response body: `{"violations": [{"fieldName": "addDepartment.departmentDTO.name", "message": "Name of department must not be empty."}]}`
******
- #### Edit department
##### Request: `PUT /departments`

Edit an existing department, Request body with ID of department required

Request body:
`{"id": "86c6ff27-a386-4479-8cec-cfde91cb9474", "name": "Development department", "description": "Department is responsible for the acquisition of funds", "additionalInformation": "Location: NYC"}`

##### Response:

Status: 202 ACCEPTED,    Response body: `{"id": "86c6ff27-a386-4479-8cec-cfde91cb9474", "name": "Development department", "description": "Department is responsible for the acquisition of funds", "additionalInformation": "Location: NYC"}`

Status: 404 NOT FOUND,  Response body: `{"message": "No department with this ID."}`
******
- #### Delete department
##### Request: `DELETE /departments/{departmentId}`

Delete an existing department, ID of department in path required

##### Response:

Status: 204 NO CONTENT

Status: 404 NOT FOUND,  Response body: `{"message": "No department with this ID."}`
******
- #### Get department
##### Request: `GET /departments/{departmentId}`

Get an existing department with its list of employees, ID of department in path required

##### Response:

Status: 200 OK,    Response body: `{"id": "86c6ff27-a386-4479-8cec-cfde91cb9474", "name": "Development department", "description": "Department is responsible for the acquisition of funds", "additionalInformation": "Location: NYC", "listOfEmployees": [{"id": "1bf10eeb-7105-4102-9c50-00d9f880651e", "name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}]}`

Status: 404 NOT FOUND,  Response body: `{"message": "No department with this ID."}`
******
- #### Get list of employees
##### Request: `GET /departments/{departmentId}/list`

Get list of employees in this department, ID of department in path required

##### Response:

Status: 200 OK,    Response body: `[{"id": "1bf10eeb-7105-4102-9c50-00d9f880651e", "name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}]`

Status: 404 NOT FOUND,  Response body: `{"message": "No department with this ID."}`
******
- #### Get all departments
##### Request: `GET /departments/all`

Get all departments with their lists of employees

##### Response:

Status: 200 OK,    Response body: `[{"id": "86c6ff27-a386-4479-8cec-cfde91cb9474", "name": "Development department", "description": "Department is responsible for the acquisition of funds", "additionalInformation": "Location: NYC", "listOfEmployees": [{"id": "1bf10eeb-7105-4102-9c50-00d9f880651e", "name": "Musk Ilon", "birthDate": "1999-03-17", "employmentDate": "2020-04-15", "idOfDepartment": "86c6ff27-a386-4479-8cec-cfde91cb9474", "jobTitle": "Manager"}]}]`

******








