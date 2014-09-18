package is202.hrms.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Common solutions to common problems
 * @author even
 */
public class JsfUtils {

    /**
     * Display an error message.
     * @param id - the id of the component msg is associated to
     * @param msg - the message
     */
    public static void addMessage(String id, String msg) {
        FacesContext ctxt = FacesContext.getCurrentInstance();
        ctxt.addMessage(id, new FacesMessage(msg));
    }

    /**
     * Get the pagename (view id) of the current page.
     * @return
     */
    public static String getCurrentViewId() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getViewRoot().getViewId();
    }


    public static void log(String msg) {
        FacesContext ctxt = FacesContext.getCurrentInstance();
        ctxt.getExternalContext().log(msg);
    }
}
