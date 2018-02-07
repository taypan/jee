package cz.cvut.fel.jee.bean;


import cz.cvut.fel.jee.service.GalleryService;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ImageBean {

    @Inject
    private GalleryService galleryService;

    public String getBase64(Long imageId) {
        return galleryService.findById(imageId).getBase64();
    }


}
