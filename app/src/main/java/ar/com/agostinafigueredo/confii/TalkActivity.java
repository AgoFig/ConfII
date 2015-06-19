package ar.com.agostinafigueredo.confii;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class TalkActivity extends Activity {


    private TextView talk_title_text;

    private TextView talk_speaker_name;

    private EditText text_input;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String talk_title = "No hay titulo aun.";

        String talk_speaker = "No sabemos quien es el orador aun.";

        this.talk_title_text = (TextView) findViewById(R.id.talk_title_text);
        this.talk_speaker_name = (TextView) findViewById(R.id.talk_speaker_name);
//        this.text_input = (EditText) findViewById(R.id.edit_text_input);

        this.talk_title_text.setText(talk_title);
        this.talk_speaker_name.setText(talk_speaker);

//        this.text_input.setOnClickListener(this.dibujarSaludo);
        //    this.text_input.setOnKeyListener(this.dibujarSaludo);
        //      this.text_input.setOnEditorActionListener(this.dibujarSaludoEnVivo);

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
