package ar.com.agostinafigueredo.confii.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.json.JSONObject;

import ar.com.agostinafigueredo.confii.Entities.Conference;
import ar.com.agostinafigueredo.confii.Entities.Talk;
import ar.com.agostinafigueredo.confii.R;


public class TalkActivity extends Activity {


    private TextView talk_title_text;
    private RelativeLayout firstTalkContainer;
    private TextView talk_speaker_name;
    private ImageView conferenceImage;

    private at.markushi.ui.CircleButton likeButton;

    private EditText text_input;

    // debe ser solo findviewby id y setcontentview no hay que spobrecargarlo nunca con llamados
    // a la base de datos.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talk);

        configurarUniversalImageLoader();

        this.talk_title_text = (TextView) findViewById(R.id.talk_title_text);
        this.talk_speaker_name = (TextView) findViewById(R.id.talk_speaker_name);
        this.firstTalkContainer = (RelativeLayout) findViewById(R.id.mostrar_conf);
        this.conferenceImage = (ImageView) findViewById(R.id.conf_logo);
        this.likeButton = (at.markushi.ui.CircleButton) findViewById(R.id.like);
/*        this.text_input = (EditText) findViewById(R.id.edit_text_input);

        this.talk_title_text.setText(talk_title);
        this.talk_speaker_name.setText(talk_speaker);
*/
        if (getIntent().getExtras() != null) {
            Talk talk = (Talk) getIntent().getExtras().getSerializable("charla");

            this.talk_title_text.setText(talk.getTitle());
            this.talk_speaker_name.setText(talk.getSpeakers().get(0).getName());
        }
        this.likeButton.setOnClickListener(this.toggleLike);

/*
        RequestQueue queue = Volley.newRequestQueue(this);
        this.obtenerDatos(queue);
  */
    }


    private Button.OnClickListener toggleLike = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Talk talk = new Talk();
            talk.toggleLiked();

            GoogleAnalytics analytics =
                    GoogleAnalytics.getInstance(TalkActivity.this);
            Tracker tracker = analytics.newTracker(R.xml.analytics_tracker);
            tracker.send(new HitBuilders.EventBuilder()
                            .setCategory("Buttons")
                            .setAction("Like")
                            .setLabel("Likes")
                            .build()
            );

        }
    };


    public void obtenerDatos(RequestQueue queue) {


        String confyUrl = "http://confy.wecode.io/api/conferences/13";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, confyUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Conference conference = gson.fromJson(jsonObject.toString(), Conference.class);

                TalkActivity.this.talk_title_text.setText(conference.getTalks().get(0).getTitle());
                TalkActivity.this.talk_speaker_name.setText(conference.getTalks().get(0).getSpeakers().get(0).getName());
                ImageLoader.getInstance().displayImage(conference.getImageUrl(), TalkActivity.this.conferenceImage);

                int length = 3;
                for (int i = 1; i < length; i++) {


                    //Create a textView, set a random ID and position it below the most recently added view
                    TextView textView = new TextView(TalkActivity.this);
                    textView.setId((int) System.currentTimeMillis());
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.addRule(RelativeLayout.BELOW, TalkActivity.this.firstTalkContainer.getId());
                    textView.setText(conference.getTalks().get(i).getTitle());
                    RelativeLayout relativeLayout = new RelativeLayout(TalkActivity.this);
                    relativeLayout.addView(textView, layoutParams);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(request);

    }

    private void configurarUniversalImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                /*.showImageForEmptyUri(R.drawable.runners)
                .showImageOnFail(R.drawable.runners)
                .showImageOnLoading(R.drawable.runners)*/
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(config);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
