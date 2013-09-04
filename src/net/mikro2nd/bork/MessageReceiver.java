/*
 * Copyright 2013 Michael D Morris <mikro2nd@gmail.com>. All rights reserved.
 */
package net.mikro2nd.bork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

/**
 * TODO: javadoc MessageReceiver
 */
public class MessageReceiver extends BroadcastReceiver
{
    private static final String TAG = "Bork";

    public static final String ACTION_DO_STUFF = "net.mikro2nd.bork.DoStuff";

    public static final IntentFilter INTENT_FILTER = new IntentFilter( ACTION_DO_STUFF );

    public static final String EXTRA = "seq";

    @Override
    public void onReceive( final Context context, final Intent intent )
    {
        Log.i( "Bork", "MessageReceiver GOT intent " + intent.getStringExtra( EXTRA ));

        final Thread longTask = new Thread(){
            @Override public void run(){
                Looper.prepare();
                for( int i = 0; i < 30; i++ ){
                    Log.i( TAG, "Nap " + i );
                    try{
                        Thread.sleep( 1000 );
                    }catch( InterruptedException ex ){
                        Log.i( TAG, "Sleep interrupted!" );
                    }
                }
                Toast.makeText( context, "Done sleeping, got action " + intent.getAction() + "\nSequence " + intent.getStringExtra( EXTRA ), Toast.LENGTH_LONG )
                    .show();
            }
        };
        longTask.start();

        final Vibrator v = (Vibrator) context.getSystemService( Context.VIBRATOR_SERVICE );
        v.vibrate( new long[]{ 0, 100, 150, 100, 150, 100 }, -1 );
        Toast.makeText( context, "Bye...", Toast.LENGTH_SHORT );
    }
}
