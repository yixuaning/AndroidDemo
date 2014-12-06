package com.wechatlomo;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserFragment extends Fragment {
	
    @Override   
    public void onCreate(Bundle savedInstanceState)   
    {   
        System.out.println("RightFragment--->onCreate");   
        super.onCreate(savedInstanceState);   
    }   
     
    @Override   
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)   
    {   
        System.out.println("RightFragment--->onCreateView");   
        return inflater.inflate(R.layout.activity_user_fragment, container, false);   
    } 
}
