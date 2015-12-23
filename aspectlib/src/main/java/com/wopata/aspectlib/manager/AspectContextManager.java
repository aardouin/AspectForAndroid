package com.wopata.aspectlib.manager;

import android.content.Context;
import android.content.ContextWrapper;

/**
 * Created by ardouin on 23/12/15.
 */
public class AspectContextManager {

    private static AspectContextManager instance;

    private Context context;

    public static AspectContextManager getInstance(){
        if (instance == null){
            instance = new AspectContextManager();
        }
        return instance;
    }
     public static void initWithContext(Context context){
         getInstance().setContext(context);
     }

    private void setContext(Context context) {
        this.context = context;
    }


    public Context getContext(){
        if( context == null){
            throw new RuntimeException("Context isn't set , please call initWithContext() first");
        }
        return context;
    }
}
