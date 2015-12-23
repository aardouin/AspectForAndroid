package com.wopata.internal_lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.wopata.aspectlib.annotations.ConfirmDialog;
import com.wopata.aspectlib.annotations.EnsureAsync;
import com.wopata.aspectlib.annotations.EnsureUiThread;
import com.wopata.aspectlib.manager.AspectContextManager;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.uithread_button)
    Button uiThreadButton;

    @Bind(R.id.async_button)
    Button asyncButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AspectContextManager.initWithContext(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.uithread_button)
    public void onUIThreadClicked(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                switchText();
            }
        },0);

    }

    @OnClick(R.id.async_button)
    public void onAsyncClicked(){
        switchTextAsync();

    }

    @EnsureAsync
    private void switchTextAsync() {
        asyncButton.setText("Test should crash");
    }

    @ConfirmDialog
    @OnClick(R.id.confirmdialog_default)
    public void onConfirmDefaultClicked(){
        Toast.makeText(this,"You were really sure!",Toast.LENGTH_SHORT).show();
    }

    @ConfirmDialog(titleRes = R.string.app_name,negativeText="nope")
    @OnClick(R.id.confirmdialog_custom)
    public void onConfirmCustimClicked(){
        Toast.makeText(this,"You were really sure!",Toast.LENGTH_SHORT).show();
    }

    @EnsureUiThread
    public void switchText() {
        if (uiThreadButton.getText().equals("UIThread state 2")){
            uiThreadButton.setText("UIThread state 1");
        }else{
            uiThreadButton.setText("UIThread state 2");
        }
    }
}
