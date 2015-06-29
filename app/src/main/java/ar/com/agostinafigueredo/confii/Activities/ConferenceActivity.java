package ar.com.agostinafigueredo.confii.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import ar.com.agostinafigueredo.confii.Adapters.TalkListAdapter;
import ar.com.agostinafigueredo.confii.Entities.Conference;
import ar.com.agostinafigueredo.confii.Entities.Talk;
import ar.com.agostinafigueredo.confii.R;

public class ConferenceActivity extends ActionBarActivity {

    private Button goToConference;
    private Talk firstTalk;
    private RecyclerView talksRecyclerView;
    private LinearLayoutManager layoutManager;
    private TalkListAdapter talksAdapter = new TalkListAdapter(ConferenceActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);

//        this.goToConference = (Button) findViewById(R.id.conference_btn);

        // goToConference.setOnClickListener(this.goToConferenceIntent);

        this.talksRecyclerView = (RecyclerView) this.findViewById(R.id.talks);
        this.talksRecyclerView.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.talksRecyclerView.setLayoutManager(this.layoutManager);
        this.talksRecyclerView.setAdapter(this.talksAdapter);

        // Agregamos los datos al adapter para que los muestre
        this.talksAdapter.notifyDataSetChanged();

        //descargar la conferencia
        RequestQueue queue = Volley.newRequestQueue(this);
        this.obtenerDatos(queue);

    }


    public void obtenerDatos(RequestQueue queue) {

        String confyUrl = "http://confy.wecode.io/api/conferences/13";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, confyUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Conference conference = gson.fromJson(jsonObject.toString(), Conference.class);
                ConferenceActivity.this.talksAdapter.setTalks(conference.getTalks());
                ConferenceActivity.this.firstTalk = conference.getTalks().get(0);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(request);

    }

    private Button.OnClickListener goToConferenceIntent = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent goToTalkListFromConference = new Intent(ConferenceActivity.this, MainActivity.class);
            //Obtener charla
            goToTalkListFromConference.putExtra("charla", ConferenceActivity.this.firstTalk);
            startActivity(goToTalkListFromConference);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_conference, menu);
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
            openSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openSettings() {
        Intent goToSettingsFromConference = new Intent(ConferenceActivity.this, SettingsActivity.class);
        startActivity(goToSettingsFromConference);
    }

}
