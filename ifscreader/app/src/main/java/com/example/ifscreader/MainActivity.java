package com.example.ifscreader;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText et_ifsc;
    Button btn_send_request;
    TextView tv_result, tv_footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        setContentView(R.layout.activity_main);

        et_ifsc = findViewById(R.id.et_get_ifsc);
        btn_send_request = findViewById(R.id.btn_get_result);
        tv_result = findViewById(R.id.tv_result);
        tv_footer = findViewById(R.id.tv_footer);

        et_ifsc.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s,int start,int count,int after) {
            }

            @Override
            public void onTextChanged(CharSequence s,int start,int before,int count) {
                tv_result.setVisibility(View.INVISIBLE);
                tv_footer.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btn_send_request.setOnClickListener(v -> {
            String ifsc = et_ifsc.getText( ).toString( ).trim( );

            if (TextUtils.isEmpty(ifsc)) {
                Toast.makeText(MainActivity.this,"Please enter IFSC Code",Toast.LENGTH_LONG).show( );
            } else if (ifsc.length( ) < 11) {
                Toast.makeText(MainActivity.this,"Length of IFSC Code should be 11 characters",Toast.LENGTH_SHORT).show( );
            } else {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getWindow( ).getDecorView( ).getRootView( ).getWindowToken( ),0);

                et_ifsc.clearFocus( );

                tv_result.setVisibility(View.VISIBLE);
                tv_footer.setVisibility(View.VISIBLE);
                tv_result.setText(R.string.loading);
                String url = "https://ifsc.razorpay.com/" + ifsc;
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>( ) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String res_micr = response.getString("MICR");
                            String res_branch = response.getString("BRANCH");
                            String res_address = response.getString("ADDRESS");
                            String res_state = response.getString("STATE");
                            String res_contact = response.getString("CONTACT");
                            String res_upi = response.getString("UPI");
                            String res_rtgs = response.getString("RTGS");
                            String res_city = response.getString("CITY");
                            String res_centre = response.getString("CENTRE");
                            String res_district = response.getString("DISTRICT");
                            String res_neft = response.getString("NEFT");
                            String res_imps = response.getString("IMPS");
                            String res_swift = response.getString("SWIFT");
                            String res_bank = response.getString("BANK");
                            String res_bankcode = response.getString("BANKCODE");
                            String res_ifsc = response.getString("IFSC");

                            tv_result.setText("MICR : " + res_micr + "\nBRANCH : " + res_branch + "\nADDRESS : " + res_address + "\nSTATE : " + res_state + "\nCONTACT : " + res_contact +
                                    "\nUPI : " + res_upi + "\nRTGS : " + res_rtgs + "\nCITY : " + res_city + "\nCENTRE : " + res_centre + "\nDISTRICT : " + res_district +
                                    "\nNEFT : " + res_neft + "\nIMPS : " + res_imps + "\nSWIFT : " + res_swift + "\nBANK : " + res_bank + "\nBANKCODE : " + res_bankcode + "\nIFSC : " + res_ifsc);
                        } catch (JSONException e) {
                            e.printStackTrace( );
                        }
                    }
                },new Response.ErrorListener( ) {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv_result.setText(R.string.failed);
                    }
                });
                queue.add(jsonObjectRequest);
            }
        });
    }
}
