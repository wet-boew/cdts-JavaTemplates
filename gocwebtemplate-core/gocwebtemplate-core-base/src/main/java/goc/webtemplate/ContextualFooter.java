package goc.webtemplate;

import java.util.List;

public class ContextualFooter {

    private String title;
    private List<Link> links;

    public ContextualFooter() {}

    public ContextualFooter(String title, List<Link> links)
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
