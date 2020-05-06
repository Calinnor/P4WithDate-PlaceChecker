package com.lamzone.mareunion.view.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lamzone.mareunion.R;
import com.lamzone.mareunion.model.PlaceItem;

import java.util.ArrayList;

public class PlaceAdapter extends ArrayAdapter <PlaceItem>{

    public PlaceAdapter(Context context, ArrayList<PlaceItem> PlaceList){
        super(context, 0, PlaceList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_places, parent, false);
        }
        ImageView placeColorTag= convertView.findViewById(R.id.place_color_tag);
        TextView placeName= convertView.findViewById(R.id.place_name);
        PlaceItem currentItem= getItem(position);
        if(currentItem != null) { //execption if current item !=null
            placeColorTag.setImageResource(currentItem.getmPlaceColorTag());
            placeName.setText(currentItem.getmPlaceName());
        }
        return convertView;
    }

}
