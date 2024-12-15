package bg.softuni.traingexampojo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import bg.softuni.traingexampojo.service.CarService;
import bg.softuni.traingexampojo.service.OfferService;
import bg.softuni.traingexampojo.service.PictureService;
import bg.softuni.traingexampojo.service.SellerService;


@Controller
public class HomeController extends BaseController {

    private final CarService carService;
    private final OfferService offerService;
    private final PictureService pictureService;
    private final SellerService sellerService;

    @Autowired
    public HomeController(CarService carService, OfferService offerService, PictureService pictureService, SellerService sellerService) {
        this.carService = carService;
        this.offerService = offerService;
        this.pictureService = pictureService;
        this.sellerService = sellerService;
    }


//    @GetMapping("/")
    public ModelAndView index() {
        boolean areImported = this.carService.areImported() &&
                this.offerService.areImported() &&
                this.pictureService.areImported() &&
                this.sellerService.areImported();

        return super.view("index", "areImported", areImported);
    }
}
