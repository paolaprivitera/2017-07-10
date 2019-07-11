package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.EsposizioneOggetti;

public class ArtsmiaDAO {

	public List<ArtObject> listObjects() {
		
		String sql = "SELECT * from objects";
		List<ArtObject> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
				result.add(artObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<EsposizioneOggetti> getEsposizioneOggetti(){
		
		String sql = "SELECT o1.object_id AS ob1, o2.object_id AS ob2, COUNT(e1.exhibition_id) AS cnt " + 
				"FROM objects AS o1, objects AS o2, exhibition_objects AS e1, exhibition_objects AS e2 " + 
				"WHERE e1.exhibition_id = e2.exhibition_id AND o1.object_id = e1.object_id " + 
				"AND o2.object_id = e2.object_id AND o1.object_id < o2.object_id " + 
				"GROUP BY o1.object_id, o2.object_id";
		
		List<EsposizioneOggetti> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				EsposizioneOggetti espOgg = new EsposizioneOggetti(res.getInt("ob1"), res.getInt("ob2"), res.getInt("cnt")); 
				
				result.add(espOgg);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
