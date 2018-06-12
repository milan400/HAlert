package com.example.milan.hospital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Welcome extends Activity implements AdapterView.OnItemSelectedListener{


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;


    private List<Name> diseaseList;
    private List<Disease> symptoms;

    private ApiInterface apiInterface;

    private ProgressDialog mprogress;
    private ProgressDialog recprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Spinner element
        final Spinner spinner = findViewById(R.id.spinner);



        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mprogress = new ProgressDialog(this);
        recprogress = new ProgressDialog(this);



        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Name>> call = apiInterface.getDiseasename();

        mprogress.setMessage("Checking database...");
        mprogress.show();

        call.enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {



                mprogress.dismiss();

                //Spinner click listener
                spinner.setOnItemSelectedListener(Welcome.this);

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
                spinner.setAdapter(dataAdapter);
                //Toast.makeText(Welcome.this,data.get(0).toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {
                mprogress.dismiss();

                Toast.makeText(Welcome.this, "Network error", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void getRecycleitem(String item)
    {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Disease>> call = apiInterface.getSymptoms(item);

        recprogress.setMessage("Checking database...");
        recprogress.show();

        call.enqueue(new Callback<List<Disease>>() {
            @Override
            public void onResponse(Call<List<Disease>> call, Response<List<Disease>> response) {

                recprogress.dismiss();
                symptoms = response.body();
                adapter = new RecyclerAdapter(symptoms);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Disease>> call, Throwable t) {
                recprogress.dismiss();
                Toast.makeText(Welcome.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(this, "Selected: " + item, Toast.LENGTH_SHORT).show();

        getRecycleitem(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}






















































































































