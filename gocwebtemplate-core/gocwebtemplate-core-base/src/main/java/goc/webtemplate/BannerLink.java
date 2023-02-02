package goc.webtemplate;

public class BannerLink {
    private String href;
    private String text;
    private boolean newWindow;
    
    public BannerLink() {}

    public BannerLink(String href)
    {
        this(href, "");
    }

    public BannerLink(String href, String text)
    {   
        this(href, text, false);
    }

    public BannerLink(String href, String text, boolean newWindow)
    {   
        this.href = href;
        this.text = text;
        this.newWindow = newWindow;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isNewWindow() {
        return newWindow;
    }

    public void setNewWindow(boolean newWindow) {
        this.newWindow = newWindow;
    }
}
