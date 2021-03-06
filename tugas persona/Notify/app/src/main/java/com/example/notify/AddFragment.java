package com.example.notify;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class AddFragment extends Fragment {

    private EditText mName, mDescription;
    private TextView mDate;
    private ImageView mDatePicker, back_btn;
    private Button save_btn;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        mName = view.findViewById(R.id.add_text_name);
        mDescription = view.findViewById(R.id.add_text_description);
        mDate = view.findViewById(R.id.add_text_date);
        mDatePicker = view.findViewById(R.id.date_input);
        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String mDay = String.valueOf(dayOfMonth);
                String mMonth = String.valueOf(month + 1);

                if (Integer.parseInt(mMonth) <= 9) {
                    mMonth = "0" + mMonth;
                }

                if (Integer.parseInt(mDay) <= 9) {
                    mDay = "0" + mDay;
                }

                String deadline = mDay + "/" + mMonth + "/" + year;
                mDate.setText(deadline);
            }
        };

        back_btn = view.findViewById(R.id.add_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_addFragment_to_listFragment);
            }
        });

        save_btn = view.findViewById(R.id.save_btn);

        if (getArguments() != null){
            mName.setText(getArguments().getString("name"));
            mDescription.setText(getArguments().getString("description"));
            mDate.setText(getArguments().getString("date"));
            save_btn.setText("Update");
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("request", "update");
                    bundle.putInt("id", getArguments().getInt("id"));
                    bundle.putString("name", mName.getText().toString());
                    bundle.putString("description", mDescription.getText().toString());
                    bundle.putString("date", mDate.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.action_addFragment_to_listFragment, bundle);
                }
            });
        }else{
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("request", "insert");
                    bundle.putString("name", mName.getText().toString());
                    bundle.putString("description", mDescription.getText().toString());
                    bundle.putString("date", mDate.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.action_addFragment_to_listFragment, bundle);
                }
            });
        }

        return view;
    }
}