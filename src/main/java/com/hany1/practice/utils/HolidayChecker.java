package com.hany1.practice.utils;

import com.ibm.icu.util.ChineseCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class HolidayChecker {
    public List<LocalDate> getHolidayList() {
        List<LocalDate> holidayList = new ArrayList<>();
        int year = LocalDate.now().getYear();

        Holiday[] holidays = Holiday.values();
        for (Holiday holiday : holidays) {

            // 공휴일이 양력인 경우
            if (holiday.isSunTypeCalendar()) {
                LocalDate localDate = LocalDate.of(year, holiday.getMonth(), holiday.getDay());

                holidayList.add(localDate);

                // 대체 공휴일이 적용되는 경우
                LocalDate subHoliday = calculateSubHoliday(holiday, localDate);
                if (subHoliday != null) {
                    holidayList.add(subHoliday);
                }
                // 공휴일이 음력인 경우
            } else {
                // 석가탄신일인 경우
                if (holiday == Holiday.BUDDHA_COMING_DAY) {
                    LocalDate mainHoliday = transferToSunCalendarTypeDate(year, holiday);
                    holidayList.add(mainHoliday);
                    continue;
                }

                // 설 또는 추석인 경우
                LocalDate mainHoliday = transferToSunCalendarTypeDate(year, holiday);
                LocalDate plusOneMainHoliday = mainHoliday.plusDays(1);
                LocalDate minusOneMainHoliday = mainHoliday.minusDays(1);

                holidayList.add(mainHoliday);
                holidayList.add(plusOneMainHoliday);
                holidayList.add(minusOneMainHoliday);

                //대체 공휴일 계산
                LocalDate subHoliday = calculateSubHoliday(mainHoliday, plusOneMainHoliday, minusOneMainHoliday);
                if (subHoliday != null) {
                    holidayList.add(subHoliday);
                }
            }
        }
        return holidayList.stream().sorted().collect(Collectors.toList());
    }

    private LocalDate calculateSubHoliday(Holiday holiday, LocalDate localDate) {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int value = dayOfWeek.getValue(); //1: Monday ~ 7: Sunday

        if (holiday.isSubHoliday()) {
            //토요일인 경우 +2일을 더해서 다음주 월요일을 대체 휴일로 만든다.
            if (value == 6) return localDate.plusDays(2);

            //일요일인 경우 +1일을 더해서 다음주 월요일을 대체 휴일로 만든다.
            if (value == 7) return localDate.plusDays(1);
        }

        return null;
    }
    private LocalDate calculateSubHoliday(LocalDate mainHoliday, LocalDate plusOneMainHoliday, LocalDate minusOneMainHoliday) {
        if (minusOneMainHoliday.getDayOfWeek().getValue() == 7)
            return minusOneMainHoliday.plusDays(3);

        if (mainHoliday.getDayOfWeek().getValue() == 7)
            return mainHoliday.plusDays(2);

        if (plusOneMainHoliday.getDayOfWeek().getValue() == 7)
            return plusOneMainHoliday.plusDays(1);

        return null;
    }
    //음력인 날짜를 양력으로 변환한다.
    private LocalDate transferToSunCalendarTypeDate(int year, Holiday holiday) {
        ChineseCalendar cc = new ChineseCalendar();
        Calendar cal = Calendar.getInstance();
        cc.set(ChineseCalendar.EXTENDED_YEAR, year + 2637);
        cc.set(ChineseCalendar.MONTH, holiday.getMonth() - 1);
        cc.set(ChineseCalendar.DAY_OF_MONTH, holiday.getDay());

        cal.setTimeInMillis(cc.getTimeInMillis());

        return LocalDate.ofInstant(cal.toInstant(), ZoneId.systemDefault());
    }

}
