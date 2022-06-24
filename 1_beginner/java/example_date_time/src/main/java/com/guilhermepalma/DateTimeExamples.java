package com.guilhermepalma;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DateTimeExamples {

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

    // 1. Escreva um exemplo que, para um determinado ano, relate a duração de cada mês desse ano.
    public static Map<String, Integer> durationPerMonth(Year year) {
        Map<String, Integer> daysInMoths = new LinkedHashMap<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year.getValue(), Calendar.JANUARY, 1);

        AtomicInteger atomicIndex = new AtomicInteger(1);
        while (atomicIndex.get() < 13){
            daysInMoths.put(Month.of(calendar.get(Calendar.MONTH) +1).toString(), calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            calendar.add(Calendar.MONTH, 1);
            atomicIndex.incrementAndGet();
        }

        return daysInMoths;
    }

    // 2. Escreva um exemplo que, para um determinado mês do ano atual, liste todas as segundas-feiras desse mês.
    public static Set<LocalDate> listAllMondaysInMonth(int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Year.now().getValue(),month,1);

        Set<LocalDate> dates = new LinkedHashSet<>();
        final Integer actualMonth = calendar.get(Calendar.MONTH);
        while (actualMonth.equals(calendar.get(Calendar.MONTH))) {
            boolean isMonday = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;

            if(isMonday) dates.add(new java.sql.Date(calendar.getTime().getTime()).toLocalDate());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return dates;
    }

}
