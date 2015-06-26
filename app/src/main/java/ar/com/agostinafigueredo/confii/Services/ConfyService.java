package ar.com.agostinafigueredo.confii.Services;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import ar.com.agostinafigueredo.confii.Entities.Conference;

/**
 * Created by ago on 25/06/15.
 */
public class ConfyService {


    public void obtenerDatos(final Activity activity, RequestQueue queue) {


        String confyUrl = "http://confy.wecode.io/api/conferences/13";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, confyUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Conference conference = gson.fromJson(jsonObject.toString(), Conference.class);

               /* final activity.this.talk_title_text.setText(conference.getTalks().get(0).getTitle());
                final activity.this.talk_speaker_name.setText(conference.getTalks().get(0).getSpeakers().get(0).getName());
                ImageLoader.getInstance().displayImage(conference.getImageUrl(), final activity.this.conferenceImage);
*/
                int length = 3;
                for (int i = 1; i < length; i++) {


                    //Create a textView, set a random ID and position it below the most recently added view
                  /*  TextView textView = new TextView(final activity.this);
                    textView.setId((int) System.currentTimeMillis());
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.addRule(RelativeLayout.BELOW,final  activity.this.firstTalkContainer.getId());
                    textView.setText(conference.getTalks().get(i).getTitle());
                    RelativeLayout relativeLayout = new RelativeLayout(final activity.this);
                    relativeLayout.addView(textView, layoutParams);
               */
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(request);

    }
}
