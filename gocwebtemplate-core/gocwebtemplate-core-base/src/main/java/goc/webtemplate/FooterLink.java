package goc.webtemplate;

@SuppressWarnings("serial")
public class FooterLink extends Link implements IFooterSection {
    
    private boolean newWindow = false;
    
    public FooterLink()
    {
    }

    public FooterLink(String href)
    {
        this(href, "", false);
    }

    public FooterLink(String href, boolean newWindow)
    {
        this(href, "", newWindow);
    }

    public FooterLink(String href, String text, boolean newWindow)
    {
        super(href, text);
        
        this.newWindow = newWindow;
    }
    
    public void setNewWindow(boolean value) { this.newWindow = value; }
    public boolean getNewWindow() {return this.newWindow; }
}
