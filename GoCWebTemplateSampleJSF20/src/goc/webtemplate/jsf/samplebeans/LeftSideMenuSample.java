package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.Link;
import goc.webtemplate.MenuItem;
import goc.webtemplate.MenuSection;
import goc.webtemplate.component.jsf.DefaultTemplateBean;

public class LeftSideMenuSample extends DefaultTemplateBean {

	@Override
	public void setHeaderTitle() {
		this.headerTitle = "My Title";
	}
	
	@Override
	public void setLeftMenuSections() {
		MenuSection leftMenu = new MenuSection();
		leftMenu.setName("Section A");
		leftMenu.setLink("http://www.google.ca");
		leftMenu.setOpenInNewWindow(true);
		leftMenu.getItems().add(new Link("http://www.tsn.ca", "TSN"));
		
		MenuItem item = new MenuItem("", "Sports");
		item.getSubItems().add(new MenuItem("http://www.nhl.com", "NHL", null, true));
		item.getSubItems().add(new MenuItem("http://www.mlb.com", "MLB"));
		
		leftMenu.getItems().add(item);
        
        this.leftMenuSections.add(leftMenu);
	}
}
