package com.example.myapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.github.kexanie.library.MathView;

public class Conversion extends AppCompatActivity {

    private RequestQueue requestQueue;
    private final String url = "https://api.unitconvert.io/api/v1/Measurements/Convert";
    private final String API_KEY = "767c24d5-202e-4862-834f-55e00a0fc1db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        requestQueue = Volley.newRequestQueue(this);
        String type = getIntent().getStringExtra("type");
        String[] units = getResources().getStringArray(typeToResourceID(type));

        EditText toEditText = findViewById(R.id.toEditText);
        toEditText.setKeyListener(null);
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(
        this, android.R.layout.simple_spinner_item, units
        );
        fromAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner fromSpinner = findViewById(R.id.fromSpinner);
        fromSpinner.setAdapter(fromAdapter);
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, units
        );
        toAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner toSpinner = findViewById(R.id.toSpinner);
        toSpinner.setAdapter(fromAdapter);

        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = requestConversion();
                requestQueue.add(stringRequest);
            }
        });
    }

    public StringRequest requestConversion() {
        EditText fromEditText = findViewById(R.id.fromEditText);
        final String fromInput = fromEditText.getText().toString().trim();
        Spinner fromSpinner = findViewById(R.id.fromSpinner);
        final String fromUnit = fromSpinner.getSelectedItem().toString();
        Spinner toSpinner = findViewById(R.id.toSpinner);
        final String toUnit = toSpinner.getSelectedItem().toString();

        String finalURL = url + "?from=" + fromInput + " " + fromUnit + "&to=" + toUnit;

        return new StringRequest(Request.Method.GET, finalURL,
                new Response.Listener<String>() {
                    // SUCCESS
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject result = new JSONObject(response);
                            double amount = result.getDouble("amount");
                            EditText toEditText = findViewById(R.id.toEditText);
                            toEditText.setText(Double.toString(amount));
                            double factor = Double.parseDouble(fromInput) / amount;
                            MathView formulaOne = findViewById(R.id.formula_one);
                            formulaOne.setEngine(0);
                            String formulaTextOne = "$$" + fromInput + "\\text{ " + fromUnit + "} \\times \\frac{1 \\text{ " + toUnit + "}}{" + factor  + " \\text{ " + fromUnit + "}}$$";
                            formulaOne.setText(formulaTextOne);
                            MathView formulaTwo = findViewById(R.id.formula_two);
                            formulaTwo.setEngine(0);
                            String formulaTextTwo = "$$=" + amount + "\\text{ " + toUnit + "}" + "$$";
                            formulaTwo.setText(formulaTextTwo);

                        } catch (JSONException e) {
                            Toast toast = Toast.makeText(Conversion.this, e.getMessage(), Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    // ERROR
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Conversion.this, "hi", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<>();
                params.put("api-key", API_KEY);

                return params;
            }
        };
    }

    private static int typeToResourceID(String type) {
        if (type.compareTo("length") == 0) {
            return R.array.length;
        } else if (type.compareTo("volume") == 0) {
            return R.array.volume;
        } else if (type.compareTo("mass") == 0) {
            return R.array.mass;
        } else if (type.compareTo("temp") == 0) {
            return R.array.temp;
        } else if (type.compareTo("time") == 0) {
            return R.array.time;
        } else {
            return R.array.speed;
        }
    }
}
