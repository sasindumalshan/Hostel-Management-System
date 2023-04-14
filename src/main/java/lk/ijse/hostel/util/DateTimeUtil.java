package lk.ijse.hostel.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateTimeUtil {
    public static String getDateNow() {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
}
