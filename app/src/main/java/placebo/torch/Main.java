package placebo.torch;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */
    Camera camera;
    ToggleButton toggleButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toggleButton=(ToggleButton)findViewById(R.id.toggleButtonToarch);




        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    camera=Camera.open();
                    Camera.Parameters params = camera.getParameters();
                    params.setFlashMode( Camera.Parameters.FLASH_MODE_TORCH );
                    camera.setParameters(params);
                    Toast.makeText(getApplicationContext(),"Torch On",Toast.LENGTH_LONG).show();
                }
                else if (!b){
                    Camera.Parameters params = camera.getParameters();
                    params.setFlashMode( Camera.Parameters.FLASH_MODE_OFF );
                    camera.setParameters(params);
                    camera.release();
                    Toast.makeText(getApplicationContext(),"Torch Off",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (camera != null) {
            camera.release();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null) {
            camera.release();
        }
    }
}
