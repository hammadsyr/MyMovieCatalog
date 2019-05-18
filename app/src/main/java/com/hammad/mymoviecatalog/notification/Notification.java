package com.hammad.mymoviecatalog.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.hammad.mymoviecatalog.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class Notification extends AppCompatActivity {
    @BindView(R.id.switch_daily)
    Switch switchDaily;
    @BindView(R.id.switch_release)
    Switch switchRelease;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        ButterKnife.bind(this);

        if (FirebaseMessaging.getInstance().subscribeToTopic("daily").isSuccessful())
            switchDaily.setChecked(true);
        if (FirebaseMessaging.getInstance().subscribeToTopic("daily_release").isSuccessful())
            switchDaily.setChecked(true);

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Timber.e("Key: " + key + " Value: " + value);
            }
        }

        switchDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("daily");
                    String msg = getString(R.string.msg_subscribed);
                    Toast.makeText(Notification.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("daily");
                    Toast.makeText(Notification.this, getString(R.string.daily_reminder_deactivated), Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchRelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("daily_release");
                    String msg = getString(R.string.msg_subscribed_release);
                    Toast.makeText(Notification.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("daily_release");
                    Toast.makeText(Notification.this, getString(R.string.daily_release_reminder_deactivated), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


        /*Button subscribeButton = findViewById(R.id.btn_subscribe);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("news");
                String msg = getString(R.string.msg_subscribed);
                Timber.e(msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        Button logTokenButton = findViewById(R.id.btn_token);
        logTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String deviceToken = instanceIdResult.getToken();
                        String msg = getString(R.string.msg_token_fmt, deviceToken);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Timber.e("Refreshed token: " + deviceToken);
                    }
                });
            }
        });*/
}

