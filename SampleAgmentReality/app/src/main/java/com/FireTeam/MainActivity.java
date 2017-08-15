package com.FireTeam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fireteam.ar.AugmentedRealityFramework;
import com.fireteam.ar.model.ARPoint;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ARPoint> arPoints = new ArrayList<ARPoint>() {{

            add(new ARPoint("افق کوروش نبش سید رضی 18", 36.329069, 59.519214, 0));
            add(new ARPoint("افق کوروش بین شریعتی 11 و 13", 36.355468, 59.524622, 0));
            add(new ARPoint("افق کوروش بین قرنی 19 و 21", 36.310801, 59.591999, 0));
            add(new ARPoint("افق کوروش بین قرنی 19 و 21", 36.328218, 59.527969, 0));
        }};


        new AugmentedRealityFramework.Builder(this)
                .setPointMarker(arPoints)
                .build();
    }
}
