package com.fireteam.ar;//package com.farkhani.ar;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.graphics.Typeface;
//import android.location.Location;
//import android.opengl.Matrix;
//import android.view.LayoutInflater;
//import android.view.View;
//
//import com.farkhani.ar.helper.LocationHelper;
//import com.farkhani.ar.model.ARPoint;
//
//import java.util.List;
//
///**
// * Created by ntdat on 1/13/17.
// */
//
//class AROverlayView extends View {
//
//    Context context;
//    private float[] rotatedProjectionMatrix = new float[16];
//    private Location currentLocation;
//    private List<ARPoint> arPoints;
//
//    public AROverlayView(Context context, List<ARPoint> mp) {
//        super(context);
//
//        this.context = context;
//        this.arPoints = mp;
//        //Demo points
////        arPoints = new ArrayList<ARPoint>() {{
////
////            add(new ARPoint("افق کوروش نبش سید رضی 18",  36.329069,59.519214, 0));
////            add(new ARPoint("افق کوروش بین شریعتی 11 و 13", 36.355468, 59.524622, 0));
////            add(new ARPoint("افق کوروش بین قرنی 19 و 21", 36.310801, 59.591999, 0));
////            add(new ARPoint("افق کوروش بین قرنی 19 و 21", 36.328218, 59.527969, 0));
////        }};
//    }
//
//    public void updateRotatedProjectionMatrix(float[] rotatedProjectionMatrix) {
//        this.rotatedProjectionMatrix = rotatedProjectionMatrix;
//        this.invalidate();
//    }
//
//    public void updateCurrentLocation(Location currentLocation) {
//        this.currentLocation = currentLocation;
//        this.invalidate();
//    }
//
//     Rect r = new Rect(10, 10, 200, 100);
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//       View view= LayoutInflater.from(context).inflate(R.layout.row_jointventure,null);
//
//        if (currentLocation == null) {
//            return;
//        }
//
//        final int radius = 30;
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.WHITE);
//        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
//        paint.setTextSize(60);
//
//        for (int i = 0; i < arPoints.size(); i++) {
//            view.setx
//            float[] currentLocationInECEF = LocationHelper.WSG84toECEF(currentLocation);
//            float[] pointInECEF = LocationHelper.WSG84toECEF(arPoints.get(i).getLocation());
//            float[] pointInENU = LocationHelper.ECEFtoENU(currentLocation, currentLocationInECEF, pointInECEF);
//
//            float[] cameraCoordinateVector = new float[4];
//            Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0);
//
//            if (cameraCoordinateVector[2] < 0) {
//                float x = (0.5f + cameraCoordinateVector[0] / cameraCoordinateVector[3]) * canvas.getWidth();
//                float y = (0.5f - cameraCoordinateVector[1] / cameraCoordinateVector[3]) * canvas.getHeight();
//
//                canvas.drawCircle(x, y, radius, paint);
//
//                paint.setStyle(Paint.Style.FILL);
//                paint.setColor(Color.MAGENTA);
//                canvas.drawRect(r, paint);
//                paint.setStyle(Paint.Style.STROKE);
//                paint.setColor(Color.BLACK);
//                canvas.drawRect(r, paint);
//
//                canvas.drawText(arPoints.get(i).getName(), x - (30 * arPoints.get(i).getName().length() / 2), y - 80, paint);
//            }
//        }
//    }
//}
