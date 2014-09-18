package is202.hrms.web;

import is202.hrms.entity.Department;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The backing bean for department.xhtml.
 *
 * @author even
 */
@Named("depbean")
@ConversationScoped
public class DepartmentBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject private Conversation conv;
    private Department department;
    private boolean updating;
    @Inject private DepartmentListBean depListBean;

    public DepartmentBean() {
    }

    /**
     * called when the user selects a department from the list in
     * departments.xhtml.
     */
    public void setDepNo(long depNo) {
        if (conv.isTransient()) {
            conv.begin();
        }

        if (depNo > 0) {
            department = depListBean.find(depNo);
            updating = true;
        } else {
            department = new Department();
            updating = false;
        }
    }

    public boolean isUpdating() {
        return updating;
    }

    public long getDepNo() {
        if (null == department) {
            return 0;
        } else {
            return department.getDepNo();
        }
    }

    public Department getDepartment() {
        return department;
    }

    public View saveDepartment() {
        depListBean.add(department);
        conv.end();
        return View.departments;
    }

    public View deleteDepartment() {
        //depEjb.delete(department);
        conv.end();

        return View.departments;
    }
}
