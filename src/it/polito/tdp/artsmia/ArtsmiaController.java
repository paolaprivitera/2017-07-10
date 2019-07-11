/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="boxLUN"
	private ChoiceBox<?> boxLUN; // Value injected by FXMLLoader

	@FXML // fx:id="btnCalcolaComponenteConnessa"
	private Button btnCalcolaComponenteConnessa; // Value injected by FXMLLoader

	@FXML // fx:id="btnCercaOggetti"
	private Button btnCercaOggetti; // Value injected by FXMLLoader

	@FXML // fx:id="btnAnalizzaOggetti"
	private Button btnAnalizzaOggetti; // Value injected by FXMLLoader

	@FXML // fx:id="txtObjectId"
	private TextField txtObjectId; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doAnalizzaOggetti(ActionEvent event) {
		// txtResult.setText("doAnalizzaOggetti");
		model.creaGrafo();
	}

	@FXML
	void doCalcolaComponenteConnessa(ActionEvent event) {
		// txtResult.setText("doCalcolaComponenteConnessa\n");
		
		String id = txtObjectId.getText();
		int idNumero = 0;
		
		// List<ArtObject> componenteConnessa = new LinkedList<ArtObject>();
		int numeroVertici = 0;
		
		if(id.compareTo("")==0) {
			txtResult.appendText("Inserire un object_id!\n");
			return;
		}
		else {
			idNumero = Integer.parseInt(id);
			if(model.controllaValidita(idNumero)) {
				
				txtResult.clear();
				
				numeroVertici = model.getComponenteConnessa(idNumero);
				
				txtResult.appendText("Numero vertici della componente connessa: "+numeroVertici);
				
				/*for(ArtObject ao : componenteConnessa) {
				txtResult.appendText(String.format("%s\n", ao.getName()));
				}*/
				
			}
			else {
				txtResult.appendText("Inserire un object_id valido!\n");
			}
		}
		
		
	}

	@FXML
	void doCercaOggetti(ActionEvent event) {
		txtResult.setText("doCercaOggetti");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxLUN != null : "fx:id=\"boxLUN\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert btnCalcolaComponenteConnessa != null : "fx:id=\"btnCalcolaComponenteConnessa\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert btnCercaOggetti != null : "fx:id=\"btnCercaOggetti\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert btnAnalizzaOggetti != null : "fx:id=\"btnAnalizzaOggetti\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert txtObjectId != null : "fx:id=\"txtObjectId\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

	}
	
	public void setModel(Model model) {
		this.model = model;
	}
}
