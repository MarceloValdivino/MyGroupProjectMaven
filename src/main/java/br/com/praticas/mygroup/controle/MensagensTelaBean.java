package br.com.praticas.mygroup.controle;

import br.com.praticas.mygroup.util.MensagensUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "msgBean")
@SessionScoped
public class MensagensTelaBean {
    
    private MensagensUtil msgs;
    private String valor;

    public String getValor(String valor) {
        return MensagensUtil.getValor(valor);
    }

    public void setValor(String valor) {
        this.valor = MensagensUtil.getValor(MensagensUtil.MSG_ERRO);
        
    }
    
    public MensagensUtil getMsgs() {
        return msgs;
    }

    public void setMsgs(MensagensUtil msgs) {
        this.msgs = msgs;
    }
}
