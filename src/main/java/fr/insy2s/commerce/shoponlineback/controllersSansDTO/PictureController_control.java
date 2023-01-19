package fr.insy2s.commerce.shoponlineback.controllersSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Picture;
import fr.insy2s.commerce.shoponlineback.servicesSansDTO.PictureService_serv;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/picture")
@RequiredArgsConstructor
public class PictureController_control {

    private final PictureService_serv pictureServiceServ;

    @GetMapping("/all-picture")
    public List<Picture> allPicture()
    {
        return this.pictureServiceServ.all();
    }

    @PostMapping("/add-picture")
    public String addPicture(@Validated @RequestBody Picture picture)
    {
        this.pictureServiceServ.add(picture);

        return "Picture successfully add";
    }

    @PutMapping("/update-picture/{idPicture}")
    public String updatePicture(@Validated @PathVariable Long idPicture, @RequestBody Picture picture)
    {
        this.pictureServiceServ.update(idPicture, picture);

        return "Picture update complete successfully";
    }

    @DeleteMapping("/remove-picture/{idPicture}")
    public String removePicture(@Validated @PathVariable Long idPicture)
    {
        this.pictureServiceServ.remove(idPicture);

        return "Picture successfully delete";
    }

    @GetMapping("/get-by-id-picture/{idPicture}")
    public Picture getByIdPicture(@Validated @PathVariable Long idPicture)
    {
        return this.pictureServiceServ.getById(idPicture);
    }
}
