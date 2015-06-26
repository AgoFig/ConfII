package ar.com.agostinafigueredo.confii.Entities;

import java.io.Serializable;

/**
 * Created by ago on 22/06/15.
 */
public class Speaker implements Serializable {
    int id;
    String name;
    String twitter;

    public String getName() {
        return name;
    }
}
