import java.util.*;
// hashmap utilise 	: put(K,V) (ajout) ; get(K) (accès valeur de la clé) ; replace(K,V) (remplace la valeur de la clé K)
// 					: remove(K) (supprime un élément de la hashmap)
public class map {
	
	// une hashmap contenant pour chaque véhicule sa position
	Map<vehicule,int[]> myMap = new HashMap<>();
	int[] limitesMap = {0,10,0,10}; // xmin, xmax, ymin, ymax
	
	
	
	public map (ArrayList<vehicule> listOfVehicules) {
		// on initialise la map à partir d'une liste de véhicules auxquels une position est assignée
		for(vehicule MyVehicule : listOfVehicules) {
			if(!myMap.containsKey(MyVehicule)) {
				myMap.put(MyVehicule, MyVehicule.getPosition());
			}
		}
	}
	
	
	
	// Si le véhicule n'existe pas sur la map, on entre une nouvelle clé véhicule dans la map
	public void majPositionVehicule(vehicule Vehicule) {
		if(!myMap.containsKey(Vehicule)) {
			myMap.put(Vehicule, Vehicule.getPosition());
		}else	{
			myMap.replace(Vehicule, Vehicule.getPosition());
		}
	}
	
	// effacement du véhicule de la map s'il en sort
	// ajouter ici le flux de véhicules sortants ?
	public void TestSortieMap(vehicule Vehicule, int[] valLimites) {
		if(Vehicule.getPosition()[1] < limitesMap[0] 
				|| Vehicule.getPosition()[1] > limitesMap[1] 
				|| Vehicule.getPosition()[2] < limitesMap[2]
				|| Vehicule.getPosition()[2] > limitesMap[3]) {
			myMap.remove(Vehicule);
		}
	}
}
