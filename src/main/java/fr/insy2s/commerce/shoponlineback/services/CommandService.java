package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Command;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandService implements Webservices<Command> {

    @Autowired
    private final CommandRepository commandRepository;

    public CommandService(CommandRepository commandeRepository) {
        this.commandRepository = commandeRepository;
    }

    @Override
    public List<Command> listes() {
        return this.commandRepository.findAll();
    }

    @Override
    public void add(Command e) {
        this.commandRepository.save(e);
    }

    @Override
    public Command update(Long id, Command e) {
        return this.commandRepository.findById(id)
                .map(p -> {
                    if (p.getCommandDate() != null)
                        p.setCommandDate(e.getCommandDate());
                    if (p.getStatut() != null)
                        p.setStatut(e.getStatut());
                    if (p.getDeliveryDate() != null)
                        p.setDeliveryDate(e.getDeliveryDate());
                    return this.commandRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("this id is not found sorrry"));
    }

    @Override
    public void remove(Long id) {

        Command commande = this.commandRepository.findById(id).get();

        if (commande != null)
            this.commandRepository.delete(commande);
    }

    @Override
    public Command getById(Long id) {
        return this.commandRepository.findById(id).orElseThrow();
    }
}
