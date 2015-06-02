package br.com.praticas.mygroup.util;

import java.util.ResourceBundle;

public class MensagensUtil {
    
    private static final String FILE_NAME = "mensagens";
    public static final String MSG_SUCESSO_SALVAR = "msg_sucesso_salvar";
    public static final String MSG_SUCESSO_REMOVER = "msg_sucesso_remover";
    public static final String MSG_SUCESSO_EDITAR = "msg_sucesso_editar";
    
    public static final String MSG_ERRO_SALVAR = "msg_erro_salvar";
    public static final String MSG_ERRO_REMOVER = "msg_erro_remover";
    public static final String MSG_ERRO_EDITAR = "msg_erro_editar";
    
    public static final String MSG_ERRO = "msg_erro";
    public static final String MSG_LOGIN = "msg_login";
    public static final String MSG_LOGIN_AUTENTICAR = "msg_autenticar";
    public static final String MSG_ERRO_LOGIN = "msg_erro_login";
    public static final String MSG_ERRO_CONVITE = "msg_erro_convite";
    
    
    private static ResourceBundle bundle;
    
    static{
        bundle = ResourceBundle.getBundle(FILE_NAME);
    }
    
    public static String getValor(String chave){
        return bundle.getString(chave);
    }
}
