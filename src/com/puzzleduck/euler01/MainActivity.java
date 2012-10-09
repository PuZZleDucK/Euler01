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

		count = (EditText)findViewById(R.id.count);
		resultView = (TextView) findViewById(R.id.result);
		mainText = (EditText) findViewById(R.id.displaytext);
		mainText.setHorizontallyScrolling(true);//that's all it needed
		mainText.setTextSize(11);
		mainText.setTextColor(Color.WHITE);
		
	}//create


	public void onClick(View p1)
	{
		if(p1.getId() == R.id.compute)
		{
            int countLimit = Integer.parseInt(count.getText().toString());
			String separator = "";

			ArrayList<Integer> strikesStart = new ArrayList<Integer>();
			ArrayList<Integer> strikesEnd = new ArrayList<Integer>();
			int sumTotal = 0;
			StringBuffer unmarkedText = new StringBuffer();
			for( int i = 1; i < countLimit; i++)
			{		

				if(i%20 == 1)
				{
					unmarkedText.append("\n");
					separator = "";
				}	
				unmarkedText.append(separator);

				if(i < 10)
				{
					unmarkedText.append("0");
				}
				if(i < 100)
				{
					unmarkedText.append("0");
				}
				int startTag = unmarkedText.length();

				unmarkedText.append(i);

				if(factorOf(i, aValue) || factorOf(i, bValue))
				{
					sumTotal += i;
				}else
				{
					strikesStart.add(Integer.valueOf(startTag));
					strikesEnd.add(Integer.valueOf(unmarkedText.length()));
				}
				separator = ", ";
			}

			SpannableString htmlText = new SpannableString(unmarkedText);
			htmlText.setSpan(new  StrikethroughSpan(), strikesStart.get(0) , strikesEnd.get(0) , 0);
			for (int index = 0; index < strikesStart.size(); index++)
			{
				htmlText.setSpan(new  ForegroundColorSpan(Color.RED), strikesStart.get(index), strikesEnd.get(index) , 0);
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
			if(aValue == 1){aValue=2;}
			a.setText(""+aValue);
		}
		
		if(p1.getId() == R.id.decb)
		{
			bValue -= 1;
			if(bValue == 1){bValue=2;}
			b.setText(""+bValue);
		}
		
		
		
		
	}
	
	
	
	private boolean factorOf(int i, int factor)
	{
		// TODOne: Just checks if modulus is zero.
		return i % factor == 0;
	}
}
	
	
	
