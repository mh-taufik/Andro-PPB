package com.example.notify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    private TextView mName, mDescription, mDate;
    private Button back_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        mName = view.findViewById(R.id.detail_name);
        mDescription = view.findViewById(R.id.detail_description);
        mDate = view.findViewById(R.id.detail_date);
        back_btn = view.findViewById(R.id.detail_back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_listFragment);
            }
        });

        if(getArguments() != null){
            mName.setText(getArguments().getString("name"));
            mDescription.setText(getArguments().getString("description"));
            mDate.setText(getArguments().getString("date"));
        }

        return view;
    }
}