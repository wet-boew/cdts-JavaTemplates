package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import goc.webtemplate.MenuItem;
import goc.webtemplate.component.BaseUtil;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.secmenu' JavaScript function in the template
 * pages. 
 * 
 * Objects of this class are meant to be children of SecMenuItem objects (subLinks property).
 * 
 * They are basically the same as SecMenuItem, with different names for the properties.
 */
public class SecMenuSubItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String  subhref;
    private String  subtext;
    private String  acronym;
    private boolean newWindow;
    
    public SecMenuSubItem() {
    }

    public SecMenuSubItem(String subhref, String subtext, String acronym, boolean newWindow) {
        this.subhref = subhref;
        this.subtext = subtext;
        this.acronym = acronym;
        this.newWindow = newWindow;
    }

    /**
     * Convenience constructor to initialize an object from a MenuItem
     * object.
     */
    public SecMenuSubItem(MenuItem menuItem) {
        this.subhref = BaseUtil.encodeUrl(menuItem.getHref());
        this.subtext = menuItem.getText();
        this.acronym = menuItem.getAcronym();
        this.newWindow = menuItem.isOpenInNewWindow();
    }

    public String getSubhref() {
        return subhref;
    }

    public void setSubhref(String subhref) {
        this.subhref = subhref;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public boolean isNewWindow() {
        return newWindow;
    }

    public void setNewWindow(boolean newWindow) {
        this.newWindow = newWindow;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
