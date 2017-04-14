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
    if (!isFull()) {
      list[size++] = e;
    } else throw new CuaPlena();
  }

  @Override
  public E remove() throws CuaBuida {
    if (!isEmpty()) {
      E aux = list[0];
      size--;
      for (int i = 0; i < size; i++) {
        list[i] = list[i+1];
      }
      return aux;
    } else throw new CuaBuida();
  }

  @Override
  public E peek() throws CuaBuida {
    if (!isEmpty()) {
      return list[0];
    } else throw new CuaBuida();
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

  /**
   * Retorna una string que representa aquesta coleccio.
   * @return una string que representa aquesta coleccio
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (int i = 0; i < size; i++) {
      sb.append(list[i]);
      if (i < size - 1) sb.append(',').append(' ');
    }
    return sb.append(']').toString();
  }
}
