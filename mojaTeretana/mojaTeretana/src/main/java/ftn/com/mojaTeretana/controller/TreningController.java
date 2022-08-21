package ftn.com.mojaTeretana.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.TreningService;
import ftn.com.mojaTeretana.service.korisnikService;

@Controller
@RequestMapping("/treninzi")
public class TreningController {
	
	@Autowired
	private TreningService treningService;
	
	  @Autowired
	    private korisnikService korisnikService;

	
	
	//@GetMapping
	
	//@GetMapping("{id}")
	
	

	
	//@PostMapping
	

    @GetMapping
    @RequestMapping("/dodavanjeTreninga")
    public String dodavanjeTreninga(@PathVariable("korisnickoIme") String korisnickoIme, Model model) {
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);
        model.addAttribute("korisnik",korisnik);
        return "dodavanjeTreninga";
    }

    @GetMapping("/dodavanjeTreninga-novitrening")
    public String dodavanjeTreninga(@RequestParam(required = true) String naziv, @RequestParam(required = true) String trener,
                                  @RequestParam(required = true) String kratakOpis, @RequestParam(required = true) String slika,
                                  @RequestParam(required = true) int cena, @RequestParam(required = true) String tipTreninga,
                                  @RequestParam(required = true) String vrstaTreninga, @RequestParam(required = true) String nivoTreninga,
                                  @RequestParam(required = true) int trajanjeTreninga, HttpSession session,
                                  HttpServletResponse response,
                                  @PathVariable("korisnickoIme") String korisnickoIme) throws IOException {
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);

        Trening trening = new Trening(naziv, kratakOpis, slika, tipTreninga, cena, vrstaTreninga,
                nivoTreninga, trajanjeTreninga, 0, trener);

        treningService.save(trening);
        return "redirect:/admin/Korisnicko_ime=" + korisnik.getKorisnickoIme();
    }

    @GetMapping("/trening/{id}")
    public String prikazTreninga(@PathVariable(required = true) Long id,
                                       @PathVariable(required = true) String korisnickoIme,
                                 Model model) {
        Trening trening = treningService.findOneById(id);
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);

        model.addAttribute("trening", trening);
        model.addAttribute("korisnik", korisnik);
        return "prikazTreninga";
    }

    @GetMapping("/trening/{id}/izmenaTreninga")
    public String izmenaTreninga(@PathVariable("id") Long treningId, @PathVariable("korisnickoIme") String korisnickoIme,
                                 Model model) {
        model.addAttribute("trening", treningService.findOneById(treningId));
        model.addAttribute("korisnik", korisnikService.findOneByUsername(korisnickoIme));

        return "izmenaTreninga";
    }
	
    
    @PostMapping("trening/{id}/izmenaTreninga-izmena")
    public String IzmenaTreninga(@PathVariable("id") Long Treningid, @ModelAttribute Trening trening1,
                                 @PathVariable("korisnickoIme") String korisnickoIme) {
        Trening trening = treningService.findOneById(Treningid);

        if(trening != null) {
            trening.setId(trening1.getId());
            trening.setSlika(trening1.getSlika());
            trening.setNaziv(trening1.getNaziv());
            trening.setTrener(trening1.getTrener());
            trening.setOpis(trening1.getOpis());
            trening.setTipTreninga(trening1.getTipTreninga());
            trening.setCena(trening1.getCena());
            trening.setVrstaTreninga(trening1.getVrstaTreninga());
            trening.setNivoTreninga(trening1.getNivoTreninga());
            trening.setTrajanjeTreninga(trening1.getTrajanjeTreninga());
            trening.setProsecnaOcena(trening1.getProsecnaOcena());

            treningService.update(trening);
        }
        return "redirect:/admin/Korisnicko_ime=" + korisnickoIme + "/trening/" + Treningid;
    }
	
	
	

}
