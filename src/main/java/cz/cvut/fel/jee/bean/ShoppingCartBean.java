package cz.cvut.fel.jee.bean;


import cz.cvut.fel.jee.data.GalleryRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ShoppingCartBean {

    @Inject
    private GalleryRepository galleryRepository;

}
