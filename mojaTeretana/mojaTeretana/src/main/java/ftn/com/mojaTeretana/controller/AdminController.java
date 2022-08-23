package ftn.com.mojaTeretana.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import ftn.com.mojaTeretana.model.Korisnik;
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
	
	
	@RequestMapping("")
	   public String admin() {
	       return "treninzi";
	}
	
	@GetMapping
	public ModelAndView index() {
		List<Trening> treninzi = treningService.findAll();
		ModelAndView rezultat = new ModelAndView("admin");
		rezultat.addObject("treninzi", treninzi);
		
		return rezultat;
	}
	
	
	
	
	
	

}
