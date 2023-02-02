package goc.webtemplate;

public class BannerLink extends Link{
    private static final long serialVersionUID = 1L;

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
        super(href, text);
        this.newWindow = newWindow;
    }

    public boolean isNewWindow() {
        return newWindow;
    }

    public void setNewWindow(boolean newWindow) {
        this.newWindow = newWindow;
    }
}
