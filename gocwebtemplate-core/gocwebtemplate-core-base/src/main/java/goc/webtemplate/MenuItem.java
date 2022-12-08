package goc.webtemplate;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MenuItem extends Link {
    private List<MenuItem> subItems = null;
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

    public MenuItem(String href, String text, List<MenuItem> subItems) {
        this(href, text, null, subItems, false);
    }

    public MenuItem(String href, String text, List<MenuItem> subItems, boolean openInNewWindow) {
        this(href, text, null, subItems, openInNewWindow);
    }

    public MenuItem(String href, String text, String acronym, List<MenuItem> subItems) {
        this(href, text, acronym, subItems, false);
    }

    public MenuItem(String href, String text, String acronym, List<MenuItem> subItems, boolean openInNewWindow) {
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

    public void setSubItems(List<MenuItem> subItems) {
        this.subItems = subItems;
    }

    public List<MenuItem> getSubItems() {
        return this.subItems;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
