package com.shongsu.backgroundcheck;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.shongsu.helloworld.MyTaskService;

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
  public void alertAgain(String message) {
    Toast.makeText(getReactApplicationContext(), "Fuck off", Toast.LENGTH_LONG).show();
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

//    PowerManager powerManager = (PowerManager) currentActivity.getSystemService(Context.POWER_SERVICE);
//    powerManager.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "Loneworker - FULL WAKE LOCK").acquire();

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
