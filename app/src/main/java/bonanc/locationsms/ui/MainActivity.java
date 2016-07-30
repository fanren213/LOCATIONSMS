package bonanc.locationsms.ui;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bonanc.locationsms.R;
import bonanc.locationsms.util.FindLocation;

public class MainActivity extends Activity {
    private Button submitButton;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    double latitude = 0;
    double longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton = (Button) findViewById(R.id.button);
        latitudeTextView = (TextView) findViewById(R.id.text2);
        longitudeTextView = (TextView) findViewById(R.id.text4);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindLocation mFindLocation = new FindLocation(v.getContext());
                Location location = mFindLocation.getLocation();
                try {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    } else {
                        throw new Exception("Can't get location.");
                    }
                    latitudeTextView.setText(Double.toString(latitude));
                    longitudeTextView.setText(Double.toString(longitude));
                    String phone = "4126609999";
                    String context = "Current Location - latitude: " + Double.toString(latitude)
                            + " longitude: " + Double.toString(longitude);
                    SmsManager manager = SmsManager.getDefault();
                    ArrayList<String> list = manager.divideMessage(context);

                    for (String text : list) {
                        manager.sendTextMessage(phone, null, text, null, null);
                    }
                    Toast.makeText(getApplicationContext(), "Message sent.", Toast.LENGTH_SHORT).show();
                }
                catch(SecurityException e){
                    Toast.makeText(getApplicationContext(),
                            "SMS permission denied.", Toast.LENGTH_SHORT).show();
                    Log.e("EX", e.getMessage());
                }
                catch(Exception e){
                    Log.e("EX",e.getMessage());
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
