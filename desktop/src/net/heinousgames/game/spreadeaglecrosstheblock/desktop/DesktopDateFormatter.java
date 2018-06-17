package net.heinousgames.game.spreadeaglecrosstheblock.desktop;

import net.heinousgames.game.spreadeaglecrosstheblock.SpreadEagles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by stevenhanus on 6/13/18
 */
class DesktopDateFormatter implements SpreadEagles.HTMLDateFormat {
    @Override
    public String convertDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("mm:ss", Locale.US);
        return formatter.format(date);
    }
}
