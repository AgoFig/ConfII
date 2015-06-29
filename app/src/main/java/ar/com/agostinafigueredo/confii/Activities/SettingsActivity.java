package ar.com.agostinafigueredo.confii.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import ar.com.agostinafigueredo.confii.R;

public class SettingsActivity extends Activity {

    CheckBox checkboxNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkboxNotifications = (CheckBox) this.findViewById(R.id.checkbox_notifications);
        checkboxNotifications.setOnCheckedChangeListener(onActivateNotifications);

        Boolean notificationsAreActive = this.getSharedPreferences("user_preferences", Context.MODE_PRIVATE).getBoolean("activate_notifications", false);

        checkboxNotifications.setChecked(notificationsAreActive);


    }

    private CheckBox.OnCheckedChangeListener onActivateNotifications = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            SharedPreferences pref = SettingsActivity.this.getSharedPreferences("user_preferences", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("activate_notifications", isChecked);
            editor.commit();
        }
    };


}
