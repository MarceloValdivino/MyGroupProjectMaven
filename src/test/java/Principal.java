
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.relatorios.UsuarioREL;
import br.com.praticas.mygroup.util.Facade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcelo
 */
public class Principal {

    public static void main(String[] args) {

        System.out.println("Entrando...");
        try {
            Facade facade = new Facade();
            List<Usuario> usuarios = facade.listarUsuarios();

            UsuarioREL relatorio = new UsuarioREL();
            
            relatorio.imprimir(usuarios);
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
