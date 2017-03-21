package goc.webtemplate;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class MenuItem extends Link {
	private ArrayList<MenuItem> subItems = null;
	private boolean openInNewWindow = false;
	
	public MenuItem() { this.subItems = new ArrayList<MenuItem>(); }

    public MenuItem(String href, String text)
    {   
        this(href, text, new ArrayList<MenuItem>());
    }

    public MenuItem(String href, String text, ArrayList<MenuItem> subItems)
    {   
        this(href, text, subItems, false);
    }
    
    public MenuItem(String href, String text, ArrayList<MenuItem> subItems, boolean openInNewWindow) {
    	super(href, text);
    	this.subItems = subItems;
    	this.openInNewWindow = openInNewWindow;
    }
    
    public void setOpenInNewWindow(boolean openInNewWindow) { this.openInNewWindow = openInNewWindow; }
    public boolean isOpenInNewWindow() { return this.openInNewWindow; }
    
    public void setSubItems(ArrayList<MenuItem> subItems) { this.subItems = subItems; }
    public ArrayList<MenuItem> getSubItems() { return this.subItems; }
}
