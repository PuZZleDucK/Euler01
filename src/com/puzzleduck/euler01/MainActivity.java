package com.puzzleduck.euler01;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.text.*;
import android.text.style.*;
import java.util.*;
import android.graphics.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	private static EditText mainText;
	private static EditText count;
	private static TextView resultView;
	private static TextView a;
	private static TextView b;
	private int aValue = 3;
	private int bValue = 5;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		Button incA = (Button)findViewById(R.id.inca);
		Button incB = (Button)findViewById(R.id.deca);
		Button decA = (Button)findViewById(R.id.incb);
		Button decB = (Button)findViewById(R.id.decb);
		Button compute = (Button)findViewById(R.id.compute);
		a = (TextView) findViewById(R.id.a);
		b = (TextView) findViewById(R.id.b);
		
		
		incA.setOnClickListener(this);
		incB.setOnClickListener(this);
		decA.setOnClickListener(this);
		decB.setOnClickListener(this);
		compute.setOnClickListener(this);
		
	//	ScrollView mainScroll = (ScrollView) findViewById(R.id.displayscroll);
	//	mainScroll.setHorizontalScrollBarEnabled(true);
	//	mainScroll.setMinimumWidth(500);
		mainText = (EditText) findViewById(R.id.displaytext);
	//	mainText.setMinimumHeight(500);
	//	mainText.setWidth(500);
		mainText.setHorizontalScrollBarEnabled(true);
		mainText.setHorizontallyScrolling(true);
		mainText.setTextSize(11);
		mainText.setTextColor(Color.WHITE);
		mainText.setText("code text");
		mainText.setHorizontalScrollBarEnabled(true);
	//	mainText.setLayoutParams( ViewGroup.LayoutParams.FILL_PARENT );
		mainText.append(Html.fromHtml("code <b>append</b>\n"));
	//	numbera numberb result

	//	NumberPicker numberA = (NumberPicker) findViewById(R.id.numbera);
	//	NumberPicker numberB = (NumberPicker) findViewById(R.id.numberb);
		resultView = (TextView) findViewById(R.id.result);
		count = (EditText)findViewById(R.id.count);
	//	numberA.setValue(5);
	//    numberA.setBottom(1);
	//	numberB.setValue(3);
	//	numberA.setOnClickListener(this);
		
	}

	
	

	public void onClick(View p1)
	{
		// TODOing: Implement this method
		
		if(p1.getId() == R.id.compute)
		{
		//	mainText.setText("compute");
		// remove old spans
		
            int countLimit = Integer.parseInt(count.getText().toString());
			String separator = "";

		//	ArrayList<SpannableString> strikes = new ArrayList<SpannableString>();
			ArrayList<Integer> strikesStart = new ArrayList<Integer>();
			ArrayList<Integer> strikesEnd = new ArrayList<Integer>();
			int sumTotal = 0;
			StringBuffer unmarkedText = new StringBuffer();
			for( int i = 1; i < countLimit; i++)
			{		
				//SpannableString htmlText = new SpannableString("");
				//String tag = "";
				//String endtag = "";
				//	int startTag = mainText.length();

				if(i%20 == 1)
				{
					//mainText.append("\n");
					unmarkedText.append("\n");
				}	
				unmarkedText.append(separator);
				int startTag = unmarkedText.length();

				//mainText.append(Html.fromHtml(separator + i ));
				unmarkedText.append(i);

				if(factorOf(i, aValue) || factorOf(i, bValue))
				{
					sumTotal += i;
				}else
				{
					//	htmlText.setSpan(new  StrikethroughSpan(), 0 , htmlText.length() , 0 );
					//new StrikethroughSpan(), startTag , htmlText.length() , 0);
					strikesStart.add(new Integer(startTag));
					strikesEnd.add(new Integer(unmarkedText.length()));
				}
				separator = ", ";
			}

			SpannableString htmlText = new SpannableString(unmarkedText);
			htmlText.setSpan(new  StrikethroughSpan(), strikesStart.get(0) , strikesEnd.get(0) , 0);
			for (int index = 0; index < strikesStart.size(); index++)
			{
				//	htmlText.setSpan(new  StrikethroughSpan(), strikesStart.get(index), strikesEnd.get(index) , 0 );
				htmlText.setSpan(new  SubscriptSpan(), strikesStart.get(index), strikesEnd.get(index) , 0);
			}

			htmlText.setSpan(new  UnderlineSpan(), 0, htmlText.length() , 0);
			mainText.setText(htmlText);
			resultView.setText("\n\nGrand total: " + sumTotal + "\n\n");
		
		}//compute
		
		
		if(p1.getId() == R.id.inca)
		{
			aValue += 1;
			a.setText(""+aValue);
		}
		
		if(p1.getId() == R.id.incb)
		{
			bValue += 1;
			b.setText(""+bValue);
		}
		
		if(p1.getId() == R.id.deca)
		{
			aValue -= 1;
			if(aValue == 0){aValue=1;}
			a.setText(""+aValue);
		}
		
		if(p1.getId() == R.id.decb)
		{
			bValue -= 1;
			if(bValue == 0){bValue=1;}
			b.setText(""+bValue);
		}
		
		
		
		
	}
	
	
	
	private boolean factorOf(int i, int factor)
	{
		// TODOne: Just checks if modulus is zero.
		return i % factor == 0;
	}
}
	
	
	