package com.example.helloworld;

import com.example.helloworld.model.Match;
import com.example.helloworld.viewmodel.FirebaseMatchesViewModel;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Context;
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
FirebaseMatchesViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new FirebaseMatchesViewModel();
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), viewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public Button like_btn;
        public Context context;

        @SuppressLint("SetTextI18n")
        public ViewHolder(LayoutInflater inflater, ViewGroup parent, FirebaseMatchesViewModel viewModel) {
            super(inflater.inflate(R.layout.fragment_matches, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            like_btn = itemView.findViewById(R.id.like_btn);
            context = itemView.getContext();
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private int length;
        private ArrayList<Match> matches;
        private FirebaseMatchesViewModel viewModel;

        ContentAdapter(Context context, FirebaseMatchesViewModel viewModel) {
            this.viewModel = viewModel;
            context.getResources();
            viewModel = new FirebaseMatchesViewModel();
            viewModel.getMatches((fb_matches -> {
                this.matches = fb_matches;
                length = matches.size();
                notifyDataSetChanged();
            }));
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent, viewModel);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (this.matches != null) {
                Match match = this.matches.get(position % this.matches.size());
                Picasso.get().load(match.getImageUrl()).into(holder.picture);
                holder.name.setText(match.getName());

                holder.like_btn.setText(match.isLiked() ? R.string.UNLIKE : R.string.LIKE);
                holder.like_btn.setOnClickListener(view -> {
                    if (!match.isLiked()) {
                        //Liked someone
                        match.setLiked(true);
                        holder.like_btn.setText(R.string.UNLIKE);
                        Toast.makeText(holder.itemView.getContext(),holder.context.getString(R.string.LikeMsg) + holder.name.getText() , Toast.LENGTH_SHORT).show();
                    } else {
                        //Unliked someone
                        match.setLiked(false);
                        holder.like_btn.setText(holder.context.getString(R.string.LIKE));
                        Toast.makeText(holder.itemView.getContext(), holder.context.getString(R.string.UnlikedMsg) + holder.name.getText(), Toast.LENGTH_SHORT).show();
                    }
                    viewModel.updateMatchItem(match);
                });
            }
        }

        @Override
        public int getItemCount() {
            return length;
        }
    }
}