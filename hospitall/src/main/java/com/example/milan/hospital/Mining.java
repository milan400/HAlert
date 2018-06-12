package com.example.milan.hospital;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.milan.hospital.MainActivity.apiInterface;

public class Mining extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private ImageView mining_image;
    private List<Name> miningList;
    private List<GraphModel> graphModel;
    
    private ProgressDialog progress;

    private ProgressDialog progressitem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mining);


        progress = new ProgressDialog(this);
        progressitem = new ProgressDialog(this);

        
        //Spinner element
        final Spinner spinner = findViewById(R.id.mining_spinner);

        mining_image = findViewById(R.id.mining_image);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progress.setMessage("Checking database...");
        progress.show();
        
        Call<List<Name>> call = apiInterface.getgraphname();
        call.enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
                
                progress.dismiss();
                //Spinner click listener
                spinner.setOnItemSelectedListener(Mining.this);

                miningList = response.body();

                //adapter for spinner
                ArrayAdapter<String> dataAdapter;

                List<String> data = new ArrayList<>();
                for(int i=0;i<miningList.size();i++)
                {
                    if (miningList != null) {
                        if (data != null) {
                            data.add(miningList.get(i).getDisname());
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
                Toast.makeText(Mining.this, "Network error", Toast.LENGTH_SHORT).show();
                progress.dismiss();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
        final String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(this, "Selected: " + item, Toast.LENGTH_SHORT).show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressitem.setMessage("Checking database...");
        progressitem.show();

        Call<List<GraphModel>> callgraph = apiInterface.getgraph(item);
        callgraph.enqueue(new Callback<List<GraphModel>>() {
            @Override
            public void onResponse(Call<List<GraphModel>> call, Response<List<GraphModel>> response) {
                Toast.makeText(Mining.this, "Network", Toast.LENGTH_SHORT).show();
                progressitem.dismiss();

                graphModel = response.body();
                int i=0;
               if(i<=position)
               {
                   //Toast.makeText(Mining.this, graphModel.get(i).getImages(), Toast.LENGTH_SHORT).show();
                   //Glide.with(getApplicationContext()).load(graphModel.get(i).getImages()).into(mining_image);

                   Picasso.with(getApplicationContext())
                           .load(graphModel.get(i).getImages())
                           .resize(550,239)
                           .into(mining_image);
                   i++;
               }




            }

            @Override
            public void onFailure(Call<List<GraphModel>> call, Throwable t) {
                Toast.makeText(Mining.this, "Network Error", Toast.LENGTH_SHORT).show();
                progressitem.dismiss();
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
