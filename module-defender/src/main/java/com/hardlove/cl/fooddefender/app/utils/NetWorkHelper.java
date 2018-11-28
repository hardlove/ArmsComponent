package com.hardlove.cl.fooddefender.app.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

/**
 * Created by Chenlu on 2017/11/17
 */

public class NetWorkHelper extends BroadcastReceiver {
    WifiManager wifiManager;
    private static final String TAG = "NetWorkHelper";

    @Override
    public void onReceive(Context context, Intent intent) {
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        final String action = intent.getAction();
        if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            handleWifiStateChange(context, intent);
        } else if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
            handleWifiScanResult(context, intent);
        } else if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            handleWifiNetworkStateChange(context, intent);
        } else if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            handleConnectivityChange(context, intent);

        }
    }

    /**
     * 处理网络变更
     *
     * @param context
     * @param intent
     */
    private void handleConnectivityChange(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        StringBuilder sb = new StringBuilder();
        if (activeNetwork != null) {
            int type = activeNetwork.getType();
            String typeName = activeNetwork.getTypeName();
            NetworkInfo.State state = activeNetwork.getState();
            NetworkInfo.DetailedState detailedState = activeNetwork.getDetailedState();
            String extraInfo = activeNetwork.getExtraInfo();
            String reason = activeNetwork.getReason();
            boolean failover = activeNetwork.isFailover();//判断是否连接失败
            sb.append(activeNetwork.toString());

        } else {
            sb.append("当前没有网络连接，请确保你已经打开网络 ");
        }
        Log.d(TAG, "ConnectivityManager.CONNECTIVITY_ACTION~~~~~~~~\n" + sb.toString());
    }

    /**
     * 处理Wifi 开关状态变更
     *
     * @param context
     * @param intent
     */
    private void handleWifiStateChange(Context context, Intent intent) {
        int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);
        int previousWifiState = intent.getIntExtra(WifiManager.EXTRA_PREVIOUS_WIFI_STATE, -1);
        Log.d(TAG, "WifiManager.WIFI_STATE_CHANGED_ACTION~~~~~~~~~~~wifiState:" + wifiState + "  previousWifiState:" + previousWifiState);
        if (wifiState == WifiManager.WIFI_STATE_DISABLING) {//0
            Log.d(TAG, "正在关闭");
        } else if (wifiState == WifiManager.WIFI_STATE_DISABLED) {//1
            Log.d(TAG, "已经关闭");
        } else if (wifiState == WifiManager.WIFI_STATE_ENABLING) {//2
            Log.d(TAG, "正在打开");
        } else if (wifiState == WifiManager.WIFI_STATE_ENABLED) {//3
            Log.d(TAG, "已经打开");
        } else if (wifiState == WifiManager.WIFI_STATE_UNKNOWN) {
            Log.d(TAG, "未知状态");
        }
    }

    /**
     * 处理wifi网络状态变更
     *
     * @param context
     * @param intent
     */
    private void handleWifiNetworkStateChange(Context context, Intent intent) {
        NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        String bssid = intent.getStringExtra(WifiManager.EXTRA_BSSID);
        WifiInfo wifiInfo = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO);

        StringBuilder sb = new StringBuilder();
        sb.append("bssid:").append(bssid).append("\n");
        sb.append("wifiInfo:\n");
        if (wifiInfo != null) {
            sb.append(wifiInfo.toString());
        } else {
            sb.append("Null\n");
        }
        sb.append("networkInfo:\n");
        if (networkInfo != null) {
            sb.append(networkInfo.toString()).append("\n");

            NetworkInfo.State state = networkInfo.getState();
            boolean isConnected = state == NetworkInfo.State.CONNECTED;//当然，这边可以更精确的确定状态
            if (isConnected) {

                // 记录当前连接的非flashAir wifi
                String ssid = wifiInfo.getSSID();

                // TODO: 2018/11/28   need fix
//                if (!FlashAirUtils.isFlashair(ssid)) {
//                    Log.i(TAG, "更新可用的wifi， ssid：" + ssid + " wifiInfo.getNetworkId()：" + wifiInfo.getNetworkId());
//                    FDApplication.getInstance().setLastNetworkId(wifiInfo.getNetworkId());
//                }
            }

        } else {
            sb.append("Null");
        }
        Log.d(TAG, "WifiManager.NETWORK_STATE_CHANGED_ACTION~~~~~~~~~~~\n" + sb.toString());

    }

    /**
     * 处理Wifi扫描结果
     *
     * @param context
     * @param intent
     */
    private void handleWifiScanResult(Context context, Intent intent) {
        boolean resultsUpdated = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
        List<ScanResult> scanResults = wifiManager.getScanResults();// need permission
        if (scanResults == null) {
            Log.d(TAG, "wifiManager.getScanResults() is null");
            return;
        }
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scanResults.size(); i++) {
            ScanResult result = scanResults.get(i);
            sb.append("SSID:").append(result.SSID).append("\n");
        }
        sb.append("size:" + scanResults.size());
        Log.d(TAG, "WifiManager.SCAN_RESULTS_AVAILABLE_ACTION~~~~~resultsUpdated:" + resultsUpdated + "\ndetails:" + sb.toString());

    }
}


