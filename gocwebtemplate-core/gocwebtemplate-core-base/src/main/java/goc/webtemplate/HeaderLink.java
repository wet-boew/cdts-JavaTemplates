package goc.webtemplate;

public class HeaderLink extends Link{
    private static final long serialVersionUID = 1L;

    private boolean newWindow;
    
    public HeaderLink() {}

    public HeaderLink(String href)
    {
        this(href, "");
    }

    public HeaderLink(String href, String text)
    {   
        this(href, text, false);
    }

    public HeaderLink(String href, String text, boolean newWindow)
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
