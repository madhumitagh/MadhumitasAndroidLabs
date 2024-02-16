package algonquin.cst2335.ghos0042;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    Button loginButton;
    @Override
    protected void onStart() {
        super.onStart();
        Log.w( "MainActivity", "In onStart() - The application has been started" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( "MainActivity", "In onPause() - The application has been paused" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w( "MainActivity", "In onStop() - The application has been stopped" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( "MainActivity", "In onDestroy() - The application has now been destroyed" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w( "MainActivity", "In onResume() - The application is now responding to user input" );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText emailAddress = findViewById(R.id.editText);
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String emailAddress2 = prefs.getString("LoginName", "");

        emailAddress.setText(emailAddress2);
        loginButton = findViewById(R.id.login_button);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );
        Log.d( TAG, "Message");



        Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);

        loginButton.setOnClickListener( clk -> {
            String s = emailAddress.getText().toString();
            nextPage.putExtra( "EmailAddress",  s);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("LoginName",s);
            editor.apply();
            startActivity( nextPage);

        });

    }


}