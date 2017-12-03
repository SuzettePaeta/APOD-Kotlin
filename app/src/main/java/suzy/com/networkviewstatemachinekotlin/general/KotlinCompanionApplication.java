package suzy.com.networkviewstatemachinekotlin.general;

import android.app.Application;

import suzy.com.networkviewstatemachinekotlin.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class KotlinCompanionApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/PT_Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
