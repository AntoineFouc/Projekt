Liste des trucs à faire : 

A changer :
- definition de la vitesse et acceleration (classe véhicule, ligne 26)

Interaction (classe véhicule, ligne 54) : 
- coder les croisements entre deux véhicules avec priorité à droite (Simon)
- anticiper le freinage en fonction de la vitesse du véhicule et de la distance de sécurité du véhicule devant

- puis obstacles

A compléter : 
- Gérer les accidents (classe Frame, ligne 109)

- Initialisations des véhicules (classe Frame) & retour

A faire ensuite : 
- connaitre le flux et l'afficher 
- créer d'autres véhicules (motos)
- gérer les obstacles voir en créer des nouveaux (feux, stop, panneaux de limitation de vitesse) 
- créer un autre plan de route ? (optionnel)
- un passage piéton avec des mamies qui traversent, le chat qui reste bloqué dans l'arbre et les pompiers qui débarquent, 
les aliens qui ont plus de carburant et puis merde c'est trop cher 2 euros donc et vas y que je laisse ma soucoupe sur l'autoroute, 
les gendarmes de saint tropez se ramènent mais jawad dit que c'était pour rendre service du coup lénine se ramène et bref j'ai raté mon avion.

BRANCHE TEMP :
fait :
- chaque véhicule sait quel est le prochain obstacle (mais ne sait pas quel obstacle c'est)
- chaque véhicule met à jour le prochain obstacle quand il le passe, et connait la voiture suivante (nouveaux attributs nextVehicule et nextObstacle)
- chaque route a sa linkedlist de véhicules qui lui permet d'avoir les véhicules dans l'ordre
- nouvelle méthode pour mettre en ordre les obstacles sur une route donnée

Branche Interaction pas ouf :
fait :
- des get un peu partout
- des changement dans les méthodes de véhicules ( méthode accel, desaccel, stop at à préciser encore)
- l'ajout d'un tableau croisement (pour avoir la position des croisements propre a une route croisement[0]=370 et croisement[1]=430) 
- un début de méthode interaction (le concept est de stopper tt les véhicules à une position précise, la position de l'obstacle ou du croisement avant, 
chaque véhicule ayant une position de sécurité dépendant de la vitesse pas besoin de faire plus si il n'ya qu'un seul obstacle par voie, si plusieurs faire une double boucle for each, le mais de cette technique c'est qu'on risque d'être limité dans le cas des priorittés de ce que je comprends)
