package goc.webtemplate.component.jsonentities.adapters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


/**
 * GSON type adapter for serializing serializing HashMap objects as a list 
 * of key+value tupples.  (Needed by CustomSearch#hiddenInput)  
 */
public class HashMapAdapter<K, V> extends TypeAdapter<HashMap<K, V>> {

    @Override
    public void write(JsonWriter out, HashMap<K, V> value) throws IOException {
        
        if (value == null) {
            out.nullValue();
            return;
        }
        
        out.beginArray();
        for (Map.Entry<K, V> entry: value.entrySet()) {
            out.beginObject();
            out.name("name");
            out.value(entry.getKey().toString());
            out.name("value");
            out.value(entry.getValue().toString());
            out.endObject();
        }
        out.endArray();
    }

    @Override
    public HashMap<K, V> read(JsonReader in) throws IOException {
        throw new UnsupportedOperationException("JSON deserialization not supported for type HashMap<K,V>.");
    }

}
