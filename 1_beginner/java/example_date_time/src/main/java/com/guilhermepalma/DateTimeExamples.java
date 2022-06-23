package com.guilhermepalma;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;

public class DateTimeExamples {
    public static void main(String[] args) {
        /*// 1. Escreva um exemplo que, para um determinado ano, relate a duração de cada mês desse ano.
        durationPerMonth();

        // 2. Escreva um exemplo que, para um determinado mês do ano atual, liste todas as segundas-feiras desse mês.
        listWeekDayInMonth();

        // 3. Escreva um exemplo que teste se uma determinada data ocorre na sexta-feira 13.
        isFriday13();

        // 1. Escreva um programa Java para criar um objeto Date usando a classe Calendar.
        convertAmericanStringDate("");

        // 3. Escreva um programa Java para obter o valor máximo do ano, mês, semana, data da data atual de um calendário padrão.
        maxDateValues();

        // 4. Escreva um programa Java para obter o valor mínimo de ano, mês, semana, data da data atual de um calendário padrão.
        minDateValues();

        // 5. Escreva um programa Java para obter a hora atual em Nova York.
        newYorkTimeZone();

        // 6. Escreva um programa Java para obter a data e hora completas atuais.
        dateTimeActual();

        // 7. Escreva um programa Java para obter o último dia do mês atual.

        // 8. Escreva um programa Java para obter a última data do mês.

        // 9. Escreva um programa Java para calcular o primeiro e o último dia de cada semana.

        // 10. Escreva um programa Java para obter o nome do primeiro e do último dia de um mês.

        // 11. Escreva um programa Java para obter o número de dias de um mês.

        // 12. Escreva um programa Java para obter o nome do dia da semana localizado.

        // 13. Escreva um programa Java para obter um dia da semana de uma data específica.

        // 14. Escreva um programa Java para obter a hora local atual.

        // 15. Escreva um programa Java para adicionar algumas horas à hora atual.

        // 16. Escreva um programa Java para obter uma data após 2 semanas.
        dateAfterTwoYears();

        // 17. Escreva um programa Java para obter uma data antes e depois de 1 ano comparando com a data atual.
        intervalOfOneYear();

        // 18. Escreva um programa Java para verificar se um ano é bissexto ou não.
        isLeapHear();

        // 19. Escreva um programa Java para obter ano e meses entre duas datas.

        // 20. Escreva um programa Java para obter o timestamp atual.
        actualLocalTimestamp();

        // 21. Escreva um programa Java para obter a hora atual em todos os fusos horários disponíveis.
        dateNowInDiferentsTimeZone();

        // 22. Escreva um programa Java para obter as datas 10 dias antes e depois de hoje.
        intervalOfTenDays();

        // 23. Escreva um programa Java para obter os meses restantes do ano.
        remainingMonths();

        // 24. Escreva um programa Java para exibir as datas nos seguintes formatos.
        formatDateInString();*/
        /*Sample format :
        Default format of LocalDate=2016-09-16
        16::Sep::2016
        Default format of LocalDateTime=2016-09-16T11:46:01.455
        16::Sep::2016 11::46::01
        Default format of Instant=2016-09-16T06:16:01.456Z
        Default format after parsing = 2014-04-27T21:39:4*/

        // 25. Write a Java program to get the information of current/given year. Go to the editor

        /*Sample Output :
        Current Year: 2001
        Is current year leap year? false
        Length of the year: 365 day*/

        // 26. Write a Java program to get the information of current/given month. Go to the editor

        /*Sample format :

        Sample Output :
        Integer value of the current month: 2
        Length of the month: 28
        Maximum length of the month: 29
        First month of the Quarter: JANUAR*/

        // 27. Write a Java program to get the information of a given time. Go to the editor

        // 28. Write a Java program to display the date time information before some hours and minutes from current date time. Go to the editor

        // 29. Write a Java program to convert a string to date. Go to the editor

        /* 30. Write a Java program to compute the difference between two dates (year, months, days). Go to the editor

            // 31. Write a Java program to compute the difference between two dates (Hours, minutes, milli, seconds and nano). Go to the editor

            // 32. Write a Java program to calculate your age. Go to the editor

            // 33. Write a Java program to get the next and previous Friday. Go to the editor

            // 34. Write a Java program to get today's date at midnight time. Go to the editor

            // 35. Write a Java program to extract date, time from the date string. Go to the editor

            // 36. Write a Java program to convert a unix timestamp to date in Java. Go to the editor

            // 37. Write a Java program to find seconds since 1970. Go to the editor

            // 38. Write a Java program to calculate the difference between two dates in days. Go to the editor

            // 39. Write a java program to convert String to date and time and vice a versa. Go to the editor

            // 40. Write a Java program to display current date without time and current time without date. Go to the editor

            // 41. Write a Java program to display combine local date and time in a single object. Go to the editor

            // 42. Write a Java program to define a period of time using date-based values (Period) and a duration of time using time-based values (Duration). Go to the editor

            // 43. Write a Java program to display all the available time zones with UTC and GMT. Go to the editor

            // 44. Write a Java program to define and extract zone offsets. Go to the editor

            // 45. Write a Java program to print yyyy-MM-dd, HH:mm:ss, yyyy-MM-dd HH:mm:ss, E MMM yyyy HH:mm:ss.SSSZ and HH:mm:ss,Z, format pattern for date and time. Go to the editor

        // 46. Write a Java program to count the number of days between two given years. Go to the editor*/

    }

