package goc.webtemplate;

public abstract class Constants {
    public static final String ENGLISH_CULTURE = "en-CA";
    public static final String FRENCH_CULTURE = "fr-CA";
    public static final String ENGLISH_ACCRONYM = "en";
    public static final String FRENCH_ACCRONYM = "fr";
    public static final String LANGUAGE_LINK_ENGLISH_TEXT = "English";
    public static final String LANGUAGE_LINK_FRENCH_TEXT = "Français";

    public static final String QUERYSTRING_KEY = "GoCTemplateQueryString";
    public static final String CURRENT_LANG_SESSION_KEY = "GoCTemplateCulture";
    
    public static final String CACHE_KEY_STATICFILES_PREFIX = "GoC.Template.CacheKey";
    
    public static final String WEB_TEMPLATE_DISTRIBUTION_VERSION = "4.0.26.1";
    
    public static enum SocialMediaSites {
		bitly, blogger, delicious, digg, diigo, email, facebook, gmail, googleplus, 
		linkedin, myspace, pinterest, reddit, stumbleupon, tumblr, twitter, yahoomail
    }
}
