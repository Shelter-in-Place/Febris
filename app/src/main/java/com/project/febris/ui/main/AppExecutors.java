package com.project.febris.ui.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
    private final ExecutorService mNetworkIO2= Executors.newFixedThreadPool(3);

    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }
    public ExecutorService getmNetworkIO2(){return mNetworkIO2;}
}
