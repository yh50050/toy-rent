package com.toy.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 计算两个日期之间的天数
 * 
 * @author 枫茗丿love
 *
 */
public class CountDaysUtils {

	private static final int[] MONTH_DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static int getBetweenDays(LocalDate startDate, LocalDate endDate) {
		String startDateStr = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String endDateStr = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String[] startDateStrs = startDateStr.split("-");
		String[] endDateStrs = endDateStr.split("-");
		int days = 0;
		int startYear = Integer.parseInt(startDateStrs[0]);
		int startMonth = Integer.parseInt(startDateStrs[1]);
		int startDay = Integer.parseInt(startDateStrs[2]);
		int endYear = Integer.parseInt(endDateStrs[0]);
		int endMonth = Integer.parseInt(endDateStrs[1]);
		int endDay = Integer.parseInt(endDateStrs[2]);
		if (endYear >= startYear) {
			if (endYear == startYear) {
				if (endMonth < startMonth) {
					return -1;
				}
				if (endMonth == startMonth) {
					if (endDay < startDay) {
						return -1;
					} else {
						days = endDay - startDay;
					}
				}
				if (endMonth > startMonth) {
					days = endDay + MONTH_DAYS[startMonth - 1] - startDay;
					for (int month = startMonth; month < endMonth - 1; month++) {
						if (((startYear % 4 == 0 && startYear % 100 != 0) || startYear % 400 == 0) && month == 1) {
							days += 29;
						} else {
							days += MONTH_DAYS[month];
						}
					}
				}
			} else {

				days = endDay;
				for (int month = 0; month < endMonth - 1; month++) {
					if (((endYear % 4 == 0 && endYear % 100 != 0) || endYear % 400 == 0) && month == 1) {
						days += 29;
					} else {
						days += MONTH_DAYS[month];
					}
				}

				for (int year = startYear + 1; year < endYear; year++) {
					if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
						days += 366;
					} else {
						days += 365;
					}
				}

				int days2 = startDay;
				for (int month = 0; month < startMonth - 1; month++) {
					if (((startYear % 4 == 0 && startYear % 100 != 0) || startYear % 400 == 0) && month == 1) {
						days2 += 29;
					} else {
						days2 += MONTH_DAYS[month];
					}
				}
				if (((startYear % 4 == 0 && startYear % 100 != 0) || startYear % 400 == 0)) {
					days2 = 366 - days2;
				} else {
					days2 = 365 - days2;
				}
				days += days2;
			}
			return days;
		} else
			return -1;
	}
}
