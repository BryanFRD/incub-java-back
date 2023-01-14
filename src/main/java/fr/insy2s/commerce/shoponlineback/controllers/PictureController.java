package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Picture;
import fr.insy2s.commerce.shoponlineback.services.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/picture")
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    @GetMapping("/all-picture")
    public List<Picture> allPicture()
    {
        return this.pictureService.all();
    }

    @PostMapping("/add-picture")
    public String addPicture(@Validated @RequestBody Picture picture)
    {
        this.pictureService.add(picture);

        return "Picture successfully add";
    }

    @PutMapping("/update-picture/{idPicture}")
    public String updatePicture(@Validated @PathVariable Long idPicture, @RequestBody Picture picture)
    {
        this.pictureService.update(idPicture, picture);

        return "Picture update complete successfully";
    }

    @DeleteMapping("/remove-picture/{idPicture}")
    public String removePicture(@Validated @PathVariable Long idPicture)
    {
        this.pictureService.remove(idPicture);

        return "Picture successfully delete";
    }

    @GetMapping("/get-by-id-picture/{idPicture}")
    public Picture getByIdPicture(@Validated @PathVariable Long idPicture)
    {
        return this.pictureService.getById(idPicture);
    }
}
