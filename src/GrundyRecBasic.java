import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This is Grundy's Game with AI for the Machine.
 * This program contains only the methods required to test playToWin.
 * This is a basic version with no improvements.
 *
 * @author J-F. Kamp
 */

class GrundyRecBasic {

    /**
     * Main method of the program
     */
    void principal() {
        testPlayToWin();
		testFirstSplit();
		testNext();
    }
	
    /**
     * Plays the winning decomposition if one exists.
     * 
     * @param jeu 	game board
     * @return 		true if there is a winning decomposition, false otherwise
     */
    boolean playToWin(ArrayList<Integer> jeu) {
	
        boolean gagnant = false;
		
        if (jeu == null) {
            System.err.println("playToWin(): le paramètre jeu est null");
        } else {
            ArrayList<Integer> essai = new ArrayList<Integer>();
			
			// Une toute première décomposition est effectuée à partir de jeu.
			// Cette première décomposition du jeu est enregistrée dans essai.
			// ligne est le numéro de la case du tableau ArrayList (qui commence à zéro) qui
			// mémorise le tas (nbre d'allumettes) qui a été décomposé
            int ligne = firstSplit(jeu, essai);
			
			// mise en oeuvre de la règle numéro2
			// Une situation (ou position) est dite gagnante pour la machine, s’il existe AU MOINS UNE décomposition
			// (c-à-d UNE action qui consiste à décomposer un tas en 2 tas inégaux) perdante pour l’adversaire. C'est
			// évidemment cette décomposition perdante qui sera choisie par la machine.
            while (ligne != -1 && !gagnant) {
				// isConfigALosingOne est récursif
                if (isConfigALosingOne(essai)) {
					// isConfigALosingOne (pour l'adversaire) à true ===> Bingo essai est la décomposition choisie par la machine qui est alors
					// certaine de gagner !!
                    jeu.clear();
                    gagnant = true;
					// essai est recopié dans jeu car essai est la nouvelle situation de jeu après que la machine ait joué (gagnant)
                    for (int i = 0; i < essai.size(); i++) {
                        jeu.add(essai.get(i));
                    }
                } else {
					// isConfigALosingOne à false ===> la machine essaye une autre décomposition en faisant appel à "suivant".
					// Si, après exécution de suivant, ligne est à (-1) alors il n'y a plus de décomposition possible à partir de jeu (et on sort du while).
					// En d'autres mots : la machine n'a PAS trouvé à partir de jeu UNE décomposition gagnante.
                    ligne = next(jeu, essai, ligne);
                }
            }
        }
		
        return gagnant;
    }
	
	/**
     * RECURSIVE method that determines whether the current game configuration (or a test game configuration)
	 * is a losing one.
	 * This method is used by the machine to determine whether the adversary is guaranteed to lose (100% certainty).
     * 
     * @param jeu 	current game board (the state of the game at a given point during the game)
     * @return 		true if the game configuration is a losing one, false otherwise
     */
    boolean isConfigALosingOne(ArrayList<Integer> jeu) {
	
        boolean ret = true; // par défaut la configuration est perdante
		
        if (jeu == null) {
            System.err.println("isConfigALosingOne(): le paramètre jeu est null");
        }
		
		else {
			// si il n'y a plus que des tas de 1 ou 2 allumettes dans le plateau de jeu
			// alors la situation est forcément perdante (ret=true) = FIN de la récursivité
            if ( !isPossible(jeu) ) {
                ret = true;
            }
			
			else {
				// création d'un jeu d'essais qui va examiner toutes les décompositions
				// possibles à partir de jeu
                ArrayList<Integer> essai = new ArrayList<Integer>(); // size = 0 !
				
				// toute première décomposition : enlever 1 allumette au premier tas qui possède
				// au moins 3 allumettes, ligne = -1 signifie qu'il n'y a plus de tas d'au moins 3 allumettes
                int ligne = firstSplit(jeu, essai);
				
                while ( (ligne != -1) && ret) {
				
					// mise en oeuvre de la règle numéro1
					// Une situation (ou position) est dite perdante si et seulement si TOUTES ses décompositions possibles
					// (c-à-d TOUTES les actions qui consistent à décomposer un tas en 2 tas inégaux) sont TOUTES gagnantes 
					// (pour l’adversaire).
					// L'appel à "isConfigALosingOne" est RECURSIF.
					
					// Si "isConfigALosingOne(essai)" est true c'est équivalent à "estGagnante" est false, la décomposition
					// essai n'est donc pas gagnante, on sort du while et on renvoie false.
                    if (isConfigALosingOne(essai) == true) {
					
						// Si UNE SEULE décomposition (à partir du jeu) est perdante (pour l'adversaire) alors le jeu n'EST PAS perdant.
						// On renverra donc false : la situation (jeu) n'est PAS perdante.
                        ret = false;
						
                    } else {
						// génère la configuration d'essai suivante (c'est-à-dire UNE décomposition possible)
						// à partir du jeu, si ligne = -1 il n'y a plus de décomposition possible
                        ligne = next(jeu, essai, ligne);
                    }
                }
            }
        }
		
        return ret;
    }
	
