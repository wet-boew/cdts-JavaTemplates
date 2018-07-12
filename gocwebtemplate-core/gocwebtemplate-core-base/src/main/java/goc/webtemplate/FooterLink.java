package goc.webtemplate;

@SuppressWarnings("serial")
public class FooterLink extends Link {
    private boolean newWindow = false;
    
    public FooterLink()
    {
    }
    
    public FooterLink(String href, String text, boolean newWindow)
    {
        super(href, text);
        
        this.newWindow = newWindow;
    }
    
    public void setNewWindow(boolean value) { this.newWindow = value; }
    public boolean getNewWindow() {return this.newWindow; }
}
