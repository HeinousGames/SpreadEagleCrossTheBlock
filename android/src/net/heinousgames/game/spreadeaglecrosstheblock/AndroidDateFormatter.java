package net.heinousgames.game.spreadeaglecrosstheblock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by stevenhanus on 6/13/18
 */
class AndroidDateFormatter implements SpreadEagles.HTMLDateFormat {
    @Override
    public String convertDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("mm:ss", Locale.US);
        return formatter.format(date);
    }
}
