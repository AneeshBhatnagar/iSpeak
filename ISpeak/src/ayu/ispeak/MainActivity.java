package ayu.ispeak;

import java.util.ArrayList;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
Button mic,help;
protected static final int RESULT_SPEECH = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		mic=(Button)findViewById(R.id.bmic);
		help=(Button)findViewById(R.id.bhelp);
		mic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(
	                     RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

	             intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

	             try {
	                 startActivityForResult(intent, RESULT_SPEECH);
	             } catch (ActivityNotFoundException a) {
	                 Toast t = Toast.makeText(getApplicationContext(),
	                         "Opps! Your device doesn't support Speech to Text",
	                         Toast.LENGTH_SHORT);
	                 t.show();
	             }
			}
		});
		help.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent next= new Intent("ayu.ispeak.HELP");
					startActivity(next);
			}
		});
		
	}
	  @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event)
	    {
	        if ((keyCode == KeyEvent.KEYCODE_BACK))
	        {
	        	AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
	        	ad.setTitle("Are you sure to exit iSpeak ?");
	 		    ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {  
	 		    public void onClick(DialogInterface dialog, int whichButton) {  
	 		         finish();
	 	        	 return;                  
	 		    }}); 
	        	   
	        	ad.setNegativeButton("No", new DialogInterface.OnClickListener() {  
	        	public void onClick(DialogInterface dialog, int whichButton) {              
	        		dialog.cancel();
	        	}}); 
	            ad.show();
	        }
	        return super.onKeyDown(keyCode, event);
	    }
	  @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	 
	        switch (requestCode) {
	        case RESULT_SPEECH: {
	            if (resultCode == RESULT_OK && null != data) {
	 
	                ArrayList<String> text = data
	                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	                String returnString=text.get(0).toLowerCase();
	                Toast.makeText(this,returnString,Toast.LENGTH_LONG).show();
	                
	                if(returnString.contains("google")){
	                	String s=returnString;
	                	s=s.substring(6);
	                	s.replace(" ", "+");
	                	Intent i=new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.google.co.in/search?q="+s+"&oq="+s));
	                	startActivity(i);
	                }
	                
	                else if (returnString.contains("open")){
	                	String s=returnString;
	                	s=s.substring(4);
	                	s.replace(" ", "+");
	                	Uri site=Uri.parse("http://www.google.com");
	                	if(s.contains("results"))
	                		site=Uri.parse("http://www.indiaresults.com");
	                	else if(s.contains("facebook"))
	                		site=Uri.parse("http://www.facebook.com");
	                	else if(s.contains("amity"))
	                		site=Uri.parse("http://www.amity.edu");
	                	else if(s.contains("google"))
	                		site=Uri.parse("http://www.google.com");
	                	else if(s.contains("amazon"))
	                		site=Uri.parse("http://www.amazon.com");
	                	else if(s.contains("wikipedia"))
	                		site=Uri.parse("http://www.wikipedia.org");
	                	else if(s.contains("gmail"))
	                		site=Uri.parse("http://www.gmail.com");
	                	else if(s.contains("book"))
	                		site=Uri.parse("http://www.bookmyshow.com");
	                	else if(s.contains("youtube"))
	                		site=Uri.parse("http://www.youtube.com");
	                	
	                	Intent i=new Intent(android.content.Intent.ACTION_VIEW, site);
	                	startActivity(i);
	                	
	                }
	            
	                else if(returnString.contains("ph") || text.get(0).toLowerCase().contains("dial")){
	                	Intent i=new Intent(android.content.Intent.ACTION_DIAL);
	        			startActivity(i);
	                }
	                
	                else if(returnString.contains("map") ){
	                	Intent i=new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:37.827500,-122.481670"));
	        			startActivity(i);
	                }
	                
	                else if(returnString.contains("face") || text.get(0).toLowerCase().contains("book") ){
	                	Intent i =  getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
	                	startActivity(i);
	                }
	                
	                else if(returnString.contains("music") ){
	                	Intent i =  getPackageManager().getLaunchIntentForPackage("com.sonyericsson.music");
	                		startActivity(i);
	                }
	            }
	            break;
	        }
	 
	        }
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
