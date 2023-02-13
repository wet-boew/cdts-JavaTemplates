package goc.webtemplate;

public class InfoBanner {
    private String mainHTML;
    private HeaderLink link;
    private HeaderLink button;

    public InfoBanner() {}

    public InfoBanner(String mainHTML, HeaderLink link, HeaderLink button)
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

    public HeaderLink getLink() {
        return link;
    }

    public void setLink(HeaderLink link) {
        this.link = link;
    }

    public HeaderLink getButton() {
        return button;
    }

    public void setButton(HeaderLink button) {
        this.button = button;
    }
}