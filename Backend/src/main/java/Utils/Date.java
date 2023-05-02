/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 *
 * @author giova
 */
public class Date {
    public static String getDate(){
        return LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }
    
    public static long getDaysFrom(String date) {
        LocalDate theDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        return DAYS.between(theDate, currentDate);
    }
}
