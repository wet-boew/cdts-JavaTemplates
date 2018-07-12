package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.splash' JavaScript function in the template
 * pages. 
 */
public class Splash implements Serializable {
    private static final long serialVersionUID = 1L;

    private String  cdnEnv;
    private String  indexEng;
    private String  indexFra;
    private String  termsEng;
    private String  termsFra;
    private String  nameEng;
    private String  nameFra;
    
    public Splash() {    
    }
    
    public Splash(String cdnEnv, 
                    String indexEng, String indexFra, 
                    String termsEng, String termsFra, 
                    String nameEng,  String nameFra) {
        this.cdnEnv = cdnEnv;
        this.indexEng = indexEng;
        this.indexFra = indexFra;
        this.termsEng = termsEng;
        this.termsFra = termsFra;
        this.nameEng = nameEng;
        this.nameFra = nameFra;
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }

    public String getIndexEng() {
        return indexEng;
    }

    public void setIndexEng(String indexEng) {
        this.indexEng = indexEng;
    }

    public String getIndexFra() {
        return indexFra;
    }

    public void setIndexFra(String indexFra) {
        this.indexFra = indexFra;
    }

    public String getTermsEng() {
        return termsEng;
    }

    public void setTermsEng(String termsEng) {
        this.termsEng = termsEng;
    }

    public String getTermsFra() {
        return termsFra;
    }

    public void setTermsFra(String termsFra) {
        this.termsFra = termsFra;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameFra() {
        return nameFra;
    }

    public void setNameFra(String nameFra) {
        this.nameFra = nameFra;
    }
}
