package com.guilhermepalma;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * Transforma uma {@link LocalDate} em {@link String}
     *
     * @return {@link String}
     */
    public static String dateToString(LocalDate localDate) throws DateTimeException {
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Converte uma Data no Formato "dd/MM/yyyy" ou "dd-MM-yyyy" para uma {@link LocalDate}
     *
     * @return {@link LocalDate}
     */
    public static LocalDate stringToAmericanDate(String date) {
        if (!validStringDate(date)) return null;

        date = date.contains("-") ? date.replace("-", "/") : date;
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Valida se a Data segue o padrão estipulado para o Projeto
     *
     * @return {@link Boolean}
     */
    public static boolean validStringDate(String date) {
        if (date == null || date.isEmpty()) return false;
        else if (!date.contains("-") && !date.contains("/")) return false;

        date = date.replace(date.contains("/") ? "/" : "-", "");
        if (date.length() > 8 || date.matches("[A-z]*")) return false;

        int day = Integer.parseInt(date.substring(0, 2));
        if (day < 1 || day > 31) return false;

        int month = Integer.parseInt(date.substring(2, 4));
        if (month < 1 || month > 12) return false;

        int year = Integer.parseInt(date.substring(4, 8));
        return year < 3000 && year > 1500;
    }

}
