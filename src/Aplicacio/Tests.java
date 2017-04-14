package Aplicacio;

import Dades.*;
import Exceptions.CuaBuida;

public class Tests {
  public static void main(String[] args) {

    DynamicQueue<Integer> queue = new DynamicQueue<>();

    queue.add(3);
    queue.add(5);
    queue.add(7);

    System.out.println(queue);

    while (!queue.isEmpty()) {
      try {
        System.out.println("Pop item: "+queue.remove());
      } catch (CuaBuida e) {
        System.out.println(e);
      }
    }

    System.out.println(queue);

    queue.add(3);
    queue.add(5);
    queue.add(7);

    System.out.println(queue);

    while (!queue.isEmpty()) {
      try {
        System.out.println("Pop item: "+queue.remove());
      } catch (CuaBuida e) {
        System.out.println(e);
      }
    }
  }
}
