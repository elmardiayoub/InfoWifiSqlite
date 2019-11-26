package com.example.litewifi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView name,freq,addr,strength,receive,transmit,ip;
    WifiManager wifiManager;
    WifiInfo connection;
    My_Bdd bd;
    ArrayList arrayList ;
    String res = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd  = new My_Bdd(this);

        Button btn = (Button) findViewById(R.id.btn);
        name = (TextView) findViewById(R.id.name);
        freq = (TextView) findViewById(R.id.frequence);
        addr = (TextView) findViewById(R.id.macAdrs);
        strength = (TextView) findViewById(R.id.strength);
        receive = (TextView) findViewById(R.id.receive);
        ip = (TextView) findViewById(R.id.ip);




        btn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                    wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    connection = wifiManager.getConnectionInfo();

                    name.setText(connection.getSSID());
                    freq.setText(connection.getFrequency() +" Mhz");
                    addr.setText(connection.getMacAddress());
                    strength.setText(connection.getRssi()+" dBm");
                    receive.setText(Integer.toString(connection.getLinkSpeed()) );

                    //receive.setText(Integer.toString(connection.getRxLinkSpeedMbps() )+" Mbs");
                    //transmit.setText(Integer.toString(connection.getTxLinkSpeedMbps())+" Mbs");
                    int ip1 = connection.getIpAddress();
                    String ipAddress = Formatter.formatIpAddress(ip1);
                    ip.setText(ipAddress);

                    Date currentTime = Calendar.getInstance().getTime();

                    bd.inserer_ligne(connection.getSSID(),connection.getRssi(),
                            connection.getFrequency(),connection.getMacAddress(),
                            connection.getLinkSpeed(),
                            ipAddress,currentTime);
                }

        });

        Button btn2 = (Button) findViewById(R.id.button5);
        btn2.setOnClickListener(new View.OnClickListener() {
       @Override
            public void onClick(View v) {
               arrayList = bd.getTousLignes();
               for (int i= 0;i<arrayList.size();i++)
               {
                   res+= arrayList.get(i);
               }
                Intent start = new Intent(getApplicationContext(),Main2Activity.class);
                start.putExtra("res1",res);
              startActivity(start);
            }
        });
    }
}
