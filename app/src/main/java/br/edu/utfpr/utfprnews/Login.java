package br.edu.utfpr.utfprnews;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.utfpr.utfprnews.activity.FormularioActivity;

public class Login extends AppCompatActivity {


    private EditText edtUsername,edtPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText)findViewById(R.id.edtUsername);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnLogin    = (Button)findViewById(R.id.btnLogin);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ValUsername = edtUsername.getText().toString();
                String ValPassword = edtPassword.getText().toString();
                Log.i("Login Screen","In Onclick");

                if (!Patterns.EMAIL_ADDRESS.matcher(ValUsername).matches()){
                    Toast.makeText(br.edu.utfpr.utfprnews.Login.this,"Formato do email invalido, Verifique!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ValUsername.equals("marcos.gbarros@hotmail.com") && ValPassword.equals("123456")) {
                    Log.i("Login Screen", "in onClick if");

                    Intent intent = new Intent(br.edu.utfpr.utfprnews.Login.this,Welcome.class);
                    intent.putExtra("userdata",ValUsername);
                    startActivity(intent);
                    finish();

                    Toast.makeText(br.edu.utfpr.utfprnews.Login.this,"Bem vindo!!!", Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(br.edu.utfpr.utfprnews.Login.this,"Usuario ou Senha Invalido.", Toast.LENGTH_SHORT).show();
                    Log.i("Login Screen","In Onclick");
                }
            }
        });
    }

    public void salvar_dados(View view) {
        Intent intent = new Intent(br.edu.utfpr.utfprnews.Login.this,Login.class);

        startActivity(intent);
        finish();
    }
}