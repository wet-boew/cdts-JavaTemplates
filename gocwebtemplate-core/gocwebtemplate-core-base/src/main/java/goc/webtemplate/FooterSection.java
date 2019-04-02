package goc.webtemplate;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a footer section, which consists of a group of footer links
 * displayed under a label/name in the application template. 
 */
@SuppressWarnings("serial")
public class FooterSection implements Serializable, IFooterSection {
    
    private String              sectionName;
    private List<FooterLink>    customFooterLinks;
    
    
    public FooterSection() {
        this("", new ArrayList<FooterLink>());
    }
    
    public FooterSection(String sectionName, List<FooterLink> customFooterLinks) {
        this.sectionName = sectionName;
        this.customFooterLinks = customFooterLinks;
    }
    
    
    public String getSectionName() {
        return sectionName;
    }
    
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    
    public List<FooterLink> getCustomFooterLinks() {
        return customFooterLinks;
    }
    
    public void setCustomFooterLinks(List<FooterLink> customFooterLinks) {
        this.customFooterLinks = customFooterLinks;
    }
}
