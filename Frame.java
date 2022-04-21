import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Frame extends JFrame implements ActionListener, MouseListener, KeyListener {

	// tableau d'objets route
	public Road[] routes = { new Road(800, 370, 0, 370), new Road(0, 430, 800, 430), new Road(370, 0, 370, 800),
			new Road(430, 800, 430, 0) };

	// l'arraylist contient les routes, les linkedlist les véhicules par route
	public ArrayList<LinkedList<Vehicule>> vehiculesParRoute = new ArrayList<>();
	public LinkedList<Vehicule> route1 = new LinkedList<>();
	public LinkedList<Vehicule> route2 = new LinkedList<>();
	public LinkedList<Vehicule> route3 = new LinkedList<>();
	public LinkedList<Vehicule> route4 = new LinkedList<>();

	// arraylist contenant tous les véhicules
	public ArrayList<Vehicule> vehicules = new ArrayList<>(); // liste des vehicules PRESENTS
	// arraylist contenant tous les obstacles
	public ArrayList<obstacle> allObstacles = new ArrayList<>(); // liste des obstacles PRESENTS

	boolean appuyé = false; 	// état de l'action clic souris
	boolean select = false; 	// variable qqchose selectionné
	String quoi; 				// type d'obstacle à placer

	private DisplayPanel p1;
	private JButton start;			// démarrage
	private JButton pause;			// pause
	private JButton restart;		// réinitialisation
	private JButton trash;			// delete obstacles
	private JSlider traffic;		// nombres de véhicules sur l'écran
	private JSlider rapidite;		// vitesse moyenne des véhicules
	private JSlider aggressivite;	// agressivité des véhicules
	private JLabel labelTime;		// affichage du temps
	boolean démarré;
	private javax.swing.Timer timer;

	// bouton feu rouge
	private JButton boutonFeu;
	private JTextField valeurFeu;
	private int valfeu;
	private double time;

	// bouton limitation
	private JButton boutonLimite;
	private JTextField valeurLimite;
	private int vallim;

	// bouton barriere
	private JButton boutonBarriere;

	// bouton panneau stop
	private JButton panneauStop;

	// timer
	private JButton unite;
	boolean unit;
	private long startTime;
	private long elapsed;
	private boolean running;
	private long pauseTime;


	public Frame() {
		// ajout des routes dans l'arraylist
		vehiculesParRoute.add(route1);
		vehiculesParRoute.add(route2);
		vehiculesParRoute.add(route3);
		vehiculesParRoute.add(route4);

		setTitle("K-Roof");
		setLayout(null);
		setSize(1115, 840);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Panneau affichage
		p1 = new DisplayPanel(this);
		p1.setLayout(null);
		p1.setTime(0);
		p1.setBounds(0, 0, 800, 800);
		p1.addMouseListener(this);
		add(p1);

		timer = new javax.swing.Timer(10, this);
		timer.addActionListener(this);
		timer.start();

		// Panneau interface
		JPanel p2 = new JPanel();
		p2.setLayout(null);
		p2.setBackground(new Color(230, 230, 230));
		p2.setBounds(800, 0, 300, 800);
		add(p2);

		// bouton start
		start = new JButton(new ImageIcon("Images/start.png"));
		start.setBounds(10, 680, 135, 50);
		start.setBackground(new Color(0, 170, 0));
		start.addActionListener(this);
		p2.add(start);
		start.setVisible(false);
		start.setVisible(true);

		// bouton pause
		pause = new JButton(new ImageIcon("Images/pause.png"));
		pause.setBounds(155, 680, 135, 50);
		pause.setBackground(new Color(250, 20, 20));
		pause.addActionListener(this);
		p2.add(pause);
		pause.setEnabled(false); // desactive le bouton au debut
		pause.setVisible(false);
		pause.setVisible(true);

		// bouton restart
		restart = new JButton(new ImageIcon("Images/stop.png"));
		restart.setBounds(155, 740, 135, 50);
		restart.setBackground(new Color(20, 20, 20));
		restart.addActionListener(this);
		p2.add(restart);
		restart.setEnabled(false); // desactive le bouton au debut
		restart.setVisible(false);
		restart.setVisible(true);

		// curseur trafic
		traffic = new JSlider(0, 100, 20);
		traffic.setBounds(10, 10, 280, 50);
		traffic.setMinorTickSpacing(5);
		traffic.setMajorTickSpacing(20);
		traffic.setPaintTicks(true);
		traffic.setPaintLabels(true);
		p2.add(traffic);
		traffic.setVisible(false);
		traffic.setVisible(true);

		// curseur conduite rapide
		rapidite = new JSlider(0, 100, 20);
		rapidite.setBounds(10, 70, 280, 50);
		rapidite.setMinorTickSpacing(5);
		rapidite.setMajorTickSpacing(20);
		rapidite.setPaintTicks(true);
		rapidite.setPaintLabels(true);
		p2.add(rapidite);
		rapidite.setVisible(false);
		rapidite.setVisible(true);

		// curseur aggressivite
		aggressivite = new JSlider(0, 100, 20);
		aggressivite.setBounds(10, 130, 280, 50);
		aggressivite.setMinorTickSpacing(5);
		aggressivite.setMajorTickSpacing(20);
		aggressivite.setPaintTicks(true);
		aggressivite.setPaintLabels(true);
		p2.add(aggressivite);
		aggressivite.setVisible(false);
		aggressivite.setVisible(true);

		// timer
		labelTime = new JLabel();
		labelTime.setBounds(36, 585, 400, 30);
		labelTime.setBackground(new Color(230, 230, 230));
		labelTime.setFont(new Font("Arial", Font.PLAIN, 20));
		labelTime.setText("Temps écoulé : " + p1.getTime() + " ms");
		p2.add(labelTime);
		labelTime.setVisible(false);
		labelTime.setVisible(true);

		// unité
		unite = new JButton(new ImageIcon("Images/seconde.png"));
		unite.setBounds(10, 590, 20, 20);
		unite.setBackground(new Color(0, 0, 255));
		unite.addActionListener(this);
		p2.add(unite);
		unite.setEnabled(true);
		unite.setVisible(false);
		unite.setVisible(true);
		unit = true;

		// bouton trash
		trash = new JButton(new ImageIcon("Images/corbeille.png"));
		trash.setBounds(155, 620, 135, 50);
		trash.setBackground(new Color(191, 191, 191));
		trash.addActionListener(this);
		p2.add(trash);
		trash.setEnabled(true);
		trash.setVisible(false);
		trash.setVisible(true);

		// bouton feu rouge
		boutonFeu = new JButton(new ImageIcon("Images/feurouge.png"));
		boutonFeu.setBounds(20, 295, 60, 135);
		boutonFeu.addMouseListener(this);
		p2.add(boutonFeu);
		boutonFeu.setVisible(false);
		boutonFeu.setVisible(true);

		// valeur feu rouge
		valeurFeu = new JTextField("Intervalle");
		valeurFeu.setBounds(20, 440, 60, 30);
		p2.add(valeurFeu);
		valeurFeu.setVisible(false);
		valeurFeu.setVisible(true);

		// bouton limites
		boutonLimite = new JButton(new ImageIcon("Images/limitation.png"));
		boutonLimite.setBounds(100, 295, 70, 70);
		boutonLimite.addMouseListener(this);
		p2.add(boutonLimite);
		boutonLimite.setVisible(false);
		boutonLimite.setVisible(true);

		// valeur limites
		valeurLimite = new JTextField("Limite");
		valeurLimite.setBounds(100, 440, 70, 30);
		p2.add(valeurLimite);
		valeurLimite.setVisible(false);
		valeurLimite.setVisible(true);

		// bouton barrière
		boutonBarriere = new JButton(new ImageIcon("Images/barriere.png"));
		boutonBarriere.setBounds(190, 295, 62, 40);
		boutonBarriere.addMouseListener(this);
		p2.add(boutonBarriere);
		boutonBarriere.setVisible(false);
		boutonBarriere.setVisible(true);

		// panneau stop
		panneauStop = new JButton(new ImageIcon("Images/Panneau stop.png"));
		panneauStop.setBounds(190, 350, 61, 61);
		panneauStop.addMouseListener(this);
		panneauStop.setVisible(true);
		p2.add(panneauStop);
		panneauStop.setVisible(false);
		panneauStop.setVisible(true);
		démarré = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer && démarré) {
			if (unit) {
				labelTime.setText("Temps écoulé : " + (this.getElapsed() / 1000) + " s");
			} else {
				labelTime.setText("Temps écoulé : " + (this.getElapsed()) + " ms");
			}
		}
		if (e.getSource() == unite) {
			if (unit) {
				unite.setIcon(new ImageIcon("Images/milliseconde.png"));
				unit = !unit;
			} else {
				unite.setIcon(new ImageIcon("Images/seconde.png"));
				unit = !unit;
			}
		}
		if (e.getSource() == start) {
			startTime = System.currentTimeMillis();
			running = true;
			démarré = true;
			start.setEnabled(false);
			pause.setEnabled(true);
			restart.setEnabled(true);
			// init.setEnabled(false);
			p1.getTimer().start(); // commence le chrono dans p1 (DisplayPanel) lorsqu'on appuie sur le bouton

		}
		if (e.getSource() == pause) {
			// met en pause la simulation
			pauseTime = System.currentTimeMillis();
			elapsed = elapsed + System.currentTimeMillis() - startTime;
			running = false;
			p1.getTimer().stop();
			pause.setEnabled(false);
			start.setEnabled(true);
			// init.setEnabled(true);
		}
		if (e.getSource() == restart) {
			elapsed = 0;
			running = false;
			pause.setEnabled(false);
			restart.setEnabled(false);
			// init.setEnabled(true);
			vehicules.clear(); // A changer
			p1.repaint();
			p1.getTimer().stop();
			p1.setTime(0);
			valeurFeu.setText("Intervalle");
			valeurLimite.setText("Limite");
			start.setEnabled(true);
		}

		if (e.getSource() == trash) {
			ArrayList<obstacle> supprimer = new ArrayList<>();
			for (obstacle o : allObstacles) {
				supprimer.add(o);
			}
			allObstacles.removeAll(supprimer);
			for(Vehicule v : vehicules) {
				v.setNextObstacle(null);
			}
			p1.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (allObstacles.contains(e.getSource())) {
			if (SwingUtilities.isRightMouseButton(e)) {
				// Supprimer l'obstacle
				allObstacles.remove(e.getSource());
				p1.repaint();
			}
		} else if (SwingUtilities.isLeftMouseButton(e)) {
			if (e.getSource() == boutonFeu) {
				appuyé = true;
				quoi = "feurouge"; // coiffeur haha
			} else if (e.getSource() == boutonLimite) {
				appuyé = true;
				quoi = "limitation";
			} else if (e.getSource() == boutonBarriere) {
				appuyé = true;
				quoi = "barriere";
			} else if (e.getSource() == panneauStop) {
				appuyé = true;
				quoi = "stop";
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (appuyé) {
			ajouterElement(quoi);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == boutonFeu && SwingUtilities.isLeftMouseButton(e) && !select) { // feu rouge
			select = true;
			quoi = "feurouge";
		} else if (e.getSource() == boutonLimite && SwingUtilities.isLeftMouseButton(e) && !select) { // limitation
			select = true;
			quoi = "limitation";
		} else if (e.getSource() == boutonBarriere && SwingUtilities.isLeftMouseButton(e) && !select) { // barriere
			select = true;
			quoi = "barriere";
		} else if (e.getSource() == panneauStop && SwingUtilities.isLeftMouseButton(e) && !select) { // panneau stop
			select = true;
			quoi = "stop";
		} else if (select && quoi.equals("feurouge")) {
			if (e.getSource() == p1 && SwingUtilities.isLeftMouseButton(e)) {
				ajouterElement("feurouge");
				select = false;
			} else {
				select = false;
			}
		} else if (select && quoi.equals("limitation")) {
			if (e.getSource() == p1 && SwingUtilities.isLeftMouseButton(e)) {
				ajouterElement("limitation");
				select = false;
			} else {
				select = false;
			}
		} else if (select && quoi.equals("barriere")) {
			if (e.getSource() == p1 && SwingUtilities.isLeftMouseButton(e)) {
				ajouterElement("barriere");
				select = false;
			} else {
				select = false;
			}
		} else if (select && quoi.equals("stop")) {
			if (e.getSource() == p1 && SwingUtilities.isLeftMouseButton(e)) {
				ajouterElement("stop");
				select = false;
			} else {
				select = false;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	public void ajouterElement(String valeur) {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX() - this.getLocationOnScreen().x;
		int y = (int) b.getY() - this.getLocationOnScreen().y - 38;
		if ((x <= p1.getWidth()) && (y <= p1.getHeight()) && (x >= 0) && (y >= 0)) { // Check si relaché dans la map
			int pos = getRoute(x, y, routes);
			if (pos != -1) { // check si le point choisi est dans une route et pas un carrefour
				// ajouter obstacle
				if (valeur.equals("feurouge")) { // cas feu rouge
					if (isInt(valeurFeu.getText())) {
						valfeu = Integer.valueOf(valeurFeu.getText());
					} else {
						valfeu = 0;
					}
					feurouge feu = new feurouge(x, y, routes[pos], valfeu);
					allObstacles.add(feu);
					p1.repaint();
				} else if (valeur.equals("limitation")) { // cas limitation
					if (isInt(valeurLimite.getText())) {
						vallim = Integer.valueOf(valeurLimite.getText());
					} else {
						vallim = 0;
					}
					limitation limite = new limitation(x, y, routes[pos], vallim);
					allObstacles.add(limite);
					p1.repaint();
				} else if (valeur.equals("barriere")) { // cas barriere
					barriere bar = new barriere(x, y, routes[pos]);
					allObstacles.add(bar);
					p1.repaint();
				} else if (valeur.equals("stop")) { // panneau stop
					stop pstop = new stop(x, y, routes[pos]);
					allObstacles.add(pstop);
					p1.repaint();
				}
			}
		}
	}

	// retourne true, on sait pas pourquoi mais c'est là
	public boolean checkPos(int x, int y, ArrayList<Road> routes) {
		boolean verif = true;
		return verif;
	}


	// détermination du nombre de véhicules sur la route
	public int howManyVehicles(Road r) {
		int i = 0;
		for (Vehicule v : vehicules) {
			if (v.getRoad().equals(r))
				i++;
		}
		return i;
	}

	// évaluation de la possibilité de création de nouveau véhicule
	public boolean newVehicle(Road r) {
		for (Vehicule v : vehicules) {
			if (v.getRoad().equals(r) && v.getSafePosition() < getTraffic())
				return false;
		}
		return true;
	}

	// détection d'accident
	public boolean isAnAccident() {
		for (Vehicule v1 : vehicules) {
			for (Vehicule v2 : vehicules) {
				if (v1.getRectangle().intersects(v2.getRectangle()) && !v1.equals(v2))
					return true;
			}
		}
		return false;
	}

	// méthode de classement des obstacles sur une route (retourne une arraylist d'obstacles DANS l'ORDRE et uniquement constituée des obstacles de la route
	public ArrayList<obstacle> sortObstaclesRoute(int RoadOrientation) {
		ArrayList<obstacle> thisRouteObstacles = new ArrayList<>();
		for (obstacle Obs : allObstacles) {
			if (Obs.getRoad().getOrientation() == RoadOrientation) {
				thisRouteObstacles.add(Obs);
			}
		}
		Collections.sort(thisRouteObstacles);
		return thisRouteObstacles;
	}


	// détermine quel est le prochain obstacles quand une voiture le dépasse
	public obstacle chercheNextObstacle(Road route, double posVehicule, int compteur) {
		ArrayList<obstacle> listOfObstacles = sortObstaclesRoute(route.getOrientation());
		int i = 0;
		while (listOfObstacles.get(i).getPosition() < posVehicule && listOfObstacles.size() < i) {
			i++;
		}
		if (i == 0) {
			return null;
		} else {
			return listOfObstacles.get(i);
		}
	}

	// détermine si il existe un obstacle sur la route devant le véhicule après en avoir dépassé un
	public boolean isAnObstacle(Road route, int compteur) {
		if (sortObstaclesRoute(route.getOrientation()).size() - compteur == 0) {
			return false;
		} else {
			return true;
		}
	}

	// met à jour le prochain obstacle sur la voiture (attribut nextObstacle)
	public void majNextObstacle() {
		for (Vehicule v : vehicules) {
			if (v.getNextObstacle() != null) {
				if (v.getNextObstacle().getPosition() < v.getFront()) {
					v.setNextObstacle(null);
					v.setObstaclesCompteur(v.getObstaclesCompteur() + 1);
					if (isAnObstacle(v.getRoad(), v.getObstaclesCompteur())) {
						v.setNextObstacle(chercheNextObstacle(v.getRoad(), v.getFront(), v.getObstaclesCompteur()));
					}
				}
			}
		}
	}

	// définit les priorités (obstacle, voiture devant...)
	/* NOTE DEF PRIO
	 * 0 : default
	 * 1 : voiture
	 * 2 : feu rouge
	 * 3 : barrière
	 * 4 : limitation de vitesse
	 * 5 : stop
	 */
	public void definePrio() {
		for (LinkedList<Vehicule> maRoute : vehiculesParRoute) {
			for (int i = 0; i < maRoute.size(); i++) {
				maRoute.get(i).setPrio(0);
				// si c'est la première voiture (pas de voiture suivante) et qu'il y a un obstacle en face
				if(maRoute.get(i).getNextVehicule()==null && maRoute.get(i).getNextObstacle()!=null) {
					// check quel obstacle est en face, et met la prio entre 2 et 5 selon l'obstacle
					if (maRoute.get(i).getNextObstacle() instanceof feurouge) {
						maRoute.get(i).setPrio(2);
					} else if (maRoute.get(i).getNextObstacle() instanceof barriere) {
						maRoute.get(i).setPrio(3);
					} else if (maRoute.get(i).getNextObstacle() instanceof limitation) {
						maRoute.get(i).setPrio(4);
					} else if (maRoute.get(i).getNextObstacle() instanceof stop) {
						maRoute.get(i).setPrio(5);
					}
				}else if(maRoute.get(i).getNextVehicule()!=null && maRoute.get(i).getNextObstacle()!=null){
					// vérifie qui est le plus proche, de l'obstacle ou de la voiture en face
					if (maRoute.get(i + 1).getBack() < maRoute.get(i).getNextObstacle().getPosition()) {
						// la voiture est plus proche, le véhicule se met en prio 1
						maRoute.get(i).setPrio(1);
					} else {
						// l'obstacle est plus proche, la voiture se met en prio 2 à 5
						if (maRoute.get(i).getNextObstacle() instanceof feurouge) {
							maRoute.get(i).setPrio(2);
						} else if (maRoute.get(i).getNextObstacle() instanceof barriere) {
							maRoute.get(i).setPrio(3);
						} else if (maRoute.get(i).getNextObstacle() instanceof limitation) {
							maRoute.get(i).setPrio(4);
						} else if (maRoute.get(i).getNextObstacle() instanceof stop) {
							maRoute.get(i).setPrio(5);
						}
					}
				}
			}
		}
	}

	// définit les actions des véhicules à l'arrivée sur un obstacle
	/* NOTE DEF PRIO
	 * 0 : default
	 * 1 : voiture
	 * 2 : feu rouge
	 * 3 : barrière
	 * 4 : limitation de vitesse
	 * 5 : stop
	 */
	public void interaction() {
		definePrio();
		for (Vehicule v : vehicules) {
			switch (v.getPrio()) {
			case 1:
				v.deccelTo(v.getNextVehicule().getSpeed());
				break;
			case 2:
				if (v.getRoad() == v.getNextObstacle().getRoad() && v.getPosition() < v.getNextObstacle().getPosition()) {
					if (((feurouge) v.getNextObstacle()).getEtat() == 1 || ((feurouge) v.getNextObstacle()).getEtat() == 2) {
						v.stopAt(v.getNextObstacle().getPosition() - v.getSize()[0]);
					} else {
						v.accel();
					}
				}
				break;
			case 3:
				if (v.getRoad() == v.getNextObstacle().getRoad()) {
					if (v.getNextObstacle().getPosition() > v.getNextObstacle().getRoad().getCroisement()[0]) {
						v.stopAt(v.getNextObstacle().getRoad().getCroisement()[0] - v.getSize()[0] * 2.0);
					} else {
						v.stopAt(v.getNextObstacle().getPosition()-v.getSize()[1]);
					}
				}
				break;
			case 4:
				if (v.getRoad() == v.getNextObstacle().getRoad() && v.getSpeed() > ((limitation) v.getNextObstacle()).getLimite()) {
					v.deccelTo(((limitation) v.getNextObstacle()).getLimite());
				}
				break;
			case 5:
				if (v.getRoad() == v.getNextObstacle().getRoad()) {
					v.stopAt(v.getNextObstacle().getPosition() - v.getSize()[0] / 2);
				}
				break;
			default:
				v.accel();
				break;
			}
		}

	}

	public int getRoute(int x, int y, Road[] routes) {
		int rep = -1;
		int liste = 0;
		for (int i = 0; i < routes.length; i++) {
			int x1 = routes[i].getStartingPoint()[0];
			int x2 = routes[i].getEndingPoint()[0];
			int y1 = routes[i].getStartingPoint()[1];
			int y2 = routes[i].getEndingPoint()[1];
			if ((x >= Math.min(x1, x2) - 29) && (x <= Math.max(x1, x2) + 29) && y >= (Math.min(y1, y2) - 29)
					&& (y <= Math.max(y1, y2) + 29)) {
				rep = i;
				liste++;
			}
		}
		if (liste > 1) {
			rep = -1;
		}
		return rep;
	}

	public static boolean isInt(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int d = Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public long getElapsed() {
		if (running) {
			return elapsed + System.currentTimeMillis() - startTime;
		} else {
			return elapsed;
		}
	}

	public int getTraffic() {
		return traffic.getValue();
	}

	public int getRapidite() {
		return rapidite.getValue();
	}

	public int getAggressivite() {
		return aggressivite.getValue();
	}

}
