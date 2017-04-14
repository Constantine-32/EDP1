package Aplicacio;

import Dades.*;
import Exceptions.*;

import java.io.*;
import java.util.*;

public class Main {
  // Objecte Scanner per obtenir la informacio de la consola.
  private static Scanner keyboard = new Scanner(System.in);
  // Llistes amb els caracters valids.
  private static String lowerLetters = "abcdefghijklmnopqrstuvwxyz";
  private static String upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  // Cua generica.
  private static TADGenericQueue<Integer> queue;
  // Cua java.util.
  private static Queue<Integer> javaqueue;

  // Metode que s'asegura d'obtenir un int de consola i el retorna.
  private static int getOption(int options) {
    int option = -1;
    boolean valid = false;

    while (!valid) {
      try {
        option = Integer.parseInt(keyboard.nextLine());
        if (1 <= option && option <= options) valid = true;
        else System.out.println("Opcio no valida!");
      } catch (NumberFormatException e) {
        System.out.println("Opcio no valida!");
      }
    }
    return option;
  }

  // Metode que xifra una linia usant una de les cues implementades.
  private static String encrypt(String line) {
    StringBuilder sb = new StringBuilder(line.length());

    for (int i = 0; i < line.length(); i++) {
      try {
        int key = queue.remove();
        queue.add(key);
        
        char chr = line.charAt(i);
  
        if (lowerLetters.indexOf(chr) != -1) {
          chr = lowerLetters.charAt((lowerLetters.indexOf(chr) + key) % lowerLetters.length());
        }
        
        if (upperLetters.indexOf(chr) != -1) {
          chr = upperLetters.charAt((upperLetters.indexOf(chr) + key) % upperLetters.length());
        }
  
        sb.append(chr);
      } catch (CuaBuida | CuaPlena e) {
        System.out.println(e);
      }
    }
    return sb.toString();
  }

  // Metode que desxifra una linia usant una de les cues implementades.
  private static String decrypt(String line) {
    StringBuilder sb = new StringBuilder(line.length());

    for (int i = 0; i < line.length(); i++) {
      try {
        int key = queue.remove();
        queue.add(key);

        char chr = line.charAt(i);
  
        if (lowerLetters.indexOf(chr) != -1) {
          chr = lowerLetters.charAt(((lowerLetters.indexOf(chr) - key) + upperLetters.length()) % lowerLetters.length());
        }
  
        if (upperLetters.indexOf(chr) != -1) {
          chr = upperLetters.charAt(((upperLetters.indexOf(chr) - key) + upperLetters.length()) % upperLetters.length());
        }
  
        sb.append(chr);
      } catch (CuaBuida | CuaPlena e) {
        System.out.println(e);
      }
    }
    return sb.toString();
  }

  // Metode que xifra una linia usant la linkedlist de java.
  private static String encryptJava(String line) {
    StringBuilder sb = new StringBuilder(line.length());

    for (int i = 0; i < line.length(); i++) {
      int key = javaqueue.remove();
      javaqueue.add(key);

      char chr = line.charAt(i);

      if (lowerLetters.indexOf(chr) != -1) {
        chr = lowerLetters.charAt((lowerLetters.indexOf(chr) + key) % lowerLetters.length());
      }

      if (upperLetters.indexOf(chr) != -1) {
        chr = upperLetters.charAt((upperLetters.indexOf(chr) + key) % upperLetters.length());
      }

      sb.append(chr);
    }
    return sb.toString();
  }

  // Metode que desxifra una linia usant la linkedlist de java.
  private static String decryptJava(String line) {
    StringBuilder sb = new StringBuilder(line.length());

    for (int i = 0; i < line.length(); i++) {
      int key = javaqueue.remove();
      javaqueue.add(key);

      char chr = line.charAt(i);

      if (lowerLetters.indexOf(chr) != -1) {
        chr = lowerLetters.charAt(((lowerLetters.indexOf(chr) - key) + upperLetters.length()) % lowerLetters.length());
      }

      if (upperLetters.indexOf(chr) != -1) {
        chr = upperLetters.charAt(((upperLetters.indexOf(chr) - key) + upperLetters.length()) % upperLetters.length());
      }

      sb.append(chr);
    }
    return sb.toString();
  }

  // Main del programa.
  public static void main(String[] args) {
    // Pregunta quina implementacio es vol fer servir.
    System.out.println("Quina implementacio vols fer servir?");
    System.out.println("\t1. Memoria estatica.");
    System.out.println("\t2. Memoria estatica cua circular.");
    System.out.println("\t3. Memoria dinamica.");
    System.out.println("\t4. Java LinkedList.");
    int option = getOption(4);

    // Pregunta el nom del fitxer.
    BufferedReader file = null;
    String filePath = "";
    boolean valid = false;
    while (!valid) {
      System.out.println("Indica el nom del fitxer:");
      filePath = keyboard.nextLine();
      try {
        file = new BufferedReader(new FileReader(filePath));
        valid = true;
      } catch (FileNotFoundException e) {
        System.out.println("No s'ha trobat el fitxer indicat.");
      }
    }

    // Demana la clau que es vol utilitzar.
    System.out.println("Indica la clau que vols utilitzar:");
    String key = keyboard.nextLine();

    // Segons l'opcio triada anteriorment inicialitza la cua corresponent.
    switch (option) {
      case 1:
        queue = new SimpleQueue<>(key.length());
        break;
      case 2:
        queue = new CircularQueue<>(key.length());
        break;
      case 3:
        queue = new DynamicQueue<>();
        break;
      case 4:
        javaqueue = new LinkedList<>();
      default: break;
    }

    // Pregunta si es vol xifrar o desxifrar.
    System.out.println("Indica si vols xifrar o desxifrar el fitxer:");
    System.out.println("\t1. Xifrar.");
    System.out.println("\t2. Desxifrar.");
    int option2 = getOption(2);

    // Inici d'execucio.
    long start = System.nanoTime();

    // Afegeix la clau a la cua
    for (int i = 0; i < key.length(); i++) {
      //queue.add(Integer.parseInt(key.substring(i,i+1)));
      if (option == 4) javaqueue.add(Character.getNumericValue(key.charAt(i)));
      else {
        try {
          queue.add(Character.getNumericValue(key.charAt(i)));
        } catch (CuaPlena e) {
          System.out.println(e);
        }
      }
    }

    // Realitza el xifrat / desxifrat del fitxer.
    try {
      BufferedWriter fileout = new BufferedWriter(new FileWriter(
              filePath.substring(0, filePath.length() - 4) + (option2 == 1 ? "_vX.txt" : "_vD.txt")));
      String line = file.readLine();
      while (line != null) {
        switch (option2) {
          case 1:
            if (option == 4) fileout.write(encryptJava(line) + '\n');
            else fileout.write(encrypt(line) + '\n');
            break;
          case 2:
            if (option == 4) System.out.println(decryptJava(line) + '\n');
            else fileout.write(decrypt(line) + '\n');
            break;
          default:
            break;
        }
        line = file.readLine();
      }
      file.close();
      fileout.close();
    } catch (IOException e) {
      System.out.println("Error amb el fitxer: " + e);
    }

    // Fi d'execucio.
    long end = System.nanoTime();
    long elapsed = end - start;

    // Indica el temps d'execucio.
    System.out.println("Done! Temps d'execucio: " + elapsed);
  }
}
