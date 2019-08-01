package goc.webtemplate;

public class IntranetTitle extends Link {
    private static final long serialVersionUID = 1L;

    private String acronym;
    private String boldText;
    

    public IntranetTitle() {
        this("", "", null);
    }

    public IntranetTitle(String href, String text) {
        this(href, text, null);
    }
    
    public IntranetTitle(String href, String text, String acronym) {
        this(href, text, acronym, null);
    }
    
    public IntranetTitle(String href, String text, String acronym, String boldText) {
        super(href, text);
        
        this.acronym = acronym;
        this.boldText = boldText;
    }
    
    public IntranetTitle(Link link) {
        super(link.getHref(), link.getText());
        
        if (link instanceof IntranetTitle) {
            this.acronym = ((IntranetTitle)link).acronym;
            this.boldText = ((IntranetTitle)link).boldText;
        }
    }
    
    public void setAcronym(String acronym) { 
        this.acronym = acronym; 
    }
    
    public String getAcronym() { 
        return this.acronym; 
    }
    
    public void setBoldText(String value) {
        this.boldText = value;
    }
    
    public String getBoldText() {
        return this.boldText;
    }
}
