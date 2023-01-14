package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Command;
import fr.insy2s.commerce.shoponlineback.services.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/command")
@RequiredArgsConstructor
public class CommandController {

    private final CommandService commandService;

    @GetMapping("/all-command")
    public List<Command> allCommand()
    {
        return this.commandService.all();
    }

    @PostMapping("/add-command")
    public String addCommand(@Validated @RequestBody Command command)
    {
        this.commandService.add(command);

        return "Command successfully add";
    }

    @PutMapping("/update-command/{idCommand}")
    public String updateCommand(@Validated @PathVariable Long idCommand, @RequestBody Command command)
    {
        this.commandService.update(idCommand, command);

        return "Command update complete successfully";
    }

    @DeleteMapping("/remove-command/{idCommand}")
    public String removeCommand(@Validated @PathVariable Long idCommand)
    {
        this.commandService.remove(idCommand);

        return "Command delete successfully";
    }

    @GetMapping("/get-by-id-command/{idCommand}")
    public Command getByIdCommand(@Validated @PathVariable Long idCommand)
    {
        return this.commandService.getById(idCommand);
    }


}
