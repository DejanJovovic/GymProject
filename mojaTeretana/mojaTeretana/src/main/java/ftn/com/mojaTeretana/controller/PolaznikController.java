package ftn.com.mojaTeretana.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import ftn.com.mojaTeretana.model.ClanskaKarta;
import ftn.com.mojaTeretana.model.Status;
import ftn.com.mojaTeretana.model.StatusClanskeKarte;
import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Korpa;
import ftn.com.mojaTeretana.model.TerminTreninga;
import ftn.com.mojaTeretana.model.TipTreninga;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.ClanskaKartaService;
import ftn.com.mojaTeretana.service.KomentarService;
import ftn.com.mojaTeretana.service.KorisnikService;
import ftn.com.mojaTeretana.service.KorpaService;
import ftn.com.mojaTeretana.service.TerminTreningaService;
import ftn.com.mojaTeretana.service.TipTreningaService;
import ftn.com.mojaTeretana.service.TreningService;


@Controller
@RequestMapping(value = "/korisnik")
public class PolaznikController implements ServletContextAware{

	
	public static final String TERMIN_ZELJA = "zeljeni_termin";
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private TreningService treningService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private TipTreningaService tipTreningaService;
	
	@Autowired
	private KomentarService komentarService;
	
	@Autowired 
	private TerminTreningaService terminTreningaService;
	
	@Autowired
	private ClanskaKartaService clanskaKartaService;
	
	@Autowired
	private KorpaService korpaService;
	
	
	@PostConstruct
	public void init() {
		bURL = servletContext.getContextPath() + "/";
	}
	

	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	
	@GetMapping
	public ModelAndView index() {
		List<Trening> treninzi = treningService.findAll();
		ModelAndView rezultati = new ModelAndView("korisnik");
		rezultati.addObject("treninzi", treninzi);
		
		return rezultati;
	}
	
	@GetMapping(value = "/profil")
	public ModelAndView profil(HttpSession session) {
		Korisnik ulogovan = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		Korisnik korisnik = korisnikService.findOneById(ulogovan.getId());
		ModelAndView rezultati = new ModelAndView("polaznikProfil");
		rezultati.addObject("korisnik", korisnik);
		
		return rezultati;
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value = "/profil")
	public void editProfil(
	@ModelAttribute Korisnik profilEdit, 
	HttpServletResponse response) throws IOException {
		Korisnik korisnik = korisnikService.findOneById(profilEdit.getId());
		if(korisnik != null) {
			if(profilEdit.getKorisnickoIme() != null && !profilEdit.getKorisnickoIme().trim().equals(""))
				korisnik.setKorisnickoIme(profilEdit.getKorisnickoIme());
			if(profilEdit.getLozinka() != null && profilEdit.getLozinka().equals(""))
				korisnik.setLozinka(profilEdit.getLozinka());
			if(profilEdit.getEmail() != null && !profilEdit.getEmail().trim().equals(""))
				korisnik.setEmail(profilEdit.getEmail());
			if(profilEdit.getIme() != null && !profilEdit.getIme().trim().equals(""))
				korisnik.setIme(profilEdit.getIme());
			if(profilEdit.getPrezime() != null && !profilEdit.getPrezime().trim().equals(""))
				korisnik.setPrezime(profilEdit.getPrezime());
			if(profilEdit.getTipKorisnika() != null)
				korisnik.setTipKorisnika(profilEdit.getTipKorisnika());
			else korisnik.setLozinka(profilEdit.getLozinka());
		}
		Korisnik sacuvaj = korisnikService.updateProfil(korisnik);
		response.sendRedirect(bURL + "korisnik");
	}
	
	@GetMapping(value = "/clanskaKarta")
	public ModelAndView clanskaKarta(HttpSession session) {
		Korisnik ulogovan = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		ClanskaKarta clanskaKarta = clanskaKartaService.findOdobreno(ulogovan.getId());
		ModelAndView rezultati = new ModelAndView("clanskaKarta");
		rezultati.addObject("clanskaKarta", clanskaKarta);
		
		return rezultati;
	}
	
