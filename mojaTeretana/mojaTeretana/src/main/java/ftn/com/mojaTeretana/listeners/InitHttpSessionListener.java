package ftn.com.mojaTeretana.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import ftn.com.mojaTeretana.controller.PolaznikController;
import ftn.com.mojaTeretana.model.TerminTreninga;



@Component
public class InitHttpSessionListener implements HttpSessionListener {

	
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Inicijalizacija sesisje HttpSessionListener...");
//		
//		//pri kreiranju sesije inicijalizujemo je ili radimo neke dodatne aktivnosti
		List<TerminTreninga> listaZelja = new ArrayList<TerminTreninga>();
		HttpSession session  = arg0.getSession();
		System.out.println("session id korisnika je "+session.getId());
		session.setAttribute(PolaznikController.TERMIN_ZELJA, listaZelja);
//		
		System.out.println("Uspeh HttpSessionListener!");
	}
	
	/** kod koji se izvrsava po brisanju sesije */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Brisanje sesije HttpSessionListener...");
		
		System.out.println("Uspeh HttpSessionListener!");
	}

}