package br.com.deltafinanceira.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Formatacao {
  public static String foramtarDoubleString(Double valor) {
    NumberFormat format = new DecimalFormat("#,##0.00");
    format.setMinimumFractionDigits(2);
    String valorFormatado = format.format(valor);
    return valorFormatado;
  }
  
  public static Date ConvercaoStringData(String data) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date dataFormatada = null;
    try {
      dataFormatada = df.parse(data);
    } catch (ParseException ex) {
      Logger.getLogger(Formatacao.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return dataFormatada;
  }
  
  public static Date ConvercaoStringDataBrasil(String data) {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date dataFormatada = null;
    try {
      dataFormatada = df.parse(data);
    } catch (ParseException ex) {
      Logger.getLogger(Formatacao.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return dataFormatada;
  }
  
  public static String foramtarQuantidade(Double valor) {
    NumberFormat format = new DecimalFormat("#,##0.000");
    format.setMinimumFractionDigits(3);
    String valorFormatado = format.format(valor);
    return valorFormatado;
  }
  
  public static String foramtarFloatString(Float valor) {
    NumberFormat format = new DecimalFormat("#,##0.00");
    format.setMinimumFractionDigits(2);
    String valorFormatado = format.format(valor);
    return valorFormatado;
  }
  
  public static Float formatarStringfloat(String valor) {
    String novoValor = "";
    for (int i = 0; i < valor.length(); i++) {
      if (valor.charAt(i) == ',') {
        novoValor = String.valueOf(novoValor) + ".";
      } else if (valor.charAt(i) != '.') {
        novoValor = String.valueOf(novoValor) + valor.charAt(i);
      } 
    } 
    return Float.valueOf(Float.parseFloat(novoValor));
  }
  
  public static Integer formatarStringInteger(String valor) {
    String novoValor = "";
    for (int i = 0; i < valor.length(); i++) {
      if (valor.charAt(i) == ',') {
        novoValor = String.valueOf(novoValor) + ".";
      } else if (valor.charAt(i) != '.') {
        novoValor = String.valueOf(novoValor) + valor.charAt(i);
      } 
    } 
    return Integer.valueOf(Integer.parseInt(novoValor));
  }
  
  public static Double formatarStringDouble(String valor) {
    String novoValor = "";
    for (int i = 0; i < valor.length(); i++) {
      if (valor.charAt(i) == ',') {
        novoValor = String.valueOf(novoValor) + ".";
      } else if (valor.charAt(i) != '.') {
        novoValor = String.valueOf(novoValor) + valor.charAt(i);
      } 
    } 
    return Double.valueOf(Double.parseDouble(novoValor));
  }
  
  public static String formatarIntegerString(Integer valor) {
    return String.valueOf(valor);
  }
  
  public static String formatarLogString(long valor) {
    return String.valueOf(valor);
  }
  
  public static Date SomarDiasData(Date data, int dias) {
    @SuppressWarnings("unused")
	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    Calendar c = new GregorianCalendar();
    c.setTime(data);
    if (dias != 0)
      c.add(5, dias); 
    return c.getTime();
  }
  
  public static boolean ValidaCNPJ(String str_cnpj) {
    String ncpf = "";
    for (int i = 0; i < str_cnpj.length(); i++) {
      if (str_cnpj.charAt(i) != '.' && str_cnpj.charAt(i) != '-' && str_cnpj.charAt(i) != '/')
        ncpf = String.valueOf(ncpf) + str_cnpj.charAt(i); 
    } 
    str_cnpj = ncpf;
    int soma = 0;
    String cnpj_calc = str_cnpj.substring(0, 12);
    if (str_cnpj.length() != 14)
      return false; 
    char[] chr_cnpj = str_cnpj.toCharArray();
    int j;
    for (j = 0; j < 4; j++) {
      if (chr_cnpj[j] - 48 >= 0 && chr_cnpj[j] - 48 <= 9)
        soma += (chr_cnpj[j] - 48) * (6 - j + 1); 
    } 
    for (j = 0; j < 8; j++) {
      if (chr_cnpj[j + 4] - 48 >= 0 && chr_cnpj[j + 4] - 48 <= 9)
        soma += (chr_cnpj[j + 4] - 48) * (10 - j + 1); 
    } 
    int dig = 11 - soma % 11;
    cnpj_calc = String.valueOf(cnpj_calc) + ((dig == 10 || dig == 11) ? "0" : Integer.toString(dig));
    soma = 0;
    for (j = 0; j < 5; j++) {
      if (chr_cnpj[j] - 48 >= 0 && chr_cnpj[j] - 48 <= 9)
        soma += (chr_cnpj[j] - 48) * (7 - j + 1); 
    } 
    for (j = 0; j < 8; j++) {
      if (chr_cnpj[j + 5] - 48 >= 0 && chr_cnpj[j + 5] - 48 <= 9)
        soma += (chr_cnpj[j + 5] - 48) * (10 - j + 1); 
    } 
    dig = 11 - soma % 11;
    cnpj_calc = String.valueOf(cnpj_calc) + ((dig == 10 || dig == 11) ? "0" : Integer.toString(dig));
    return str_cnpj.equals(cnpj_calc);
  }
  
  public static String SomarDatas(Date data, int dias, String formato) throws Exception {
    SimpleDateFormat sd = new SimpleDateFormat(formato);
    Calendar c = new GregorianCalendar();
    c.add(5, dias);
    return sd.format(c.getTime());
  }
  
  public static String SubtarirDatas(Date data, int dias, String formato) {
    SimpleDateFormat sd = new SimpleDateFormat(formato);
    Calendar c = new GregorianCalendar();
    c.add(5, dias - 2 * dias);
    return sd.format(c.getTime());
  }
  
  public static String ConvercaoDataSql(Date data) {
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    String dataFormatada = df.format(data);
    return dataFormatada;
  }
  
  public static String ConvercaoCalendarSql(Calendar data) {
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    String dataFormatada = df.format(data);
    return dataFormatada;
  }
  
  public static String ConvercaoDataNfe(Date data) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String dataFormatada = df.format(data);
    return dataFormatada;
  }
  
  public static String ConvercaoDataPadrao(Date data) {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    String dataFormatada = df.format(data);
    return dataFormatada;
  }
  
  public static Float ConvercaoMonetariaFloat(String valor) {
    String cValor = "";
    for (int i = 0; i < valor.length(); i++) {
      if (valor.charAt(i) == ',') {
        cValor = String.valueOf(cValor) + ".";
      } else if (valor.charAt(i) != '.') {
        cValor = String.valueOf(cValor) + valor.charAt(i);
      } 
    } 
    float novoValor = Float.parseFloat(cValor);
    return Float.valueOf(novoValor);
  }
  
  public static Double ConvercaoMonetariaDouble(String valor) {
    String cValor = "";
    for (int i = 0; i < valor.length(); i++) {
      if (valor.charAt(i) == ',') {
        cValor = String.valueOf(cValor) + ".";
      } else if (valor.charAt(i) != '.') {
        cValor = String.valueOf(cValor) + valor.charAt(i);
      } 
    } 
    double novoValor = Double.parseDouble(cValor);
    return Double.valueOf(novoValor);
  }
  
  public static Double calcularValorPercentual(double percentualDesejado, double valorCusto) {
    double percentual = (100.0D - percentualDesejado) / 100.0D;
    double novoValorCusto = valorCusto / percentual;
    return Double.valueOf(novoValorCusto);
  }
  
  public static Double calcularValorValorVenda(double valorVenda, double valorCusto) {
    valorCusto *= 100.0D;
    double percentual = valorCusto / valorVenda;
    percentual = 100.0D - percentual;
    return Double.valueOf(percentual);
  }
  
  public static String getDataDoDia() {
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    String data = df.format(new Date(System.currentTimeMillis()));
    return data;
  }
  
  public static int gerarDiaSemana(Date data) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(data);
    int dia = cal.get(7);
    return dia;
  }
  
  public static String decinalValorMonetario(String texto) {
    String novoTexto = "";
    for (int i = 0; i < texto.length(); i++) {
      if (texto.charAt(i) == '.') {
        novoTexto = String.valueOf(novoTexto) + ",";
      } else {
        novoTexto = String.valueOf(novoTexto) + texto.charAt(i);
      } 
    } 
    return novoTexto;
  }
  
  public static boolean validaCPF(String s_aux) {
    String ncpf = "";
    for (int i = 0; i < s_aux.length(); i++) {
      if (s_aux.charAt(i) != '.' && s_aux.charAt(i) != '-')
        ncpf = String.valueOf(ncpf) + s_aux.charAt(i); 
    } 
    s_aux = ncpf;
    if (s_aux.length() == 11) {
      int d2 = 0, d1 = d2;
      int resto = 0, digito2 = resto, digito1 = digito2;
      for (int n_Count = 1; n_Count < s_aux.length() - 1; n_Count++) {
        int digitoCPF = Integer.valueOf(s_aux.substring(n_Count - 1, n_Count)).intValue();
        d1 += (11 - n_Count) * digitoCPF;
        d2 += (12 - n_Count) * digitoCPF;
      } 
      resto = d1 % 11;
      if (resto < 2) {
        digito1 = 0;
      } else {
        digito1 = 11 - resto;
      } 
      d2 += 2 * digito1;
      resto = d2 % 11;
      if (resto < 2) {
        digito2 = 0;
      } else {
        digito2 = 11 - resto;
      } 
      String nDigVerific = s_aux.substring(s_aux.length() - 2, s_aux.length());
      String nDigResult = String.valueOf(String.valueOf(digito1)) + String.valueOf(digito2);
      return nDigVerific.equals(nDigResult);
    } 
    if (s_aux.length() == 14) {
      int soma = 0;
      int dig = 0;
      String cnpj_calc = s_aux.substring(0, 12);
      char[] chr_cnpj = s_aux.toCharArray();
      int j;
      for (j = 0; j < 4; j++) {
        if (chr_cnpj[j] - 48 >= 0 && chr_cnpj[j] - 48 <= 9)
          soma += chr_cnpj[j] - 4 * (6 - j + 1); 
      } 
      for (j = 0; j < 8; j++) {
        if (chr_cnpj[j + 4] - 48 >= 0 && chr_cnpj[j + 4] - 48 <= 9)
          soma += chr_cnpj[j + 4] - 4 * (10 - j + 1); 
      } 
      dig = 11 - soma % 11;
      cnpj_calc = String.valueOf(cnpj_calc) + ((dig == 10 || dig == 11) ? "0" : Integer.toString(dig));
      soma = 0;
      for (j = 0; j < 5; j++) {
        if (chr_cnpj[j] - 48 >= 0 && chr_cnpj[j] - 48 <= 9)
          soma += chr_cnpj[j] - 4 * (7 - j + 1); 
      } 
      for (j = 0; j < 8; j++) {
        if (chr_cnpj[j + 5] - 48 >= 0 && chr_cnpj[j + 5] - 48 <= 9)
          soma += chr_cnpj[j + 5] - 4 * (10 - j + 1); 
      } 
      dig = 11 - soma % 11;
      cnpj_calc = String.valueOf(cnpj_calc) + ((dig == 10 || dig == 11) ? "0" : Integer.toString(dig));
      return s_aux.equals(cnpj_calc);
    } 
    return false;
  }
  
  public static String valorPorExtenso(double vlr) {
    if (vlr == 0.0D)
      return "ZERO"; 
    String ve = String.valueOf(vlr);
    String nve = "";
    for (int i = 0; i < ve.length(); i++) {
      if (ve.charAt(i) != '.') {
        nve = String.valueOf(nve) + ve.charAt(i);
      } else {
        int numeroI = ve.length() - i;
        if (numeroI > 3) {
          nve = String.valueOf(nve) + ve.charAt(i) + ve.substring(i + 1, i + 4);
        } else if (numeroI == 2) {
          nve = String.valueOf(nve) + ve.charAt(i) + ve.substring(i + 1, i + 2);
        } else if (numeroI == 3) {
          nve = String.valueOf(nve) + ve.charAt(i) + ve.substring(i + 1, i + 3);
        } 
        i = 1000;
      } 
    } 
    vlr = Double.parseDouble(nve);
    long inteiro = (long)Math.abs(vlr);
    double resto = vlr - inteiro;
    String vresto = String.valueOf(resto);
    if (vresto.length() > 5)
      vresto = vresto.substring(0, 5); 
    double valorvResto = Double.parseDouble(vresto);
    if (valorvResto > 0.99D) {
      resto = 0.0D;
      inteiro++;
    } else {
      resto = Double.parseDouble(vresto);
    } 
    String vlrS = String.valueOf(inteiro);
    if (vlrS.length() > 15)
      return "Erro: valor superior a 999 trilha"; 
    String s = "";
    String centavos = String.valueOf((int)Math.round(resto * 100.0D));
    String[] unidade = { 
        "", "UM", "DOIS", "TRES", "QUATRO", "CINCO", "SEIS", "SETE", "OITO", "NOVE", 
        "DEZ", 
        "ONZE", "DOZE", "TREZE", "QUATORZE", "QUINZE", "DEZESSEIS", "DEZESSETE", "DEZOITO", "DEZENOVE" };
    String[] centena = { "", "CENTO", "DUZENTOS", "TREZENTOS", "QUATROCENTOS", "QUINHENTOS", "SEISCENTOS", 
        "SETECENTOS", "OITOCENTOS", "NOVECENTOS" };
    String[] dezena = { "", "", "VINTE", "TRINTA", "QUARENTA", "CINQUENTA", "SESSENTA", "SETENTA", "OITENTA", 
        "NOVENTA" };
    String[] qualificaS = { "", "MIL", "MILHAO", "BILHAO", "TRILHAO"};
    String[] qualificaP = { "", "MIL", "MILHOES", "BILHOES", "TRILHOES"};
    int j = 0;
    boolean umReal = false, tem = false;
    while (!vlrS.equals("0")) {
      String vlrP;
      int tam = vlrS.length();
      if (tam > 3) {
        vlrP = vlrS.substring(tam - 3, tam);
        vlrS = vlrS.substring(0, tam - 3);
      } else {
        vlrP = vlrS;
        vlrS = "0";
      } 
      if (!vlrP.equals("000")) {
        String saux = "";
        if (vlrP.equals("100")) {
          saux = "CEM";
        } else {
          int n = Integer.parseInt(vlrP, 10);
          int cent = n / 100;
          int dez = n % 100 / 10;
          int unid = n % 100 % 10;
          if (cent != 0)
            saux = centena[cent]; 
          if (n % 100 <= 19) {
            if (saux.length() != 0) {
              saux = String.valueOf(saux) + " E " + unidade[n % 100];
            } else {
              saux = unidade[n % 100];
            } 
          } else {
            if (saux.length() != 0) {
              saux = String.valueOf(saux) + " E " + dezena[dez];
            } else {
              saux = dezena[dez];
            } 
            if (unid != 0)
              if (saux.length() != 0) {
                saux = String.valueOf(saux) + " E " + unidade[unid];
              } else {
                saux = unidade[unid];
              }  
          } 
        } 
        if (vlrP.equals("1") || vlrP.equals("001")) {
          if (j == 0) {
            umReal = true;
          } else {
            saux = String.valueOf(saux) + " " + qualificaS[j];
          } 
        } else if (j != 0) {
          saux = String.valueOf(saux) + " " + qualificaP[j];
        } 
        if (s.length() != 0) {
          s = String.valueOf(saux) + ", " + s;
        } else {
          s = saux;
        } 
      } 
      if ((j == 0 || j == 1) && s.length() != 0)
        tem = true; 
      j++;
    } 
    if (s.length() != 0)
      if (umReal) {
        s = String.valueOf(s) + " REAL";
      } else if (tem) {
        s = String.valueOf(s) + " REAIS";
      } else {
        s = String.valueOf(s) + " DE REAIS";
      }  
    if (!centavos.equals("0")) {
      if (s.length() != 0)
        s = String.valueOf(s) + " E "; 
      if (centavos.equals("1")) {
        s = String.valueOf(s) + "UM CENTAVO";
      } else {
        int n = Integer.parseInt(centavos, 10);
        if (n <= 19) {
          s = String.valueOf(s) + unidade[n];
        } else {
          int unid = n % 10;
          int dez = n / 10;
          s = String.valueOf(s) + dezena[dez];
          if (unid != 0)
            s = String.valueOf(s) + " E " + unidade[unid]; 
        } 
        s = String.valueOf(s) + " CENTAVOS";
      } 
    } 
    return s;
  }
  
  public static String configurarCaminhoArquivos(String localIni) {
    String novo = "";
    for (int i = 0; i < localIni.length(); i++) {
      if (localIni.charAt(i) == '\\') {
        novo = String.valueOf(novo) + "/";
      } else {
        novo = String.valueOf(novo) + localIni.charAt(i);
      } 
    } 
    return novo;
  }
  
  public static String retornaDataInicia(int mes) {
    if (mes == 1)
      return "01-01"; 
    if (mes == 2)
      return "02-01"; 
    if (mes == 3)
      return "03-01"; 
    if (mes == 4)
      return "04-01"; 
    if (mes == 5)
      return "05-01"; 
    if (mes == 6)
      return "06-01"; 
    if (mes == 7)
      return "07-01"; 
    if (mes == 8)
      return "08-01"; 
    if (mes == 9)
      return "09-01"; 
    if (mes == 10)
      return "10-01"; 
    if (mes == 11)
      return "11-01"; 
    if (mes == 12)
      return "12-01"; 
    return "0";
  }
  
  public static String retornaDataIniciaBrasil(int mes) {
    if (mes == 1)
      return "01/01"; 
    if (mes == 2)
      return "01/02"; 
    if (mes == 3)
      return "01/03"; 
    if (mes == 4)
      return "01/04"; 
    if (mes == 5)
      return "01/05"; 
    if (mes == 6)
      return "01/06"; 
    if (mes == 7)
      return "01/07"; 
    if (mes == 8)
      return "01/08"; 
    if (mes == 9)
      return "01/09"; 
    if (mes == 10)
      return "01/10"; 
    if (mes == 11)
      return "01/11"; 
    if (mes == 12)
      return "01/12"; 
    return "0";
  }
  
  public static String retornaDataFinal(int mes) {
    if (mes == 1)
      return "01-31"; 
    if (mes == 2)
      return "02-28"; 
    if (mes == 3)
      return "03-31"; 
    if (mes == 4)
      return "04-30"; 
    if (mes == 5)
      return "05-31"; 
    if (mes == 6)
      return "06-30"; 
    if (mes == 7)
      return "07-31"; 
    if (mes == 8)
      return "08-31"; 
    if (mes == 9)
      return "09-30"; 
    if (mes == 10)
      return "10-31"; 
    if (mes == 11)
      return "11-30"; 
    if (mes == 12)
      return "12-31"; 
    return "0";
  }
  
  public static String retornaDataFinalBrasil(int mes) {
    if (mes == 1)
      return "31/01"; 
    if (mes == 2)
      return "28/02"; 
    if (mes == 3)
      return "31/03"; 
    if (mes == 4)
      return "30/04"; 
    if (mes == 5)
      return "31/05"; 
    if (mes == 6)
      return "30/06"; 
    if (mes == 7)
      return "31/07"; 
    if (mes == 8)
      return "31/08"; 
    if (mes == 9)
      return "30/09"; 
    if (mes == 10)
      return "31/10"; 
    if (mes == 11)
      return "30/11"; 
    if (mes == 12)
      return "31/12"; 
    return "0";
  }
  
  public static String retornaDataFinalBarras(int mes) {
    if (mes == 1)
      return "01/31"; 
    if (mes == 2)
      return "02/28"; 
    if (mes == 3)
      return "02/31"; 
    if (mes == 4)
      return "04/30"; 
    if (mes == 5)
      return "05/31"; 
    if (mes == 6)
      return "06/30"; 
    if (mes == 7)
      return "07/31"; 
    if (mes == 8)
      return "08/31"; 
    if (mes == 9)
      return "09/30"; 
    if (mes == 10)
      return "10/31"; 
    if (mes == 11)
      return "11/30"; 
    if (mes == 12)
      return "12/31"; 
    return "0";
  }
  
  public static String nomeMes(int mes) {
    if (mes == 1)
      return "Janeiro"; 
    if (mes == 2)
      return "Fevereiro"; 
    if (mes == 3)
      return "Março"; 
    if (mes == 4)
      return "Abril"; 
    if (mes == 5)
      return "Maio"; 
    if (mes == 6)
      return "Junho"; 
    if (mes == 7)
      return "Julho"; 
    if (mes == 8)
      return "Agosto"; 
    if (mes == 9)
      return "Setembro"; 
    if (mes == 10)
      return "Outubro"; 
    if (mes == 11)
      return "Novembro"; 
    if (mes == 12)
      return "Dezembro"; 
    return "não encontrado";
  }
  
  public static int subtrairDatas(Date dataInicial, Date dataFinal) {
    int resultado = 0;
    resultado = (int)((dataInicial.getTime() - dataFinal.getTime()) / 86400000L);
    return resultado;
  }
  
  public static String foramtarHoraString() {
    DateFormat formato = new SimpleDateFormat("HH:mm:ss");
    String formattedDate = formato.format(new Date());
    return formattedDate;
  }
  
  public static String retirarNegativo(String valor) {
    String novoValor = "";
    for (int i = 0; i < valor.length(); i++) {
      if (!valor.substring(i).equalsIgnoreCase("-")) {
        novoValor = String.valueOf(novoValor) + valor.substring(i + 1);
        i = 1000;
      } 
    } 
    return novoValor;
  }
  
  public static String removeAcentos(String str) {
    String strSemAcentos = Normalizer.normalize(str, Normalizer.Form.NFD);
    strSemAcentos = strSemAcentos.replaceAll("[^\\p{ASCII}]", "");
    return strSemAcentos;
  }
  
  public static String gerarCopetencia(Date data) {
    String smes, copetencia = "";
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(data);
    int mes = calendar.get(2);
    int ano = calendar.get(1);
    if (mes > 9) {
      smes = String.valueOf(mes);
    } else {
      smes = "0" + String.valueOf(mes);
    } 
    copetencia = String.valueOf(smes) + "/" + String.valueOf(ano);
    return copetencia;
  }
  
  public static String formatarFloatString(Float valor) {
    NumberFormat format = new DecimalFormat("#,##0.00");
    format.setMinimumFractionDigits(2);
    String valorFormatado = format.format(valor);
    return valorFormatado;
  }
  
  public static int diaSemana(Date data) {
    Calendar calendario = new GregorianCalendar();
    calendario.setTime(data);
    int diaSemana = calendario.get(7);
    return diaSemana;
  }
  
  public static String formatarHoraString() {
    DateFormat formato = new SimpleDateFormat("HH:mm");
    String formattedDate = formato.format(new Date());
    return formattedDate;
  }
  
  public static int getAnoData(Date data) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(data);
    int ano = calendar.get(1);
    return ano;
  }
  
  public static String diaSemanaEscrito(Date data) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(data);
    int diaSemana = cal.get(7);
    if (diaSemana == 1)
      return "Domingo"; 
    if (diaSemana == 2)
      return "Segunda"; 
    if (diaSemana == 3)
      return "Terça"; 
    if (diaSemana == 4)
      return "Quarta"; 
    if (diaSemana == 5)
      return "Quinta"; 
    if (diaSemana == 6)
      return "Sexta"; 
    if (diaSemana == 7)
      return "Sabado"; 
    return "Erro";
  }
  
  public static int getMesData(Date data) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(data);
    int mes = calendar.get(2);
    return mes;
  }
  
  public static int getDiaData(Date data) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(data);
    int dia = calendar.get(5);
    return dia;
  }
}
