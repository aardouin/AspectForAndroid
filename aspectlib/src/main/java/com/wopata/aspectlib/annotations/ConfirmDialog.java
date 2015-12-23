package com.wopata.aspectlib.annotations;

/**
 * Created by ardouin on 22/12/15.
 */
public @interface ConfirmDialog {
    String title() default "Are you sure ?";
    String positiveText() default "Yes";
    String negativeText() default "No";
    int titleRes() default 0;
    int positiveTextRes() default 0;
    int negativeTextRes() default 0;
}
