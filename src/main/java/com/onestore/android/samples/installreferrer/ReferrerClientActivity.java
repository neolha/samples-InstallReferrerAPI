package com.onestore.android.samples.installreferrer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

import com.onestore.android.external.installreferrer.IGetInstallReferrerService;

import java.util.List;

public class ReferrerClientActivity extends AppCompatActivity {

    /**
     * Service binder.
     */
    private IGetInstallReferrerService mService;
    private final static String[] SERVICE_PACKAGE_NAMES = {"com.skt.skaf.A000Z00040", "com.kt.olleh.storefront", "com.kt.olleh.istore", "com.lguplus.appstore", "android.lgt.appstore"};
    private final static String SERVICE_NAME = "com.onestore.android.external.installreferrer.GetInstallReferrerService";
    private final static String SERVICE_ACTION_NAME = "com.onestore.android.external.BIND_GET_INSTALL_REFERRER_SERVICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent serviceIntent = new Intent(SERVICE_ACTION_NAME);
        boolean connectionResult = false;
        for (String servicePackageName : SERVICE_PACKAGE_NAMES) {
            serviceIntent.setComponent(new ComponentName(servicePackageName, SERVICE_NAME));
            List<ResolveInfo> intentServices = getPackageManager().queryIntentServices(serviceIntent, 0);
            if (intentServices != null && !intentServices.isEmpty()) {
                connectionResult = bindService(serviceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
                break;
            }
        }
        if (connectionResult) {
            //TODO
        } else {
            //TODO
        }
    }

    private final static int SUCCESS = 0;
    private final static int FAIL = -1;
    private final static int NOT_FOUND = 1;
    private final static int NOT_ALLOWED = 2;
    private final static int BAD_REQUEST = 3;
    private final static int INVALID_PACKAGE_NAME = 4;

    /**
     * Service connection
     */
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            mService = IGetInstallReferrerService.Stub.asInterface(iBinder);
            //TODO
            try {
                Bundle bundle = new Bundle();
                bundle.putString("package_name", getApplicationContext().getPackageName());
                bundle = mService.getInstallReferrer(bundle);
                int resultCode = bundle.getInt("result_code", -10);
                switch (resultCode) {
                    case SUCCESS:
                        String installReferrer = bundle.getString("install_referrer", "");
                        String onestorePid = bundle.getString("onestore_pid", "");
                        long referrerClickTimeStampSeconds = bundle.getLong("referrer_click_timestamp_seconds", -1);
                        long installBeginTimeStampSeconds = bundle.getLong("install_begin_timestamp_seconds", -1);
                        //TODO
                        break;
                    case FAIL:
                        //TODO
                        break;
                    case NOT_FOUND:
                        //TODO
                        break;
                    case NOT_ALLOWED:
                        //TODO
                        break;
                    case BAD_REQUEST:
                        //TODO
                        break;
                    case INVALID_PACKAGE_NAME:
                        //TODO
                        break;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            //TODO
        }
    };
}