	@PostMapping(value = "/posalji")
	public void posalji(
	HttpServletResponse response, HttpSession session) throws IOException{
		Korisnik ulogovan = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		int procenat = 50;
		int brojBodova = 10;
		StatusClanskeKarte statusC = StatusClanskeKarte.CEKANJE;
		ClanskaKarta clanskaKarta = new ClanskaKarta(ulogovan, procenat, brojBodova, statusC);
		clanskaKartaService.save(clanskaKarta);
		response.sendRedirect(bURL + "korisnik");
		
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/korpa")
	@ResponseBody
	public ModelAndView dodajZeljeno(HttpSession session) {
		Korisnik ulogovan = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		List<TerminTreninga> zaKorpu = (List<TerminTreninga>) session.getAttribute(TERMIN_ZELJA);
		List<Korpa> korpa = korpaService.findKorpa(null);
		ModelAndView rezultati = new ModelAndView("korpa");
		rezultati.addObject("terminTreninga", zaKorpu);
		rezultati.addObject("korpa", korpa);
		
		return rezultati;		
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/dodajUKorpu")
	public void dodajUKorpu(
	@RequestParam(name = "terminId", required = false) Long id,
	HttpSession session, HttpServletResponse response) throws IOException{
		List<TerminTreninga> zaKorpu = (List<TerminTreninga>) session.getAttribute(TERMIN_ZELJA);
		TerminTreninga terminTreninga = terminTreningaService.findOneById(id);
		zaKorpu.add(terminTreninga);
		response.sendRedirect(bURL + "korisnik");
		
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/korpa/ukloni")
	@ResponseBody
	public void ukloniIzKopre(
		@RequestParam(name = "idTermina") Long id, HttpSession session, HttpServletResponse response) throws IOException {
		List<TerminTreninga> zaKorpu = (List<TerminTreninga>) session.getAttribute(TERMIN_ZELJA);
		for (TerminTreninga termin : zaKorpu) {
			if(termin.getId().equals(id)) {
				zaKorpu.remove(termin);
				break;
			}
		}
		response.sendRedirect(bURL + "korisnik");
		
	}
	
	@PostMapping(value = "/korpa/zakazi")
	public void dodajUKorpu(
	@RequestParam(name = "idTermina") Long id, HttpServletResponse response, HttpSession session	) throws IOException {
		Korisnik ulogovan = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		TerminTreninga terminTreninga = terminTreningaService.findOneById(id);
		Korpa korpa = new Korpa(ulogovan, terminTreninga);
		int broj = (int) (terminTreninga.getTreningId().getCena() / 500);
		ClanskaKarta clanskaKarta = clanskaKartaService.findOdobreno(ulogovan.getId());
		clanskaKarta.setBrojPoena(clanskaKarta.getBrojPoena() + broj);
		int popustC = broj * 5;
		clanskaKarta.setPopust(clanskaKarta.getPopust() + popustC);
		clanskaKartaService.update(clanskaKarta);
		korpaService.save(korpa);
		response.sendRedirect(bURL + "korisnik");
		
	}
	
	@PostMapping(value = "korpa/ukloniIzKorpe")
	private void deleteZakazano(
	@RequestParam(name = "idTermina") Long id,
	@RequestParam(name = "idTerm") Long idTermin, HttpServletResponse response, HttpSession session) throws IOException{
		Korpa obrisiIzKorpe = korpaService.deleteZakazano(id);
		Korisnik ulogovan = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		TerminTreninga terminTreninga = terminTreningaService.findOneById(idTermin);
		int broj = (int) (terminTreninga.getTreningId().getCena() / 500);
		ClanskaKarta clanskaKarta = clanskaKartaService.findOdobreno(ulogovan.getId());
		clanskaKarta.setBrojPoena(clanskaKarta.getBrojPoena() - broj);
		int popust = broj * 5;
		clanskaKarta.setPopust(clanskaKarta.getPopust() - popust);
		clanskaKartaService.update(clanskaKarta);
		response.sendRedirect(bURL + "korisnik");
	
	}
	
	@GetMapping(value = "/details")
	@ResponseBody
	public ModelAndView details(
	@RequestParam Long id, HttpServletResponse httpServletResponse) {
		Trening trening = treningService.findOne(id);
		List<Komentar> komentari = komentarService.findAllById(id);
		List<TerminTreninga> terminiTreninga = terminTreningaService.findAll(id);
		List<TipTreninga> tipTreninga = tipTreningaService.findAll(id);
		ModelAndView rezultati = new ModelAndView("trening");
		rezultati.addObject("trening", trening);
		rezultati.addObject("terminiTreninga", terminiTreninga);
		rezultati.addObject("tipTreninga", tipTreninga);
		rezultati.addObject("komentar", komentari);
		
		return rezultati;
		
	}
	
	@PostMapping(value = "/addKomentar")
	public void create(
	@RequestParam String tekstKomentara,
	@RequestParam int ocena,
	@RequestParam Long id,
	@RequestParam(required=false) boolean anoniman,
	 HttpServletResponse response, HttpSession session)  throws IOException{
		LocalDate datum = LocalDate.now();
		String autor = new String("Dejan");
		Korisnik ulogovani = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		Status status = Status.CEKANJE;
		Trening trening = treningService.findOne(id);
		Komentar komentar = new Komentar(id, tekstKomentara, ocena, datum,ulogovani,trening, status, anoniman, autor);
		
		if(anoniman == true) {
			String anonimanKorisnik = new String("true");
		} else {
			String korisnik = new String("false");
		}
	
		komentarService.save(komentar);
		response.sendRedirect(bURL + "korisnik");
		
	}
	
	
	@PostMapping(value = "/zakaziTrening") 
	public void zakaziTrening(
	@RequestParam(required = false) Long id, HttpServletResponse response, HttpSession session	) throws IOException{
		Korisnik ulogovan = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		TerminTreninga terminTreninga = terminTreningaService.findOneById(id);
		Korpa korpa = new Korpa(ulogovan, terminTreninga);
		int brojTreninga = (int) (terminTreninga.getTreningId().getCena() / 500);
		ClanskaKarta clanskaKarta = clanskaKartaService.findOdobreno(ulogovan.getId());
		clanskaKarta.setBrojPoena(clanskaKarta.getBrojPoena() + brojTreninga);
		int popust = brojTreninga * 5;
		clanskaKarta.setPopust(clanskaKarta.getPopust() + popust);
		clanskaKartaService.update(clanskaKarta);
		korpaService.save(korpa);
		response.sendRedirect(bURL + "korisnik");
		
	}
	
	@GetMapping(value = "/logout")
	@ResponseBody
	public void logout(
	HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		request.getSession().removeAttribute(KorisnikController.KORISNIK_KEY);
		request.getSession().invalidate();
		response.sendRedirect(bURL);
		
	}

}
