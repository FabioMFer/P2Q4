package quartaquestao;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VulnerableClass {
  /**
   * Reads or write to a file.
  */
  @SuppressWarnings("resource")
  public void vulnerableMethod(String filename) {
    System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo,"
        + " W para escrever em um arquivo>? ");
    String opr = new Scanner(System.in).nextLine();
    char charOpr = opr.charAt(0);
    while (charOpr != 'R' && charOpr != 'W') {
      System.out.println("caracter não reconhecido");
      System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo,"
          + " W para escrever em um arquivo>? ");
      opr = new Scanner(System.in).nextLine();
      charOpr = opr.charAt(0);
    }
    if (opr.equals("R")) {
      //BufferedReader br = null;
      //FileReader fr = null;
      //estas variáveis tornam o código noncompliant, assim seguem as mudanças:
      try {
        FileReader fr = new FileReader(filename);
        /*
        br = new BufferedReader(fr);
        br = new BufferedReader(new FileReader(filename));
        */
        String current;
        while ((current = new BufferedReader(fr).readLine()) != null) {
          System.out.println(current);
        }
//desta forma minimizamos os escopos das variáveis e excluimos variáveis 
//não utilizadas, o mesmo foi ferito com a variável console acima.
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } else {
      BufferedWriter buffWrite;
      try {
        buffWrite = new BufferedWriter(new FileWriter(filename));
        String linha = "";
        System.out.println("Escreva algo: ");
        linha = new Scanner(System.in).nextLine();
        //Segue a sanitizção da entrada para previnir a inserção de 
        //dados maliciosos em subsistemas
        while (linha.contains("/")) {
          System.out.println("codigo potencialmente malicioso");
          System.out.println("Escreva algo: ");
          linha = new Scanner(System.in).nextLine();
        }
        buffWrite.append(linha + "\n");   
      } catch (IOException e) {
        e.printStackTrace();
      } 
    }
  }
}