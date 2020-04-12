package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.os.Process; // for THREAD_PRIORITY_BACKGROUND

import ro.pub.cs.systems.eim.lab05.startedservice.general.Constants;

public class StartedService extends Service {
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            Log.d(Constants.TAG, "Stopping, received message: " + message.toString());
            stopSelf(message.arg1);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "onStartCommand() method was invoked");
        // TODO: exercise 5 - implement and start the ProcessingThread
        HandlerThread handlerThread = new HandlerThread("handlerThreadName", Process.THREAD_PRIORITY_BACKGROUND) {
            @Override
            public void run() {
                //super.run();
                Intent intent1 = new Intent();
                intent1.setAction(Constants.ACTION_STRING);
                intent1.putExtra(Constants.DATA, Constants.STRING_DATA);
                sendBroadcast(intent1);
                Log.d(Constants.TAG, "Broadcasted string action");

                try {
                    Thread.sleep(Constants.SLEEP_TIME);
                    Log.d(Constants.TAG, "Slept 1");
                }
                catch (InterruptedException ex) { Log.e(Constants.TAG, ex.getMessage()); }

                Intent intent2 = new Intent();
                intent2.setAction(Constants.ACTION_INTEGER);
                intent2.putExtra(Constants.DATA, Constants.INTEGER_DATA);
                sendBroadcast(intent2);
                Log.d(Constants.TAG, "Broadcasted integer action");

                try {
                    Thread.sleep(Constants.SLEEP_TIME);
                    Log.d(Constants.TAG, "Slept 2");
                }
                catch (InterruptedException ex) { Log.e(Constants.TAG, ex.getMessage()); }

                Intent intent3 = new Intent();
                intent3.setAction(Constants.ACTION_ARRAY_LIST);
                intent3.putExtra(Constants.DATA, Constants.ARRAY_LIST_DATA);
                sendBroadcast(intent3);
                Log.d(Constants.TAG, "Broadcasted array list action");

                try {
                    Thread.sleep(Constants.SLEEP_TIME);
                    Log.d(Constants.TAG, "Slept 3");
                }
                catch (InterruptedException ex) { Log.e(Constants.TAG, ex.getMessage()); }
            }
        };

        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        ServiceHandler serviceHandler = new ServiceHandler(looper);

        return START_REDELIVER_INTENT;
    }

}
