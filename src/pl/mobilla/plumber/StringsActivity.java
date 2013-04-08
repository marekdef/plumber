package pl.mobilla.plumber;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class StringsActivity extends Activity
{
    private TextView textMemory;
    private ImageView imageView;
    private Handler handler;

    private boolean doGC;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textMemory = (TextView) findViewById(R.id.textViewMemory);
        imageView = (ImageView) findViewById(R.id.imageViewCog);

        refreshMemoryInfo();

        imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doGC = !doGC;
            }
        });

        final List<Object> aList = new ArrayList<Object>();
        
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    String plumber = new String(RandomStringUtils.randomAlphabetic(255));
                    aList.add(plumber);
                    if(doGC)
                    {
                        System.gc();
                        System.gc();
                    }
                    handler.post(this);
                    refreshMemoryInfo();
                }
                catch(OutOfMemoryError e) {
                    aList.clear();;
                    Toast.makeText(StringsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void refreshMemoryInfo() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        StringBuilder sb = new StringBuilder();

        sb.append("Available memory ");
        sb.append(humanReadableByteCount(mi.availMem, false))   ;
        sb.append('\n');
        sb.append("For the application ");
        sb.append(activityManager.getMemoryClass());
        sb.append("Mb");
        sb.append('\n');
        sb.append("For the application (large) ");
        sb.append(activityManager.getLargeMemoryClass());
        sb.append("Mb");
        sb.append('\n');
        sb.append("Free (current) memory is ");
        sb.append(humanReadableByteCount(Runtime.getRuntime().freeMemory(), false))   ;
        sb.append('\n');
        sb.append("Total (current) memory is ");
        sb.append(humanReadableByteCount(Runtime.getRuntime().totalMemory(), false))   ;
        sb.append('\n');

        sb.append("GC is o");
        sb.append(doGC ? "n" : "off");
        sb.append('\n');

        textMemory.setText(sb.toString());
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

}
