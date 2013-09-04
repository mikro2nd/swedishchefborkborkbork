package net.mikro2nd.bork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private TextView textView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        registerReceiver( new MessageReceiver(), MessageReceiver.INTENT_FILTER );
    }

    @Override
    public void onStart()
    {
        super.onStart();
        textView = (TextView) findViewById( R.id.text );

        Intent i = new Intent( MessageReceiver.ACTION_DO_STUFF );
        i.putExtra( MessageReceiver.EXTRA, "ONE" );
        sendBroadcast( i );

        sleep( 1000 );

        i = new Intent( this, MessageReceiver.class );
        i.putExtra( MessageReceiver.EXTRA, "TWO" );
        sendBroadcast( i );
    }

    private void sleep( final long millis ){
        try{
            Thread.sleep( millis );
        }catch( InterruptedException ex ){
        }
    }
}
