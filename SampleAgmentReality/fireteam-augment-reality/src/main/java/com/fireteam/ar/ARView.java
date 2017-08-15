package com.fireteam.ar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Location;
import android.opengl.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fireteam.R;
import com.fireteam.ar.helper.LocationHelper;
import com.fireteam.ar.model.ARPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ntdat on 1/13/17.
 */

class ARView extends View {
    List<View> viewList;
    Context context;
    private float[] rotatedProjectionMatrix = new float[16];
    private Location currentLocation;
    private List<ARPoint> arPoints;

    public ARView(Context context, List<ARPoint> mp, OnClickListener listener) {
        super(context);

        this.context = context;
        this.arPoints = mp;
        //Demo points
        viewList = new ArrayList<>();
        for (ARPoint point : arPoints) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_jointventure, null);
            TextView tvName =(TextView) view.findViewById(R.id.textView_jointventure_name);
            TextView tvDistanse =(TextView) view.findViewById(R.id.textView_jointventure_distance);

            tvName.setTypeface(FontManager.getInstance().getIranSans(context));
            tvDistanse.setTypeface(FontManager.getInstance().getIranSans(context));
            view.setTag(point);
            view.setOnClickListener(listener);
            viewList.add(view);
//            addView(view);
        }
//        arPoints = new ArrayList<ARPoint>() {{
//
//            add(new ARPoint("افق کوروش نبش سید رضی 18",  36.329069,59.519214, 0));
//            add(new ARPoint("افق کوروش بین شریعتی 11 و 13", 36.355468, 59.524622, 0));
//            add(new ARPoint("افق کوروش بین قرنی 19 و 21", 36.310801, 59.591999, 0));
//            add(new ARPoint("افق کوروش بین قرنی 19 و 21", 36.328218, 59.527969, 0));
//        }};
    }

    public void updateRotatedProjectionMatrix(float[] rotatedProjectionMatrix) {
        this.rotatedProjectionMatrix = rotatedProjectionMatrix;
        this.invalidate();
    }

    public void updateCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        this.invalidate();
    }

    //    Rect r = new Rect(10, 10, 200, 100);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float[] cameraCoordinateVector = new float[4];

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (currentLocation == null) {
            return;
        }

        final int radius = 30;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
//        paint.setTextSize(60);

        for (int i = 0; i < arPoints.size(); i++) {

            float[] currentLocationInECEF = LocationHelper.WSG84toECEF(currentLocation);
            float[] pointInECEF = LocationHelper.WSG84toECEF(arPoints.get(i).getLocation());
            float[] pointInENU = LocationHelper.ECEFtoENU(currentLocation, currentLocationInECEF, pointInECEF);

            Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0);

            if (cameraCoordinateVector[2] < 0) {
                float x = (0.5f + cameraCoordinateVector[0] / cameraCoordinateVector[3]) * canvas.getWidth();
                float y = (0.5f - cameraCoordinateVector[1] / cameraCoordinateVector[3]) * canvas.getHeight();
                View view = viewList.get(i);
                view.setX(x);
                view.setY(y);

//                canvas.drawCircle(x, y, radius, paint);
//
//                paint.setStyle(Paint.Style.FILL);
//                paint.setColor(Color.MAGENTA);
//                canvas.drawRect(r, paint);
//                paint.setStyle(Paint.Style.STROKE);
//                paint.setColor(Color.BLACK);
//                view.setDrawingCacheEnabled(true);
//                view.buildDrawingCache();
                view.setDrawingCacheEnabled(true);

// this is the important code :)
// Without it the view will have a dimension of 0,0 and the bitmap will be null
                view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

                view.buildDrawingCache(true);
                Bitmap b = Bitmap.createBitmap(view.getDrawingCache());
                canvas.drawBitmap(b,x,y,paint);
                //

 //                canvas.drawText(arPoints.get(i).getName(), x - (30 * arPoints.get(i).getName().length() / 2), y - 80, paint);
            }
        }
    }
}
