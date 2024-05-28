package com.hany1.practice.utils;

import lombok.Getter;

@Getter
public enum Holiday {
    NEW_YEAR_DAY(CalendarType.SUN, 1, 1, false, "신정"),
    INDEPENDENCE_MOVEMENT_DAY(CalendarType.SUN, 3, 1, true, "삼일절"),
    CHILDREN_DAY(CalendarType.SUN, 5, 5, true, "어린이날"),
    MEMORIAL_DAY(CalendarType.SUN, 6, 6, false, "현충일"),
    NATIONAL_LIBERATION_DAY(CalendarType.SUN, 8, 15, true, "광복절"),
    NATIONAL_FOUNDATION_DAY(CalendarType.SUN, 10, 3, true, "개천절"),
    HANGUL_DAY(CalendarType.SUN, 10, 9, true, "한글날"),
    CHRISTMAS_DAY(CalendarType.SUN, 12, 25, false, "크리스마스"),
    MOON_NEW_YEAR_DAY(CalendarType.MOON, 1, 1, true, "설날"),
    BUDDHA_COMING_DAY(CalendarType.MOON, 4, 8, false, "석가탄신일"),
    THANKSGIVING_DAY(CalendarType.MOON, 8, 15, true, "추석");

    private CalendarType calendarType;
    private int month;
    private int day;
    private boolean isSubHoliday;
    private String description;

    Holiday(CalendarType calendarType, int month, int day, boolean isSubHoliday, String description) {
        this.calendarType = calendarType;
        this.month = month;
        this.day = day;
        this.isSubHoliday = isSubHoliday;
        this.description = description;
    }

    public boolean isSunTypeCalendar() {
        return this.calendarType == CalendarType.SUN;
    }
}
