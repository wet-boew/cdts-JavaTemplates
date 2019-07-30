package goc.webtemplate.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import goc.webtemplate.FooterLink;
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
     * Returns a list containing the specified item if not null and is a link
     * with an href value, otherwise returns null.
     */
    public static <T extends Link> List<T> getNonEmptySingleItemLinkList(T link)
    {
        if ( (link == null) || Utility.isNullOrEmpty(link.getHref()) ) return null;
        
        return Arrays.asList(link); 
    }
    
    /**
     * Returns a copy of the specified link list with its entries URL-escaped and blanks transformed into nulls.
     */
    public static ArrayList<Link> getNonEmptyLinkList(List<Link> links)
    {
        ArrayList<Link> vtr;
        
        if ((links == null) || (links.size() <= 0)) return null;
        
        vtr = new ArrayList<Link>();
        
        copyNonEmptyLinkList(links, vtr);

        return vtr;
    }
    
    /**
     * Returns a copy of the specified link list with its entries URL-escaped and blanks transformed into nulls.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<FooterLink> getNonEmptyFooterLinkList(List<FooterLink> links)
    {
        ArrayList<FooterLink> vtr;
        
        if ((links == null) || (links.size() <= 0)) return null;
        
        vtr = new ArrayList<FooterLink>();
        
        //NOTE: That weird double-typecast is necessary because Java's handling of wildcard generics
        //      so the second parameter of the following function can't be List<? extends Link>
        //      (A FooterLink is a Link, so it is ok to typecast to List<Link>)
        copyNonEmptyLinkList(links, (List<Link>)(List<? extends Link>)vtr);

        return vtr;
    }

    /**
     * Update specified target list with a copy of the specified link list with its entries URL-escaped and blanks transformed into nulls.
     */
    public static void copyNonEmptyLinkList(List<? extends Link> links, List<Link> target)
    {
        for (Link l: links) {
            if (l.getHref() == null) continue;  //don't add entries without hrefs
            
            Link normalisedLink = l.clone(); //can be of type Link of any sub-class
            normalisedLink.setHref(BaseUtil.encodeUrl(l.getHref()));
            normalisedLink.setText(JsonValueUtils.getNonEmptyString(l.getText()));
            
            target.add(normalisedLink);
        }
    }
}
