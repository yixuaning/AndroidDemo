package com.wechatlomo;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        System.out.println("Activity--->onCreate");   
        
        FragmentManager manager = getFragmentManager();   
        FragmentTransaction transaction = manager.beginTransaction();   
        // 动态增加Fragment   
        UserFragment rightFragment = new UserFragment();   
        AdvertisingFragment leftFragment = new AdvertisingFragment();   
        transaction.add(R.id.left_layout, leftFragment, "leftfragment");   
        transaction.add(R.id.right_layout, rightFragment, "rightfragment");   
        transaction.commit();
	}

}
