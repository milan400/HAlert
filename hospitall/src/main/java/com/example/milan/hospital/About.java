package com.example.milan.hospital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element adsElement = new Element();
        adsElement.setTitle("Create your next app with us");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.nepalsoft)
                .setDescription("This is first version")
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("tripathimilan28@gmail.com")
                .addFacebook("milan.tripathi.986")
                .addYoutube("UCOFQr3I8PT5oxd2ejeI0wZQ")
                .addGitHub("milan400")
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }

    private Element createCopyright() {
        Element copyright = new Element();
        final String copyrightString = String.format("Copyright %d by NEPALSOFT", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(About.this, copyrightString, Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }

}


















































