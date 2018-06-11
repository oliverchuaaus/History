package jdk8.dates;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class TestDates {
	@Test
	public void testLocalDate() {
		LocalDate date = LocalDate.now();
		System.out.println(date);
		date = LocalDate.of(1977, 6, 20);
		System.out.println(date);
		Month month = date.getMonth();
		assertEquals(1977, date.getYear());
		assertEquals(6, month.getValue());
		assertEquals(20, date.getDayOfMonth());
		System.out.println("");
	}

	@Test
	public void testLocalTime() {
		LocalTime time = LocalTime.now();
		System.out.println(time);
		time = LocalTime.of(23, 11);
		System.out.println(time);
		assertEquals(23, time.getHour());
		assertEquals(11, time.getMinute());
		assertEquals(0, time.getSecond());
		time = LocalTime.of(23, 11, 01, 193121431);
		System.out.println(time);
		System.out.println("");
	}

	@Test
	public void testLocalDateTime() {
		LocalDateTime date = LocalDateTime.now();
		System.out.println(date);
		date = LocalDateTime.of(1977, 6, 20, 23, 11);
		System.out.println(date);
		Month month = date.getMonth();
		assertEquals(1977, date.getYear());
		assertEquals(6, month.getValue());
		assertEquals(20, date.getDayOfMonth());
		System.out.println("");
	}

	@Test
	public void testZonedDateTime() {
		ZonedDateTime date = ZonedDateTime.now();
		System.out.println(date);
		date = ZonedDateTime.of(1977, 6, 20, 23, 11, 0, 0, ZoneId.of("Asia/Manila"));
		System.out.println(date);
		Month month = date.getMonth();
		assertEquals(1977, date.getYear());
		assertEquals(6, month.getValue());
		assertEquals(20, date.getDayOfMonth());
		System.out.println("");
	}

	@Test
	public void testOffsetDateTime() {
		OffsetDateTime date = OffsetDateTime.now();
		System.out.println(date);
		date = OffsetDateTime.of(1977, 6, 20, 23, 11, 0, 0, ZoneOffset.of("+08:00"));
		System.out.println(date);
		Month month = date.getMonth();
		assertEquals(1977, date.getYear());
		assertEquals(6, month.getValue());
		assertEquals(20, date.getDayOfMonth());
		System.out.println("");
	}

	@Test
	public void testPeriod() {
		// 3 years, 2 months, 1 day
		Period period = Period.of(3, 2, 1);
		LocalDate date = LocalDate.of(1977, 6, 20);
		date = date.plus(period);
		System.out.println(date);
		System.out.println("");
	}

	@Test
	public void testDuration() {
		Duration duration = Duration.ofDays(5);
		System.out.println(duration);
		duration = Duration.between(LocalTime.now(), LocalTime.now().plusHours(-12));
		System.out.println(duration);
		System.out.println("");
	}

	@Test
	public void testInstant() {
		Instant instant = Instant.now();
		System.out.println(instant);
		instant = Instant.ofEpochMilli(System.currentTimeMillis());
		System.out.println(instant);
		System.out.println("");
	}

	@Test
	public void testMonthDayYearMonth() {
		MonthDay monthDay = MonthDay.of(6, 20);
		System.out.println(monthDay);

		YearMonth yearMonth = YearMonth.of(2021, 06);
		System.out.println(yearMonth);
		System.out.println("");
	}

	@Test
	public void testFormatter() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse("20/06/1977", formatter);
		System.out.println(date);
		System.out.println(formatter.format(date));
		date = LocalDate.parse("1977-02-01");
		System.out.println(date);
		System.out.println("");
	}

	@Test
	public void testDateArithmetic() {
		LocalDate date = LocalDate.of(1977, 6, 20);
		date = date.plusDays(1);
		System.out.println(date);

		date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
		System.out.println(date);

		LocalTime time = LocalTime.now();
		System.out.println(time);
		time = time.truncatedTo(ChronoUnit.SECONDS);
		System.out.println(time);

		System.out.println("");
	}
}
