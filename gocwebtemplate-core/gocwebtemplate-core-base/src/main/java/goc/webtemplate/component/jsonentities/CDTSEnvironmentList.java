package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A container of CDTSEnvironment matching the format of the
 * JSON file the data is read from.
 */
public class CDTSEnvironmentList implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<CDTSEnvironment>  environments;

    /**
     * Map of theme/version -> fileName -> sriHash
     *
     * This deeply nested HashMap of HashMap of HashMap is to avoid creating custom
     * classes for each level of nesting.
     */
    private HashMap<String, HashMap<String, String>> themeSRIHashes;

    public CDTSEnvironmentList() {
    }

    public ArrayList<CDTSEnvironment> getEnvironments() {
        return this.environments;
    }

    public void setEnvironments(ArrayList<CDTSEnvironment> value) {
        this.environments = value;
    }

    public HashMap<String, HashMap<String, String>> getThemeSRIHashes() {
        return this.themeSRIHashes;
    }

    public void setThemeSRIHashes(HashMap<String, HashMap<String, String>> value) {
        this.themeSRIHashes = value;
    }
}
