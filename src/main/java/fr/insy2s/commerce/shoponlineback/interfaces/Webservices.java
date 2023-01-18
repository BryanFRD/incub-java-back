package fr.insy2s.commerce.shoponlineback.interfaces;

import java.security.GeneralSecurityException;
import java.util.List;

public interface Webservices<T> {

    List<T> all();

    void add(T e) throws GeneralSecurityException;

    T update(Long id, T e);

    void remove(Long id);

    T getById(Long id);
}
