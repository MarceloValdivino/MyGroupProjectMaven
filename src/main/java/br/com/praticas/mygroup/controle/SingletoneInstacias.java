package br.com.praticas.mygroup.controle;

import java.util.HashMap;
import java.util.Map;



public class SingletoneInstacias {
    
    private static Map<String, Object> coisas;
    private static SingletoneInstacias instanciaUnica;
    
    public static Map<String, Object> getCoisas() {
        if(coisas==null){
            coisas = new HashMap<>();
        }
        return coisas;
    }
    
    
    private SingletoneInstacias(){
    }
}
