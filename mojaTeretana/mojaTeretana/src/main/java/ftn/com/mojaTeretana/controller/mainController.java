package ftn.com.mojaTeretana.controller;

import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.service.korisnikService;
import ftn.com.mojaTeretana.service.treningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/")
public class mainController implements ServletContextAware {

    public static final String KORISNIK = "korisnik";

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

    @GetMapping("/login")
    public String getPrijava() {
        return "prijava";
    }

    @GetMapping(value="/login-prijava")
    public String login(@RequestParam String korisnickoIme, @RequestParam String lozinka,
                        HttpServletResponse response, HttpSession session) throws IOException {
        Korisnik korisnik = korisnikService.findOne(korisnickoIme, lozinka);
        if(korisnik!=null) {
            if(korisnik.getUloga().equals("admin")) {
                session.setAttribute(KORISNIK, korisnik);
                return "redirect:/admin/Korisnicko_ime="+ korisnik.getKorisnickoIme();
            }
            else if(korisnik.getUloga().equals("clanTeretane")) {
                session.setAttribute(KORISNIK, korisnik);
                return "redirect:/clanTeretane/Korisnicko_ime="+ korisnik.getKorisnickoIme();
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
                             HttpServletResponse response) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String sada = dtf.format(now);

        Korisnik korisnik = new Korisnik(korisnickoIme, lozinka,email, ime, prezime,
                datumRodjenja,adresa,brojTelefona,sada,"clanTeretane");
        korisnikService.save(korisnik);
        System.out.println(bURL);
        response.sendRedirect(bURL + "login");
    }
}
