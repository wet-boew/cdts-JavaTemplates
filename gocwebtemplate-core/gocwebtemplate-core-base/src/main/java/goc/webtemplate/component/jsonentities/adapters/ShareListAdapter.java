package goc.webtemplate.component.jsonentities.adapters;

import java.io.IOException;

import com.google.gson.TypeAdapter;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import goc.webtemplate.Constants;

import goc.webtemplate.component.jsonentities.ShareList;

/**
 * GSON type adapter for serializing the "showShare" parameter to the WET
 * function "wet.build.preFooter".  Needed because this parameter can be a boolean 
 * on array depending on situation.  
 */
public class ShareListAdapter extends TypeAdapter<ShareList> {

    @Override
    public void write(JsonWriter jw, ShareList obj) throws IOException {
        if (obj == null) {
            jw.nullValue();
            return;
        }
        
        if (obj.isShown()) { //share to be displayed
            if (obj.getSites() != null && obj.getSites().size() > 0) {
                jw.beginArray();
                for (Constants.SocialMediaSites site: obj.getSites()) jw.value(site.toString());
                jw.endArray();
            } else {
                jw.value(true);
            }
            return;
        }
        
        //If we get here, share should NOT be displayed
        jw.value(false);
    }

    @Override
    public ShareList read(JsonReader jr) throws IOException {
        throw new UnsupportedOperationException("JSON deserialization not supported for type ShareList.");
    }

}