    // 1. Escreva um programa Java para criar um objeto Date usando a classe Calendar.
    public static Date dateWithCalendar(Calendar calendar) {
        return calendar.getTime();
    }

    // 3. Escreva um exemplo que teste se uma determinada data ocorre na sexta-feira 13.
    public static boolean isFriday13(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    // 3. Escreva um programa Java para obter o valor máximo do ano, mês, semana, data da data atual de um calendário padrão.
    public static LocalDate maxDateValues() {
        return LocalDate.MAX;
    }

    // 4. Escreva um programa Java para obter o valor mínimo de ano, mês, semana, data da data atual de um calendário padrão.
    public static LocalDate minDateValues() {
        return LocalDate.MIN;
    }

    // 5. Escreva um programa Java para obter a hora atual em Nova York.
    public static LocalDateTime newYorkTimeZone() {
        return LocalDateTime.now(ZoneId.of("America/New_York"));
    }

    // 6. Escreva um programa Java para obter a data e hora completas atuais.
    public static LocalDateTime dateTimeActual() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    // 16. Escreva um programa Java para obter uma data após 2 semanas.
    public static LocalDate dateAfterTwoWeeks(LocalDate date) {
        return date.minusWeeks(2L);
    }

    // 17. Escreva um programa Java para obter uma data antes e depois de 1 ano comparando com a data atual.
    public static List<LocalDate> intervalOfOneYear(LocalDate date) {
        return new ArrayList<>(Arrays.asList(date.plusYears(1L), date.minusYears(1L)));
    }

    // 18. Escreva um programa Java para verificar se um ano é bissexto ou não.
    public static boolean isLeapHear(Year year) {
        return year.isLeap();
    }

    // 21. Escreva um programa Java para obter a hora atual em todos os fusos horários disponíveis.
    public static Map<String, LocalDateTime> dateNowInDiferentsTimeZone() {
        Map<String, LocalDateTime> list = new HashMap<>();
        LocalDateTime dateNow = LocalDateTime.now();
        ZoneId.getAvailableZoneIds().forEach(value -> list.put(TimeZone.getTimeZone(value).getID(), dateNow));
        return list;
    }

    // 22. Escreva um programa Java para obter as datas 10 dias antes e depois de hoje.
    public static List<LocalDate> intervalOfTenDays() {
        List<LocalDate> dates = new ArrayList<>();

        final Calendar calendar = Calendar.getInstance();
        final int actualDay = calendar.get(Calendar.DAY_OF_YEAR);

       for (int i = -10; i < 1; i++) {
            calendar.set(Calendar.DAY_OF_YEAR, actualDay + i);
            dates.add(new java.sql.Date(calendar.getTime().getTime()).toLocalDate());
        }

        for (int i = 1; i < 11; i++) {
            calendar.set(Calendar.DAY_OF_YEAR, actualDay + i);
            dates.add(new java.sql.Date(calendar.getTime().getTime()).toLocalDate());
        }

        return dates;
    }

    // 23. Escreva um programa Java para obter os meses restantes do ano.
    public static List<String> remainingMonthsInYear() {
        Month actualMonth = LocalDate.now().getMonth();

        List<String> values = new ArrayList<>();
        for (int i =actualMonth.getValue() +1; i < 13; i++) {
            values.add(Month.of(i).toString());
        }

        return values;
    }

    // 24. Escreva um programa Java para exibir as datas nos seguintes formatos.
    public static String formatDateInString(LocalDateTime dateTime) {
        return null;
    }

    // 1. Escreva um exemplo que, para um determinado ano, relate a duração de cada mês desse ano.
    public static Map<String, Integer> durationPerMonth(Year year) {
        return null;

    }

    // 2. Escreva um exemplo que, para um determinado mês do ano atual, liste todas as segundas-feiras desse mês.
    public static List<Date> listAllMondaysInMonth(Month month) {
        return null;
    }

}
