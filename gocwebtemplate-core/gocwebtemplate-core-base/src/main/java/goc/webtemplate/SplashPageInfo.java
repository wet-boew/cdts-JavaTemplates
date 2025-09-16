package goc.webtemplate;

import java.io.Serializable;

public class SplashPageInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String englishHomeUrl;
    private String frenchHomeUrl;
    private String englishTermsUrl;
    private String frenchTermsUrl;
    private String englishName;
    private String frenchName;
    private String languagePrecedence;
    
    public SplashPageInfo() {
        //everything null by default is fine
    }
    
    public SplashPageInfo(String englishHomeUrl, String frenchHomeUrl,
                           String englishTermsUrl, String frenchTermsUrl,
                           String englishName, String frenchName) {
        this(englishHomeUrl, frenchHomeUrl, englishTermsUrl,
                frenchTermsUrl, englishName, frenchName, null);
	}

    public SplashPageInfo(String englishHomeUrl, String frenchHomeUrl,
                           String englishTermsUrl, String frenchTermsUrl,
                           String englishName, String frenchName, String languagePrecedence) {
        this.englishHomeUrl = englishHomeUrl;
        this.frenchHomeUrl = frenchHomeUrl;
        this.englishTermsUrl = englishTermsUrl;
        this.frenchTermsUrl = frenchTermsUrl;
        this.englishName = englishName;
        this.frenchName = frenchName;
        this.languagePrecedence = languagePrecedence;
    }

    public String getEnglishHomeUrl() {
        return englishHomeUrl;
    }

    public void setEnglishHomeUrl(String englishHomeUrl) {
        this.englishHomeUrl = englishHomeUrl;
    }

    public String getFrenchHomeUrl() {
        return frenchHomeUrl;
    }

    public void setFrenchHomeUrl(String frenchHomeUrl) {
        this.frenchHomeUrl = frenchHomeUrl;
    }

    public String getEnglishTermsUrl() {
        return englishTermsUrl;
    }

    public void setEnglishTermsUrl(String englishTermsUrl) {
        this.englishTermsUrl = englishTermsUrl;
    }

    public String getFrenchTermsUrl() {
        return frenchTermsUrl;
    }

    public void setFrenchTermsUrl(String frenchTermsUrl) {
        this.frenchTermsUrl = frenchTermsUrl;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getFrenchName() {
        return frenchName;
    }

    public void setFrenchName(String frenchName) {
        this.frenchName = frenchName;
    }

    public String getLanguagePrecedence() {
        return languagePrecedence;
    }

    public void setLanguagePrecedence(String languagePrecedence) {
        this.languagePrecedence = languagePrecedence;
    }
}
