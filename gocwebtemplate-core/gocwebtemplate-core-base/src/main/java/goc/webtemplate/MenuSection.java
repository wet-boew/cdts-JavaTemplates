package goc.webtemplate;

import java.util.ArrayList;
import java.util.List;

public class MenuSection {
    private String name = "";
    private String link = "";
    private List<Link> items = null;
    private boolean openInNewWindow = false;

    public MenuSection() {
        this("", new ArrayList<Link>());
    }

    public MenuSection(String sectionName, List<Link> menuLinks) {
        this("", "", menuLinks);
    }

    public MenuSection(String sectionName, String sectionLink, List<Link> menuLinks) {
        this(sectionName, sectionLink, menuLinks, false);
    }

    public MenuSection(String sectionName, String sectionLink, List<Link> menuLinks, boolean openInNewWindow) {
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

    public void setItems(List<Link> menuLinks) {
        this.items = menuLinks;
    }

    public List<Link> getItems() {
        return this.items;
    }
}