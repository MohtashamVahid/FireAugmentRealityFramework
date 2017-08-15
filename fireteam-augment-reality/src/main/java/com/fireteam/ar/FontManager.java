package com.fireteam.ar;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Omid Heshmatinia on 12/21/2016.
 */

public class FontManager {

  private static FontManager sInstance;
  private Typeface IranSans;
  private Typeface IranSansBold;
  private FontManager(){}

  public static FontManager getInstance(){
    if(sInstance==null)
      sInstance = new FontManager();
    return sInstance;
  }

  public Typeface getIranSans(Context context) {
    if(sInstance.IranSans==null)
      sInstance.IranSans = Typeface.createFromAsset(context.getAssets(),"fonts/iran_sans_mobile.ttf");
    return sInstance.IranSans;
  }

  public Typeface getIranSansBold(Context context) {
    if(sInstance.IranSansBold==null)
      sInstance.IranSansBold = Typeface.createFromAsset(context.getAssets(),"fonts/iran_sans_mobile_bold.ttf");
    return sInstance.IranSansBold;
  }
}
