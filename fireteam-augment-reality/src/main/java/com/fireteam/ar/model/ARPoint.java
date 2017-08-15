package com.fireteam.ar.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ntdat on 1/16/17.
 */

public class ARPoint implements Parcelable {
    Location location;
    String name;
    long distance;

    public ARPoint(String name, double lat, double lon, double altitude) {
        this.name = name;
        location = new Location("ARPoint");
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setAltitude(altitude);
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.name);
        dest.writeLong(this.distance);
    }

    protected ARPoint(Parcel in) {
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.name = in.readString();
        this.distance = in.readLong();
    }

    public static final Creator<ARPoint> CREATOR = new Creator<ARPoint>() {
        @Override
        public ARPoint createFromParcel(Parcel source) {
            return new ARPoint(source);
        }

        @Override
        public ARPoint[] newArray(int size) {
            return new ARPoint[size];
        }
    };
}
