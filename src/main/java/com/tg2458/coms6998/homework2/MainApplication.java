package com.tg2458.coms6998.homework2;

import android.app.Application;

/**
 * Created by LanKyoungHong on 3/5/17.
 */

public class MainApplication extends Application {
    public MuseumList museumList;
    public MainApplication()
    {
        museumList = new MuseumList();
    }


}
