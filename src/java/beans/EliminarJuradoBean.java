/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Classes.Data;
import controlers.PersonaJpaController;
import entities.Persona;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cristian
 */
@ManagedBean
@RequestScoped
public class EliminarJuradoBean {

    private Persona persona = new Persona();
    private String error = "";
    private String success = "";

    /**
     * Creates a new instance of EliminarJuradoBean
     */

    public EliminarJuradoBean() {
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void eliminar() {
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("eliminar_jurado");
        System.out.println("El valo es =" + value);
        PersonaJpaController pController = new PersonaJpaController(Data.EMF);
        try {
            Long id = Long.parseLong(value);
            pController.destroy(id);
            success = "Se elimino correctamente el jurado";
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            } catch (IOException ex) {
                Logger.getLogger(EliminarEstudianteBean.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error: " + ex.getMessage());
            }
        } catch (Exception e) {
        }
    }

}
