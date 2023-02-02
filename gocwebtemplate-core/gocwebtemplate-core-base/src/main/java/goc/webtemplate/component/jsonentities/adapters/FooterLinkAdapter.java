package goc.webtemplate.component.jsonentities.adapters;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import static goc.webtemplate.component.JsonRenderer.gson;

import goc.webtemplate.component.jsonentities.FooterLinkContext;

/**
 * GSON type adapter for serializing the "privacyLink" or "termsLink" parameter to the WET
 * function "wet.build.footer".  Needed because this parameter can be a footer link
 * or an array of footer links depending on if showFooter is true or false.
 */
public class FooterLinkAdapter extends TypeAdapter<FooterLinkContext>{
    @Override
    public void write(JsonWriter jw, FooterLinkContext obj) throws IOException {

        if (obj == null) {
            jw.nullValue();
            return;
        }

        //If showFooter is false, render footer link as an array
        if (!obj.isShowFooter()) jw.beginArray();
        gson.toJson(obj.getFooterLink(), obj.getFooterLink().getClass(), jw);
        if (!obj.isShowFooter()) jw.endArray();
    }

    @Override
    public FooterLinkContext read(JsonReader jr) throws IOException {
        throw new UnsupportedOperationException("JSON deserialization not supported for type FooterLinkContext.");
    }
}