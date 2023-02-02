package goc.webtemplate;

public class InfoBanner {
    private String mainHTML;
    private BannerLink link;
    private BannerLink button;

    public InfoBanner() {}

    public InfoBanner(String mainHTML, BannerLink link, BannerLink button)
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

    public BannerLink getLink() {
        return link;
    }

    public void setLink(BannerLink link) {
        this.link = link;
    }

    public BannerLink getButton() {
        return button;
    }

    public void setButton(BannerLink button) {
        this.button = button;
    }
}