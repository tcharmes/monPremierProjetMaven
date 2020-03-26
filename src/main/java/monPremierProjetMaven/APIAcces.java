package monPremierProjetMaven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;

public class APIAcces {
	
	private static HttpURLConnection connection;
	public static StringBuffer responseContent;
	
	public static void initializeConnection() {
		
		BufferedReader reader;
		String line;
		responseContent = new StringBuffer();
		
		try {
			URL url = new URL("https://my-json-server.typicode.com/tcharmes/testRestAPI/joueurs");
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			int status = connection.getResponseCode();
			
			System.out.println("Le code réponse renvoyé par le serveur est : "+status+"\n");
			
			if (status>299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();	
			}
			else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		
	}
	
	public static void main(String[] args) {
		
		initializeConnection();
		
		existNameInAPI("Messi");
		
		getClubByName("Messi");
		getNumberByName("Messi");
		getPosteByName("Messi");
	}
	
	public static boolean existNameInAPI(String name) {
		JSONArray joueurs = new JSONArray(responseContent.toString());
		boolean exist = false;
		int i = 0;
		while((exist == false) && (i<joueurs.length())) {
				org.json.JSONObject joueur = joueurs.getJSONObject(i);
				String sonNom = joueur.getString("nom");
				if(sonNom.equals(name)) {
					System.out.println("Le joueur "+name+" existe dans l'API");
					exist = true;
				}
				i++;
			}
		if (exist == false) {
			System.out.println("Le joueur "+name+" saisi n'est pas dans la liste");
		}
		return exist;
	}
	
	/**
	 * renvoie le numero d'un joueur dont le nom est passé en paramètres
	 * renvoie -1 si le nom n'existe pas
	 * @param responseBody
	 * @param name
	 * @return
	 */
	public static int getNumberByName(String name) {
		JSONArray joueurs = new JSONArray(responseContent.toString());
		int number = 0;
		int i = 0;
		while((number == 0) && (i<joueurs.length())) {
				org.json.JSONObject joueur = joueurs.getJSONObject(i);
				int sonNumero = joueur.getInt("numero");
				String sonNom = joueur.getString("nom");
				if(sonNom.equals(name)) {
					System.out.println("Le joueur "+name+" parte le numéro "+sonNumero);
					number = sonNumero;
				}
				i++;
			}
		if (number == 0) {
			System.out.println("Le joueur "+name+" saisi n'est pas dans la liste");
		}
		return number;
	}
	
	public static String getClubByName(String name) {
		JSONArray joueurs = new JSONArray(responseContent.toString());
		String club = null;
		int i = 0;
		while((club == null) && (i<joueurs.length())) {
				org.json.JSONObject joueur = joueurs.getJSONObject(i);
				String sonClub = joueur.getString("club");
				String sonNom = joueur.getString("nom");
				if(sonNom.equals(name)) {
					System.out.println("Le joueur "+name+" joue pour le club "+sonClub);
					club = sonClub;
				}
				i++;
			}
		if (club == null) {
			System.out.println("Le joueur "+name+" saisi n'est pas dans la liste");
		}
		return club;
	}
	
	public static String getPosteByName(String name) {
		JSONArray joueurs = new JSONArray(responseContent.toString());
		String poste = null;
		int i = 0;
		while((poste == null) && (i<joueurs.length())) {
				org.json.JSONObject joueur = joueurs.getJSONObject(i);
				String sonPoste = joueur.getString("poste");
				String sonNom = joueur.getString("nom");
				if(sonNom.equals(name)) {
					System.out.println("Le joueur "+name+" joue "+sonPoste);
					poste = sonPoste;
				}
				i++;
			}
		if (poste == null) {
			System.out.println("Le joueur "+name+" saisi n'est pas dans la liste");
		}
		return poste;
	}
	
	public static void afficheAllNumbers(String responseBody) {
		JSONArray joueurs = new JSONArray(responseBody);
		for(int i=0; i<joueurs.length(); i++) {
			org.json.JSONObject joueur = joueurs.getJSONObject(i);
			int numero = joueur.getInt("numero");
			String nom = joueur.getString("nom");
			System.out.println("Le numéro du joueur "+nom+" est : "+numero);
		}
	}
	
	public static void afficheNameByNumber(int number) {
		JSONArray joueurs = new JSONArray(responseContent.toString());
		int count = 0;
		for(int i=0; i<joueurs.length(); i++) {
			org.json.JSONObject joueur = joueurs.getJSONObject(i);
			int numero = joueur.getInt("numero");
			String nom = joueur.getString("nom");
			if(numero == number) {
				System.out.println("Le joueur "+nom+" fait partie de la liste des numéros "+number);
				count ++;
			}
			/*else {
				System.out.println("Le joueur "+nom+" ne fait pas partie de la liste des numéros "+number
						+" car il porte le numéro "+numero);
			}*/
		}
		if(count == 0) {
			System.out.println("Aucun joueur ne porte le numéro : "+number);
		}
	}
	
	public static void afficheNameByClub(String club) {
		JSONArray joueurs = new JSONArray(responseContent.toString());
		int count = 0;
		for(int i=0; i<joueurs.length(); i++) {
			org.json.JSONObject joueur = joueurs.getJSONObject(i);
			String sonClub = joueur.getString("club");
			String nom = joueur.getString("nom");
			if(sonClub.equals(club)) {
				System.out.println("Le joueur "+nom+" fait partie du club "+club);
				count ++;
			}
		}
		if(count == 0) {
			System.out.println(club+" n'est pas un club");
		}
	}

}
