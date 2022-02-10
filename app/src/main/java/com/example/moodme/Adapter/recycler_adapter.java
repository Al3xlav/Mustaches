package com.example.moodme.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.moodme.R;

import java.util.ArrayList;

public class recycler_adapter extends RecyclerView.Adapter<recycler_adapter.PhoneViewHold>  {

    ArrayList<adapter_helper> phoneLaocations;
    final private ListItemClickListener mOnClickListener;

    public recycler_adapter(ArrayList<adapter_helper> phoneLaocations, ListItemClickListener listener) {
        this.phoneLaocations = phoneLaocations;
        mOnClickListener = listener;
    }

    @NonNull

    @Override
    public PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card, parent, false);
        return new PhoneViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHold holder, int position) {


        adapter_helper phonehelper = phoneLaocations.get(position);
        holder.image.setImageResource(phonehelper.getImage());
        holder.relativeLayout.setBackground(phonehelper.getgradient());
    }

    @Override
    public int getItemCount() {
        return phoneLaocations.size();

    }

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex);
    }

    public class PhoneViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView image;
        RelativeLayout relativeLayout;


        public PhoneViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks

            image = itemView.findViewById(R.id.phone_image);
            relativeLayout = itemView.findViewById(R.id.background_color);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onphoneListClick(clickedPosition);
        }
    }

}
