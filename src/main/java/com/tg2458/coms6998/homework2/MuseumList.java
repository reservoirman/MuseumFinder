package com.tg2458.coms6998.homework2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by LanKyoungHong on 3/5/17.
 */

class Museum implements Parcelable{
    double latitude;
    double longitude;
    String name;
    String address;

    protected Museum(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        name = in.readString();
        address = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(name);
        dest.writeString(address);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Museum> CREATOR = new Creator<Museum>() {
        @Override
        public Museum createFromParcel(Parcel in) {
            return new Museum(in);
        }

        @Override
        public Museum[] newArray(int size) {
            return new Museum[size];
        }
    };
}


public class MuseumList {
    private ArrayList<Museum> museumArrayList;

    private double sumLatitude = 0;
    private double sumLongitude = 0;

    public ArrayList<Museum> getMuseumArrayList()
    {
        return museumArrayList;
    }

    public MuseumList()
    {
        museumArrayList = new ArrayList<>();
    }

    public void clearList()
    {
        museumArrayList.clear();
    }

    public boolean addMuseum(double latitude, double longitude, String name, String address)
    {
        Museum m = new Museum(Parcel.obtain());
        m.latitude = latitude;
        m.longitude = longitude;
        m.name = name;
        m.address = address;
        sumLatitude += latitude;
        sumLongitude += longitude;
        return museumArrayList.add(m);
    }

    public boolean addMuseum(Museum m)
    {
        return museumArrayList.add(m);
    }

    public Museum getMuseum(int index)
    {
        return museumArrayList.get(index);
    }

    public Museum getLastMuseum()
    {
        if (museumArrayList.size() > 0) {
            return museumArrayList.get(museumArrayList.size() - 1);
        }
        else
        {
            return null;
        }
    }

    public int count()
    {
        return museumArrayList.size();
    }

    public double avgLatitude()
    {
        return sumLatitude / museumArrayList.size();
    }

    public double avgLongitude()
    {
        return sumLongitude / museumArrayList.size();
    }

}
