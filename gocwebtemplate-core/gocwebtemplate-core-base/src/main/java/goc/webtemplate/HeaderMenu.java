package goc.webtemplate;

import java.util.List;

public class HeaderMenu {

    private String text;
    private List<HeaderLink> links;
    private Link logoutLink;

    public HeaderMenu() {}

    public HeaderMenu(String text, List<HeaderLink> links, Link logoutLink) {
        this.text = text;
        this.links = links;
        this.logoutLink = logoutLink;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<HeaderLink> getLinks() {
        return links;
    }

    public void setLinks(List<HeaderLink> links) {
        this.links = links;
    }

    public Link getLogoutLink() {
        return logoutLink;
    }

    public void setLogoutLink(Link logoutLink) {
        this.logoutLink = logoutLink;
    }
}