	/**
     * Indicates whether the configuration is a winning one.
	 * Method that simply calls "isConfigALosingOne".
     * 
     * @param jeu 	game board
     * @return 		true if the configuration is a winning one, false otherwise
     */
    boolean configWins(ArrayList<Integer> jeu) {
        boolean ret = false;
		
        if (jeu == null) {
            System.err.println("estGagnante(): le paramètre jeu est null");
        } else {
            ret = !isConfigALosingOne(jeu);
        }
		
        return ret;
    }

    /**
     * Tests of the method playToWin()
     */
    void testPlayToWin() {
	
        System.out.println();
        System.out.println("*** testPlayToWin() ***");

        System.out.println("Test des cas normaux");
        ArrayList<Integer> jeu1 = new ArrayList<Integer>();
        jeu1.add(6);
        ArrayList<Integer> resJeu1 = new ArrayList<Integer>();
        resJeu1.add(4);
        resJeu1.add(2);
		
        testCasPlayToWin(jeu1, resJeu1, true);
        
    }

    /**
     * Test one case of the method playToWin()
	 *
	 * @param jeu 		game board
	 * @param resJeu 	the game board after playing the winning move.
	 * @param res 		the expected result of playToWin
     */
    void testCasPlayToWin(ArrayList<Integer> jeu, ArrayList<Integer> resJeu, boolean res) {
	
        // Arrange
        System.out.print("playToWin (" + jeu.toString() + ") : ");

        // Act
        boolean resExec = playToWin(jeu);

        // Assert
        System.out.print(jeu.toString() + " " + resExec + " : ");
		boolean egaliteJeux = jeu.equals(resJeu);
        if (  egaliteJeux && (res == resExec) ) {
            System.out.println("OK\n");
        } else {
            System.err.println("ERREUR\n");
        }
    }	

    /**
     * Splits the matches at index into two stacks (1 index = 1 stack).
	 * The new stack must be placed at the end of the array.
	 * The stack being split is reduced by the number of matches removed.
     * 
     * @param jeu   array of matches
     * @param index stack whose matches need to be split
     * @param nb    number of matches REMOVED from the stack (= splitting)
     */
    void removing ( ArrayList<Integer> jeu, int index, int nb ) {
	
		// traitement des erreurs
        if (jeu == null) {
            System.err.println("removing() : le paramètre jeu est null");
			
        } else if (index >= jeu.size()) {
            System.err.println("removing() : le numéro de index est trop grand");
			
        } else if (nb >= jeu.get(index)) {
            System.err.println("removing() : le nb d'allumettes à retirer est trop grand");
			
        } else if (nb <= 0) {
            System.err.println("removing() : le nb d'allumettes à retirer est trop petit");
			
        } else if (2 * nb == jeu.get(index)) {
            System.err.println("removing() : le nb d'allumettes à retirer est la moitié");
			
        } else {
			// nouveau tas ajouté au jeu (nécessairement en fin de tableau)
			// ce nouveau tas contient le nbre d'allumettes retirées (nb) du tas à séparer			
            jeu.add(nb);
			// le tas restant possède "nb" allumettes en moins
            jeu.set ( index, (jeu.get(index) - nb) );
        }
    }

    /**
     * Checks whether one of the stacks can be split.
     * 
     * @param jeu 	game board
     * @return 		true if there is at least one stack containing 3 or more matches, false otherwise
     */
    boolean isPossible(ArrayList<Integer> jeu) {
	
        boolean ret = false;
		
        if (jeu == null) {
            System.err.println("isPossible(): le paramètre jeu est null");
        } else {
		
            int i = 0;
			
            while (i < jeu.size() && !ret) {
                if (jeu.get(i) > 2) {
                    ret = true;
                }
                i = i + 1;
            }
        }
		
        return ret;
    }

