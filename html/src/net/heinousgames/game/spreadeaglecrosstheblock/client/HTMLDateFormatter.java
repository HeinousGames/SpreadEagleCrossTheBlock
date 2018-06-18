package net.heinousgames.game.spreadeaglecrosstheblock.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DefaultDateTimeFormatInfo;

import net.heinousgames.game.spreadeaglecrosstheblock.SpreadEagles;

import java.util.Date;

/**
 * Created by stevenhanus on 6/13/18
 */
class HTMLDateFormatter implements SpreadEagles.HTMLDateFormat {
    @Override
    public String convertDate(Date date) {
        DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
        DateTimeFormat dateFormat = new DateTimeFormat("mm:ss", info) {};
        return dateFormat.format(date);
    }
}
