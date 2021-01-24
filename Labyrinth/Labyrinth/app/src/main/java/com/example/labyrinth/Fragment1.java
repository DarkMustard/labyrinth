package com.example.labyrinth;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment implements PopupMenu.OnMenuItemClickListener {

    ImageView menuback;
    ImageView player;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.howto_fragment, container, false);
        menuback = (ImageView) view.findViewById(R.id.menu_back);
        player = (ImageView) view.findViewById(R.id.player);

        menuback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        return view;
    }

    public void showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_player);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_white:
                Constants.PLAYER_COLOR = Color.WHITE;
                return true;
            case R.id.item_red:
                Constants.PLAYER_COLOR = Color.rgb(128, 0, 0);
                return true;
            case R.id.item_green:
                Constants.PLAYER_COLOR = Color.rgb(0, 102, 0);
                return true;
            case R.id.item_orange:
                Constants.PLAYER_COLOR = Constants.BASIC_COLOR;
                return true;
            default:
                return false;
        }
    }
}
