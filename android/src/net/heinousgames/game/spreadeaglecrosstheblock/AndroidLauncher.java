package net.heinousgames.game.spreadeaglecrosstheblock;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements SpreadEagles.SendFeedback {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		SpreadEagles game = new SpreadEagles();
		game.setFeedbackCallback(this);

		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(game, config);
	}

	@Override
	public void sendEmail() {
		String versionName = null;
        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
			versionName = "Version Name Not Found";
        }

        String s = "App Version: " + versionName +
                "\nOS API Level: " + Build.VERSION.RELEASE +
                "\nDevice: " + Build.MODEL + "\n\n";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ministeve412@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Spread Eagles Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, s);
        startActivity(Intent.createChooser(emailIntent, "Send Feedback"));
	}
}
