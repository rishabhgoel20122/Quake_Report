package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResouceId=0;
        int magnitudeFloor=(int)Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResouceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResouceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResouceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResouceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResouceId=R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResouceId=R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResouceId=R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResouceId=R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResouceId=R.color.magnitude9;
                break;
            case 10:
                magnitudeColorResouceId=R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResouceId);
    }

    private static  final  String LOCATION_SEPERATOR=" of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
    super(context,0,earthquakes);}

    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat=new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat=new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat=new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;

        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        Earthquake currentEarthquake=getItem(position);

        TextView magnitudeView=(TextView)listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude=formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle=(GradientDrawable)magnitudeView.getBackground();
        int magnitudeColor=getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String originalLocation=currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffset;
        if(originalLocation.contains(LOCATION_SEPERATOR)){
            String[] parts=originalLocation.split(LOCATION_SEPERATOR);
            locationOffset=parts[0]+LOCATION_SEPERATOR;
            primaryLocation=parts[1];
        }
        else{
            locationOffset=getContext().getString(R.string.near_the);
            primaryLocation=originalLocation;
        }

        TextView primaryLocationView=(TextView)listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);
        TextView locationOffsetView=(TextView)listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        Date dateObject=new Date(currentEarthquake.getmTimeInMilliseconds());

        TextView dateView=(TextView)listItemView.findViewById(R.id.date);
        String formattedDate=formatDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeView=(TextView)listItemView.findViewById(R.id.time);
        String formattedtime=formatTime(dateObject);
        timeView.setText(formattedtime);

        return listItemView;

    }
}
