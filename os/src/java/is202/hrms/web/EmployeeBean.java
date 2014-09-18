/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package is202.hrms.web;

import is202.hrms.entity.Employee;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean for employee.xhtml
 *
 * @author even
 */
@Named("empbean")
@ConversationScoped
public class EmployeeBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject private EmployeeListBean empListBean;
    @Inject private DepartmentListBean depListBean;
    @Inject private Conversation conv;
    private Employee employee;
    private boolean updating;

    public EmployeeBean() {
    }

    public void setEmpId(long empId) {
        if (conv.isTransient()) {
            conv.begin();
        }
        if (0 < empId) {
            employee = empListBean.find(empId);
            updating = true;
        }
        else {
            employee = new Employee();
            employee.setDepartment(depListBean.getDefaultDepartment());
            updating = false;
        }
    }

    public long getEmpId() {
        if (null == employee) {
            return 0;
        } else {
            return employee.getId();
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public boolean isUpdating() {
        return updating;
    }

    public View saveEmployee() {
        empListBean.add(employee);
        conv.end();
        return View.employees;
    }

}
