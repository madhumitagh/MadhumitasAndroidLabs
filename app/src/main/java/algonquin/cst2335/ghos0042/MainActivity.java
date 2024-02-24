package algonquin.cst2335.ghos0042;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/** This page checks the password text and see all the 4 requirements fulfilled.
 * (Upper case,Lower case, Number, and a special character)
 * @author Madhumita Ghosh
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /**
     * This holds the text at the centre of the screen
     */
    private TextView tv = null;
    /**
     * This holds password text
     */
    private EditText et = null;
    /**
     * This holds a logic button
     */
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPasswordComplexity(et.getText().toString())) {
                    tv.setText(getString(R.string.password_meets_requirements));
                } else {
                    tv.setText(getString(R.string.password_does_not_meet_requirements));
                }
                et.setText(""); // set edit text empty
            }
        });
    }
    /**
     * This function checks if the string has an Upper Case,lower case letter, a number and a special symbol(#$%%^&)
     *
     * @param pw is the string object that we are checking
     * @return Returns true if password is complex and contains all the 4 requirements
     */
    private boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase = false, foundLowerCase = false, foundNumber = false, foundSpecial = false;

        // Loop through each character in the password
        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }

        // Check if any of the conditions are not met and show a toast message accordingly
        if (!foundUpperCase) {
            Toast.makeText(getApplicationContext(), getString(R.string.password_missing_uppercase), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(getApplicationContext(),getString(R.string.password_missing_lowercase), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundNumber) {
            Toast.makeText(getApplicationContext(),getString(R.string.password_missing_number), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundSpecial) {
            Toast.makeText(getApplicationContext(),getString(R.string.password_missing_special_char), Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true; // This should only be returned if all conditions are true
        }   }

    /**
     * Checks if the given character is a special character(#$%^&*!@?).
     *
     * @param c The character to check.
     * @return Returns true if the character is a special character.
     */
    private boolean isSpecialCharacter(char c) {
        switch (c) {

            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '!':
            case '@':
            case '?':
                return true;
            default:
                return false;
        }
    }
}


