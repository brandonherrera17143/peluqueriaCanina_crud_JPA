
package com.mycompany.peluqueriacanina.logica;

  // @author :) Guerr
import com.mycompany.peluqueriacanina.persistencia.ControladoraPersistencia;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void guardar(String nombre_mascota, String raza, String color, String observaciones, String alergico, String atencionEspe, String nombreDuenio, String celDuenio) {

        Duenio duenio = new Duenio();
        duenio.setNombre(nombreDuenio);
        duenio.setCelDuenio(celDuenio);
        
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre_mascota);
        mascota.setRaza(raza);
        mascota.setColor(color);
        mascota.setAlegico(alergico);
        mascota.setAtencion_especial(atencionEspe);
        mascota.setObservaciones(observaciones);
        mascota.setUnDuenio(duenio);
        
        controlPersis.guardar(duenio, mascota);
        
    }

    public List<Mascota> traerMascotas() {
        
        return controlPersis.traerMascotas();
        
    }

    public void borrarMascota(int num_cliente) {

        controlPersis.borrarMascota(num_cliente);

    }

    public Mascota traerMascota (int num_cliente) {
       return controlPersis.traerMascota(num_cliente);

    }

    public void modificarMascota(Mascota mascota, String nombre_mascota, String raza, String color, 
                                String observaciones, String alergico, String atencionEspe, 
                                String nombreDuenio, String celDuenio) {

        try {
            mascota.setNombre(nombre_mascota);
            mascota.setRaza(raza);
            mascota.setColor(color);
            mascota.setObservaciones(observaciones);
            mascota.setAlegico(alergico);
            mascota.setAtencion_especial(atencionEspe);
            
            //modifico la mascota
            controlPersis.modificarMascota(mascota);
            
            //colocamos el valor al duenio
            Duenio duenio = this.buscarDuenio(mascota.getUnDuenio().getId_duenio());
            duenio.setCelDuenio(celDuenio);
            duenio.setNombre(nombreDuenio);
            
            this.modificarDuenio(duenio);
            } catch (Exception ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }

    
    public Duenio buscarDuenio(int id_duenio){
        return controlPersis.buscarDuenio(id_duenio);
    }

    private void modificarDuenio(Duenio duenio) throws Exception {

        controlPersis.modificarDuenio(duenio);

    }
    
}
