package goc.webtemplate;

public class IntranetTitle extends Link {
    private static final long serialVersionUID = 1L;

    private String acronym;
    

    public IntranetTitle()
    {
        this("", "", "");
    }
    
    public IntranetTitle(String href, String text, String acronym)
    {
        super(href, text);
        
        this.acronym = acronym;
    }
    
    public IntranetTitle(Link link)
    {
        super(link.getHref(), link.getText());
        if (link instanceof IntranetTitle) this.acronym = ((IntranetTitle)link).acronym;
    }
    
    public void setAcronym(String acronym) { this.acronym = acronym; }
    public String getAcronym() { return this.acronym; }
}
