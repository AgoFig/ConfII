package ar.com.agostinafigueredo.confii.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import ar.com.agostinafigueredo.confii.R;

public class LikesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);

/*
        this.talksRecyclerView = (RecyclerView) this.findViewById(R.id.talks);
        this.talksRecyclerView.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.talksRecyclerView.setLayoutManager(this.layoutManager);
        this.talksRecyclerView.setAdapter(this.talksAdapter);

        // Agregamos los datos al adapter para que los muestre
        this.talksAdapter.notifyDataSetChanged();
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_likes, menu);
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
