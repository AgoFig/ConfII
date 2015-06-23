package ar.com.agostinafigueredo.confii.Entities;

import java.util.List;

/**
 * Created by ago on 22/06/15.
 */
public class Conference {
    List<Talk> talks;
    String imageUrl;
    public List<Talk> getTalks() {
        return talks;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
