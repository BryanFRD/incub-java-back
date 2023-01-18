package fr.insy2s.commerce.shoponlineback.interfaces;

import java.util.List;

public interface Webservices<T> {

    List<T> all();

    void add(T e);

    T update(Long id, T e);

    void remove(Long id);

    T getById(Long id);
}
