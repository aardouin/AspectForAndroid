package com.wopata.aspectlib.aspects;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.Annotation;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wopata.aspectlib.annotations.ConfirmDialog;
import com.wopata.aspectlib.manager.AspectContextManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by ardouin on 22/12/15.
 */
@Aspect
public class ConfirmDialogAspect {

    private static final String POINTCUT_CONFIRMDIALOG  = "execution(@com.wopata.aspectlib.annotations.ConfirmDialog * *(..))";

    @Around(POINTCUT_CONFIRMDIALOG)
    public void advice(final ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method m = ms.getMethod();

        ConfirmDialog annotation = m.getAnnotation(ConfirmDialog.class);
        MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(AspectContextManager.getInstance().getContext());

        if( annotation.titleRes() != 0){
            dialogBuilder.title(annotation.titleRes());
        }else{
            dialogBuilder.title(annotation.title());
        }

        if( annotation.negativeTextRes() != 0){
            dialogBuilder.negativeText(annotation.negativeTextRes());
        }else{
            dialogBuilder.negativeText(annotation.negativeText());
        }

        if( annotation.positiveTextRes() != 0){
            dialogBuilder.positiveText(annotation.positiveTextRes());
        }else{
            dialogBuilder.positiveText(annotation.positiveText());
        }

        dialogBuilder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                try {
                    pjp.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        dialogBuilder.show();
    }

}
