package com.example.trazemaze2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.TextView;

public class PracticeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice);
		//Getting the practice letter
		Intent intent = getIntent();
		String practice_letter = intent.getStringExtra("practice");
		
		//Setting the content of the text to the practice letter
		TextView t = (TextView) findViewById(R.id.canvas);
		t.setText(practice_letter);
		
		//Initializing the canvas and setting its bitmap to the screenshot of the practice letter
		final DrawingView draw = (DrawingView) findViewById(R.id.drawing);
		draw.setVisibility(View.INVISIBLE);
		final Bitmap b = takeScreenshot(this, R.id.root);
		draw.setbitmap(b);
		draw.setVisibility(View.VISIBLE);
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
}