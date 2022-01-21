package com.ftn.mojaTeretana.controller;

import com.ftn.mojaTeretana.model.Korisnik;
import com.ftn.mojaTeretana.model.TipTreninga;
import com.ftn.mojaTeretana.model.Trening;
import com.ftn.mojaTeretana.service.korisnikService;
import com.ftn.mojaTeretana.service.treningService;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class mainController implements ServletContextAware {

    @Autowired
    private ServletContext servletContext;
    private String bURL;

    @Autowired
    private korisnikService korisnikService;

    @Autowired
    private treningService treningService;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping("")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String prijava() {
        return "prijava";
    }

    @RequestMapping(value="/login-prijava")
    public String login(@RequestParam String korisnickoIme, @RequestParam String lozinka) {
        Korisnik korisnik = korisnikService.findOne(korisnickoIme, lozinka);

        if(korisnik!=null) {
            if(korisnik.getUloga().equals("administrator"))
            {
                return "redirect:/admin/Korisnicko_ime="+korisnik.getKorisnickoIme();
            }
            else if(korisnik.getUloga().equals("clanTeretane")) {
                return "redirect:/clanTeretane/Korisnicko_ime="+korisnik.getKorisnickoIme();
            }
        }
        return "prijava";
    }

    @RequestMapping("/register")
    public String registracija() {
        return "registracija";
    }

    @PostMapping("/register-registracija")
    public void registracija(@RequestParam(required = true) String korisnickoIme, @RequestParam(required = true) String lozinka,
                             @RequestParam(required = true) String email, @RequestParam(required = true) String ime,
                             @RequestParam(required = true) String prezime, @RequestParam(required = true) String datumRodjenja,
                             @RequestParam(required = true) String adresa, @RequestParam(required = true) String brojTelefona,
                             HttpSession session, HttpServletResponse response) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String sada = dtf.format(now);

        Korisnik korisnik = new Korisnik(korisnickoIme, lozinka,email, ime, prezime,
                datumRodjenja,adresa,brojTelefona,sada,"clanTeretane");
        korisnikService.save(korisnik);
        response.sendRedirect(bURL + "login");
    }
    @GetMapping("/clanTeretane/trening/{id}")
    public ModelAndView prikazTreninga(@PathVariable(required = true) Long id) {
        Trening trening = treningService.findOneById(id);

        ModelAndView rezultat = new ModelAndView("prikazTreninga");
        rezultat.addObject("trening", trening);
        return rezultat;
    }

    @RequestMapping("/admin/dodavanjeTreninga")
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
}
