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
import javax.transaction.Transactional;
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
        label.setText("Hello World!");
    }
    
    @FXML
    @Transactional
    private void handleGenerateAbandonedIslandButton(ActionEvent event) {
        Island island = new Island().setName("Java").setSize(126700);
        em.persist(island);
    }
    
    
    @FXML
    @Transactional
    private void handleGenerateIslandWith60PenguinsButton(ActionEvent event) {     
        Island island = new Island().setName("Lombok").setSize(4725);
        em.persist(island);        
        for(int i=0; i < 60; i++) {
            Penguin penguin = new Penguin();
            penguin.setName("Lombok-Penguin #"+i).setHome(island);
            em.persist(penguin);
        }
    }  
}
