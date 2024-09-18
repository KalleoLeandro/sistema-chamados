package br.com.srv.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {

	public static String converterData(Date data) {

		String formatoDeDestino = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat sdfDestino = new SimpleDateFormat(formatoDeDestino);
		String dataFormatada = sdfDestino.format(data);
		return dataFormatada;
	}
}
