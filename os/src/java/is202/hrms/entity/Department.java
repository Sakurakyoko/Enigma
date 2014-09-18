package is202.hrms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Department implements Serializable {

    private static final long serialVersionUID = 42L;
    @Id
    @GeneratedValue
    private long depNo;

    // The name must not be null, and contain at least one character
    @NotNull @Size(min=1)
    private String name;

    /** This should have been a list. java.util.Set is used here to
     * prevent multiple registration of the same employee. In the
     * final version the db will take care of that, so it will be
     * safe to use List again. */
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    public Department() {
        employees = new HashSet<Employee>();
    }

    public long getDepNo() {
        return depNo;
    }

    public void setDepNo(long depNo) {
        this.depNo = depNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }


    public List<Employee> getEmployees() {
        return new ArrayList<Employee>(employees);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.depNo ^ (this.depNo >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Department other = (Department) obj;
        if (depNo != other.depNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + depNo + " " + name;
    }
}
