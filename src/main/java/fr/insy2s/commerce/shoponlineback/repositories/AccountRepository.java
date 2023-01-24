package fr.insy2s.commerce.shoponlineback.repositories;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
}
