import java.util.*;
// hashmap utilise 	: put(K,V) (ajout) ; get(K) (accès valeur de la clé) ; replace(K,V) (remplace la valeur de la clé K)
// 					: remove(K) (supprime un élément de la hashmap)
public class map{
	
	// une hashmap contenant pour chaque véhicule sa position
	Map<vehicule,int[]> myMap = new HashMap<>();
	int[] limitesMap = {0,800,0,800}; // xmin, xmax, ymin, ymax
	
	
	
	public map (ArrayList<Vehicule> listOfVehicules) {
		// on initialise la map à partir d'une liste de véhicules auxquels une position est assignée
		for(vehicule MyVehicule : listOfVehicules) {
			if(!myMap.containsKey(MyVehicule)) {
				myMap.put(MyVehicule, MyVehicule.getPosition());
			}
		}
	}
	
	
	
	// Si le véhicule n'existe pas sur la map, on entre une nouvelle clé véhicule dans la map
	public void maPositionVehicule(vehicule Vehicule) {
		if(!myMap.containsKey(Vehicule)) {
			myMap.put(Vehicule, Vehicule.getPosition());
		}else	{
			myMap.replace(Vehicule, Vehicule.getPosition());
		}
	}
	
	// effacement du véhicule de la map s'il en sort
	// ajouter ici le flux de véhicules sortants ?
	public void TestSortieMap(Vehicule v) {
		if(v.getPosition()[1] - v.size[0]/2 < limitesMap[0]
		|| v.getPosition()[1] + v.size[0]/2 > limitesMap[1]
		|| v.getPosition()[2] - v.size[0]/2 < limitesMap[2]
		|| v.getPosition()[2] + v.size[0]/2 > limitesMap[3]) {
			myMap.remove(v);
		}
	}
}
