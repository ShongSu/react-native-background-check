package com.shongsu.backgroundcheck;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

public class BackgroundCheckModule extends ReactContextBaseJavaModule {
  private static final String NULL_ACTIVITY_MESSAGE = "Activity is null";

  public BackgroundCheckModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "BackgroundCheckModule";
  }

  @ReactMethod
  public void openSettings(Callback errorCallback, Callback successCallback) {
    Activity currentActivity = getCurrentActivity();
    if(currentActivity == null) {
      errorCallback.invoke(NULL_ACTIVITY_MESSAGE);
      return;
    }
    currentActivity.startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
  }

  @ReactMethod
  public void bringApptoBackground(Callback errorCallback, Callback successCallback) {
    Activity currentActivity = getCurrentActivity();
    if(currentActivity == null) {
      errorCallback.invoke(NULL_ACTIVITY_MESSAGE);
      return;
    }
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_HOME);
    currentActivity.startActivity(intent);
  }

  @ReactMethod
  public void bringApptoForeground(Callback errorCallback, Callback successCallback) {
    Activity currentActivity = getCurrentActivity();
    if(currentActivity == null) {
      errorCallback.invoke(NULL_ACTIVITY_MESSAGE);
      return;
    }
    Context ctx = getReactApplicationContext();
    this.lightScreen();
    Intent intent = new Intent(ctx, currentActivity.getClass());
    intent.setAction(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

    ctx.startActivity(intent);

  }


  @ReactMethod
  public void lightScreen(Callback errorCallback, Callback successCallback) {
    final Activity currentActivity = getCurrentActivity();
    if(currentActivity == null) {
      errorCallback.invoke(NULL_ACTIVITY_MESSAGE);
      return;
    }
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

  @ReactMethod
  public void clearWindow(Callback errorCallback, Callback successCallback) {
    final Activity currentActivity = getCurrentActivity();
    if(currentActivity == null) {
      errorCallback.invoke(NULL_ACTIVITY_MESSAGE);
      return;
    }
    currentActivity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        int flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;

        Window window = currentActivity.getWindow();

        window.clearFlags(flags);
      }
    });
  }

  @ReactMethod
  public void isLocked(Callback errorCallback, Callback successCallback) {
    try {
      if(currentActivity == null) {
        errorCallback.invoke(NULL_ACTIVITY_MESSAGE);
        return;
      }
      Context context = currentActivity.getApplicationContext();
      KeyguardManager myKM = (KeyguardManager) context
              .getSystemService(Context.KEYGUARD_SERVICE);
      Boolean isLocked = myKM.inKeyguardRestrictedInputMode() ? true : false;
      successCallback.invoke(isLocked);
    } catch (IllegalViewOperationException e) {
      errorCallback.invoke(e.getMessage());
    }
  }
}
