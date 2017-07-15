package util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class  Util {

    public static Date convertToDate(String data){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formatter.parse(data);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static java.sql.Date convertToDateSql(Date data){
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        return sqlDate;
    }

    public static Calendar convertToCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String convertBigDecimalToStringFormat(BigDecimal bigDecimal){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format (bigDecimal);
    }
}
