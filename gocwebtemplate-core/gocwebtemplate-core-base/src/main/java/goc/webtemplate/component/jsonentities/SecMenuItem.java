package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.ArrayList;

import goc.webtemplate.Link;
import goc.webtemplate.MenuItem;

import goc.webtemplate.component.BaseUtil;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.secmenu' JavaScript function in the template
 * pages. 
 * 
 * Objects of this class are meant to be children of SecMenuSection objects (menuLinks property).
 */
public class SecMenuItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String                      href;
    private String                      text;
    private String                      acronym;
    private Boolean                     newWindow;
    private ArrayList<SecMenuSubItem>   subLinks;
    
    public SecMenuItem() {
    }

    public SecMenuItem(String href, String text, String acronym, Boolean newWindow, ArrayList<SecMenuSubItem> subLinks) {
        this.href = href;
        this.text = text;
        this.acronym = acronym;
        this.newWindow = newWindow;
        this.subLinks = subLinks;
    }

    /**
     * Convenience constructor to initialize an object from a Link
     * object.
     */
    public SecMenuItem(Link link) {
        this.href = BaseUtil.encodeUrl(link.getHref());
        this.text = link.getText();
        this.acronym = null;
        this.newWindow = null;
        this.subLinks = null;
        
        if (link instanceof MenuItem) {
            MenuItem mi = (MenuItem)link; 
            
            this.newWindow = mi.isOpenInNewWindow();
            this.acronym = mi.getAcronym();
            
            if (mi.getSubItems() != null && mi.getSubItems().size() > 0) {
                this.subLinks = new ArrayList<SecMenuSubItem>(mi.getSubItems().size());
                for (MenuItem subItem: mi.getSubItems()) this.subLinks.add(new SecMenuSubItem(subItem));
            }
        }
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getNewWindow() {
        return newWindow;
    }

    public void setNewWindow(Boolean newWindow) {
        this.newWindow = newWindow;
    }

    public ArrayList<SecMenuSubItem> getSubLinks() {
        return subLinks;
    }

    public void setSubLinks(ArrayList<SecMenuSubItem> subLinks) {
        this.subLinks = subLinks;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
