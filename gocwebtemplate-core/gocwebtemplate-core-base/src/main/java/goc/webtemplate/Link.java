package goc.webtemplate;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Link implements Serializable, Cloneable {
    private String href = "";
    private String text = "";
    private boolean newWindow;
    
    public Link() {}

    public Link(String href)
    {
        this(href, "");
    }
    
    public Link(String href, String text)
    {
        this(href, text, false);
    }

    public Link(String href, String text, boolean newWindow)
    {   
        this.href = href;
        this.text = text;
        this.newWindow = newWindow;
    }

    public void setHref(String href) { this.href = href; }
    public String getHref() { return this.href; }
    
    public void setText(String text) { this.text = text; }
    public String getText() { return this.text; }
    
    public void setNewWindow(boolean value) { this.newWindow = value; }
    public boolean getNewWindow() {return this.newWindow; }

    public Link clone() {
    	try {
    		return (Link)super.clone();
    	}
    	catch (CloneNotSupportedException ex) {
    		throw new RuntimeException("Clone is not a supported method of this object.", ex);
    	}
    }
}
