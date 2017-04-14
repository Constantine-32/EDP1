package Dades;

import Exceptions.*;

public class CircularQueue<E> implements TADGenericQueue<E> {
  private E[] list;
  private int size;
  private int head;

  @SuppressWarnings("unchecked")
  public CircularQueue(int dim) {
    list = (E[])new Object[dim];
    size = 0;
    head = 0;
  }

  @Override
  public void add(E e) throws CuaPlena {
    if (isFull()) throw new CuaPlena();
    list[(head + size) % list.length] = e;
    size++;
  }

  @Override
  public E remove() throws CuaBuida {
    if (isEmpty()) throw new CuaBuida();
    E aux = list[head];
    head = (head + 1) % list.length;
    size--;
    return aux;
  }

  @Override
  public E peek() throws CuaBuida {
    if (isEmpty()) throw new CuaBuida();
    return list[head];
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean isFull() {
    return size == list.length;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public String toString() {
    if (isEmpty()) return "[]";
    StringBuilder sb = new StringBuilder(size * 10).append('[');
    for (int i = head; i < head + size - 1; i++) {
      sb.append(list[i % list.length].toString()).append(',').append(' ');
    }
    sb.append(list[head + size - 1].toString()).append(']');
    return sb.toString();
  }
}
