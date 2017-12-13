package com.example.xl.chatandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.Socket;
import com.github.nkzawa.socketio.client.IO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private com.github.nkzawa.socketio.client.Socket socket; // socket object
    List<message> listaMessage = new ArrayList<message>();
    ListView listViewMessages;
    ImageButton btnSend;
    EditText texto;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewMessages = (ListView) findViewById(R.id.lvMessages);
        listViewMessages.setDivider(null);
        btnSend = (ImageButton) findViewById(R.id.btnSend);
        texto = (EditText) findViewById(R.id.texto);
        Intent intent=getIntent();
        Bundle parametros = intent.getExtras();
        name = parametros.getString("name");
        String host = parametros.getString("host");
        Log.d("--------------------", name + "<->" + host);
        try {
            //  socket = IO.socket("http://192.168.137.1:80");//host del socket
            socket = IO.socket(host);
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.on(com.github.nkzawa.socketio.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
            //lo que hace cuando se conecta
            @Override
            public void call(Object... args) {

                JSONObject mensaje = new JSONObject();
                try {
                    mensaje.put("author", "Android");
                    //  mensaje.put("text", "Hola desde Android");

                    // socket.emit("new-message", mensaje);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            // this is the emit from the server
        }).on("messages", new Emitter.Listener() {
            //escycha lo que emita del servidor
            @Override
            public void call(Object... args) {
                // this argas[0] can have any type you send from the server
                final JSONArray messages = (JSONArray) args[0];
                runOnUiThread(new Runnable() {
                    //core un hilo de ejcecucion
                    public void run() {
                        for (int i = 0; i <= messages.length(); i++) {
                            try {
                                JSONObject item = messages.getJSONObject(i);
                                message m = new message();
                                m.setId("0");
                                m.setAuthor(item.getString("author"));
                                m.setText(item.getString("text"));
                                listaMessage.add(m);

                                Log.i("socket mensajes", "" + item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        AdapterMessage adapter = new AdapterMessage(getApplicationContext(), listaMessage, name);
                        listViewMessages.setAdapter(adapter);

                    }
                });
            }
        }).on(com.github.nkzawa.socketio.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            //cuando se desconecta
            @Override
            public void call(Object... args) {
                Log.d("ActivityName: ", "socket disconnected");
            }

        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject mensaje = new JSONObject();
                try {
                    mensaje.put("author", name);
                    mensaje.put("text", texto.getText());
                    socket.emit("new-message", mensaje);
                    texto.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
