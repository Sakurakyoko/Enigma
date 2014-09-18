/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package is202.hrms.web;

//import is202.hrms.ejb.DepartmentEJB;
import is202.hrms.entity.Department;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author evenal
 */
@Named("deplistbean")
@ApplicationScoped
// Application scope is a hack. With smaller scope the data would disappear
public class DepartmentListBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Long,Department> deps;


    public DepartmentListBean() {
        JsfUtils.log("deplistbean.constructor");
        deps = new HashMap<Long,Department>();
    }

    public List<Department> getDepartments() {
        JsfUtils.log("deplistbean.getDepartments");
        return new ArrayList(deps.values());
    }

    public Department find(Object key) {
        JsfUtils.log("deplistbean.find");
        if (key instanceof Long) {
            return deps.get((Long)key);
        }
        else return null;
    }

    public void add(Department dep) {
        JsfUtils.log("deplistbean.add");
        if (dep.getDepNo() == 0) dep.setDepNo(getMaxDepNo() + 1);

        deps.put(dep.getDepNo(), dep);
    }

    public long getMaxDepNo() {
        long max = 0;
        for (Department d : deps.values()) {
            if (d.getDepNo() > max) max = d.getDepNo();
        }
        return max;
    }

    public Department getDefaultDepartment() {
        if (deps.isEmpty()) return null;
        else {
            return deps.values().iterator().next();
        }
    }
}
