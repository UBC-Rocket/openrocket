package net.sf.openrocket.android.motor;

import net.sf.openrocket.R;
import net.sf.openrocket.android.db.DbAdapter;
import net.sf.openrocket.android.util.AndroidLogWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SlidingDrawer;

public class MotorDetailsActivity extends FragmentActivity
implements SlidingDrawer.OnDrawerCloseListener, SlidingDrawer.OnDrawerOpenListener {

	private SlidingDrawer slidingDrawer;
	private ImageView handle;
	
	private ExtendedThrustCurveMotor motor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidLogWrapper.d(MotorDetailsActivity.class,"onCreate Bundle = "+ String.valueOf(savedInstanceState));
		setContentView(R.layout.motor_detail);

		Intent i = getIntent();
		long motorId = i.getLongExtra("Motor",-1);

		DbAdapter mDbHelper = new DbAdapter(this);
		mDbHelper.open();

		try {
			motor = mDbHelper.getMotorDao().fetchMotor(motorId);
		} catch ( Exception e ) {
			
		}

		mDbHelper.close();
		
		BurnPlotFragment burnPlot = (BurnPlotFragment) getSupportFragmentManager().findFragmentById(R.id.burnPlotFragment);
		burnPlot.init(motor);
		
		MotorDetailsFragment motorDetails = (MotorDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.motorDetailForm);
		motorDetails.init(motor);
		
		slidingDrawer = (SlidingDrawer) findViewById(R.id.drawer);
		
		slidingDrawer.setOnDrawerOpenListener(this);
		slidingDrawer.setOnDrawerCloseListener(this);
		
		handle = (ImageView) findViewById(R.id.handle);
		
	}
	
	@Override
	public void onDrawerOpened() {
		handle.setImageResource(R.drawable.arrow_down_float);
	}
	
	@Override
	public void onDrawerClosed() {
		handle.setImageResource(R.drawable.arrow_up_float);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.motor_details_option_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()) {
		case R.id.save:
			// Extract form data to Motor.
			// Save motor.
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}


}