package goc.webtemplate.component.jsonentities.adapters;

import java.io.IOException;

import com.google.gson.TypeAdapter;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import goc.webtemplate.Utility;

import goc.webtemplate.component.jsonentities.FeedbackLink;

/**
 * GSON type adapter for serializing the "showFeedback" parameter to the WET
 * function "wet.build.preFooter".  Needed because this parameter can be a boolean 
 * or string depending on situation.  
 */
public class FeedbackLinkAdapter extends TypeAdapter<FeedbackLink> {

    @Override
    public void write(JsonWriter jw, FeedbackLink obj) throws IOException {
        
        if (obj == null) {
            jw.nullValue();
            return;
        }
        
        if (obj.isShown()) { //Feedback link to be displayed
            if (Utility.isNullOrEmpty(obj.getUrl())) {
                jw.value(true);
            } else {
                jw.value(obj.getUrl());
            }
            return;
        }
        
        //If we get here, feedbacklinkk should NOT be displayed
        jw.value(false);
    }

    @Override
    public FeedbackLink read(JsonReader jr) throws IOException {
        throw new UnsupportedOperationException("JSON deserialization not supported for type FeedbackLink.");
    }
}
