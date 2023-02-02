package goc.webtemplate.component;

import com.google.gson.Gson;

public final class JsonRenderer {
    /**
     * Object used for JSON serialization.  (https://github.com/google/gson)
     * 
     * According to documentation (http://www.javadoc.io/doc/com.google.code.gson/gson/2.8.0) 
     * and source code, Gson objects are thread-safe.
     */
    //NOTE: Doesn't render null values by default, which is what we want
    //NOTE: Escapes HTML by default, which is what we want (though URLs still need to be encoded)
    //NOTE: Indented output can be obtained by chaining a call to setPrettyPrinting()
    public static Gson gson = new com.google.gson.GsonBuilder()
                                        .setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.IDENTITY)
                                        .create();
}
