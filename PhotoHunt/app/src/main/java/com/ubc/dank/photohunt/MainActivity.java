package com.ubc.dank.photohunt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.gms.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameUtils;

import java.util.ArrayList;

public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    final static int RC_SELECT_PLAYERS = 10000;
    ImageButton mLaunchCameraButton;
    Context mContext = this;
    private static int RC_SIGN_IN = 9001;

    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInflow = true;
    private boolean mSignInClicked = false;

    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the Google Api Client with access to the Play Games services
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                        // add other APIs and scopes here as needed
                .build();

        // ...

//        mLaunchCameraButton = (ImageButton) findViewById(R.id.launch_camera_button);
//
//        mLaunchCameraButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "You pressed the camera", Toast.LENGTH_SHORT).show();
//            }
//        });

        final Button button = (Button) findViewById(R.id.button_invite);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // launch the player selection screen
                // minimum: 1 other player; maximum: 1 other player
                Intent intent = Games.RealTimeMultiplayer.getSelectOpponentsIntent(mGoogleApiClient, 1, 1);
                startActivityForResult(intent, RC_SELECT_PLAYERS);
            }
        });
    }

    @Override
    public void onActivityResult(int request, int response, Intent data) {
        if (request == RC_SELECT_PLAYERS) {
            if (response != Activity.RESULT_OK) {
                // user canceled
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence text = "Canceled";
                Toast.makeText(context, text, duration).show();
                return;
            }

            // get the invitee list
            Bundle extras = data.getExtras();
            final ArrayList<String> invitees =
                    data.getStringArrayListExtra(Games.EXTRA_PLAYER_IDS);

            // get auto-match criteria
            Bundle autoMatchCriteria = null;
            int minAutoMatchPlayers =
                    data.getIntExtra(Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, 0);
            int maxAutoMatchPlayers =
                    data.getIntExtra(Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, 0);

            if (minAutoMatchPlayers > 0) {
                autoMatchCriteria = RoomConfig.createAutoMatchCriteria(
                        minAutoMatchPlayers, maxAutoMatchPlayers, 0);
            } else {
                autoMatchCriteria = null;
            }

            // create the room and specify a variant if appropriate
            RoomConfig.Builder roomConfigBuilder = makeBasicRoomConfigBuilder();
            roomConfigBuilder.addPlayersToInvite(invitees);
            if (autoMatchCriteria != null) {
                roomConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
            }
            RoomConfig roomConfig = roomConfigBuilder.build();
            Games.RealTimeMultiplayer.create(mGoogleApiClient, roomConfig);

            // prevent screen from sleeping during handshake
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    // create a RoomConfigBuilder that's appropriate for your implementation
    private RoomConfig.Builder makeBasicRoomConfigBuilder() {
//        return RoomConfig.builder(this)
//                .setMessageReceivedListener(this)
//                .setRoomStatusUpdateListener(this);
        return null; // TODO: fix this
    }


    // arbitrary request code for the waiting room UI.
// This can be any integer that's unique in your Activity.

    // create a RoomConfigBuilder that's appropriate for your implementation
//    private RoomConfig.Builder makeBasicRoomConfigBuilder() {
//        return RoomConfig.builder(this)
//                .setMessageReceivedListener(this)
//                .setRoomStatusUpdateListener(this);

//    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mResolvingConnectionFailure) {
            // already resolving
            return;
        }

        // if the sign-in button was clicked or if auto sign-in is enabled,
        // launch the sign-in flow
        if (mSignInClicked || mAutoStartSignInflow) {
            mAutoStartSignInflow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = true;

            // Attempt to resolve the connection failure using BaseGameUtils.
            // The R.string.signin_other_error value should reference a generic
            // error string in your strings.xml file, such as "There was
            // an issue with sign-in, please try again later."
            if (!BaseGameUtils.resolveConnectionFailure(this,
                    mGoogleApiClient, connectionResult,
                    RC_SIGN_IN, "Sign in failed!")) {
                mResolvingConnectionFailure = false;
            }
        }

        // Put code here to display the sign-in button
    }

    @Override
    public void onConnectionSuspended(int i) {
        // Attempt to reconnect
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // The player is signed in. Hide the sign-in button and allow the
        // player to proceed.
    }

    // Call when the sign-in button is clicked
    public void signInClicked(View view) {
        mSignInClicked = true;
        mGoogleApiClient.connect();
    }

    // Call when the sign-out button is clicked
    public void signOutclicked(View view) {
        mSignInClicked = false;
        Games.signOut(mGoogleApiClient);
    }

    // arbitrary request code for the waiting room UI.
// This can be any integer that's unique in your Activity.
    final static int RC_WAITING_ROOM = 10002;

    public void onJoinedRoom(int statusCode, Room room) {
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            // display error
            return;
        }

        // get waiting room intent
        Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(mGoogleApiClient, room, Integer.MAX_VALUE);
        startActivityForResult(i, RC_WAITING_ROOM);
    }

    public void onRoomCreated(int statusCode, Room room) {
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            // display error
            return;
        }

        // get waiting room intent
        Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(mGoogleApiClient, room, Integer.MAX_VALUE);
        startActivityForResult(i, RC_WAITING_ROOM);
    }

}
