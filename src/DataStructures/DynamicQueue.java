package DataStructures;

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
    if (isEmpty()) throw new CuaBuida();
    E aux = head.getItem();
    head = head.getNext();
    size--;
    return aux;
  }

  @Override
  public E peek() throws CuaBuida {
    if (isEmpty()) throw new CuaBuida();
    return head.getItem();
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

  @Override
  public String toString() {
    if (isEmpty()) return "[]";
    StringBuilder sb = new StringBuilder(size * 10).append('[');
    Node<E> aux = head;
    while (aux.getNext() != null) {
      sb.append(aux.getItem().toString()).append(',').append(' ');
      aux = aux.getNext();
    }
    sb.append(aux.getItem().toString()).append(']');
    return sb.toString();
  }
}
