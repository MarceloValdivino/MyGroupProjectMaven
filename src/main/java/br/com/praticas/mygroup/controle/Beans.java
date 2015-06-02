package br.com.praticas.mygroup.controle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

abstract class Beans {
    protected void showMessageDialog(String principal, String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, principal, mensagem);

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    protected void showMessageGrowl(String titulo, String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,  mensagem) );
    }
    
    protected void showDialog(String dialog){
        RequestContext.getCurrentInstance().execute("PF('"+dialog+"').show()");
    }
    
    protected void hideDialog(String dialog){
        RequestContext.getCurrentInstance().execute("PF('"+dialog+"').hide()");
    }
}
