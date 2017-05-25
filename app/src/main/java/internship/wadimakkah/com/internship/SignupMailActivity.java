package internship.wadimakkah.com.internship;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import internship.wadimakkah.com.dpObjects.User;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupMailActivity extends AppCompatActivity {

    //declaring the firebase variables
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        final EditText signupName = (EditText) findViewById(R.id.fullname);
        final EditText signupEmail = (EditText) findViewById(R.id.signupEmail);
        final EditText signupPass = (EditText) findViewById(R.id.signupPass);
        final EditText signupConfrimPass = (EditText) findViewById(R.id.signupConfrimPass);

        Button signupbutton = (Button) findViewById(R.id.signupButton);
        auth = FirebaseAuth.getInstance();

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameSignup = signupName.getText().toString();
                final String emailSignup = signupEmail.getText().toString();
                final String passSignup = signupPass.getText().toString();
                final String confrimPassSignup = signupConfrimPass.getText().toString();

                //checks the fields before save the info on the DB
                if (nameSignup.isEmpty() || emailSignup.isEmpty() || passSignup.isEmpty() || confrimPassSignup.isEmpty()) //null vaildation
                {
                    Toast.makeText(getApplicationContext(), "All input is requaird", Toast.LENGTH_SHORT).show();

                } else if (isValidPassword(passSignup) || isValidPassword(confrimPassSignup)) // password vaildation
                {
                    Toast.makeText(getApplicationContext(), "invalid Password ", Toast.LENGTH_SHORT).show();

                } else if (!passSignup.equals(confrimPassSignup)) // password match vaildation
                {
                    Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_LONG).show();

                } else if (!isValidEmailAddress(emailSignup)) {

                    Toast.makeText(getApplicationContext(), "invalid Email", Toast.LENGTH_LONG).show();
                }else {

                    //Creating a new user and save its info on the firebase :)
                    auth.createUserWithEmailAndPassword(emailSignup, passSignup)
                            .addOnCompleteListener(SignupMailActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(SignupMailActivity.this, "Welcome Internship Girls :) ", Toast.LENGTH_SHORT).show();


                                    //save the other user info.
                                    User user=new User(nameSignup,passSignup ,emailSignup);
                                    //1- Declare the DB refrence :)
                                    Firebase.setAndroidContext(getApplicationContext());
                                    Firebase mDatabase = new Firebase("https://internship-57a51.firebaseio.com/");
                                    //2- get the generated  ID :)
                                    String ID=auth.getCurrentUser().getUid();
                                    //3- set the user object in the DB under the generated ID :)
                                    mDatabase.child("User").child(ID).setValue(user);


                                    if (!task.isSuccessful()) {
                                        Toast.makeText(SignupMailActivity.this, "Authentication failed." + "",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(SignupMailActivity.this, MainActivity.class));
                                        finish();
                                    }
                                }
                            });
                }

            }
        });

    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass == null || pass.length() < 6) {
            return true;
        }
        return false;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}