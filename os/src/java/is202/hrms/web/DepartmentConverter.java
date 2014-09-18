/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package is202.hrms.web;

// import is202.hrms.ejb.DepartmentEJB;
import is202.hrms.entity.Department;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Even (UiA)
 */
@Named("depconverter") @RequestScoped
@FacesConverter(forClass=Department.class)
public class DepartmentConverter implements Converter {

    @Inject private DepartmentListBean departmentListBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        long depId = Long.parseLong(value);
        Department dep = null;
        if (null == departmentListBean) {
            System.out.println("No EJB!");
            throw new ConverterException("No EJB!");
        }
        dep = departmentListBean.find(depId);
        if (null == dep) {
            System.out.println("No value!");
            throw new ConverterException("Cannot convert \""+value+"\" to Department");
        }
        return dep;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (null != value && value instanceof Department) {
            Department dep = (Department)value;
            return ""+dep.getDepNo();
        }
        throw new ConverterException("Illegal value - must be a Department object");
    }

}
