package com.fireteam.ar;

import android.app.Activity;

import com.fireteam.ar.model.ARPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farkhani on 8/13/2017.
 */

public class AugmentedRealityFramework {
    List<ARPoint> mPoints;
    Activity mAactivity;

    private AugmentedRealityFramework() {

    }

    AugmentedRealityFramework(Builder builder) {
        this.mAactivity = builder.mAactivity;
        this.mPoints = builder.mPoints;
       ARActivity.start(mAactivity,mPoints);
    }

    /**
     * ********************
     */
    public static class Builder {
        List<ARPoint> mPoints;
        Activity mAactivity;

        public Builder(Activity activity) {
            this.mAactivity = activity;
            this.mPoints = new ArrayList<>();
        }

        public Builder setPointMarker(List<ARPoint> mPoints) {
            this.mPoints = mPoints;
            return this;
        }

        public AugmentedRealityFramework build() {
            return new AugmentedRealityFramework(this);
        }
    }
}
