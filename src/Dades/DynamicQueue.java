package Dades;

import Exceptions.*;

public class DynamicQueue<E> implements TADGenericQueue<E> {
  private Node<E> head;
  private Node<E> tail;
  private int size;

  public DynamicQueue() {
    head = null;
    tail = null;
    size = 0;
  }

  @Override
  public void add(E e) {
    Node<E> aux = new Node<>(e, null);
    if (isEmpty()) {
      head = aux;
      tail = aux;
    } else {
      tail.setNext(aux);
      tail = aux;
    }
    size++;
  }

  @Override
  public E remove() throws CuaBuida {
    if (!isEmpty()) {
      E aux = head.getItem();
      head = head.getNext();
      size--;
      return aux;
    } else throw new CuaBuida();
  }

  @Override
  public E peek() throws CuaBuida {
    if (!isEmpty()) {
      return head.getItem();
    } else throw new CuaBuida();
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  /**
   * Retorna una string que representa aquesta coleccio.
   * @return una string que representa aquesta coleccio
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    Node<E> aux = head;
    while (aux != null) {
      sb.append(aux.getItem());
      aux = aux.getNext();
      if (aux != null) sb.append(',').append(' ');
    }
    return sb.append(']').toString();
  }
}