    /**
     * Creates a first initial configuration from the game board.
     * 
     * @param jeu    	game board
     * @param jeuEssai 	new game configuration
     * @return 			the index of the stack split in two, or (-1) if there is no stack containing at least 3 matches
     */
    int firstSplit(ArrayList<Integer> jeu, ArrayList<Integer> jeuEssai) {
	
        int numTas = -1; // pas de tas à séparer par défaut
		int i;
		
        if (jeu == null) {
            System.err.println("firstSplit(): le paramètre jeu est null");
			
        } else if (!isPossible((jeu)) ){
            System.err.println("firstSplit(): aucun tas n'est divisible");
			
        } else if (jeuEssai == null) {
            System.err.println("firstSplit(): le paramètre jeuEssai est null");
			
        } else {
            // avant la copie du jeu dans jeuEssai il y a un reset de jeuEssai 
            jeuEssai.clear(); // size = 0
            i = 0;
			
			// recopie case par case de jeu dans jeuEssai
			// jeuEssai est le même que le jeu avant la première configuration d'essai
            while (i < jeu.size()) {
                jeuEssai.add(jeu.get(i));
                i = i + 1;
            }
			
            i = 0;
			// rechercher un tas d'allumettes d'au moins 3 allumettes dans le jeu
			// sinon numTas = -1
			boolean trouve = false;
            while ( (i < jeu.size()) && !trouve) {
				
				// si on trouve un tas d'au moins 3 allumettes
				if ( jeuEssai.get(i) >= 3 ) {
					trouve = true;
					numTas = i;
				}
				
				i = i + 1;
            }
			
			// sépare le tas (case numTas) en un nouveau tas d'UNE SEULE allumette qui vient se placer en fin du tableau 
			// le tas en case numTas a diminué d'une allumette (retrait d'une allumette)
			// jeuEssai est le plateau de jeu qui fait apparaître cette séparation
            if ( numTas != -1 ) removing ( jeuEssai, numTas, 1 );
        }
		
        return numTas;
    }

    /**
     * Test of the method firstSplit()
     */
    void testFirstSplit() {
	
        System.out.println();
        System.out.println("*** testFirstSplit()");

        ArrayList<Integer> jeu1 = new ArrayList<Integer>();
        jeu1.add(10);
        jeu1.add(11);
        int ligne1 = 0;
        ArrayList<Integer> res1 = new ArrayList<Integer>();
        res1.add(9);
        res1.add(11);
        res1.add(1);
        testCasFirstSplit(jeu1, ligne1, res1);
    }

    /**
     * Test one case of the method testFirstSplit
	 *
	 * @param 		jeu game board
	 * @param ligne the index of the stack splitting first
	 * @param res 	the game board after the first split
     */
    void testCasFirstSplit(ArrayList<Integer> jeu, int ligne, ArrayList<Integer> res) {
	
        // Arrange
        System.out.print("firstSplit (" + jeu.toString() + ") : ");
        ArrayList<Integer> jeuEssai = new ArrayList<Integer>();
		
        // Act
        int noLigne = firstSplit(jeu, jeuEssai);
		
        // Assert
        System.out.println("\nnoLigne = " + noLigne + " jeuEssai = " + jeuEssai.toString());
		boolean egaliteJeux = jeuEssai.equals(res);
        if ( egaliteJeux && noLigne == ligne ) {
            System.out.println("OK\n");
        } else {
            System.err.println("ERREUR\n");
        }
    }

