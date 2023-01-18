package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Picture;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PictureService implements Webservices<Picture> {

    private final PictureRepository pictureRepository;

    @Override
    public List<Picture> all() {
        return this.pictureRepository.findAll();
    }

    @Override
    public void add(Picture e) {

        this.pictureRepository.save(e);
    }

    @Override
    public Picture update(Long id, Picture e) {
        return this.pictureRepository.findById(id)
                .map(p -> {
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if (p.getUrl() != null)
                        p.setUrl(e.getUrl());
                    if (p.getProduct() != null)
                        p.setProduct(e.getProduct());
                    return this.pictureRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("not found this id for picture"));
    }

    @Override
    public void remove(Long id) {

        Picture picture = this.pictureRepository.findById(id).get();

        if (picture != null)
            this.pictureRepository.delete(picture);
    }

    @Override
    public Picture getById(Long id) {
        return this.pictureRepository.findById(id).orElseThrow(()-> new RuntimeException("not found sorry"));
    }
}
