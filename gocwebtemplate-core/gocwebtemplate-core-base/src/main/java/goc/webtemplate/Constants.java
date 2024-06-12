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
    
    public static final String WEB_TEMPLATE_DISTRIBUTION_VERSION = "2.7.2";

    public static final String CDTS_DEFAULT_VERSION = "v5_0_1";
    
    public static final String STATIC_FALLBACK_FILES_INTERNAL_PATH = "/goc/webtemplate/StaticFallbackFiles";
    
    public static enum SocialMediaSites {
		blogger, diigo, email, facebook, gmail, linkedin, myspace, pinterest,
		reddit, tinyurl, tumblr, twitter, whatsapp, yahoomail
    }
}
