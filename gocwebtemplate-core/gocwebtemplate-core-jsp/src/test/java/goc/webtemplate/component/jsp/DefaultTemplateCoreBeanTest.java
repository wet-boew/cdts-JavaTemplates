package goc.webtemplate.component.jsp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultTemplateCoreBeanTest
{
    @Test
    public void testCDNEnvironment_loadedFromProps()
    {
        DefaultTemplateCoreBean sut = new DefaultTemplateCoreBean();
        
        //(default is Akamai, but defined as PROD_SSL in cdn.properties)
        assertEquals("PROD_SSL", sut.getCDNEnvironment());
    }    
}
