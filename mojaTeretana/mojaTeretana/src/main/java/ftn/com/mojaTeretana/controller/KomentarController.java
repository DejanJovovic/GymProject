package ftn.com.mojaTeretana.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.ServletContextAware;

import ftn.com.mojaTeretana.model.EStatusKomentara;
import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.service.KomentarService;
import ftn.com.mojaTeretana.service.KorisnikService;

public class KomentarController implements ServletContextAware {
	
    @Autowired
    private ServletContext servletContext;
    private String bURL;
	
    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KomentarService komentarService;

	
	
	 @GetMapping("/komentari")
	    public String komentari(@PathVariable("korisnickoIme") String korisnickoIme, Model model) {
	        List<Komentar> komentari = komentarService.FindAll();
	        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);

	        model.addAttribute("komentari", komentari);
	        model.addAttribute("korisnik", korisnik);
	        return "komentari";
	    }

	    @PostMapping("/komentari-odbi/{idKomentar}")
	    public String odbiKomentar(@PathVariable("korisnickoIme") String korisnickoIme,
	                               @PathVariable("idKomentar") Long idKomentar, @ModelAttribute Komentar komentar1) {
	        Komentar komentar = komentarService.FindOneById(idKomentar);
	        komentar.setId(komentar1.getId());
	        komentar.setAnoniman(komentar1.isAnoniman());
	        komentar.setTekstKomentara(komentar1.getTekstKomentara());
	        komentar.setAutor(komentar1.getAutor());
	        komentar.setTrening(komentar1.getTrening());
	        komentar.setDatumPostavljanja(komentar1.getDatumPostavljanja());
	        komentar.setStatusKomentara(EStatusKomentara.ODBIJEN);

	        komentarService.update(komentar);

	        return "redirect:/admin/Korisnicko_ime=" + korisnickoIme;
	    }

	    @GetMapping("/komentari-odobri/{idKomentar}")
	    public String odobriKomentar1(@PathVariable("korisnickoIme") String korisnickoIme,
	                               @PathVariable("idKomentar") Long idKomentar, @ModelAttribute Komentar komentar1, Model model) {
	        Komentar komentar = komentarService.FindOneById(idKomentar);

	        model.addAttribute("komentar", komentar);

	        komentar.setId(komentar1.getId());
	        komentar.setAnoniman(komentar1.isAnoniman());
	        komentar.setTekstKomentara(komentar1.getTekstKomentara());
	        komentar.setAutor(komentar1.getAutor());
	        komentar.setTrening(komentar1.getTrening());
	        komentar.setDatumPostavljanja(komentar1.getDatumPostavljanja());
	        //komentar.setStatus("ODOBREN");
	        komentar.setStatusKomentara(EStatusKomentara.ODOBREN);

	        komentarService.update(komentar);

	        return "redirect:/admin/Korisnicko_ime=" + korisnickoIme;
	    }

		@Override
		public void setServletContext(ServletContext servletContext) {
			// TODO Auto-generated method stub
			
		}

	
}
