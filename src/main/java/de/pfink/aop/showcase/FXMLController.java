package de.pfink.aop.showcase;

import de.pfink.aop.showcase.entities.Island;
import de.pfink.aop.showcase.entities.Penguin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import lombok.extern.java.Log;

@Log
public class FXMLController implements Initializable {
    private EntityManager em;
    
    @FXML
    private Label label;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        em = MainApp.getEntityManager();
    }  
    
    @FXML
    private void handleHelloWorldButton(ActionEvent event) {
        log.info("Triggered 'handleHelloWorldButton' method!");
        
        label.setText("Hello World!");
        
        log.info("Finished 'handleHelloWorldButton' method successfully!");
    }
    
    @FXML
    private void handleGenerateAbandonedIslandButton(ActionEvent event) {
        log.info("Triggered 'handleGenerateAbandonedIslandButton' method");
        
        em.getTransaction().begin();        
        try {
            Island island = new Island().setName("Java").setSize(126700);
            em.persist(island);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            em.getTransaction().rollback();
        }
        
        log.info("Finished 'handleGenerateAbandonedIslandButton' method successfully!");
    }
    
    
    @FXML
    private void handleGenerateIslandWith60PenguinsButton(ActionEvent event) {
        log.info("Triggered 'handleGenerateIslandWith60PenguinsButton' method");
        
        em.getTransaction().begin();
        try {            
            Island island = new Island().setName("Lombok").setSize(4725);
            em.persist(island);        
            for(int i=0; i < 60; i++) {
                Penguin penguin = new Penguin();
                penguin.setName("Lombok-Penguin #"+i).setHome(island);
                em.persist(penguin);
            }
            em.getTransaction().commit();
        }
        catch(Exception e) {
            em.getTransaction().rollback();
        }
        log.info("Finished 'handleGenerateIslandWith60PenguinsButton' method successfully!");
    }  
}
