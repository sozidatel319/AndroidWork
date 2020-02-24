package com.aseevei.githubuserstest.user.details.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aseevei.githubuserstest.R;
import com.aseevei.githubuserstest.user.details.presentation.UserSingleUIModel;
import com.aseevei.githubuserstest.user.list.view.ChangeView;
import com.aseevei.githubuserstest.user.list.view.ViewPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class UserSingleAdapter extends RecyclerView.Adapter<UserSingleAdapter.UserViewHolder> {
    private ArrayList<UserSingleUIModel> userList = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_singleuser, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserSingleUIModel user = userList.get(position);
        holder.name.setText(user.getName());
        holder.location.setText(user.getLocation());
        holder.email.setText(user.getEmail());
        holder.blog.setText(user.getBlog());
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

    public void addUsers(UserSingleUIModel users) {
        int lastPosition = getItemCount();
        userList.add(users);
        notifyItemRangeInserted(lastPosition, userList.size());
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView avatar;
        TextView location;
        TextView email;
        TextView blog;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            avatar = itemView.findViewById(R.id.image_avatar);
            location = itemView.findViewById(R.id.location);
            email = itemView.findViewById(R.id.email);
            blog = itemView.findViewById(R.id.blog);
        }
    }
}