package goc.webtemplate;

/**
 *  Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter where a "lngLink" or "lngLinks" is required.
 */
public class LanguageLink extends Link {
    private static final long serialVersionUID = 1L;

    private String lang;
    
    public LanguageLink() {
    }

    public LanguageLink(String href, String lang, String text) {
        super(href, text);
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
