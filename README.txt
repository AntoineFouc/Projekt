===================================== README K-ROOF PROJECT =====================================
Fonctionnalités :
____________________________________
Basiques :
- 	bouton "start" (vert) 		: lancer la simulation
- 	bouton "pause" (rouge) 		: mettre la simulation en pause
- 	bouton "stop" (noir) 		: remettre la simulation à zéro
____________________________________
Obstacles :
° fonctionnement icônes : 
	clicker sur l'icône et appuyer sur la zone de la route où l'on veut déposer un obstacle
° fonctionnement cadres de texte :
	effacer le texte présent dans le cadre et entre la valeur numérique voulue
° fonctionnement boutons de selection d'état
	clicker sur un bouton de selection d'état (voir sous le texte "intervalle" sous le feu rouge) 
	permet de sélectionner l'état du feu rouge voulu

- 	feu rouge :		le feu rouge permet de signaler un arrêt nécessaire périodique aux voitures
				si le feu passe au orange, les voitures doivent s'y arrêter, puis rester à l'arrêt au 
				rouge, et ne peut repartir qu'au retour du feu vert.
				
				on peut sélectionner son état initial, et la période (intervalle) de changement de 
				couleur (en "ticks", donc pas en secondes) (intervalle fonctionnel = env.100) 
-	limitation :	la limitation va changer la vitesse maximale des véhicules qui la passent. La vitesse,
				en "km/h" (on considère que la vitesse moyenne des véhicules est autours de 80km/h)
-	barrière :		une barrière bloque totalement la route sur laquelle elle est posée
-	stop :		un panneau stop va faire s'arrêter les véhicules pour un certain temps, (<3sec), selon
				le niveau d'agressivité du véhicule
____________________________________
Curseurs :
- curseur quantité de trafic :	le curseur quantité de trafic gère la quantité de véhicules générables sur
						l'écran
- curseur agressivité :			le curseur agressivité gère l'agressivité des véhicules, c'est-à-dire :
						° le temps d'arrêt aux panneaux stop
						° l'accéleration (un véhicule agressif accélère plus)
						° les distances de sécurité (un véhicule agressif sera plus proche de 
						  son suivant)

____________________________________
Affichage :
- partie gauche :		affichage de la simulation (routes, véhicules, obstacles)
- partie droite :		affichage des objets/variations possibles sur la simulation
- flux :			flux de véhicules sortants