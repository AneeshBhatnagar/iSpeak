package ayu.ispeak;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends Activity {

	/*@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent next=new Intent("ayu.ispeak.MAINACTIVITY");
		startActivity(next);
		
	}*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		TextView tvhelp=(TextView)findViewById(R.id.tvhelp);
		tvhelp.setTextColor(Color.WHITE);
		tvhelp.setText("Welcome to iSpeak\n\n\t"
				+"Ask your smart phone to open various websites which are mentioned below so you dont need to type them any more. Just say open followed by website name." +
				"\n\n facebook,google,amity,amazon,yahoo,gmail,bookmyshow,youtube,wikipedia.\n\n\t-developed by Ayushi");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
