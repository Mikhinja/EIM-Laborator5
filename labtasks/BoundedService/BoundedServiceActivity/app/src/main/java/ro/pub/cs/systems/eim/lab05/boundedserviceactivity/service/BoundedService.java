package ro.pub.cs.systems.eim.lab05.boundedserviceactivity.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

import ro.pub.cs.systems.eim.lab05.boundedserviceactivity.general.Constants;

public class BoundedService extends Service {

    final private IBinder boundedServiceBinder = new BoundedServiceBinder();
    final private Random random = new Random();

    // exercise 10a - implement a IBinder public class to provide a reference
    // to the service instance through a getService() public method

    public class BoundedServiceBinder extends Binder {
        public BoundedService getService() {
            return BoundedService.this;
        }
    }

    // : exercise 10f - override the lifecycle callback method and log a message
    // of the moment they are invoked
    @Override
    public void onCreate() { Log.d(Constants.TAG, "onCreate()"); }
    @Override
    public void onDestroy() { Log.d(Constants.TAG, "onDestroy()"); }
    @Override
    public boolean onUnbind(Intent intent) { Log.d(Constants.TAG, "onUnbind()"); return true; }
    @Override
    public void onRebind(Intent intent) { Log.d(Constants.TAG, "onRebind()"); }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "OnStartCommand()");
        return START_REDELIVER_INTENT; // schedule for restart, on crash
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "onBind() method was invoked");
        return boundedServiceBinder;
    }

    public String getMessage() {
        //  exercise 10b - return a random value from the Constants.MESSAGES array list
        Log.d(Constants.TAG, "getMessage()");
        return Constants.MESSAGES.get(random.nextInt(Constants.MESSAGES.size()));
    }

}
