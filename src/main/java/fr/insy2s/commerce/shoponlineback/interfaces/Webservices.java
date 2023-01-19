package fr.insy2s.commerce.shoponlineback.interfaces;

import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Webservices<T> {

    List<T> all();

//    Page<AccountDTO> all(Pageable pageable);


    void add(T e);

    T update(Long id, T e);

    void remove(Long id);

    T getById(Long id);
}
