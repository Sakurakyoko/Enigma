/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package is202.hrms.web;

//import is202.hrms.ejb.EmployeeEJB;
import is202.hrms.entity.Employee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Backing bean for employee.xhtml
 *
 * @author even
 */
@Named("emplistbean")
@ApplicationScoped
public class EmployeeListBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private long nextEmpId = 1;
    private Map<Long,Employee> emps;

    public EmployeeListBean() {
        emps = new HashMap<Long,Employee>();
    }

    public List<Employee> getEmployees() {
        return  new ArrayList<Employee>(emps.values());
    }


    public Employee find(long empid) {
        return emps.get(empid);
    }
    
    
    public void add(Employee emp) {
        if (0 == emp.getId()) {
            emp.setId(nextEmpId);
            nextEmpId++;
            emps.put(emp.getId(), emp);
        }
        else {
            // ???
        }
    }
}
