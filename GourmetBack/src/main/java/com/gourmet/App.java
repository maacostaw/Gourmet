package com.gourmet;


import static org.mockito.Mockito.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.gourmet.comunicacion.AccionUsuario;
import com.gourmet.comunicacion.GestionadorDeAcciones;
import com.gourmet.comunicacion.Notificacion;
import com.gourmet.recetario.entities.IngredienteAbstract;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;
import com.gourmet.recetario.enums.Perfil;
import com.gourmet.recetario.service.RecetaService;
import com.gourmet.recetario.service.RecetarioService;
import com.gourmet.usuario.Usuario;

@Import({AppConfig.class})
@SpringBootApplication
public class App implements CommandLineRunner{
	
	@Autowired
	private RecetaService recetaService;
	
	@Autowired
	private RecetarioService recetarioService;
	
	public static String imprimirRecetario(Recetario recetario) {
		String respuesta = recetario.getNombre() + "\n";
		for(Receta receta : recetario.getRecetas()) {
			respuesta += "\t" + receta.getNombre() + "\n";
			for(IngredienteAbstract ingrediente : receta.getIngredientes()) {
				respuesta += "\t\t" + ingrediente.toString() + "\n";
			}
		}
		return respuesta;
	}
	
	public void testNotificacion() {
		Recetario recetario1 = recetarioService.getRecetario((long)1);
    	
    	Receta pastaConCarne = recetaService.getReceta((long)1);
    	Receta pastaArrozYChile = recetaService.getReceta((long)2);
    	Receta pastaConQueso = recetaService.getReceta((long)3);
    	
    	Usuario miguel = new Usuario();
    	
    	Notificacion notificacion = mock(Notificacion.class);
    	
    	notificacion.publicar("a");
    	
    	miguel.agregarSuscripcion(recetario1, "maacosta@hexacta.com", Perfil.VEGETARIANO, notificacion);
    	
    	recetario1.addReceta(pastaConCarne);
    	recetario1.addReceta(pastaArrozYChile);
    	GestionadorDeAcciones.getInstancia().desactivarAccionUsuario(AccionUsuario.AVISAR_USUARIO);
    	recetario1.addReceta(pastaConQueso);
    	
    	//System.out.println( "Hello World!" );
    	//System.out.println(imprimirRecetario(recetario1));
    	    	
    	//System.out.println(receta1.cumpleElPerfil(Perfil.CARNIVORO));
	}
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }

	@Override
	public void run(String... args) throws Exception {

	}
}
