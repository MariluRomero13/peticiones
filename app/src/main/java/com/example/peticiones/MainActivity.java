package com.example.peticiones;

import android.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.peticiones.model.Persona;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2;
    TextView txt1 , txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        txt1 = findViewById(R.id.tvnombre);
        txt2 = findViewById(R.id.tv2nombre);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.btn1:

                        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, "http://nuevo.rnrsiilge-org.mx/principal", null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try
                                        {

                                            JSONObject jsonjObject = new JSONObject(response.toString());
                                            String valorLlave = jsonjObject.getString("Nombre");

                                            JSONArray jsonArray = jsonjObject.getJSONArray(response.toString());
                                            Log.d("valor", jsonjObject.getJSONArray(response.toString()).toString());
                                            for (int i = 0; i < jsonArray.length(); i++)
                                            {
                                                try
                                                {
                                                    JSONObject jsonObjectHijo = jsonArray.getJSONObject(i);
                                                }
                                                catch (JSONException e)
                                                {
                                                    Log.e("Parser JSON", e.toString());
                                                }
                                            }



                                            //Log.d("valor", response.getJSONArray(Personas"").toString());
                                            //Gson gson = new Gson();
                                            //Persona personas = gson.fromJson(response.toString(), Persona.class);
                                            //Log.d("valor2", personas.toString());
                                            //txt1.setText(personas.toString());
                                        }
                                        catch(Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                , new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                        });
                        VolleySingleton.getInstance(MainActivity.this).getRequestQueue().add(jor);
                        break;

                    case R.id.btn2:

                        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, "http://nuevo.rnrsiilge-org.mx/lista", null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        try
                                        {

                                            Gson gson = new Gson();
                                            Persona[] personas = gson.fromJson(response.toString(), Persona[].class);

                                            Type type = new TypeToken<List<Persona>>(){}.getType(); //construyendo un tipo de lista // crea un tipo de lista de personas
                                            List<Persona> lp  = gson.fromJson(response.toString(), type);
                                            txt2.setText(lp.get(0).getNombre());





                                            //Persona[] persona = gson.fromJson(response.toString(), Persona[].class);



                                            //Log.d("valor2", persona[0].getNombre() + " " + persona[1].getApellido());
                                            //txt2.setText(persona[0].getNombre());
                                        }
                                        catch(Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                , new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        VolleySingleton.getInstance(MainActivity.this).getRequestQueue().add(jar);
                        break;


                }
            }
        };
        btn.setOnClickListener(click);
        btn2.setOnClickListener(click);

    }
}
