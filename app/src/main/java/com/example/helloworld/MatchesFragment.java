package com.example.helloworld;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public Button like_btn;

        @SuppressLint("SetTextI18n")
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_matches, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);

            like_btn = itemView.findViewById(R.id.like_btn);
            Context context = itemView.getContext();

            like_btn.setOnClickListener(view -> {
                if (like_btn.getText().equals(context.getString(R.string.UNLIKE))) {
                    like_btn.setText(R.string.LIKE);
                    Toast.makeText(itemView.getContext(),context.getString(R.string.LikeMsg) + name.getText() , Toast.LENGTH_SHORT).show();
                } else {
                    like_btn.setText(context.getString(R.string.UNLIKE));
                    Toast.makeText(itemView.getContext(), context.getString(R.string.UnlikedMsg) + name.getText(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;
        private final String[] mMatches;
        private final String[] mMatchesDesc;
        private final Drawable[] mMatchesPictures;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mMatches = resources.getStringArray(R.array.matches);
            mMatchesDesc = resources.getStringArray(R.array.match_desc);
            TypedArray a = resources.obtainTypedArray(R.array.match_picture);
            mMatchesPictures = new Drawable[a.length()];
            for (int i = 0; i < mMatchesPictures.length; i++) {
                mMatchesPictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mMatchesPictures[position % mMatchesPictures.length]);
            holder.name.setText(mMatches[position % mMatches.length]);
            holder.description.setText(mMatchesDesc[position % mMatchesDesc.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}