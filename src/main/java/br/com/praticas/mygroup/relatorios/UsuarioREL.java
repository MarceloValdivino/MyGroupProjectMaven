package br.com.praticas.mygroup.relatorios;

import br.com.praticas.mygroup.modelo.entidades.Usuario;
import java.util.List;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UsuarioREL {

    private String path; // Caminho Base

    private String pathToReportPackage; // Caminho para o pacote onde estão armazenados os relatórios Jasper

    // Recupera os caminhos para que a classe possa encontrar os relatórios
    //Recupera os caminhos para que a classe possa encontrar os relatórios
    public UsuarioREL() {
        this.path = this.getClass().getClassLoader().getResource("").getPath();
        this.pathToReportPackage = "c:/";
        System.out.println(path);
    }

    //Imprime/gera uma lista de Clientes
    public void imprimir(List<Usuario> usuarios) throws Exception {
        JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "UsuarioRelatorio.jrxml");

        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(usuarios));

        JasperExportManager.exportReportToPdfFile(print, "c:/Relatorio_de_Clientes.pdf");
    }

    public String getPathToReportPackage() {
        return pathToReportPackage;
    }

    public void setPathToReportPackage(String pathToReportPackage) {
        this.pathToReportPackage = pathToReportPackage;
    }

}
