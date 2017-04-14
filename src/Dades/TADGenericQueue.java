package Dades;

import Exceptions.*;
/**
 * Interface per a definir el contenidor/col·leccio cua generica.
 * @param <E> el tipus dels elements continguts a la cua.
 */
public interface TADGenericQueue<E> {
  /**
   * Afegeix l'element especificat a la cua.
   * @param e - element a afegir
   * @throws CuaPlena - la cua esta plena i no es pot afegir l'element
   */
  void add(E e) throws CuaPlena;

  /**
   * Retorna i elimina el primer element de la cua.
   * @return el primer element de la cua
   * @throws CuaBuida - la cua esta buida i no hi ha cap element per a retornar
   */
  E remove() throws CuaBuida;

  /**
   * Retorna, pero no elimina, el primer element de la cua.
   * @return el primer element de la cua
   * @throws CuaBuida - la cua esta buida i no hi ha cap element per a retornar
   */
  E peek() throws CuaBuida;

  /**
   * Retorna si la cua esta buida.
   * @return cert si la cua esta buida, fals en cas contrari
   */
  boolean isEmpty();

  /**
   * Retorna si la cua esta plena.
   * @return cert si la cua esta plena, fals en cas contrari
   */
  boolean isFull();

  /**
   * Retorna el numero d'enters guardats a la cua.
   * @return numero d'enters guardats a la cua
   */
  int size();
}
