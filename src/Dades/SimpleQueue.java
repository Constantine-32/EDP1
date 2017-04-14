package Dades;

import Exceptions.*;

public class SimpleQueue<E> implements TADGenericQueue<E> {
  private E[] list;
  private int size;

  @SuppressWarnings("unchecked")
  public SimpleQueue(int dim) {
    list = (E[]) new Object[dim];
    size = 0;
  }

  @Override
  public void add(E e) throws CuaPlena {
    if (isFull()) throw new CuaPlena();
    list[size++] = e;
  }

  @Override
  public E remove() throws CuaBuida {
    if (isEmpty()) throw new CuaBuida();
    E aux = list[0];
    size--;
    System.arraycopy(list, 1, list, 0, size);
    return aux;
  }

  @Override
  public E peek() throws CuaBuida {
    if (isEmpty()) throw new CuaBuida();
    return list[0];
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
    for (int i = 0; i < size - 1; i++) {
      sb.append(list[i].toString()).append(',').append(' ');
    }
    sb.append(list[size - 1].toString()).append(']');
    return sb.toString();
  }
}
