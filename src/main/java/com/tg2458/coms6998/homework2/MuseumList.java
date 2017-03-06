package com.tg2458.coms6998.homework2;

import java.util.ArrayList;

/**
 * Created by LanKyoungHong on 3/5/17.
 */

class Museum {
    double latitude;
    double longitude;
    String name;
    String address;
}

public class MuseumList {
    private ArrayList<Museum> museumArrayList;

    private double sumLatitude = 0;
    private double sumLongitude = 0;

    public MuseumList()
    {
        museumArrayList = new ArrayList<>();
    }

    public boolean addMuseum(double latitude, double longitude, String name, String address)
    {
        Museum m = new Museum();
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
