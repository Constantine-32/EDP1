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
    if (!isFull()) {
      list[(head + size) % list.length] = e;
      size++;
    } else throw new CuaPlena();
  }

  @Override
  public E remove() throws CuaBuida {
    if (!isEmpty()) {
      E aux = list[head];
      head = (head + 1) % list.length;
      size--;
      return aux;
    } else throw new CuaBuida();
  }

  @Override
  public E peek() throws CuaBuida {
    if (!isEmpty()) {
      return list[head];
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
    for (int i = head; i < head + size; i++) {
      sb.append(list[i % list.length]);
      if (i < head + size - 1) sb.append(',').append(' ');
    }
    return sb.append(']').toString();
  }
}
