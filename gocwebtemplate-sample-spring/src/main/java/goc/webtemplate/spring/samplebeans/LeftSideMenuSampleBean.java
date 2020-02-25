package goc.webtemplate.spring.samplebeans;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import goc.webtemplate.Link;
import goc.webtemplate.MenuItem;
import goc.webtemplate.MenuSection;
import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "leftsidemenusamplebean")
public class LeftSideMenuSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("My Title");
        
        ArrayList<MenuSection>  sections = new ArrayList<MenuSection>();
        
        MenuSection leftMenu = new MenuSection();
        leftMenu.setName("Section A");
        leftMenu.setLink("http://www.google.ca");
        leftMenu.setOpenInNewWindow(true);
        leftMenu.getItems().add(new Link("http://www.tsn.ca", "TSN"));
        
        MenuItem item = new MenuItem("", "Sports");
        item.getSubItems().add(new MenuItem("http://www.nhl.com", "NHL", null, true));
        item.getSubItems().add(new MenuItem("http://www.mlb.com", "MLB"));
        
        leftMenu.getItems().add(item);
        
        sections.add(leftMenu);
        
        this.setLeftMenuSections(sections);
    }
}
