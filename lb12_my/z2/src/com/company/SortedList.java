package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class SortedList<T extends Comparable> implements List<T> {
    private ArrayList<T> list = new ArrayList<>();

    //@Override (Task 2a) simple add to ArrayList<>()
    //public boolean add(T t) {
    //    return list.add(t);
    //}

    //@Override (Task 2b) my solution LUL; add and sort
    //public boolean add(T t) {
    //    list.add(t);
    //    list = (ArrayList<T>) list.stream().sorted().collect(Collectors.toList());
    //    return true;
    //}

    // I don't know how does it work LUL
    // maybe later i'll know LOL
    @Override // Task (2b) algorithm "divide and conquer"
    public boolean add(T t) {
        if (list.size() == 0)
            return list.add(t);
        else {
            int last = list.size() - 1, first = 0;
            int middle = (last + first) / 2;
            while (first <= last) {
                if (list.get(middle).compareTo(t) > 0) {
                    last = middle - 1;
                } else {
                    first = middle + 1;
                }
                middle = (last + first) / 2;
            }
            list.add(first, t);
        }
        return true;
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
