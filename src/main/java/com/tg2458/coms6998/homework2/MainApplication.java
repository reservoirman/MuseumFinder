package com.tg2458.coms6998.homework2;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by LanKyoungHong on 3/5/17.
 */

public class MainApplication extends Application {
    public MuseumList museumList;

    RequestQueue queue;

    @Override
    public void onCreate() {
        queue = Volley.newRequestQueue(this);
        super.onCreate();
    }

    public MainApplication()
    {
        museumList = new MuseumList();

    }

    public RequestQueue getQueue()
    {
        return queue;
    }

}
