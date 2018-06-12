package com.example.milan.hospital;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import mehdi.sakout.aboutpage.AboutPage;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionFragment extends Fragment {

    private CardView  test_gnd,doc_inform,bn_chatapp,bn_logout,about,google;


    OnLogoutListener onLogoutListener;

    public interface OnLogoutListener
    {
        public void LogoutPerformed();
    }



    public OptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_option, container, false);

        about = view.findViewById(R.id.about);
        test_gnd = view.findViewById(R.id.disease);
        doc_inform = view.findViewById(R.id.doc_info);
        bn_chatapp = view.findViewById(R.id.chat);
        bn_logout = view.findViewById(R.id.logout);
        google = view.findViewById(R.id.google);


        doc_inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Form.class);
                startActivity(intent);
            }
        });

        test_gnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getContext(),Welcome.class);
               startActivity(intent);
            }
        });

        bn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutListener.LogoutPerformed();
            }
        });

        bn_chatapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchFacebookApplication = getActivity().getPackageManager().getLaunchIntentForPackage("com.example.kiran.chatapp");
                startActivity(launchFacebookApplication);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Aboutpage = new Intent(getContext(), Mining.class);
                startActivity(Aboutpage);
            }
        });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Google = new Intent(getContext(), com.example.milan.hospital.WebView.class);
                startActivity(Google);
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        onLogoutListener = (OnLogoutListener) activity;
    }
}
