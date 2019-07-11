package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private SimpleWeightedGraph<ArtObject, DefaultWeightedEdge> grafo;
	private List<ArtObject> oggetti;
	private ArtsmiaDAO dao;
	private Map<Integer, ArtObject> oggettiIdMap;
	
	public Model() {
		dao = new ArtsmiaDAO();
		oggetti = dao.listObjects();
		oggettiIdMap = new HashMap<Integer, ArtObject>();
	}

	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<ArtObject, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		for(ArtObject ao : this.oggetti) {
			oggettiIdMap.put(ao.getId(), ao);
		}
		
		Graphs.addAllVertices(this.grafo, oggetti);
		
		
		List<EsposizioneOggetti> espOgg = dao.getEsposizioneOggetti();
		
		for(EsposizioneOggetti eo : espOgg) {
		
			ArtObject o1 = oggettiIdMap.get(eo.getOggetto1());
			ArtObject o2 = oggettiIdMap.get(eo.getOggetto2());
			int peso = eo.getPeso();
			
			Graphs.addEdgeWithVertices(this.grafo, o1, o2, peso);
			
		}
		
		System.out.println("Grafo creato!");
		System.out.println("Vertici: " + grafo.vertexSet().size());
		System.out.println("Archi: " + grafo.edgeSet().size());
		
	}

	public boolean controllaValidita(int idNumero) {
		
		if(oggettiIdMap.containsKey(idNumero))
			return true;
		
		return false;
	}

	public int getComponenteConnessa(int idNumero) {
		
		List<ArtObject> result = new LinkedList<ArtObject>();
		
		ArtObject sorgente = oggettiIdMap.get(idNumero);
		
		DepthFirstIterator<ArtObject, DefaultWeightedEdge> it =
				new DepthFirstIterator<ArtObject, DefaultWeightedEdge>(this.grafo, sorgente);
		
		while(it.hasNext()) {
			result.add(it.next());
		}
		
		return result.size();
		
	}
	
	
}
