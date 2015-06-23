package ar.com.agostinafigueredo.confii.Entities;

import java.util.ArrayList;

/**
 * Created by ago on 22/06/15.
 */
public class Talk {
    int id;
    String title;
    String description;
    String videoUrl;
    int conferenceId;
    String slug;
    ArrayList<Speaker> speakers;

    public ArrayList<Speaker> getSpeakers() {
        return speakers;
    }

    public String getTitle() {
        return title;
    }
}
