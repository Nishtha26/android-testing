package com.ot.devicecheck;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pranjal Gupta on 15-10-2017.
 */

public class DeviceInfo extends AppCompatActivity {

    //String[] names = {"OS Version", "Release", "Device"};
    List<String> values;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.device_info);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("OT Device Check");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        List<String> names = Arrays.asList("OS Version", "Version Release", "Device", "Model", "Product", "Brand", "Display", "Hardware", "ID", "Manufacturer", "Serial", "User", "Host", "Total RAM", "Free RAM", "Total Int Memory", "Available Int Memory");

        values = getDeviceInfo();
        ListView name = (ListView)findViewById(R.id.list_name);

        ViewAdapter adapter = new ViewAdapter(getBaseContext(),names,values);


        name.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public List<String> getDeviceInfo(){
        String _OSVERSION = System.getProperty("os.version");
        String _RELEASE = android.os.Build.VERSION.RELEASE;
        String _DEVICE = android.os.Build.DEVICE;
        String _MODEL = android.os.Build.MODEL;
        String _PRODUCT = android.os.Build.PRODUCT;
        String _BRAND = android.os.Build.BRAND;
        String _DISPLAY = android.os.Build.DISPLAY;
        String[] _CPU_ABI = Build.SUPPORTED_ABIS;
        String _UNKNOWN = android.os.Build.UNKNOWN;
        String _HARDWARE = android.os.Build.HARDWARE;
        String _ID = android.os.Build.ID;
        String _MANUFACTURER = android.os.Build.MANUFACTURER;
        String _SERIAL = android.os.Build.SERIAL;
        String _USER = android.os.Build.USER;
        String _HOST = android.os.Build.HOST;
        long free = freeRamMemorySize();
        String freeRam = (""+free+" MB");
        String totalRam = (""+totalRamMemorySize()+" MB");
        String freeMem = formatSize(getAvailableInternalMemorySize());
        String totalMem = formatSize(getTotalInternalMemorySize());

        Log.i("info",_OSVERSION+" "+_RELEASE+" "+_DEVICE+" "+_MODEL+" "+" "+_PRODUCT+" "+_BRAND+" "+_DISPLAY+" "+_CPU_ABI+" "+_UNKNOWN+" "+_HARDWARE+" "+_ID+" "+_MANUFACTURER+" "+_SERIAL+" "+_USER+" "+_HOST);

        List<String> result = Arrays.asList(""+_OSVERSION,""+_RELEASE,""+_DEVICE,""+_MODEL,""+_PRODUCT,""+_BRAND,""+_DISPLAY,""+_HARDWARE,""+_ID,""+_MANUFACTURER,""+_SERIAL,""+_USER,""+_HOST,""+freeRam,""+totalRam,""+totalMem,""+freeMem);

        return result;
    }

    private long freeRamMemorySize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.availMem / 1048576L;

        return availableMegs;
    }

    private long totalRamMemorySize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.totalMem / 1048576L;
        return availableMegs;
    }

    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    public static String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = " KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = " MB";
                size /= 1024;
            }
        }
        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }
        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }
}
