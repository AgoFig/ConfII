package ar.com.agostinafigueredo.confii.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ar.com.agostinafigueredo.confii.Entities.Talk;
import ar.com.agostinafigueredo.confii.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        goToConferences = Button findViewByid(R.id.goToConferences);
        goToSettings = Button findViewByid(R.id.goToSettings);
*/
        if (getIntent().getExtras() != null) {
            Talk talk = (Talk) getIntent().getExtras().getSerializable("charla");

            TextView talk_title = (TextView) findViewById(R.id.talk_title_text);

            talk_title.setText(talk.getTitle());
        }

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
