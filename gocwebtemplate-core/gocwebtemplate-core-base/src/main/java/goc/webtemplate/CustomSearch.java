package goc.webtemplate;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.annotations.JsonAdapter;

/**
 * Holds confirugration properties for the Custom Search feature of the 
 * application template.
 */
@SuppressWarnings("serial")
public class CustomSearch implements Serializable {
    
    private String                  action;
    private String                  placeholder;
    private String                  id;
    private String                  method;
    //NOTE: Custom serialization/adapter because value is serialized as a list of name+value tupples
    @JsonAdapter(goc.webtemplate.component.jsonentities.adapters.MapAdapter.class)
    private Map<String, String> hiddenInput;
    
    public CustomSearch() {
    }
    
    public CustomSearch(String action, String placeholder, String id, String method) {
        this.action = action;
        this.placeholder = placeholder;
        this.id = id;
        this.method = method;
    }

    /**
     * Returns the path to the action attribute.
     * @return action value 
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the path to the action attribute.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Returns the text for the search label, placeholder and hidden heading. If not set, the text "search" will be used for all the text.
     * @return the placeHolder value
     */
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * Sets the text for the search label, placeholder and hidden heading. If not set, the text "search" will be used for all the text.
     */
    public void setPlaceholder(String placeHolder) {
        this.placeholder = placeHolder;
    }

    /**
     * Returns the (optional) id to usefor the search input field, also used in the `for` attribute for the search label. 
     * @return the id value
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the (optional) id to usefor the search input field, also used in the `for` attribute for the search label. 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the HTTP method to be used when submitting the search action (e.g. GET or POST)
     * @return the method value
     */
    public String getMethod() {
        return method;
    }

    /**
     * Returns the HTTP method to be used when submitting the search action (e.g. GET or POST)
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Returns the (optional) hidden form input fields.
     */
    public Map<String, String> getHiddenInput() {
        return hiddenInput;
    }

    /**
     * Sets the (optional) hidden form input fields.
     */
    public void setHiddenInput(Map<String, String> hiddenInput) {
        this.hiddenInput = hiddenInput;
    }
}
