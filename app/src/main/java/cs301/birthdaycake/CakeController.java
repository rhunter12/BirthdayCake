package cs301.birthdaycake;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private CakeView cView;
    private CakeModel cModel;

    public CakeController(CakeView view) {
        cView = view;
        cModel = cView.getModel();
    }

    @Override
    public void onClick(View view) {
        //Log.d("button", "Button clicked");
        System.out.println("Button clicked");
        if (cModel.isLit == true) {
            cModel.isLit = false;
        }
        else {
            cModel.isLit = true;
        }

        cView.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        cModel.hasCandles = b;

        cView.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        cModel.numCandlesLit = progress;
        cView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
