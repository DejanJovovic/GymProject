package ftn.com.mojaTeretana.controller;

import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.korisnikService;
import ftn.com.mojaTeretana.service.treningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/Korisnicko_ime={korisnickoIme}")
public class administratorController implements ServletContextAware {

    @Autowired
    private ServletContext servletContext;
    private String bURL;

    @Autowired
    private treningService treningService;

    @Autowired
    private korisnikService korisnikService;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping("")
    public String admin() {
        return "admin";
    }

    @GetMapping
    @ResponseBody
    public ModelAndView getData(@PathVariable(required = true) String korisnickoIme){
        List<Trening> treninzi = treningService.findAll();
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);
        ModelAndView rezultat = new ModelAndView("treninzi");
        rezultat.addObject("treninzi", treninzi);
        rezultat.addObject("korisnik", korisnik);

        return rezultat;
    }

    @RequestMapping("/dodavanjeTreninga")
    public String trening() {
        return "dodavanjeTreninga";
    }

    @PostMapping("/dodavanjeTreninga-novitrening")
    public void dodavanjeTreninga(@RequestParam(required = true) String naziv, @RequestParam(required = true) String trener,
                                  @RequestParam(required = true) String kratkiOpis, @RequestParam(required = true) String slika,
                                  @RequestParam(required = true) int cena, @RequestParam(required = true) String tipTreninga,
                                  @RequestParam(required = true) String vrstaTreninga, @RequestParam(required = true) String nivoTreninga,
                                  @RequestParam(required = true) int trajanjeTreninga, HttpSession session,
                                  HttpServletResponse response) throws IOException {

        Trening trening = new Trening(naziv, kratkiOpis, slika, tipTreninga, cena, vrstaTreninga,
                nivoTreninga, trajanjeTreninga, 0, trener);

        treningService.save(trening);
        response.sendRedirect(bURL + "admin");
    }

    @GetMapping("/trening/{id}")
    public ModelAndView prikazTreninga(@PathVariable(required = true) Long id,
                                       @PathVariable(required = true) String korisnickoIme) {
        Trening trening = treningService.findOneById(id);
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);

        ModelAndView rezultat = new ModelAndView("prikazTreninga");
        rezultat.addObject("trening", trening);
        rezultat.addObject("korisnik", korisnik);
        return rezultat;
    }

    @RequestMapping("/trening/{id}/izmenaTreninga")
    public String izmenaTreninga() {
        return "izmenaTreninga";
    }


}
