package ar.com.agostinafigueredo.confii;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONObject;

import ar.com.agostinafigueredo.confii.Entities.Conference;


public class TalkActivity extends Activity {


    private TextView talk_title_text;
    private RelativeLayout firstTalkContainer;
    private TextView talk_speaker_name;
    private ImageView conferenceImage;

    private EditText text_input;

    // debe ser solo findviewby id y setcontentview no hay que spobrecargarlo nunca con llamados
    // a la base de datos.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String talk_title = "No hay titulo aun.";

        String talk_speaker = "No sabemos quien es el orador aun.";

        this.talk_title_text = (TextView) findViewById(R.id.talk_title_text);
        this.talk_speaker_name = (TextView) findViewById(R.id.talk_speaker_name);
        this.firstTalkContainer = (RelativeLayout) findViewById(R.id.mostrar_conf);
        this.conferenceImage = (ImageView) findViewById(R.id.conf_logo);
//        this.text_input = (EditText) findViewById(R.id.edit_text_input);

        this.talk_title_text.setText(talk_title);
        this.talk_speaker_name.setText(talk_speaker);

//        this.text_input.setOnClickListener(this.dibujarSaludo);
        //    this.text_input.setOnKeyListener(this.dibujarSaludo);
        //      this.text_input.setOnEditorActionListener(this.dibujarSaludoEnVivo);

        configurarUniversalImageLoader();

        RequestQueue queue = Volley.newRequestQueue(this);
        this.obtenerDatos(queue);
    }

    public void obtenerDatos(RequestQueue queue) {

        final ImageLoader imageLoader = ImageLoader.getInstance();

        String confyUrl = "http://confy.wecode.io/api/conferences/13";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, confyUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Conference conference = gson.fromJson(jsonObject.toString(), Conference.class);

                TalkActivity.this.talk_title_text.setText(conference.getTalks().get(0).getTitle());
                TalkActivity.this.talk_speaker_name.setText(conference.getTalks().get(0).getSpeakers().get(0).getName());
                imageLoader.displayImage(conference.getImageUrl(), TalkActivity.this.conferenceImage);

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
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();

        ImageLoader.getInstance().init(config);

    }

    private TextView.OnEditorActionListener dibujarSaludoEnVivo = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            talk_title_text.setText(text_input.getText());
            return true;
        }
    };

    private View.OnKeyListener dibujarSaludo = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            talk_title_text.setText(text_input.getText());

            return true;
        }

    };

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
