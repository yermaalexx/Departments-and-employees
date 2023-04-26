package employeesdepartments.entities;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String additionalInformation;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
