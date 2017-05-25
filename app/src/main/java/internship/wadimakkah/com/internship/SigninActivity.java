package internship.wadimakkah.com.internship;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Button forgotPasswordbutton = (Button) findViewById(R.id.forgotPassword);
        forgotPasswordbutton.setPaintFlags(forgotPasswordbutton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button SignupMailbutton = (Button) findViewById(R.id.SignupMail);
        Button SignupGitbutton = (Button) findViewById(R.id.SignupGit);
        Button SignInbutton = (Button) findViewById(R.id.Signin);


        final EditText signinEmail = (EditText) findViewById(R.id.signinEmail);
        final EditText signinPass = (EditText) findViewById(R.id.signinPass);

        // ---------------------Firebase--------------------------
           auth =  FirebaseAuth.getInstance();

       //-------------when the user has no account and need to signup with mail------
        SignupMailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignupMailPage = new Intent(getApplicationContext(), SignupMailActivity.class);
                startActivity(goToSignupMailPage);
            }
        });

        //---------when the user has no account and need to sign up with git -------

        SignupGitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignupGitPage = new Intent(getApplicationContext(), SignupGitActivity.class);
                startActivity(goToSignupGitPage);
            }
        });
        //-------------signin process ----------
        SignInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signinEmail.getText().toString();
                final String password = signinPass.getText().toString();
                //Checking methods
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //---firebase login ---
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                    Toast.makeText(SigninActivity.this, " Password is less than 6 characters", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SigninActivity.this, "Wrong E-mail or Password ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser !=null){
            startActivity(new Intent(SigninActivity.this, MainActivity.class));
            finish();

        }


    }
}
