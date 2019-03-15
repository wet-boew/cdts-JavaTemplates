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
    
    private String              name;
    private List<FooterLink>    customFooterLinks;
    
    
    public FooterSection() {
        this("", new ArrayList<FooterLink>());
    }
    
    public FooterSection(String name, List<FooterLink> customFooterLinks) {
        this.name = name;
        this.customFooterLinks = customFooterLinks;
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<FooterLink> getCustomFooterLinks() {
        return customFooterLinks;
    }
    
    public void setCustomFooterLinks(List<FooterLink> customFooterLinks) {
        this.customFooterLinks = customFooterLinks;
    }
}
