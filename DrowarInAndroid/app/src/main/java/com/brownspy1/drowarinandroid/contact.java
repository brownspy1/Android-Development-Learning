package com.brownspy1.drowarinandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class contact extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View my_viwe = inflater.inflate(R.layout.fragment_contact, container, false);


        return my_viwe;
    }
}