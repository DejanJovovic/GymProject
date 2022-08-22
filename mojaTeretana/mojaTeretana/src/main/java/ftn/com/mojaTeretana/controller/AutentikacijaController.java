package ftn.com.mojaTeretana.controller;

import static ftn.com.mojaTeretana.controller.mainController.KORISNIK;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.service.KorisnikService;

public class AutentikacijaController implements ServletContextAware {
	
	  @Autowired
	    private ServletContext servletContext;
	    private String bURL;
	
	 @Autowired
	    private KorisnikService korisnikService;
	 
	 

	

    @GetMapping("/logout")
    public String Odjava(HttpServletRequest request) {

        request.getSession().removeAttribute(KORISNIK);
        request.getSession().invalidate();
        return "redirect:/login";
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


	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
	}
	
}
