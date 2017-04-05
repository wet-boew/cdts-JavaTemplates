package goc.webtemplate.component;

import goc.webtemplate.Utility;

public final class JsonValueUtils {
    
    /**
     * Returns the specified String value if non-empty, or null 
     */
    public static String GetNonEmptyString(String value)
    {
        return Utility.isNullOrEmpty(value)? null: value;
    }
    
    /**
     * Returns the specified String value, formated using the specified arguments if non-empty, or null
     * 
     *  NOTE: Formatting of value is done by calling String.format
     */
    public static String GetNonEmptyFormattedString(String value, Object... formatArgs)
    {
        //NOTE: Consider switching to MessageFormat.format instead... but this has repercusions for existing clients
        return Utility.isNullOrEmpty(value)? null: String.format(value, formatArgs);
    }
    
    /**
     * Returns the specified String value, encoded for use as a URL if non-empty, or null 
     */
    public static String GetNonEmptyURLEncodedString(String value)
    {
        return Utility.isNullOrEmpty(value)? null: BaseUtil.encodeUrl(value);
    }
}
