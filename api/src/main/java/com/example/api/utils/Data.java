package com.example.api.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Data {

    public static boolean validarDataNascimento(String dataNascimento) {
        LocalDate data = LocalDate.parse(dataNascimento);
        return !data.isAfter(LocalDate.now());
    }

    public static boolean validarFinalDeSemana(LocalDate date){
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public static String formatarDataCadastroParaTelaStatus(LocalDateTime data)  {
        // Define o formato da string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        // Formata a data conforme necessário
        DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        String dataFormatada = data.format(formatoDesejado);
        return dataFormatada;
    }

}
