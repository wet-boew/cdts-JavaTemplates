package goc.webtemplate.component;

import java.util.ArrayList;
import java.util.List;

import goc.webtemplate.Link;
import goc.webtemplate.Utility;

public final class JsonValueUtils {
    
    /**
     * Returns the specified String value if non-empty, or null 
     */
    public static String getNonEmptyString(String value)
    {
        return Utility.isNullOrEmpty(value)? null: value;
    }
    
    /**
     * Returns the specified String value, formated using the specified arguments if non-empty, or null
     * 
     *  NOTE: Formatting of value is done by calling String.format
     */
    public static String getNonEmptyFormattedString(String value, Object... formatArgs)
    {
        //NOTE: Consider switching to MessageFormat.format instead... but this has repercusions for existing clients
        return Utility.isNullOrEmpty(value)? null: String.format(value, formatArgs);
    }
    
    /**
     * Returns the specified String value, encoded for use as a URL if non-empty, or null 
     */
    public static String getNonEmptyURLEscapedString(String value)
    {
        return Utility.isNullOrEmpty(value)? null: BaseUtil.encodeUrl(value);
    }
    
    /**
     * Returns a copy of the specified link list with its entries URL-escaped and blanks transformed into nulls.
     */
    public static ArrayList<Link> getNonEmptyLinkList(List<Link> links)
    {
        ArrayList<Link> vtr;
        
        if ((links == null) || (links.size() <= 0)) return null;
        
        vtr = new ArrayList<Link>();
        for (Link l: links) {
            if (l.getHref() == null) continue;
            vtr.add(new Link(BaseUtil.encodeUrl(l.getHref()), 
                             JsonValueUtils.getNonEmptyString(l.getText())));
        }

        return vtr;
    }
}
