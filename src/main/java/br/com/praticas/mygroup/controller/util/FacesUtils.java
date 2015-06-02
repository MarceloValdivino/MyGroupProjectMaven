/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.praticas.mygroup.controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Marcelo
 */
public class FacesUtils {

    public static void showMessageDialog(String principal, String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, principal, mensagem);

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public static void showMessageGrowl(String titulo, String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,  mensagem) );
    }
    
    public static void showDialog(String dialog){
        RequestContext.getCurrentInstance().execute("PF('"+dialog+"').show()");
    }
    
    public static void hideDialog(String dialog){
        RequestContext.getCurrentInstance().execute("PF('"+dialog+"').hide()");
    }
}
