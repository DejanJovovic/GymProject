package ftn.com.mojaTeretana.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import ftn.com.mojaTeretana.model.ClanskaKarta;
import ftn.com.mojaTeretana.model.ENivoTreninga;
import ftn.com.mojaTeretana.model.EVrstaTreninga;
import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Korpa;
import ftn.com.mojaTeretana.model.Sala;
import ftn.com.mojaTeretana.model.TerminTreninga;
import ftn.com.mojaTeretana.model.TipTreninga;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.ClanskaKartaService;
import ftn.com.mojaTeretana.service.KomentarService;
import ftn.com.mojaTeretana.service.KorisnikService;
import ftn.com.mojaTeretana.service.KorpaService;
import ftn.com.mojaTeretana.service.SalaService;
import ftn.com.mojaTeretana.service.TerminTreningaService;
import ftn.com.mojaTeretana.service.TipTreningaService;
import ftn.com.mojaTeretana.service.TreningService;


@Controller
@RequestMapping(value = "/admin")
public class AdminController implements ServletContextAware{

	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private TreningService treningService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KomentarService komentarService;
	
	@Autowired
	private TipTreningaService tipTreningaService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private TerminTreningaService terminTreningaService;
	
	@Autowired
	private ClanskaKartaService clanskaKartaService;
	
	@Autowired
	private KorpaService korpaService;
	
	
	//Inicijalizacija podataka za kontroler */
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
		ModelAndView rezultat = new ModelAndView("admin");
		rezultat.addObject("treninzi", treninzi);
		
