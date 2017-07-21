package goc.webtemplate;

import java.util.ArrayList;

public class MenuSection {
    private String name = "";
    private String link = "";
    private ArrayList<Link> items = null;
    private boolean openInNewWindow = false;

    public MenuSection() {
        this("", new ArrayList<Link>());
    }

    public MenuSection(String sectionName, ArrayList<Link> menuLinks) {
        this("", "", menuLinks);
    }

    public MenuSection(String sectionName, String sectionLink, ArrayList<Link> menuLinks) {
        this(sectionName, sectionLink, menuLinks, false);
    }

    public MenuSection(String sectionName, String sectionLink, ArrayList<Link> menuLinks, boolean openInNewWindow) {
        this.name = sectionName;
        this.link = sectionLink;
        this.items = menuLinks;
        this.openInNewWindow = openInNewWindow;
    }

    public void setOpenInNewWindow(boolean openInNewWindow) {
        this.openInNewWindow = openInNewWindow;
    }

    public boolean isOpenInNewWindow() {
        return this.openInNewWindow;
    }

    public void setName(String sectionName) {
        this.name = sectionName;
    }

    public String getName() {
        return this.name;
    }

    public void setLink(String sectionLink) {
        this.link = sectionLink;
    }

    public String getLink() {
        return this.link;
    }

    public void setItems(ArrayList<Link> menuLinks) {
        this.items = menuLinks;
    }

    public ArrayList<Link> getItems() {
        return this.items;
    }
}