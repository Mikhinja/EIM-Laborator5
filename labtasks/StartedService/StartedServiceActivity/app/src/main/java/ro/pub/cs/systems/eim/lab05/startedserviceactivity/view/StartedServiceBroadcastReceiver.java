package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;

import ro.pub.cs.systems.eim.lab05.startedserviceactivity.general.Constants;

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private TextView messageTextView;

    // TODO: exercise 9 - default constructor

    public StartedServiceBroadcastReceiver() {
        super();
    }
    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        super();
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView
        String action = intent.getAction();
        String dataAsStr = "";
        if (Constants.ACTION_STRING.equals(action)) {
            dataAsStr = intent.getStringExtra(Constants.DATA);
        }
        else if (Constants.ACTION_INTEGER.equals(action)) {
            dataAsStr = new Integer(intent.getIntExtra(Constants.DATA, 1970)).toString();
        }
        else if (Constants.ACTION_ARRAY_LIST.equals(action)) {
            ArrayList<String> dataArrStr = intent.getStringArrayListExtra(Constants.DATA);
            StringBuilder sb = new StringBuilder();
            for (String s : dataArrStr) { sb.append(s); sb.append(", "); }
            dataAsStr = sb.toString();
        }

        Log.d(Constants.TAG, "onReceive() data = " + dataAsStr);
        if (this.messageTextView != null) {
            this.messageTextView.setText(
                    this.messageTextView.getText() + "\n" + dataAsStr);
            Log.d(Constants.TAG, "onReceive() messageTextView is not null!");
        }
        else {
            Intent startMainActivityIntent = new Intent(context, StartedServiceActivity.class);
            startMainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startMainActivityIntent.putExtra(Constants.MESSAGE, dataAsStr);
            context.startActivity(startMainActivityIntent);
        }

        // TODO: exercise 9 - restart the activity through an intent
        // if the messageTextView is not available
    }
}
