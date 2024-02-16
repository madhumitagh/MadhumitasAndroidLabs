package algonquin.cst2335.ghos0042;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button callNumber_button = findViewById(R.id.callNumber_button);
        ImageView picture = findViewById(R.id.profileImage);
        Button change_pic = findViewById(R.id.button2);
        EditText phoneNumber = findViewById(R.id.editTextPhone);

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        TextView emailText = findViewById(R.id.emailText);
        emailText.setText("Welcome back " + emailAddress);

        Intent call = new Intent(Intent.ACTION_DIAL);
        callNumber_button.setOnClickListener( clk -> {
            String PhoneNumber = phoneNumber.getText().toString();
            call.setData(Uri.parse("tel:" + PhoneNumber));
            startActivity(call);
        });


        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    @Override

                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK) {

                            ImageView profileImage = findViewById(R.id.profileImage);
                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            profileImage.setImageBitmap( thumbnail );


                            FileOutputStream fOut = null;

                            try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);


                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                                fOut.flush();

                                fOut.close();

                            }

                            catch (FileNotFoundException e)

                            { e.printStackTrace();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }

                    }

                } );
        picture.setOnClickListener( clk -> {
            cameraResult.launch(cameraIntent);
        });
        change_pic.setOnClickListener( clk ->{
            cameraResult.launch(cameraIntent);
        });

        String filename = "Picture.png";
        File file = new File( getFilesDir(), filename);

        if(file.exists())

        {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            picture.setImageBitmap( theImage );
        }
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        String PhoneNumber2 = prefs.getString("PhoneNumher", "");
        phoneNumber.setText(PhoneNumber2);
        //editor.putString("LoginName", PhoneNumber2);

    }
    @Override
    protected void onPause() {
        super.onPause();
        EditText phoneNumber = findViewById(R.id.editTextPhone);

        String num = phoneNumber.getText().toString();
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor et = prefs.edit();
        et.putString("PhoneNumher", num);

        et.apply();



    }
}