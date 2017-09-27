package com.shongsu.backgroundcheck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BackgroundCheckModule extends ReactContextBaseJavaModule {

  public BackgroundCheckModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "BackgroundCheckModule";
  }

  @ReactMethod
  public void alert(String message) {
    Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_LONG).show();
  }

  @ReactMethod
  public void openSettings() {
    Activity currentActivity = getCurrentActivity();
    currentActivity.startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
  }

  @ReactMethod
  public void bringApptoBackground() {
    Activity currentActivity = getCurrentActivity();
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_HOME);
    currentActivity.startActivity(intent);
  }

  @ReactMethod
  public void bringApptoForeground() {

    final Activity currentActivity = getCurrentActivity();
    Context ctx = getReactApplicationContext();

    System.out.println("Notification Received========bringApptoForeground==============");

    lightScreen();


    Intent intent = new Intent(ctx, currentActivity.getClass());
    intent.setAction(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

    ctx.startActivity(intent);

  }


  @ReactMethod
  public void lightScreen() {
    System.out.println("Notification Received========lightScreen==============");

    final Activity currentActivity = getCurrentActivity();

    currentActivity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        int flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;

        Window window = currentActivity.getWindow();

        window.addFlags(flags);
      }
    });
  }

}
