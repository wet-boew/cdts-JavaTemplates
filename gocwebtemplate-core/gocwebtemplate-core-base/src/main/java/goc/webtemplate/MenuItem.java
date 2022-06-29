package goc.webtemplate;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class MenuItem extends Link {
    private ArrayList<MenuItem> subItems = null;
    private boolean openInNewWindow = false;
    private String acronym = null;

    public MenuItem() {
        this.subItems = new ArrayList<MenuItem>();
    }

    public MenuItem(String href, String text) {
    	this(href, text, null, new ArrayList<MenuItem>(), false);
    }

    public MenuItem(String href, String text, String acronym) {
        this(href, text, acronym, new ArrayList<MenuItem>(), false);
    }

    public MenuItem(String href, String text, boolean openInNewWindow) {
        this(href, text, null, new ArrayList<MenuItem>(), openInNewWindow);
    }

    public MenuItem(String href, String text, String acronym, boolean openInNewWindow) {
        this(href, text, acronym, new ArrayList<MenuItem>(), openInNewWindow);
    }

    public MenuItem(String href, String text, ArrayList<MenuItem> subItems) {
        this(href, text, null, subItems, false);
    }

    public MenuItem(String href, String text, ArrayList<MenuItem> subItems, boolean openInNewWindow) {
        this(href, text, null, subItems, openInNewWindow);
    }

    public MenuItem(String href, String text, String acronym, ArrayList<MenuItem> subItems) {
        this(href, text, acronym, subItems, false);
    }

    public MenuItem(String href, String text, String acronym, ArrayList<MenuItem> subItems, boolean openInNewWindow) {
        super(href, text);
        this.subItems = subItems;
        this.openInNewWindow = openInNewWindow;
        this.acronym = acronym;
    }

    public void setOpenInNewWindow(boolean openInNewWindow) {
        this.openInNewWindow = openInNewWindow;
    }

    public boolean isOpenInNewWindow() {
        return this.openInNewWindow;
    }

    public void setSubItems(ArrayList<MenuItem> subItems) {
        this.subItems = subItems;
    }

    public ArrayList<MenuItem> getSubItems() {
        return this.subItems;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
