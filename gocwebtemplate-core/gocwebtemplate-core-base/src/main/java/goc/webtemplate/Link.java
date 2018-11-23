package goc.webtemplate;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Link implements Serializable, Cloneable {
    private String href = "";
    private String text = "";
    
    public Link() {}

    public Link(String href)
    {
        this(href, "");
    }
    
    public Link(String href, String text)
    {   
        this.href = href;
        this.text = text;
    }

    public void setHref(String href) { this.href = href; }
    public String getHref() { return this.href; }
    
    public void setText(String text) { this.text = text; }
    public String getText() { return this.text; }
    
    public Link clone() {
    	try {
    		return (Link)super.clone();
    	}
    	catch (CloneNotSupportedException ex) {
    		throw new RuntimeException("Clone is not a supported method of this object.", ex);
    	}
    }
}
