// Generated code from Butter Knife. Do not modify!
package com.wopata.internal_lib;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.wopata.internal_lib.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492975, "field 'asyncButton' and method 'onAsyncClicked'");
    target.asyncButton = finder.castView(view, 2131492975, "field 'asyncButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onAsyncClicked();
        }
      });
    view = finder.findRequiredView(source, 2131492974, "field 'uiThreadButton' and method 'onUIThreadClicked'");
    target.uiThreadButton = finder.castView(view, 2131492974, "field 'uiThreadButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onUIThreadClicked();
        }
      });
    view = finder.findRequiredView(source, 2131492977, "method 'onConfirmCustimClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onConfirmCustimClicked();
        }
      });
    view = finder.findRequiredView(source, 2131492976, "method 'onConfirmDefaultClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onConfirmDefaultClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.asyncButton = null;
    target.uiThreadButton = null;
  }
}
