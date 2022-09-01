package ftn.com.mojaTeretana.controller;



import java.io.IOException;
import java.io.PrintWriter;


import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

import ftn.com.mojaTeretana.model.ETipKorisnika;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.service.KorisnikService;

@Controller
@RequestMapping(value = "/korisnici")
public class KorisnikController implements ServletContextAware{

	
	public static final String KORISNIK_KEY = "korisnik";
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Controller
	public class ViewController {
		@RequestMapping("/locale")
		public String locale() {
			return "locale";
		}
	}
	
	@Autowired
	private KorisnikService korisnikService;
	
	
	//Inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {
		bURL = servletContext.getContextPath() + "/";
	}
	

	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	
	
	@GetMapping(value = "/registracija")
	public String create(HttpServletResponse response) {
		return "registracija";
	}
	
	
	@PostMapping(value = "/registracija")
	public void registracija(
	@RequestParam(required = true) String korisnickoIme,
	@RequestParam(required = true) String email,
	@RequestParam(required = true) String lozinka,
	@RequestParam(required = true) String ime,
	@RequestParam(required = true) String prezime,
	@RequestParam(required = true) String datumRodjenja,
	@RequestParam(required = true) String adresa,
	@RequestParam(required = true) String brojTelefona,
	HttpSession session, HttpServletResponse response) throws IOException {
		ETipKorisnika tipKorisnika = ETipKorisnika.POLAZNIK;
		String datumIVremeRegistracije = new String("11.07.2022, 15:39");
		
		Korisnik korisnik = new Korisnik(korisnickoIme, lozinka,email, ime, prezime, datumRodjenja, adresa, 
				brojTelefona,datumIVremeRegistracije, tipKorisnika);
		korisnikService.save(korisnik);
		response.sendRedirect(bURL + "index.html");
		
	}
	
	
	@GetMapping(value = "/login")
	public void getLogin(
	@RequestParam(required = false) String email,
	@RequestParam(required = false) String lozinka,
	HttpSession session, HttpServletResponse response) throws IOException {
		
	}
	
	
	
	@PostMapping(value = "/login")
	public void postLogin(
	@RequestParam(required = false) String email,
	@RequestParam(required = false) String lozinka,
	HttpSession session, HttpServletResponse response) throws IOException {
		Korisnik korisnik = korisnikService.findOne(email, lozinka);
		String greska = "";
		if(korisnik == null) 
			greska = "Kredencijali nisu ispravni! <br/>";
		if(korisnik != null) {
			if(korisnik.getUloga().equals("admin")) {
				session.setAttribute(KORISNIK_KEY, korisnik);
				response.sendRedirect(bURL + "admin");
			}
			else if(korisnik.getUloga().equals("clanTeretane")) {
				session.setAttribute(KORISNIK_KEY, korisnik);
				response.sendRedirect(bURL + "korisnik");
			}
		}
	
		if(!greska.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();
			
			
			StringBuilder rtVal = new StringBuilder();
			rtVal.append("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset =\"UTF-8\">\r\n"
			+ "<link rel =\"stylesheet\" type =\"text/css\" href = \"css/StiloveForma.css\"/>\r\n"
			+ "<link rel =\"stylesheet\" type =\"text/css\" href = \"css/StiloviHorizontalniMeni.css\"/>\r\n"
			+ "<link rel =\"stylesheet\" type =\"text/css\" href = \"css/registracija.css\"/>\r\n"
			+ "<base href = \"/ProjekatTeretana/\"> \r\n" 
			+ "<title>Prijava korisnika</title>\r\n"
		
			+ "</head>\r\n" + "<body>\r\n" + "<ul>\r\n");
			
		if(!greska.equals(""))
			rtVal.append("<div>" + greska + "</div>\r\n");
		rtVal.append("<form method = \"post\" action = \"korisnici/login\">\r\n" + "<table>\r\n" 
			+ "<caption>Prijava korisnika na sistem</caption>\r\n"
			+ "<tr><th>Email:</th><td><input type =\"text\" value = \"\" name = \"email\" required/></td></tr>\r\n" 
			+ "<tr><th>Lozinka:</th><td><input type = \"password\" value = \"\" name = \"lozinka\" required/></td></tr>\r\n" 
			+ "<tr><th>>/th><td><input type = \"submit\" value = \"Prijavite se\" /></td>\r\n" 
			+ "</table>\r\n" + "</form>\r\n" + "<br/>\r\n" + "</body>\r\n" + "</html>");
			
		out.write(rtVal.toString());
		return;
		}
		
		if(session.getAttribute(KORISNIK_KEY) != null)
			greska = "Korisnik je vec prijavljen na sistem</br>Morate se prethodno odjaviti<br/>";
		
		if(!greska.equals("")) {
			response.setContentType("text/html; charset = UTF-8");
			PrintWriter out;
			out = response.getWriter();
			
			StringBuilder rtVal = new StringBuilder();
			rtVal.append("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset =\"UTF-8\">\r\n"
			+ "<base href = \"/ProjekatTeretana/\">\r\n" 
			+ "<title>Prijava korisnika</title>\r\n" 
			+ "</head>\r\n" + "<body>\r\n" + "<ul>\r\n");
			
		if(!greska.equals(""))
			rtVal.append("<div>" + greska + "</div>\r\n");
			rtVal.append("<a href = \"index.html\">Povratak</a>\r\n" 
			+ "<br/>\r\n" + "</body>\r\n" + "</html>");
		out.write(rtVal.toString());
		return;
			
			
		}
		if(korisnik.getTipKorisnika().equals(ETipKorisnika.ADMINISTRATOR)) {
			session.setAttribute(KORISNIK_KEY, korisnik);
			response.sendRedirect(bURL + "admin");
		}
		else if(korisnik.getTipKorisnika().equals(ETipKorisnika.POLAZNIK)) {
			session.setAttribute(KORISNIK_KEY, korisnik);
			response.sendRedirect(bURL + "korisnik");
		}
		else {
			System.out.println("Korisnik je blokiran");
			response.sendRedirect(bURL + "greska.html");
		}
		
	}
	
	
	@PostMapping(value = "/greska")
	public void greska(HttpSession session, HttpServletResponse response) throws IOException {
		response.sendRedirect(bURL + "greska.html");
	
	}
	
	
}
