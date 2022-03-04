package persistence;

import org.json.JSONObject;

// Referencing WorkRoom
public interface Writable {
    JSONObject toJson();
}
