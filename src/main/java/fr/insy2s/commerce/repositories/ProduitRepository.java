package fr.insy2s.commerce.repositories;

import fr.insy2s.commerce.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
