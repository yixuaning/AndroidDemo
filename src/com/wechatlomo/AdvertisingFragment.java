package com.wechatlomo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AdvertisingFragment extends Fragment {
	
	List<String> imgList;
	ImageView advImg, playBtn;

	int imageIndex = 1, playBtnImgIndex = 1;
	int playImageId[] = { R.drawable.cover_slide1, R.drawable.cover_slide2,
			R.drawable.cover_slide3, R.drawable.cover_slide4,
			R.drawable.cover_slide5, R.drawable.cover_slide6,
			R.drawable.cover_slide7, R.drawable.cover_slide8 };
	Thread imageThread, playThread;
	Animation alphaAnimation;
	final Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			playBtn.setBackgroundDrawable(getResources().getDrawable(
					playImageId[playBtnImgIndex]));
			playBtnImgIndex++;
			if (playBtnImgIndex == 8) {
				playBtnImgIndex = 0;
				advImg.startAnimation(alphaAnimation);
				advImg.setBackground(new BitmapDrawable(getLoacalBitmap(imgList
						.get(imageIndex))));
				imageIndex++;
				if (imageIndex == 4) {
					imageIndex = 0;
				}
			}
			handler.postDelayed(this, 1000);
		}
	};
	
	public Bitmap getLoacalBitmap(String url) {
	     try {
	          FileInputStream fis = new FileInputStream(url);
	          return BitmapFactory.decodeStream(fis);
	     } catch (FileNotFoundException e) {
	          e.printStackTrace();
	          return null;
	     }
	}

	public boolean checkIsImageFile(String fName) {
		boolean isImageFile = false;

		// 获取扩展名
		String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
				fName.length()).toLowerCase();
		if (FileEnd.equals("jpg") || FileEnd.equals("gif")
				|| FileEnd.equals("png") || FileEnd.equals("jpeg")
				|| FileEnd.equals("bmp")) {
			isImageFile = true;
		} else {
			isImageFile = false;
		}

		return isImageFile;
	}

	public List<String> getPictures(final String strPath) {
		// 图片列表
		List<String> picList = new ArrayList<String>();

		// 得到sd卡内路径
		String imagePath = Environment.getExternalStorageDirectory().toString();
		Log.e("yixaun", imagePath);
		// 得到该路径文件夹下所有的文件
		File mfile = new File(imagePath);
		File[] files = mfile.listFiles();

		// 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (checkIsImageFile(file.getPath())) {
				picList.add(file.getPath());
				Log.e("yixaun", file.getPath());
			}
		}

		// 返回得到的图片列表
		return picList;
	}
	
    /** Fragment第一次附属于Activity时调用,在onCreate之前调用 */  
    @Override   
    public void onAttach(Activity activity)   
    {   
        super.onAttach(activity);   
        System.out.println("LeftFragment--->onAttach");
    }   
     
    @Override   
    public void onCreate(Bundle savedInstanceState)   
    {   
        super.onCreate(savedInstanceState);   
        System.out.println("LeftFragment--->onCreate");
    }   
     
    @Override   
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)   
    {   
        System.out.println("LeftFragment--->onCreateView");   
        return inflater.inflate(R.layout.activity_advertising_fragment, container, false);   
    }   
     
    @Override   
    public void onResume()   
    {   
        super.onResume();   
        System.out.println("LeftFragment--->onResume");  

        imgList = getPictures("/mnt/media_rw/sdcard");
        
        advImg = (ImageView) getActivity().findViewById(R.id.advImg);
        playBtn = (ImageView) getActivity().findViewById(R.id.playBtn);
		alphaAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_in);
		handler.post(runnable);
    }
    
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
        System.out.println("LeftFragment--->onDestroyView"); 
        handler.removeCallbacks(runnable);
	}
}
