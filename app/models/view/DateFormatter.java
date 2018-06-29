package models.view;

import utils.ConfigUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static final String defaultFormat = "yyyy/MM/dd HH:mm:ss";

    public static String formatDefaultDate(Date date) {
        String dateFormat = ConfigUtil.get("app.view.view.defaultFormat").getOrElse(defaultFormat);
        return new SimpleDateFormat(dateFormat).format(date);
    }
}
