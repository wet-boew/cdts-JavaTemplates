package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import goc.webtemplate.MenuSection;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.secmenu' JavaScript function in the template
 * pages. 
 */
public class SecMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SecMenuSection>   sections;
    
    public SecMenu() {
    }

    //NOTE: Clashes with the other constructor because of Java Generics' type erasure.
    //      we could do a whole thing to decide at run-time which code we want to run,
    //      but since we dont' really need the field initialization constructor, we'll
    //      just comment it out....
    //
    //public SecMenu(List<SecMenuSection> sections) {
    //    this.sections = sections;
    //}

    /**
     * Convenience constructor to initialize an object from an List of MenuSection
     * objects.
     */
    public SecMenu(List<MenuSection> menuSections) {
         if (menuSections != null && menuSections.size() > 0) {
             this.sections  = new ArrayList<SecMenuSection>(menuSections.size());
             for (MenuSection ms: menuSections) this.sections.add(new SecMenuSection(ms));
         } //(else this.sections stays null)
    }

    public List<SecMenuSection> getSections() {
        return sections;
    }

    public void setSections(List<SecMenuSection> sections) {
        this.sections = sections;
    }
}
