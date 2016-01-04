package com.wopata.aspectlib.aspects;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.wopata.aspectlib.annotations.EnsureAsync;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by ardouin on 22/12/15.
 */
@Aspect
public class EnsureLooperAspect {


    private HashMap<Looper,Handler> mHandlerMap = new HashMap<>();

    private final Looper mAsyncLooper;

    private static final String POINTCUT_ASYNC_THREAD = "execution(@com.wopata.aspectlib.annotations.EnsureAsync * *(..))";

    private static final String POINTCUT_UI_THREAD = "execution(@com.wopata.aspectlib.annotations.EnsureUiThread * *(..))";

    class LooperThread extends HandlerThread {
        public LooperThread(){
            super("AsyncLooper");
        }
    }

    public EnsureLooperAspect(){

        mHandlerMap.put(Looper.getMainLooper(),new Handler(Looper.getMainLooper()));

        LooperThread looperThread = new LooperThread();
        looperThread.start();
        mAsyncLooper = looperThread.getLooper();
        mHandlerMap.put(mAsyncLooper,new Handler(mAsyncLooper));
    }

    @Around(POINTCUT_ASYNC_THREAD)
    public void adviceEnsureAsync(final ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method m = ms.getMethod();
        EnsureAsync annotation = m.getAnnotation(EnsureAsync.class);
        ensureRunsOnLooper(mAsyncLooper, pjp);

    }

    @Around(POINTCUT_UI_THREAD)
    public void adviceEnsureUiThread(final ProceedingJoinPoint pjp) throws Throwable {
        ensureRunsOnLooper(Looper.getMainLooper(), pjp);
    }

    private void ensureRunsOnLooper(Looper looper, final ProceedingJoinPoint pjp) throws Throwable {
        if (Looper.myLooper() == looper) {
            pjp.proceed();
        } else {
            mHandlerMap.get(looper).post(new Runnable() {
                @Override
                public void run() {
                    try {
                        pjp.proceed();
                    } catch (Throwable throwable) {
                        Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(),throwable);
                    }
                }
            });
        }
    }



}
