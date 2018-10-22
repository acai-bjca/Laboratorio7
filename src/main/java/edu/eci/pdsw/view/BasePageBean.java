package edu.eci.pdsw.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import com.google.inject.Injector;

/**
 * Esta clase con su contenido es para que se puedan inyectar los 
 * componentes necesarios en todas las clases “hijas” que serán 
 * los beans de la capa de presentación:
 * @author amalia
 *
 */
public abstract class BasePageBean implements Serializable {

    private Injector injector;

    public Injector getInjector() {
        if (injector == null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                    .getContext();
            injector = (Injector) servletContext.getAttribute(Injector.class.getName());
        }
        return injector;
    }

    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @PostConstruct
    public void init() {
        getInjector().injectMembers(this);
    }
}
