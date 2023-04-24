package employeesdepartments.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer id;

    @Column
    private String name;

    @Column
    private LocalDate birthDate;

    @Column
    private LocalDate employmentDate;

    @Column
    private Integer idOfDepartment;

    @Column
    private String jobTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Integer getIdOfDepartment() {
        return idOfDepartment;
    }

    public void setIdOfDepartment(Integer idOfDepartment) {
        this.idOfDepartment = idOfDepartment;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
