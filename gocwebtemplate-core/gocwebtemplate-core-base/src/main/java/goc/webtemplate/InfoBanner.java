package goc.webtemplate;

public class InfoBanner {
    private String mainHTML;
    private Link link;
    private Link button;

    public InfoBanner() {}

    public InfoBanner(String mainHTML, Link link, Link button)
    {
        this.mainHTML = mainHTML;
        this.link = link;
        this.button = button;
    }

    public String getMainHTML() {
        return mainHTML;
    }

    public void setMainHTML(String mainHTML) {
        this.mainHTML = mainHTML;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Link getButton() {
        return button;
    }

    public void setButton(Link button) {
        this.button = button;
    }
}