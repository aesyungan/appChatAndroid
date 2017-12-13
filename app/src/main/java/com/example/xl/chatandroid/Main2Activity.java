package com.example.xl.chatandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText name, host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn = (Button) findViewById(R.id.btnIngresar);
        name = (EditText) findViewById(R.id.txtname);
        host = (EditText) findViewById(R.id.txthost);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("host", host.getText().toString());
                //Toast.makeText(getApplicationContext(),name.getText()+""+host.getText(),Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}
