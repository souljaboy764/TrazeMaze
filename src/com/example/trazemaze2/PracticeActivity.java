package com.example.trazemaze2;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class PracticeActivity extends Activity {
	
	private String practice_letter;
	private Bitmap b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice);
		//Getting the practice letter
		Intent intent = getIntent();
		practice_letter = intent.getStringExtra("practice");

		//Setting the content of the text to the practice letter
		TextView t = (TextView) findViewById(R.id.canvas);
		t.setText(practice_letter);

		//Initializing the canvas and setting its bitmap to the screenshot of the practice letter
		final DrawingView draw = (DrawingView) findViewById(R.id.drawing);
		draw.setVisibility(View.INVISIBLE);
		final Button save = (Button) findViewById(R.id.save);
		save.setVisibility(View.INVISIBLE);
		final Button clear= (Button) findViewById(R.id.clear);
		clear.setVisibility(View.INVISIBLE);
		b = takeScreenshot(this, R.id.root);
		draw.setbitmap(b);
		draw.setVisibility(View.VISIBLE);
		save.setVisibility(View.VISIBLE);
		clear.setVisibility(View.VISIBLE);
	}
	
	public void clear(View v) {
		ProgressDialog pd = new ProgressDialog(PracticeActivity.this);
		pd.show();
		DrawingView draw = (DrawingView) findViewById(R.id.drawing);
		draw.setbitmap(b);
		pd.dismiss();
	}

	public Bitmap takeScreenshot(Activity activity, int ResourceID) { 
		View v1 = findViewById(ResourceID);
		//getting size of view
		v1.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		//selecting the part to be saved
		v1.layout(0, 0, v1.getMeasuredWidth(), v1.getMeasuredHeight()); 
		v1.setDrawingCacheEnabled(true);
		final Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache()); // actually taking the screen shot
		v1.setDrawingCacheEnabled(false);
		return bitmap;
	} 

	public void save(View v) {
		try {
		File mediaStorageDir = new File(Environment.getExternalStorageDirectory()+"/TrazeMaze");
		if (! mediaStorageDir.exists()){
			if (! mediaStorageDir.mkdirs()){
				Log.d("MyCameraApp","failed to create directory");
			}
		}

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File file;
		file = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+practice_letter+"_"+ timeStamp + ".jpg");

		//final String img_name= mediaFile.getAbsolutePath();
		DrawingView drawView = (DrawingView) findViewById(R.id.drawing);
		drawView.setDrawingCacheEnabled(true);
		Bitmap savedImg = drawView.getDrawingCache();
		//MediaStore.Images.Media.insertImage(

		FileOutputStream fOut = new FileOutputStream(file);

		savedImg.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
		fOut.flush();
		fOut.close();
		//getContentResolver(),drawView.getDrawingCache(),img_name, "drawing");

		drawView.destroyDrawingCache();

		Toast savedToast = Toast.makeText(getApplicationContext(),"Drawing saved", Toast.LENGTH_SHORT);
		savedToast.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}