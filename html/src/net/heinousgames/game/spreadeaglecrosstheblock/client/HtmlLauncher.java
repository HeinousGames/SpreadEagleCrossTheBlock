package net.heinousgames.game.spreadeaglecrosstheblock.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import net.heinousgames.game.spreadeaglecrosstheblock.SpreadEagles;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(1334, 750);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new SpreadEagles();
        }

        @Override
        public ApplicationListener createApplicationListener() {
                return null;
        }
}