package com.example.trazemaze2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class EnglishActivity extends Activity {
	
	private	LinearLayout root;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_english);
		root= (LinearLayout) findViewById(R.id.main_root);
		LayoutGenerator('A', 'Z', 6);
		LayoutGenerator('a', 'z', 6);
		LayoutGenerator('0', '9', 5);
	}
	
	private void LayoutGenerator(char start, char end , int limit) {
		
		int count=0;
		LinearLayout l= new LinearLayout(EnglishActivity.this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		
		for(char i=start;i<=end;i++){
			final String practice_letter=""+i; //char to string, final as it is not edited in the duration of its use

			//button for current character
			Button b = new Button(EnglishActivity.this);
			b.setText(practice_letter);
			//centering the text in the button
			b.setGravity(Gravity.CENTER);
			b.setLayoutParams(params);
			b.setOnClickListener(new OnClickListener() {
			    public void onClick(View v) {
			    	Intent intent = new Intent(EnglishActivity.this, PracticeActivity.class);
			        intent.putExtra("practice", practice_letter);
			        startActivity(intent);
			    } 
			});
			
			//adding button to view
			l.addView(b);
			count++;
			
			// resetting the LinearLayout if the maximum in that row are reached after adding it to the root layout
			if (count==limit) {
				root.addView(l);
				l= new LinearLayout(EnglishActivity.this);
				count=0;
			}
		}
		
		//if there are any buttons remaining in l
		root.addView(l);
	}
}