    /**
     * Generates the next test configuration (namely ONE possible decomposition).
     * 
     * @param jeu      	game board
     * @param jeuEssai 	test game configuration after the split
     * @param indice    the index of the stack that was split most recently
     * @return 			the index of the stack split in two, to generate the new configuration, or -1 if no further decomposition is possible
     */
    int next(ArrayList<Integer> jeu, ArrayList<Integer> jeuEssai, int indice) {
			
		int numTas = -1; // par défaut il n'y a plus de décomposition possible
		
        int i = 0;
		// traitement des erreurs
        if (jeu == null) {
            System.err.println("next(): le paramètre jeu est null");
        } else if (jeuEssai == null) {
            System.err.println("next() : le paramètre jeuEssai est null");
        } else if (indice >= jeu.size()) {
            System.err.println("next(): le paramètre indice est trop grand");
        }
		
		else {
		
			int nbAllumEnIndice = jeuEssai.get(indice);
			int nbAllDernCase = jeuEssai.get(jeuEssai.size() - 1);
			
			// si sur la même case (= indice passée en paramètre) on peut encore retirer des allumettes,
			// c-à-d si l'écart entre le nombre d'allumettes à cet indice et le nombre d'allumettes en fin de tableau
			// est > 2, alors on retire encore 1 allumette à cet indice et on ajoute 1 allumette en dernière case	
            if ( (nbAllumEnIndice - nbAllDernCase) > 2 ) {
                jeuEssai.set ( indice, (nbAllumEnIndice - 1) );
                jeuEssai.set ( jeuEssai.size() - 1, (nbAllDernCase + 1) );
                numTas = indice;
            } 
			
			// sinon il faut examiner le tas (indice) suivant du jeu pour éventuellement le décomposer
			// on recrée une nouvelle configuration d'essai identique au plateau de jeu
			else {
                // copie du jeu dans JeuEssai
                jeuEssai.clear();
                for (i = 0; i < jeu.size(); i++) {
                    jeuEssai.add(jeu.get(i));
                }
				
                boolean separation = false;
                i = indice + 1; // tas suivant
				
				// si il y a encore un tas et qu'il contient au moins 3 allumettes
				// alors on effectue une première séparation en enlevant 1 allumette
                while ( i < jeuEssai.size() && !separation ) {
					// le tas doit faire minimum 3 allumettes
                    if ( jeu.get(i) > 2 ) {
                        separation = true;
						// on commence par enlever 1 allumette à ce tas
                        removing(jeuEssai, i, 1);
						numTas = i;
                    } else {
                        i = i + 1;
                    }
                }				
            }
        }
		
        return numTas;
    }

    /**
     * Test of the method next()
     */
    void testNext() {
	
        System.out.println();
        System.out.println("*** testNext() ****");

        int ligne1 = 0;
        int resLigne1 = 0;
        ArrayList<Integer> jeu1 = new ArrayList<Integer>();
        jeu1.add(10);
        ArrayList<Integer> jeuEssai1 = new ArrayList<Integer>();
        jeuEssai1.add(9);
        jeuEssai1.add(1);
        ArrayList<Integer> res1 = new ArrayList<Integer>();
        res1.add(8);
        res1.add(2);
        testCasNext(jeu1, jeuEssai1, ligne1, res1, resLigne1);

        int ligne2 = 0;
        int resLigne2 = -1;
        ArrayList<Integer> jeu2 = new ArrayList<Integer>();
        jeu2.add(10);
        ArrayList<Integer> jeuEssai2 = new ArrayList<Integer>();
        jeuEssai2.add(6);
        jeuEssai2.add(4);
        ArrayList<Integer> res2 = new ArrayList<Integer>();
        res2.add(10);
        testCasNext(jeu2, jeuEssai2, ligne2, res2, resLigne2);

        int ligne3 = 1;
        int resLigne3 = 1;
        ArrayList<Integer> jeu3 = new ArrayList<Integer>();
        jeu3.add(4);
        jeu3.add(6);
        jeu3.add(3);
        ArrayList<Integer> jeuEssai3 = new ArrayList<Integer>();
        jeuEssai3.add(4);
        jeuEssai3.add(5);
        jeuEssai3.add(3);
        jeuEssai3.add(1);
        ArrayList<Integer> res3 = new ArrayList<Integer>();
        res3.add(4);
        res3.add(4);
        res3.add(3);
        res3.add(2);
        testCasNext(jeu3, jeuEssai3, ligne3, res3, resLigne3);

    }

    /**
     * Test one case of the method next
	 * 
	 * @param jeu		game board
	 * @param jeuEssai 	the game board obtained after splitting a stack
	 * @param ligne 	the index of the stack that was split most recently
	 * @param resJeu 	expected jeuEssai after the split
	 * @param resLigne 	expected index of the stack being split
     */
    void testCasNext(ArrayList<Integer> jeu, ArrayList<Integer> jeuEssai, int ligne, ArrayList<Integer> resJeu, int resLigne) {
	
        // Arrange
        System.out.print("next (" + jeu.toString() + ", " + jeuEssai.toString() + ", " + ligne + ") : ");
		
        // Act
        int noLigne = next(jeu, jeuEssai, ligne);
		
        // Assert
        System.out.println("\nnoLigne = " + noLigne + " jeuEssai = " + jeuEssai.toString());
		boolean egaliteJeux = jeuEssai.equals(resJeu);
        if ( egaliteJeux && noLigne == resLigne ) {
            System.out.println("OK\n");
        } else {
            System.err.println("ERREUR\n");
        }
    }

}