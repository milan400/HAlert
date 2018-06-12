package com.example.milan.hospital;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {


    private android.webkit.WebView mywebview;
    private ProgressDialog progress ;
    private String urltogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progress = new ProgressDialog(WebView.this);
        urltogo = "https://symptomchecker.isabelhealthcare.com/suggest_diagnoses_advanced/landing_page#";

        setContentView(R.layout.activity_web_view);


        mywebview = findViewById(R.id.webView);

        mywebview.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                if(progress.isShowing())
                {
                    progress.dismiss();
                }
            }
        });

        progress.setMessage("Loading..Please waiit.");

        progress.setCanceledOnTouchOutside(false);
        progress.show();

        mywebview.loadUrl(urltogo);

        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled(true);



    }

}

