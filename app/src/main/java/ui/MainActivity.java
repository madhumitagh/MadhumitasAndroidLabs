package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.ghos0042.R;
import algonquin.cst2335.ghos0042.data.MainViewModel;
import algonquin.cst2335.ghos0042.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;

    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.mybutton.setOnClickListener(click ->
                {
                    model.editString.postValue(variableBinding.myedittext.getText().toString());
                });

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has: " + s);
        });

        model.isSelected.observe( this, selected -> {
            variableBinding.checkbox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
        });

        variableBiniding.checkBox.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText( this, "the value is now: " +isChecked, duration);
            toast.show();
        });

        variableBiniding.switch1.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText( this, "the value is now: " +isChecked, duration);
            toast.show();
        });

        variableBiniding.radioButton.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText( this, "the value is now: " +isChecked, duration);
            toast.show();
        });

        //TextView mytext =  variableBinding.textview;
        //Button btn = findViewById(R.id.mybutton);
        //EditText myedit = findViewById(R.id.myedittext);
        //String editString = myedit.getText().toString();
        //mytext.setText( "Your edit text has: " + editString);

    }
}