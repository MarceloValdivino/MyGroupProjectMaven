/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.relatorios.UsuarioREL;
import br.com.praticas.mygroup.util.Facade;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcelo
 */
public class RelatorioTeste {
    
    public RelatorioTeste() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaRelatorio() throws Exception{
        Facade facade = new Facade();
        List<Usuario> usuarios = new ArrayList<>();
        
        Usuario user = new Usuario();
        
        user.setId(Long.parseLong("1"));
        user.setNome("Cabeça de Pica Pereira");
        user.setLogin("picadura");
        user.setSenha("nadaquelheinteressa");
        user.setEmail("cabeçadepica@arrombado.com");
        user.setAtivo(true);
        UsuarioREL relatorio = new UsuarioREL();
        
        relatorio.imprimir(usuarios);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
