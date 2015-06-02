package br.com.praticas.mygroup.controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class RenderBean {
    private boolean renderDTMinhasSalas = false;
    private boolean renderDTSalasParticipo = false;

    public boolean isRenderDTSalasParticipo() {
        return renderDTSalasParticipo;
    }

    public void setRenderDTSalasParticipo(boolean renderDTSalasParticipo) {
        this.renderDTSalasParticipo = renderDTSalasParticipo;
    }

    public boolean isRenderDTMinhasSalas() {
        return renderDTMinhasSalas;
    }

    public void setRenderDTMinhasSalas(boolean renderDTMinhasSalas) {
        this.renderDTMinhasSalas = renderDTMinhasSalas;
    }
    
    public void ativarRenderDTMinhasSalas(){
        renderDTMinhasSalas = !renderDTMinhasSalas;
        renderDTSalasParticipo = false;
    }
    
    public void ativarRenderDTSalasParticipando(){
        renderDTSalasParticipo = !renderDTSalasParticipo;
        renderDTMinhasSalas = false;
    }
}