		return rezultat;
	}
	
	
	@GetMapping(value = "/korisnici") 
	public ModelAndView korisnici() {
		List<Korisnik> korisnici = korisnikService.findAll();
		ModelAndView rezultati = new ModelAndView("korisnici");
		rezultati.addObject("korisnici", korisnici);
		
		return rezultati;
	}
	
	@GetMapping(value = "/profil")
	public ModelAndView profil(HttpSession session) {
		Korisnik ulogovan = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		Korisnik korisnik = korisnikService.findOneById(ulogovan.getId());
		ModelAndView rezultati = new ModelAndView("profil");
		rezultati.addObject("korisnik", korisnik);
		
		return rezultati;
		
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping(value = "/profil")
	public void editProfil(
	@ModelAttribute Korisnik profilEdit,
	@RequestParam(name = "ponovljenaLozinka") String ponovljenaLozinka, HttpServletResponse response) throws IOException{
		Korisnik korisnik = korisnikService.findOneById(profilEdit.getId());
		if(korisnik != null) {
			if(profilEdit.getKorisnickoIme() != null && !profilEdit.getKorisnickoIme().trim().equals(""))
				korisnik.setKorisnickoIme(profilEdit.getKorisnickoIme());
			if(profilEdit.getLozinka() != null && profilEdit.getLozinka().equals(ponovljenaLozinka))
				korisnik.setLozinka(profilEdit.getLozinka());
			else korisnik.setLozinka(profilEdit.getLozinka());
			
			if(profilEdit.getEmail() != null && !profilEdit.getEmail().trim().equals(""))
				korisnik.setEmail(profilEdit.getEmail());
			if(profilEdit.getIme() != null && !profilEdit.getIme().trim().equals(""))
				korisnik.setIme(profilEdit.getIme());
			if(profilEdit.getPrezime() != null && !profilEdit.getPrezime().trim().equals(""))
				korisnik.setPrezime(profilEdit.getPrezime());
			if(profilEdit.getTipKorisnika() != null)
				korisnik.setTipKorisnika(profilEdit.getTipKorisnika());
		}
		Korisnik sacuvaj = korisnikService.updateProfil(korisnik);
		response.sendRedirect(bURL + "admin");
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping(value = "/edit")
	public void editKorisnika(
	@ModelAttribute Korisnik korisnikEdit, HttpServletResponse response) throws IOException{
		Korisnik korisnik = korisnikService.findOneById(korisnikEdit.getId());
		if(korisnik != null) {
			if(korisnikEdit.getKorisnickoIme() != null && !korisnikEdit.getKorisnickoIme().trim().equals(""))
				korisnik.setKorisnickoIme(korisnikEdit.getKorisnickoIme());
			if(korisnikEdit.getEmail() != null && !korisnikEdit.getEmail().trim().equals(""))
				korisnik.setEmail(korisnikEdit.getEmail());
			if(korisnikEdit.getIme() != null && !korisnikEdit.getIme().trim().equals(""))
				korisnik.setIme(korisnikEdit.getIme());
			if(korisnikEdit.getPrezime() != null && !korisnikEdit.getPrezime().trim().equals(""))
				korisnik.setPrezime(korisnikEdit.getPrezime());
			if(korisnikEdit.getTipKorisnika() != null)
				korisnik.setTipKorisnika(korisnikEdit.getTipKorisnika());
			if(korisnikEdit.isAktivan() != true)
				korisnik.setAktivan(korisnikEdit.isAktivan());
			else
				korisnik.setAktivan(true);
		}
		Korisnik sacuvaj = korisnikService.update(korisnik);
		response.sendRedirect(bURL + "admin");
	}
	

	
	@GetMapping(value = "/komentari")
	public ModelAndView komentari() {
		List<Komentar> komentari = komentarService.FindAll();
		ModelAndView rezultati = new ModelAndView("komentari");
		rezultati.addObject("komentari", komentari);
		
		return rezultati;
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value = "komentari/odobriKomentar")
	private void odobriKomentar(
	@RequestParam Long id, HttpServletResponse response) throws IOException{
		Komentar komentar = komentarService.odobreno(id);
		response.sendRedirect(bURL + "admin");
		
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value = "komentari/obrisiKomentar")
	private void obrisiKomentar(
	@RequestParam Long id, HttpServletResponse response	) throws IOException{
		Komentar komentar = komentarService.delete(id);
		response.sendRedirect(bURL + "admin");
		
	}
	
	
	@GetMapping(value = "/clanskeKarte")
	public ModelAndView clanskeKarte() {
		List<ClanskaKarta> clanskeKarte = clanskaKartaService.findAll();
		ModelAndView rezultati = new ModelAndView("clanskeKarte");
		rezultati.addObject("clanskaKarta", clanskeKarte);
		
		return rezultati;
	}
	
	@PostMapping(value = "clanskeKarte/odobriClanskuKartu")
	private void odobriClanskuKartu(
	@RequestParam Long id, HttpServletResponse response) throws IOException{
		ClanskaKarta clanskaKarta = clanskaKartaService.odobri(id);
		response.sendRedirect(bURL + "admin");	
	}
	
	@PostMapping(value = "clanskeKarte/obrisiClanskuKartu")
	private void obrisiClanskuKartu(
	@RequestParam Long id, HttpServletResponse response) throws IOException{
		ClanskaKarta obrisanaClanskaKarta = clanskaKartaService.delete(id);
		response.sendRedirect(bURL + "admin");
	}
	
	
	
	@GetMapping(value = "/add")
	public String create(HttpServletResponse response) {
		return "dodajTrening";
	}
	

	@GetMapping(value = "/detaljiTreninga")
	@ResponseBody
	public ModelAndView detaljiTreninga(
	@RequestParam Long id, HttpServletResponse httpServletResponse) {
		Trening trening = treningService.findOneById(id);
		List<Komentar> komentari = komentarService.FindAllById(id);
		List<TerminTreninga> terminiTreninga = terminTreningaService.findAll(id);
		List<TipTreninga> tipoviTreninga = tipTreningaService.findAll(id);
		ModelAndView rezultati = new ModelAndView("treningAdmin");
		rezultati.addObject("trening", trening);
		rezultati.addObject("termini", terminiTreninga);
		rezultati.addObject("tipTreninga", tipoviTreninga);
		rezultati.addObject("komentar", komentari);
		
		return rezultati;
		
	}

	
	
	@GetMapping(value = "/sale")
	public ModelAndView sale() {
		List<Sala> sale = salaService.findAll();
		ModelAndView rezultati = new ModelAndView("sala");
		rezultati.addObject("sala", sale);
		
		return rezultati;
	}

	
	@SuppressWarnings("unused")
	@PostMapping(value = "sale/addSala")
	public void create(
	@RequestParam int kapacitet, HttpServletResponse response)  throws IOException{
		Sala sala = new Sala(kapacitet);
		Sala saved = salaService.save(sala);
		response.sendRedirect(bURL + "admin");
		
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping(value = "sale/editSala")
	private void editSala(
	@RequestParam(name = "id") Long id,
	@RequestParam(name = "kapacitet") int kapacitet, HttpServletResponse response) throws IOException{
		Sala sala = salaService.findOneById(id);
		System.out.println(kapacitet);
		sala.setKapacitet(kapacitet);
		salaService.update(sala);
		response.sendRedirect(bURL + "admin");
		
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping(value = "sale/deleteSala")
	private void deleteSala(
	@RequestParam Long id, HttpServletResponse response) throws IOException{
		List<TerminTreninga> terminiProvera = terminTreningaService.checkifExists(id);
		if(terminiProvera.size() > 0) {
			System.out.println("Nije moguce obrisati salu zato sto vec postoji termin treninga za nju!");
			return;
		}
		
	}
	
	@GetMapping(value = "/addTerminTreninga")
	public ModelAndView terminiTreninga() {
		List<Trening> treninzi = treningService.findAll();
		List<Sala> sale = salaService.findAll();
		ModelAndView rezultati = new ModelAndView("dodajTerminTreninga");
		rezultati.addObject("treninzi", treninzi);
		rezultati.addObject("sala", sale);
		
		return rezultati;
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping(value = "/addTerminTreninga")
	public void create(
	@RequestParam Long treningId,
	@RequestParam Long salaId,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datumTermina,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime vreme, HttpServletResponse response) throws IOException{
		LocalDateTime datumIVremeTermina = LocalDateTime.of(datumTermina, vreme);
		Trening trening = treningService.findOneById(treningId);
		Sala sala = salaService.findOneById(salaId);
		TerminTreninga terminTreninga = new TerminTreninga(trening, sala, datumIVremeTermina);
		terminTreningaService.save(terminTreninga);
		response.sendRedirect(bURL + "admin");
	}
	
	
	@SuppressWarnings("unused") 
	@PostMapping(value = "/addTrening")
	public void create(
	@RequestParam String naziv,
	@RequestParam String trener,
	@RequestParam String kratakOpis,
	@RequestParam String slika,
	@RequestParam int cena,
	@RequestParam EVrstaTreninga vrstaTreninga,
	@RequestParam ENivoTreninga nivoTreninga,
	@RequestParam int trajanjeTreninga,
	@RequestParam int prosecnaOcena, HttpServletResponse response) throws IOException{
		Trening trening = new Trening(naziv, trener, kratakOpis, slika, cena, vrstaTreninga, nivoTreninga,
				trajanjeTreninga, prosecnaOcena);
		Trening saved = treningService.save(trening);
		response.sendRedirect(bURL + "admin");
		
	}

	
	
	@SuppressWarnings("unused") 
	@PostMapping(value = "/deleteKorisnik")
	private void deleteKorisnik(
	@RequestParam Long id, HttpServletResponse response) throws IOException{
		Korisnik obrisan = korisnikService.delete(id);
		response.sendRedirect(bURL + "admin");
		
	}
	
	
	@GetMapping(value = "/korpa")
	@ResponseBody
	public ModelAndView detaljiKorpe(@RequestParam Long id) {
		Korpa korpa = korpaService.findOneById(id);
		ModelAndView rezultati = new ModelAndView("korpa");
		rezultati.addObject("korpa", korpa);
		
		return rezultati;
	}
	
	@GetMapping(value = "/details")
	@ResponseBody
	public ModelAndView details(@RequestParam Long id) {
		Korisnik korisnik = korisnikService.findOneById(id);
		List<Korpa> korpe = korpaService.findKorpa(id);
		Float sum = treningService.sum(id);
		ModelAndView rezultati = new ModelAndView("editKorisnika");
		rezultati.addObject("korisnik", korisnik);
		rezultati.addObject("korpa", korpe);
		rezultati.addObject("sum", sum);
		
		return rezultati;
		
	}
	
	@GetMapping(value ="/logout")
	@ResponseBody
	public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		request.getSession().removeAttribute(KorisnikController.KORISNIK_KEY);
		request.getSession().invalidate();
		response.sendRedirect(bURL);
		
	}
}
