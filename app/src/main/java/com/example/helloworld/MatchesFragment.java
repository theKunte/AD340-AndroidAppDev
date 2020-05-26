package com.example.helloworld;

import com.example.helloworld.model.Match;
import com.example.helloworld.viewmodel.FirebaseMatchesViewModel;
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

import java.util.ArrayList;

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
        private ArrayList<Match> matches;

        public ContentAdapter(Context context) {
            FirebaseMatchesViewModel viewModel = new FirebaseMatchesViewModel();
            viewModel.getMatches((matches -> {
                this.matches = matches;
            }));
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Match match = matches.get(position % matches.size());

//            holder.picture.setImageDrawable(match.imageUrl);
            holder.name.setText(match.name);
//            holder.description.setText(matches[position % len]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}