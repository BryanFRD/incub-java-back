package fr.insy2s.commerce.shoponlineback.repositories;

import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Ordered, Long> {
}
