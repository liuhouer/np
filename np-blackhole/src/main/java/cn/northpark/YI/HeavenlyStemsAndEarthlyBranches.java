package cn.northpark.YI;

import java.util.Calendar;

/**
 * @author zhangyang
 * @date 2023年09月23日 11:28:19
 */


public class HeavenlyStemsAndEarthlyBranches {
    private static final String[] HEAVENLY_STEMS = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] EARTHLY_BRANCHES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    public static String getHeavenlyStemAndEarthlyBranch(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute);

        int heavenlyStemIndex = (year - 4) % 10;
        int earthlyBranchIndex = (year - 4) % 12;

        if (month >= 3 && month <= 12) {
            heavenlyStemIndex = (heavenlyStemIndex + month - 1) % 10;
            earthlyBranchIndex = (earthlyBranchIndex + month - 1) % 12;
        } else {
            heavenlyStemIndex = (heavenlyStemIndex + month + 9) % 10;
            earthlyBranchIndex = (earthlyBranchIndex + month + 9) % 12;
        }

        int daysSinceStartOfYear = getDaysSinceStartOfYear(year, month, day);
        int hoursSinceStartOfDay = hour;

        heavenlyStemIndex = (heavenlyStemIndex + daysSinceStartOfYear) % 10;
        earthlyBranchIndex = (earthlyBranchIndex + daysSinceStartOfYear) % 12;
        heavenlyStemIndex = (heavenlyStemIndex + hoursSinceStartOfDay) % 10;
        earthlyBranchIndex = (earthlyBranchIndex + hoursSinceStartOfDay / 2) % 12;

        return HEAVENLY_STEMS[heavenlyStemIndex] + EARTHLY_BRANCHES[earthlyBranchIndex];
    }

    private static int getDaysSinceStartOfYear(int year, int month, int day) {
        int days = 0;
        for (int i = 1; i < month; i++) {
            days += getDaysInMonth(year, i);
        }
        days += day - 1;
        return days;
    }

    private static int getDaysInMonth(int year, int month) {
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int[] daysInMonth = {31, isLeapYear ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysInMonth[month - 1];
    }

    public static void main(String[] args) {
        int year = 2023;
        int month = 9;
        int day = 22;
        int hour = 21;
        int minute = 15;

        String heavenlyStemAndEarthlyBranch = getHeavenlyStemAndEarthlyBranch(year, month, day, hour, minute);
        System.out.println("时支天干结果为: " + heavenlyStemAndEarthlyBranch);
    }
}