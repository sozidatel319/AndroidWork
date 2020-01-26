package com.aseevei.githubuserstest.user.list.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aseevei.githubuserstest.R;
import com.aseevei.githubuserstest.user.list.presentation.UserUIModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<UserUIModel> userList = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserUIModel user = userList.get(position);
        holder.name.setText(user.getName());
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatar())
                .apply(RequestOptions.circleCropTransform()
                        .placeholder(R.drawable.avatar_placeholder)
                        .error(R.drawable.avatar_placeholder))
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void addUsers(List<UserUIModel> users) {
        int lastPosition = getItemCount();
        userList.addAll(users);
        notifyItemRangeInserted(lastPosition, users.size());
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView avatar;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            avatar = itemView.findViewById(R.id.image_avatar);
        }
    }
}