package goc.webtemplate.spring.samplebeans;

import org.springframework.stereotype.Component;

@Component("nestedchildpagesamplebean")
public class NestedChildPageSampleBean extends NestedMasterPageSampleBean {
    
    @Override 
    public void onWebTemplateInitialize() {
        super.onWebTemplateInitialize(); //call our parent first, we'll override the values we need
        
        this.setHeaderTitle("Nested Master Page Sample");
    }
}
