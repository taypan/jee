package cz.cvut.fel.jee.model;

import java.util.UUID;

/**
 * @author Vaclav Rechtberger
 */
public class RestObject {
    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    private String uniqueID = UUID.randomUUID().toString();
}
