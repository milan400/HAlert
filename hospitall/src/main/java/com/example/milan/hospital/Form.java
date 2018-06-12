package com.example.milan.hospital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form extends Activity implements AdapterView.OnItemSelectedListener {

    private ApiInterface apiInterface;

    private List<Name> zoneList;
    private List<Name> districtList;
    private List<Name> diseaseList;

    private Spinner spineerdistrict;
    private Spinner spinnerdisease;

    private Button registerform_btn;

    private String itemzone,itemdistrict,itemdisease;

    private ProgressDialog zoneprogress;
    private ProgressDialog districtprogress;
    private ProgressDialog submitprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        //Spinner element
        final Spinner spinnerzone = findViewById(R.id.spinnerzone);

         spineerdistrict = findViewById(R.id.spineerdistrict);
         spinnerdisease = findViewById(R.id.spinnerdisease);

         zoneprogress = new ProgressDialog(this);
         districtprogress = new ProgressDialog(this);
         submitprogress = new ProgressDialog(this);

         registerform_btn = findViewById(R.id.formsubmit);


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        zoneprogress.setMessage("Checking database...");
        zoneprogress.show();

        Call<List<Name>> call = apiInterface.getZonename();

        call.enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {

                zoneprogress.dismiss();

                spinnerzone.setOnItemSelectedListener(Form.this);

                zoneList = response.body();

                //adapter for spinner
                ArrayAdapter<String> dataAdapter;

                List<String> data = new ArrayList<>();
                for(int i=0;i<zoneList.size();i++)
                {
                    if (zoneList != null) {
                        if (data != null) {
                            data.add(zoneList.get(i).getDisname());
                        }
                    }
                }

                dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,data);

                //Drop down layout style
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //attaching data adapter to spinner
                spinnerzone.setAdapter(dataAdapter);
                //Toast.makeText(Welcome.this,data.get(0).toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {
                zoneprogress.dismiss();
                Toast.makeText(Form.this, "Network error", Toast.LENGTH_SHORT).show();

            }
        });


        Call<List<Name>> calldisease = apiInterface.getDiseasename();
        zoneprogress.show();


        calldisease.enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {

                zoneprogress.dismiss();

                spinnerdisease.setOnItemSelectedListener(Form.this);

                diseaseList = response.body();

                //adapter for spinner
                ArrayAdapter<String> dataAdapter;

                List<String> data = new ArrayList<>();
                for(int i=0;i<diseaseList.size();i++)
                {
                    if (diseaseList != null) {
                        if (data != null) {
                            data.add(diseaseList.get(i).getDisname());
                        }
                    }
                }

                dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,data);

                //Drop down layout style
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //attaching data adapter to spinner
                spinnerdisease.setAdapter(dataAdapter);
                //Toast.makeText(Welcome.this,data.get(0).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {
                zoneprogress.dismiss();
                MainActivity.prefConfig.displayToast("Something went wrong...");
            }
        });

        registerform_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFormRegistration();
            }
        });

    }

    private void registerdata() {

        Call<FormData> callregister = apiInterface.performformregister(itemzone,itemdistrict,itemdisease);
        submitprogress.setMessage("Submitting");
        submitprogress.show();

        callregister.enqueue(new Callback<FormData>() {
            @Override
            public void onResponse(Call<FormData> call, Response<FormData> response) {
                submitprogress.dismiss();

                if(response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("Registration success...");

                }
                else if(response.body().getResponse().equals("error"))
                {
                    MainActivity.prefConfig.displayToast("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<FormData> call, Throwable t) {
                submitprogress.dismiss();
                MainActivity.prefConfig.displayToast("Something went wrong...");

            }
        });

    }

    private void performFormRegistration() {
        registerdata();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId())
        {
            case R.id.spinnerzone:
                itemzone = parent.getItemAtPosition(position).toString();
                //Toast.makeText(this, "Selected: " + itemzone, Toast.LENGTH_SHORT).show();
                getdistrict(itemzone);
                break;
            case R.id.spineerdistrict:
                itemdistrict = parent.getItemAtPosition(position).toString();
                //Toast.makeText(this, "Selected: " + itemdistrict, Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinnerdisease:
                itemdisease = parent.getItemAtPosition(position).toString();
                //Toast.makeText(this, "Selected: " + itemdisease, Toast.LENGTH_SHORT).show();
                break;
        }






    }

    private void getdistrict(String item) {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Name>> call = apiInterface.getDistrictname(item);

        districtprogress.setMessage("Checking database...");
        districtprogress.show();

        call.enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {

                districtprogress.dismiss();

                spineerdistrict.setOnItemSelectedListener(Form.this);
                districtList = response.body();

                //adapter for spinner
                ArrayAdapter<String> dataAdapter;

                List<String> data = new ArrayList<>();
                for(int i=0;i<districtList.size();i++)
                {
                    if (districtList != null) {
                        if (data != null) {
                            data.add(districtList.get(i).getDisname());
                        }
                    }
                }

                dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,data);

                //Drop down layout style
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //attaching data adapter to spinner
                spineerdistrict.setAdapter(dataAdapter);
                //Toast.makeText(Welcome.this,data.get(0).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {
                districtprogress.dismiss();
                MainActivity.prefConfig.displayToast("Something went wrong...");

            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
