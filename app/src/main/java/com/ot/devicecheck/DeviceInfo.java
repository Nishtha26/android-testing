package com.ot.devicecheck;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pranjal Gupta on 15-10-2017.
 */

public class DeviceInfo extends Activity {

    //String[] names = {"OS Version", "Release", "Device"};
    List<String> values;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.device_info);


        List<String> names = Arrays.asList("OS Version", "Version Release", "Device", "Model", "Product", "Brand", "Display", "Hardware", "ID", "Manufacturer", "Serial", "User", "Host");

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

        Log.i("info",_OSVERSION+" "+_RELEASE+" "+_DEVICE+" "+_MODEL+" "+" "+_PRODUCT+" "+_BRAND+" "+_DISPLAY+" "+_CPU_ABI+" "+_UNKNOWN+" "+_HARDWARE+" "+_ID+" "+_MANUFACTURER+" "+_SERIAL+" "+_USER+" "+_HOST);

        List<String> result = Arrays.asList(""+_OSVERSION,""+_RELEASE,""+_DEVICE,""+_MODEL,""+_PRODUCT,""+_BRAND,""+_DISPLAY,""+_HARDWARE,""+_ID,""+_MANUFACTURER,""+_SERIAL,""+_USER,""+_HOST);

        return result;
    }

}
