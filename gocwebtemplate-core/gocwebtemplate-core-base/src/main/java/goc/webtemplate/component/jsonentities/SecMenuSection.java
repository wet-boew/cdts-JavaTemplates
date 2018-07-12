package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.ArrayList;

import goc.webtemplate.Link;
import goc.webtemplate.MenuSection;
import goc.webtemplate.Utility;

import goc.webtemplate.component.BaseUtil;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.secmenu' JavaScript function in the template
 * pages. 
 * 
 * Objects of this class are meant to be children of SecMenu objects (sections property).
 */
public class SecMenuSection implements Serializable {
    private static final long serialVersionUID = 1L;

    private String                  sectionName;
    private String                  sectionLink;
    private Boolean                 newWindow;
    private ArrayList<SecMenuItem>  menuLinks;
    
    public SecMenuSection() {
    }

    public SecMenuSection(String sectionName, String sectionLink, Boolean newWindow, ArrayList<SecMenuItem> menuLinks) {
        this.sectionName = sectionName;
        this.sectionLink = sectionLink;
        this.newWindow = newWindow;
        this.menuLinks = menuLinks;
    }
    
    /**
     * Convenience constructor to initialize an object from a MenuSection
     * object.
     */
    public SecMenuSection(MenuSection menuSection) {
        this.sectionName = menuSection.getName();
        this.sectionLink = null;
        this.newWindow = null;
        this.menuLinks = null;
        
        if (!Utility.isNullOrEmpty(menuSection.getLink())) {
            this.sectionLink = BaseUtil.encodeUrl(menuSection.getLink());
            this.newWindow = menuSection.isOpenInNewWindow();
        }
        
        if (menuSection.getItems() != null && menuSection.getItems().size() > 0) {
            this.menuLinks = new ArrayList<SecMenuItem>(menuSection.getItems().size());
            for (Link link: menuSection.getItems()) this.menuLinks.add(new SecMenuItem(link));
        }
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionLink() {
        return sectionLink;
    }

    public void setSectionLink(String sectionLink) {
        this.sectionLink = sectionLink;
    }

    public Boolean getNewWindow() {
        return newWindow;
    }

    public void setNewWindow(Boolean newWindow) {
        this.newWindow = newWindow;
    }

    public ArrayList<SecMenuItem> getMenuLinks() {
        return menuLinks;
    }

    public void setMenuLinks(ArrayList<SecMenuItem> menuLinks) {
        this.menuLinks = menuLinks;
    }
}
