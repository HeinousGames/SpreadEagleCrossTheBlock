package net.heinousgames.game.spreadeaglecrosstheblock;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		SpreadEagles game = new SpreadEagles(new AndroidDateFormatter());

		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(game, config);
	}
}
