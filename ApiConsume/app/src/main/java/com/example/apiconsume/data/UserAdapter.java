package com.example.apiconsume.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiconsume.R;
import com.example.apiconsume.pojo.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> data;
    private Context context;

    public UserAdapter(List<User> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Glide.with(context).load(data.get(position).getAvatar()).into(holder.mAvatar);
        Picasso.get()
                        .load(data.get(position).getAvatar())
                        .into(holder.mAvatar);
        holder.mName.setText(data.get(position).getFirstName() + " " + data.get(position).getLastName());
        holder.mEmail.setText(data.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mAvatar;
        private TextView mName, mEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAvatar = itemView.findViewById(R.id.user_avatar);
            mName = itemView.findViewById(R.id.user_name);
            mEmail = itemView.findViewById(R.id.user_email);
        }
    }
}
