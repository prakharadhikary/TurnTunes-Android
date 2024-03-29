package com.prakhara.turntunes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlaylistRecyclerAdapter extends RecyclerView.Adapter<PlaylistRecyclerAdapter.SongViewHolder> {
    private List<Song> playlist;

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        // Maybe add setters?
        public CardView card;
        public TextView songName;
        public TextView artist;
        public ImageView cover;

        public SongViewHolder(View v) {
            super(v);
            card = (CardView) v.findViewById(R.id.playlistCardView);
            songName = (TextView) v.findViewById(R.id.playlistSong);
            cover = (ImageView) v.findViewById(R.id.playlistCover);
        }

    }

    public PlaylistRecyclerAdapter(List<Song> data) {
        playlist = data;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_card_view, parent, false);
        SongViewHolder viewHolder = new SongViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SongViewHolder songViewHolder, int songindex) {
        Song curSong = playlist.get(songindex);
        songViewHolder.songName.setText(curSong.getSong());
        String cover = curSong.getImg();
        if (cover.equals("img/cover-art.png")) {
            songViewHolder.cover.setImageResource(R.drawable.cover_art);
        } else {
            new LoadSongImage(songViewHolder.cover).execute(cover);
        }
    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }
}