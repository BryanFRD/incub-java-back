package fr.insy2s.commerce.shoponlineback.repositories;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Long> {

   List<Ordered> findByAccount(Account account);

   Optional<Ordered> findByRefOrdered(String refOrdered);

   List<Ordered> findByStatut(String statut);


}
