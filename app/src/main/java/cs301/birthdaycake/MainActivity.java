package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        CakeView myCakeView = (CakeView) findViewById(R.id.cakeview);
        CakeController myController = new CakeController(myCakeView);

        Button blowOut = (Button)findViewById(R.id.blow_out);
        blowOut.setOnClickListener(myController);

        Switch candleSwitch = (Switch)findViewById(R.id.candles_toggle);
        candleSwitch.setOnCheckedChangeListener(myController);

        SeekBar candleBar = (SeekBar)findViewById(R.id.candle_bar);
        candleBar.setOnSeekBarChangeListener(myController);
    }

    public void goodbye(View button) {
        //System.out.println("Goodbye");
        Log.i("button", "Goodbye");
    }
}
