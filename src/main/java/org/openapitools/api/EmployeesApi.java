/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import employeesdepartments.models.EmployeeDTO;
import employeesdepartments.models.EmployeeListDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-23T17:22:50.332529600+03:00[Europe/Kiev]")
@Validated
@Tag(name = "employees", description = "actions on employee records")
public interface EmployeesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /employees : Add a new employee
     *
     * @param employeeDTO  (required)
     * @return Successful operation: new employee added (status code 200)
     */
    @Operation(
        operationId = "addEmployee",
        summary = "Add a new employee",
        tags = { "employees" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation: new employee added", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/employees",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<EmployeeDTO> addEmployee(
        @Parameter(name = "EmployeeDTO", description = "", required = true) @Valid @RequestBody EmployeeDTO employeeDTO
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"employment_date\" : \"2020-03-30T00:00:00.000+00:00\", \"birth_date\" : \"2000-01-30T00:00:00.000+00:00\", \"name\" : \"Mask Ilon\", \"id\" : 12, \"id_of_department\" : 3, \"job_title\" : \"Manager\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /employees/{employeeId} : Delete employee by employeeID
     *
     * @param employeeId ID of employee to delete (required)
     * @return Successful operation: employee deleted (status code 200)
     *         or Employee id not found (status code 404)
     */
    @Operation(
        operationId = "deleteEmployee",
        summary = "Delete employee by employeeID",
        tags = { "employees" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation: employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee id not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/employees/{employeeId}"
    )
    default ResponseEntity<Void> deleteEmployee(
        @Parameter(name = "employeeId", description = "ID of employee to delete", required = true, in = ParameterIn.PATH) @PathVariable("employeeId") Integer employeeId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PATCH /employees/{employeeId}/{newDepartmentId} : Set new department id for given employee
     *
     * @param employeeId ID of employee to edit department (required)
     * @param newDepartmentId ID of department to set (required)
     * @return Successful operation: department of employee edited (status code 200)
     *         or Employee id not found (status code 404)
     */
    @Operation(
        operationId = "editDepartmentOfEmployee",
        summary = "Set new department id for given employee",
        tags = { "employees" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation: department of employee edited", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Employee id not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/employees/{employeeId}/{newDepartmentId}",
        produces = { "application/json" }
    )
    default ResponseEntity<EmployeeDTO> editDepartmentOfEmployee(
        @Parameter(name = "employeeId", description = "ID of employee to edit department", required = true, in = ParameterIn.PATH) @PathVariable("employeeId") Integer employeeId,
        @Parameter(name = "newDepartmentId", description = "ID of department to set", required = true, in = ParameterIn.PATH) @PathVariable("newDepartmentId") Integer newDepartmentId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"employment_date\" : \"2020-03-30T00:00:00.000+00:00\", \"birth_date\" : \"2000-01-30T00:00:00.000+00:00\", \"name\" : \"Mask Ilon\", \"id\" : 12, \"id_of_department\" : 3, \"job_title\" : \"Manager\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /employees : Edit employee by employeeID in DTO
     *
     * @param employeeDTO  (required)
     * @return Successful operation: employee edited (status code 200)
     *         or Employee id not found (status code 404)
     */
    @Operation(
        operationId = "editEmployee",
        summary = "Edit employee by employeeID in DTO",
        tags = { "employees" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation: employee edited", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Employee id not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/employees",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<EmployeeDTO> editEmployee(
        @Parameter(name = "EmployeeDTO", description = "", required = true) @Valid @RequestBody EmployeeDTO employeeDTO
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"employment_date\" : \"2020-03-30T00:00:00.000+00:00\", \"birth_date\" : \"2000-01-30T00:00:00.000+00:00\", \"name\" : \"Mask Ilon\", \"id\" : 12, \"id_of_department\" : 3, \"job_title\" : \"Manager\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /employees/{employeeId} : Get employee by employeeID
     *
     * @param employeeId ID of employee to get (required)
     * @return Successful operation: employee data received (status code 200)
     *         or Employee id not found (status code 404)
     */
    @Operation(
        operationId = "getEmployee",
        summary = "Get employee by employeeID",
        tags = { "employees" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation: employee data received", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Employee id not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/employees/{employeeId}",
        produces = { "application/json" }
    )
    default ResponseEntity<EmployeeDTO> getEmployee(
        @Parameter(name = "employeeId", description = "ID of employee to get", required = true, in = ParameterIn.PATH) @PathVariable("employeeId") Integer employeeId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"employment_date\" : \"2020-03-30T00:00:00.000+00:00\", \"birth_date\" : \"2000-01-30T00:00:00.000+00:00\", \"name\" : \"Mask Ilon\", \"id\" : 12, \"id_of_department\" : 3, \"job_title\" : \"Manager\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /employees/all : Get list of all employees
     *
     * @return Successful operation: list of employees received (status code 200)
     */
    @Operation(
        operationId = "getListOfEmployees",
        summary = "Get list of all employees",
        tags = { "employees" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation: list of employees received", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeListDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/employees/all",
        produces = { "application/json" }
    )
    default ResponseEntity<EmployeeListDTO> getListOfEmployees(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"employees\" : [ { \"employment_date\" : \"2020-03-30T00:00:00.000+00:00\", \"birth_date\" : \"2000-01-30T00:00:00.000+00:00\", \"name\" : \"Mask Ilon\", \"id\" : 12, \"id_of_department\" : 3, \"job_title\" : \"Manager\" }, { \"employment_date\" : \"2020-03-30T00:00:00.000+00:00\", \"birth_date\" : \"2000-01-30T00:00:00.000+00:00\", \"name\" : \"Mask Ilon\", \"id\" : 12, \"id_of_department\" : 3, \"job_title\" : \"Manager\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
