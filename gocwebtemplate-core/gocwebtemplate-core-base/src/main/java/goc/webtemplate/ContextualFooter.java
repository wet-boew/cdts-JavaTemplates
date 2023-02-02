package goc.webtemplate;

import java.util.List;

public class ContextualFooter {

    private String title;
    private List<FooterLink> links;

    public ContextualFooter() {}

    public ContextualFooter(String title, List<FooterLink> links)
    {
        this.title = title;
        this.links = links;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FooterLink> getLinks() {
        return links;
    }

    public void setLinks(List<FooterLink> links) {
        this.links = links;
    }
}
