package com.example.administrator.tajjarulbaharapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    EditText fullname, uname, password, city, country, hometown, address, mobileno, email, cnic, drivinglic;
    Button registerBtn;

    String URL= "http://192.168.10.14/Tajar/load-provider/include/register.php";

    JSONParser jsonParser=new JSONParser();

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.fullName);
        uname = findViewById(R.id.uName);
        password = findViewById(R.id.password);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        hometown = findViewById(R.id.homeTown);
        address = findViewById(R.id.address);
        mobileno = findViewById(R.id.mobileNo);
        email = findViewById(R.id.eMail);
        cnic = findViewById(R.id.cnic);
        drivinglic = findViewById(R.id.drivingLicense);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttemptRegister attemptRegister= new AttemptRegister();
                attemptRegister.execute(fullname.getText().toString(),uname.getText().toString(),password.getText().toString(), city.getText().toString(), country.getText().toString(), hometown.getText().toString(), address.getText().toString(), mobileno.getText().toString(), email.getText().toString(), cnic.getText().toString(), drivinglic.getText().toString());


            }
        });
    }

    private class AttemptRegister extends AsyncTask<String, String, JSONObject>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            String drivinglic = args[10];
            String cnic = args[9];
            String email= args[8];
            String mobileno= args[7];
            String address= args[6];
            String hometown= args[5];
            String country= args[4];
            String city= args[3];
            String password= args[2];
            String uname= args[1];
            String fullname= args[0];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("full_name", fullname));
            params.add(new BasicNameValuePair("uname", uname));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("city", city));
            params.add(new BasicNameValuePair("country", country));
            params.add(new BasicNameValuePair("hometown", hometown));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("mobile_no", mobileno));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("cnic_no", cnic));
            params.add(new BasicNameValuePair("driving_lic", drivinglic));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;
        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
