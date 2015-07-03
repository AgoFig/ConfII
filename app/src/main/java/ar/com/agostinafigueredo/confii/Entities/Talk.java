package ar.com.agostinafigueredo.confii.Entities;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ago on 22/06/15.
 */
public class Talk extends SugarRecord<Talk> implements Serializable {
    int id;
    String title;
    String description;
    String videoUrl;
    int conferenceId;
    String slug;
    ArrayList<Speaker> speakers;
    boolean isLiked;

    public boolean isLiked() {
        return isLiked;
    }

    public ArrayList<Speaker> getSpeakers() {
        return speakers;
    }

    public String getTitle() {
        return title;
    }

    public void toggleLiked() {
        if (isLiked()) {
            this.isLiked = false;
        } else {
            this.isLiked = true;
        }

        this.save();
    }
}
