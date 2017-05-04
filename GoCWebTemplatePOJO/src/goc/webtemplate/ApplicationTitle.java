package goc.webtemplate;

import java.io.Serializable;

public class ApplicationTitle implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private String url;
    
    public ApplicationTitle() {
        this("", "");
    }
    
    public ApplicationTitle(String text, String url)  {
        this.text = text;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }    
}
