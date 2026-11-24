/**
 * TP1
 * @author Valerian GOMBAULT 
 */

hey
class SimplesTableau {
    /**
     * Cette classe effectue des opérations élémentaires sur un ou plusieurs tableaux d'entiers. La taille d'un tableau est par définition le nombre
     * TOTAL de cases = tab.length. Un tableau d'entiers créé possède nbElem éléments qui est nécessairement inférieur ou égal à la taille du
     * tableau : nbElem = tab.length (= taille).
     */
    void principal() {
        testVerifTab();
        testAfficherTab();
        testTirerAleatoire();
        testRemplirAleatoire();
        testEgalite();
        testCopier();
        testLeMax();
        testInverse();
        testEchange();
        testMelange();
        testDecalerGche();
        testSupprimerUneValeur();
        testInclusion();
        testRemplirToutesDiff();
    
    }

    /** 
     * verifTab
     * Factorisation : appelé chaque fois qu'une vérification doit se faire sur la validité d'un tableau. Le tableau n'est pas valide si celui-ci est
     * inexistant ou si le nombre d'éléments qu'il contiendra est incompatible avec sa taille (leTab.length).
     * @param tab - le tableau dont on doit vérifier l'existence (leTab différent de null)
     * @param n - le nombre d'éléments que contiendra le tableau, doit vérifier la condition 0  nbElem = leTab.length
     * @return vrai ssi le tableau est valide
     */
    boolean verifTab(int[] tab, int n){ 
        boolean ret = false;
        if( tab != null) { 
            if( (n>=1) && (n <= tab.length)){ 
                ret = true;
            }
        }
        return ret;
    }
    
    /**
     * testCasVerifTab() 
     * Teste la methode verifTab
    */
    void testVerifTab(){ 
        // cas normal 
        System.out.println(" ----- Test de verifTab ----- "); 
        System.out.println(" -- Cas normaux --");  
        int[] tab1 = {1,3,5}; 
        int n1 = 2; 
        testCasVerifTab(tab1, n1, true); 
        int[] tab3 = {}; 
        int n3 = 0; 
        testCasVerifTab(tab3, n3, false); 
        int[] tab4 = null;
        int n4 = 80;
        testCasVerifTab(tab4, n4, false);

        // cas limite 
        System.out.println(" -- Cas limite --");
        int[] tab2 = {650}; 
        int n2 = 1; 
        testCasVerifTab(tab2, n2, true); 	

    }
    
    /**
     * testVerifTab() 
     * Teste un cas de la methode verifTab
     * @param tab - le tableau dont on doit vérifier l'existence (leTab différent de null)
     * @param n - le nombre d'éléments que contiendra le tableau, doit vérifier la condition 0  nbElem = leTab.length
     * @param result - Résultat attendu  
    */
    void testCasVerifTab(int[] tab, int n, boolean result){ 
	    boolean resExec = verifTab(tab,n); 
        if(resExec == result) { 
            System.out.println("Test réussi");
        }else{  
            System.out.println("Echec du test");
        }
    }

    /** 
     * afficherTab() 
     * Affiche le contenu des nbElem cases d'un tableau une par une.
     * @param leTab - le tableau à afficher
     * @param nbElem - le nombre d'entiers que contient le tableau
     */
    void afficherTab(int[] leTab, int nbElem){ 
        if(verifTab(leTab, nbElem)){
            System.out.print("{"); 

            for(int i = 0; i < (nbElem-1); i++){ 
                System.out.print(leTab[i] + ",");

            }
            System.out.print(leTab[nbElem-1] + "}"); 

            System.out.println(); 
        }else{ 
            System.err.println("afficherTab() tableau non valide");
        }
    }
    /** 
     * testCasAfficherTab()
     * Teste la fonction afficherTab
     */
    void testAfficherTab(){ 
        // cas normal 
        System.out.println(" ----- Test de afficherTab ----- "); 
        System.out.println(" -- Cas normaux --");  
        int[] tab1 = {1,3,5}; 
        int nbElem1 = 3;
        testCasAfficherTab(tab1, nbElem1, false);
        int[] tab2 = {32,32,48,8823,82378908,2763,8,209873}; 
        int nbElem2 = 4;
        testCasAfficherTab(tab2, nbElem2, false);
        int[] tab3 = {32,32,48,3,2,4,3,9}; 
        int nbElem3 = 8;
        testCasAfficherTab(tab3, nbElem3, false);
        // cas limite 
        System.out.println(" -- Cas limite --");  
        int[] tab4 = {92}; 
        int nbElem4 = 1;
        testCasAfficherTab(tab4, nbElem4, false);
        // cas d'erreur 
        System.out.println(" -- Cas d'erreur --");  
        int[] tab5 = {}; 
        int nbElem5 = 0;
        testCasAfficherTab(tab5, nbElem5, true);

        

    }

    /** 
     * testCasAfficherTab
     * Test un cas de afficherTab()
     * @param leTab - le tableau à afficher
     * @param nbElem - le nombre d'entiers que contient le tableau
     * @param casErreur - Résultat attendu 
     */
    void testCasAfficherTab(int[] leTab, int nbElem, boolean casErreur){ 
        if(!casErreur){ 
            afficherTab(leTab, nbElem);
        }else{
            System.out.println("Message d'erreur attendu");
            afficherTab(leTab, nbElem);
        }
    }


    /** 
     * tirerAleatoire() 
     * Renvoie un entier aléatoire compris entre min et max (min = valeur = max). Vérifier que min = max et min = 0, sinon afficher
    une erreur (et dans ce cas retourner -1).
     * @param min - la valeur de l'entier minimum 
     * @param max - la valeur de l'entier maximum
     * @return l'entier aléatoire 
     */ 
    int tirerAleatoire(int min, int max){ 
        int nombrealeatoire;
        if (min >= max || min < 0){ 
            System.err.println("tirerAleatoire() Valeur incorecte");
            nombrealeatoire = -1;
        }else{

            nombrealeatoire =  (int)(Math.random()*(max-min));
            nombrealeatoire = nombrealeatoire + min; 
        }

        return nombrealeatoire;
    }
        /** 
     * testCasTirerAleatoire() 
     * Test un cas de la fonction tirerAleatoire() 
     * @param min - la valeur de l'entier minimum 
     * @param max - la valeur de l'entier maximum
     * @param casErreur - true si un message d'erreur est attendu
     */ 
    void testCasTirerAleatoire(int min, int max, boolean casErreur){ 
        if(!casErreur){ 
            int tirage = tirerAleatoire(min, max);
            System.out.println(tirage);
        }else{
            System.out.println("Message d'erreur attendu");
            tirerAleatoire(min, max);
        }

    }

    /** 
     * testTirerAleatoire()
     * Teste la fonction tirerAleatoire
     */
    void testTirerAleatoire(){ 
        // cas normal 
        System.out.println(" ----- Test de tirerAleatoire ----- "); 
        System.out.println(" -- Cas normaux --");   

        System.out.println(" Nombres entre 1 et 10 : "); 
        for(int i = 0; i < 6; i++){ 
            testCasTirerAleatoire(1, 10, false);
        }
        System.out.println(" Nombres entre 5 et 10 : "); 
        for(int i = 0; i < 6; i++){ 
            testCasTirerAleatoire(5, 10, false);
        }
        // cas limite 
        System.out.println(" Nombres entre 2 et 3 : "); 
        for(int i = 0; i < 6; i++){ 
            testCasTirerAleatoire(2, 3, false);
        }
        // cas d'erreur
        System.out.println(" -- Cas d'erreur --");   
        testCasTirerAleatoire(10, 5, true);



           

    }

    /** 
     * remplirAleatoire() 
     * A partir d'un tableau créé, remplir aléatoirement ce tableau de nbElem valeurs comprises entre min et max. 
     * @param leTab - le tableau à remplir de valeurs tirées aléatoirement
     * @param nbElem -  le nombre d'entiers que contiendra le tableau 
     * @param min - la valeur de l'entier minimum 
     * @param max - la valeur de l'entier maximum
     */ 
    void remplirAleatoire(int[] leTab, int nbElem, int min, int max){ 
        if(verifTab(leTab, nbElem)){
            if (min >= max || min <= 0){ 
            System.err.println("remplirAleatoire() Valeur incorecte");
            }else{ 
                for(int i = 0; i < nbElem; i++){ 
                    leTab[i] = tirerAleatoire(min, max);

                }
            }
        }else{ 
            System.err.println("remplirAleatoire() tableau non valide");
        }
    }
        /**
     * testCasRemplirAleatoire()
     * Teste un cas de remplirAleatoire()
     * @param leTab - le tableau à remplir
     * @param nbElem - le nombre d'entiers à tirer
     * @param min - la valeur de l'entier minimum
     * @param max - la valeur de l'entier maximum
     * @param casErreur - true si un message d'erreur est attendu
     */
    void testCasRemplirAleatoire(int[] leTab, int nbElem, int min, int max, boolean casErreur){
        if(!casErreur){
            remplirAleatoire(leTab, nbElem, min, max);
            afficherTab(leTab, nbElem);
        }else{
            System.out.println("Message d'erreur attendu");
            remplirAleatoire(leTab, nbElem, min, max);
        }
    }

    /**
     * testRemplirAleatoire()
     * Teste la fonction remplirAleatoire
     */
    void testRemplirAleatoire(){
        System.out.println(" ----- Test de remplirAleatoire ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] tab1 = new int[5];
        testCasRemplirAleatoire(tab1, 5, 1, 10, false);
        int[] tab2 = new int[10];
        testCasRemplirAleatoire(tab2, 4, 5, 20, false);
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] tab3 = new int[1];
        testCasRemplirAleatoire(tab3, 1, 2, 3, false);
        // cas d'erreur
        System.out.println(" -- Cas d'erreur --");
        int[] tab4 = new int[5];
        testCasRemplirAleatoire(tab4, 5, 10, 5, true);  
        int[] tab5 = new int[3];
        testCasRemplirAleatoire(tab5, 8, 1, 10, true);   
        int[] tab6 = null;
        testCasRemplirAleatoire(tab6, 3, 1, 10, true);  
    }



        /**
     * egalite()
     * Renvoie vrai si les 2 tableaux passés en paramètre sont exactement les mêmes en nombre d'éléments et en contenu (case par case).
     * Tenir compte des cas d'erreurs concernant tab1, nbElem1 et tab2, nbElem2 (appel de verifTab 2 fois) et afficher un éventuel
     * message d'erreur (et dans ce cas renvoyer faux).
     * @param tab1 - le 1er tableau à comparer
     * @param tab2 - le 2ème tableau à comparer
     * @param nbElem1 - le nombre d'entiers présents dans le 1er tableau
     * @param nbElem2 - le nombre d'entiers présents dans le 2ème tableau
     * @return true si égalité parfaite sinon false
     */
    boolean egalite(int[] tab1, int[] tab2, int nbElem1, int nbElem2){
        boolean ret = false;
        if(verifTab(tab1, nbElem1) && verifTab(tab2, nbElem2)){
            if(nbElem1 == nbElem2){
                ret = true;
                int i = 0;
                while(ret && i < nbElem1){
                    if(tab1[i] != tab2[i]){
                        ret = false;
                    }
                    i++;
                }
            }
        }else{
            System.err.println("egalite() tableau non valide");
        }
        return ret;
    }

    /**
     * testCasEgalite()
     * Teste un cas de egalite()
     * @param tab1 - le 1er tableau à comparer
     * @param nbElem1 - le nombre d'entiers présents dans tab1
     * @param tab2 - le 2ème tableau à comparer
     * @param nbElem2 - le nombre d'entiers présents dans tab2
     * @param attendu - le résultat attendu
     * @param casErreur - true si un message d'erreur est attendu
     */
    void testCasEgalite(int[] tab1, int nbElem1, int[] tab2, int nbElem2, boolean attendu, boolean casErreur){
        if(casErreur){
            System.out.println("Message d'erreur attendu");
        }
        boolean resExec = egalite(tab1, tab2, nbElem1, nbElem2);
        if(resExec == attendu){
            System.out.println("Test réussi");
        }else{
            System.out.println("Echec du test");
        }
    }



    /**
     * testEgalite()
     * Teste la fonction egalite
     */
    void testEgalite(){
        System.out.println(" ----- Test de egalite ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] t1a = {1,3,5};
        int[] t1b = {1,3,5};
        testCasEgalite(t1a, 3, t1b, 3, true, false);      
        int[] t2a = {1,3,5};
        int[] t2b = {1,4,5};
        testCasEgalite(t2a, 3, t2b, 3, false, false);     
        int[] t4a = {1,3,5,8};
        int[] t4b = {1,3,5,9};
        testCasEgalite(t4a, 3, t4b, 3, true, false);     
        int[] t5a = {1,3,5};
        int[] t5b = {5,3,1};
        testCasEgalite(t5a, 3, t5b, 3, false, false);     
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] t6a = {92};
        int[] t6b = {92};
        testCasEgalite(t6a, 1, t6b, 1, true, false);
        int[] t7a = {92};
        int[] t7b = {93};
        testCasEgalite(t7a, 1, t7b, 1, false, false);
        int[] t8a = {1,3,5};
        testCasEgalite(t8a, 3, t8a, 3, true, false);      
        // cas d'erreur
        System.out.println(" -- Cas d'erreur --");
        int[] t9a = null;
        int[] t9b = {1,3,5};
        testCasEgalite(t9a, 3, t9b, 3, false, true);      
        int[] t10a = {1,3,5};
        int[] t10b = null;
        testCasEgalite(t10a, 3, t10b, 3, false, true);    
    }
        /**
     * copier()
     * Renvoie la copie exacte (clone) du tableau passé en paramètre.
     * @param tabToCopy - le tableau à copier
     * @param nbElem - le nombre d'entiers à tirer
     * @return - la copie du tableau
     */
    int[] copier(int[] tabToCopy, int nbElem){ 
        int[] newTab = new int[nbElem];
        for(int i = 0; i < nbElem; i++){ 
            newTab[i] = tabToCopy[i];

        }
        return newTab; 


    }

    /**
     * testCasCopier()
     * Teste un cas de copier()
     * @param tab - le tableau à copier
     * @param nbElem - le nombre d'entiers à copier
     */
    void testCasCopier(int[] tab, int nbElem){
        int[] resExec = copier(tab, nbElem);

    
        boolean ok = true;
        int i = 0;
        while(ok && i < nbElem){
            if(resExec[i] != tab[i]){
                ok = false;
            }
            i++;
        }
        if(ok){
            System.out.println("Test réussi");
        }else{
            System.out.println("Echec du test");
        }
        
    }

    /**
     * testCopier()
     * Teste la fonction copier
     */
    void testCopier(){
        System.out.println(" ----- Test de copier ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] tab1 = {1,3,5};
        testCasCopier(tab1, 3);
        int[] tab2 = {32,32,48,8823,2763,8};
        testCasCopier(tab2, 4);       
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] tab3 = {92};
        testCasCopier(tab3, 1);


    }

            /**
     * leMax()
     * Renvoie le maximum parmi les éléments du tableau.
     * @param leTab - le tableau 
     * @param nbElem -  le nombre d'entiers présents dans le tableau
     * @return le maximum des éléments du tableau
     */
    int leMax(int[] leTab, int nbElem){ 
        int max = leTab[0]; 
        for(int i = 0; i < nbElem;i++){ 
            if(leTab[i] > max){
                max = leTab[i];
            }
        }
        return max;
    }

    /**
     * testCasLeMax()
     * Teste un cas de leMax()
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @param attendu - le maximum attendu
     */
    void testCasLeMax(int[] leTab, int nbElem, int attendu){
        int resExec = leMax(leTab, nbElem);
        if(resExec == attendu){
            System.out.println("Test réussi");
        }else{
            System.out.println("Echec du test");
        }
    }

    /**
     * testLeMax()
     * Teste la fonction leMax
     */
    void testLeMax(){
        System.out.println(" ----- Test de leMax ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] tab1 = {1,3,5};
        testCasLeMax(tab1, 3, 5);
        int[] tab2 = {9,7,27,9283,2,56};
        testCasLeMax(tab2, 6, 9283);
        int[] tab3 = {10,20,30,5};
        testCasLeMax(tab3, 2, 20);          
        int[] tab4 = {-5,-2,-9};
        testCasLeMax(tab4, 3, -2);          
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] tab5 = {92};
        testCasLeMax(tab5, 1, 92);
        int[] tab6 = {9,1,2};
        testCasLeMax(tab6, 3, 9);        
        int[] tab7 = {1,2,9};
        testCasLeMax(tab7, 3, 9);            
    }

    /**
     * inverse()
     * Renvoie un nouveau tableau qui est l'inverse de celui passé en paramètre. Son jème élément est égal au (nbElem+1-j) élément du
tableau initial (dans l'explication, j=1 signifie premier élément du tableau).
     * @param leTab - le tableau 
     * @param nbElem -  le nombre d'entiers présents dans le tableau
     * @return le nouveau tableau qui est l'inverse de leTab sur la plage (0...nbElem-1)
     */
    int[] inverse(int[] leTab, int nbElem){ 
        int[] invers = new int[nbElem]; 

        for(int i = 0; i < nbElem; i++){
            invers[i] = leTab[(nbElem-1)-i];
        }

        return invers;


    }
        /**
     * testCasInverse()
     * Teste un cas de inverse()
     * @param leTab - le tableau à inverser
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @param attendu - le tableau inversé attendu
     */
    void testCasInverse(int[] leTab, int nbElem, int[] attendu){
        int[] resExec = inverse(leTab, nbElem);

        boolean ok = (resExec.length == nbElem);
        int i = 0;
        while(ok && i < nbElem){
            if(resExec[i] != attendu[i]){
                ok = false;
            }
            i++;
        }
        if(ok){
            System.out.println("Test réussi");
        }else{
            System.out.println("Echec du test");
        }
    }

    /**
     * testInverse()
     * Teste la fonction inverse
     */
    void testInverse(){
        System.out.println(" ----- Test de inverse ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] tab1 = {1,3,5,7};
        int[] att1 = {7,5,3,1};
        testCasInverse(tab1, 4, att1);
        int[] tab2 = {32,32,48,8823,2763,8};
        int[] att2 = {8823,48,32,32};
        testCasInverse(tab2, 4, att2);       
        int[] tab3 = {1,3,5};
        int[] att3 = {5,3,1};
        testCasInverse(tab3, 3, att3);     
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] tab4 = {92};
        int[] att4 = {92};
        testCasInverse(tab4, 1, att4);
    }

    /**
     * echange()
     * Echange les contenus des cases du tableau passé en paramètre, cases identifiées par les indices ind1 et ind2. Vérifier que les indices
ind1 et ind2 sont bien compris entre zéro et (nbElem-1), sinon afficher un message d'erreur.
     * @param leTab -  le tableau
     * @param nbElem -  le nombre d'entiers présents dans le tableau
     * @param ind1 -  numéro de la première case à échanger
     * @param ind2 -  numéro de la deuxième case à échanger
     */
    void echange(int[] leTab, int nbElem, int ind1, int ind2){ 
        if(ind1 <= nbElem-1 && ind2 <= nbElem-1 && ind1 >= 0 && ind2 >= 0 ){ 
            int temp = leTab[ind1]; 
            leTab[ind1] = leTab[ind2];
            leTab[ind2] = temp;


        }else{ 
            System.err.println("Erreur, valeur d'échange incorrecte");

        }
        


    }


    /**
     * testCasEchange()
     * Teste un cas de echange()
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @param ind1 - numéro de la première case à échanger
     * @param ind2 - numéro de la deuxième case à échanger
     * @param casErreur - true si un message d'erreur est attendu
     */
    void testCasEchange(int[] leTab, int nbElem, int ind1, int ind2, boolean casErreur){
        if(!casErreur){
            int v1 = leTab[ind1];
            int v2 = leTab[ind2];
            echange(leTab, nbElem, ind1, ind2);
            if(leTab[ind1] == v2 && leTab[ind2] == v1){
                System.out.println("Test réussi");
            }else{
                System.out.println("Echec du test");
            }
        }else{
            System.out.println("Message d'erreur attendu");
            echange(leTab, nbElem, ind1, ind2);
        }
    }

    /**
     * testEchange()
     * Teste la fonction echange
     */
    void testEchange(){
        System.out.println(" ----- Test de echange ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] tab1 = {9,7,27,9283,2,56};
        testCasEchange(tab1, 6, 3, 5, false);
        int[] tab2 = {1,3,5};
        testCasEchange(tab2, 3, 0, 2, false);
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] tab3 = {92};
        testCasEchange(tab3, 1, 0, 0, false);   
        int[] tab4 = {4,8,15,16};
        testCasEchange(tab4, 4, 2, 2, false);    
        int[] tab5 = {4,8,15,16};
        testCasEchange(tab5, 4, 0, 3, false);   
        // cas d'erreur
        System.out.println(" -- Cas d'erreur --");
        int[] tab6 = {1,3,5};
        testCasEchange(tab6, 3, 1, 3, true);    
        int[] tab7 = {1,3,5};
        testCasEchange(tab7, 3, 7, 0, true);    
    }

    /**
     * melange()
     * TRetourne un nouveau tableau qui a la même taille et les mêmes occurrences d'élements que le tableau passé en paramètre mais ces
     * éléments sont répartis selon des indices aléatoires (0 = indice = nbElem-1). Une technique simple consiste à utiliser les méthodes
     * "echange" et "tirerAleatoire" pour effectuer le mélange.
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @return - le nouveau tableau qui a le même contenu que le tableau initial mais mélangé
     */

    int[] melange(int[] leTab, int nbElem){ 
        int[] tabMelange = copier(leTab, nbElem);
        for(int i =0; i < nbElem; i++){ 
            int melangePos1 = tirerAleatoire(0, (nbElem));
            int melangePos2 = tirerAleatoire(0, (nbElem)); 
            echange(tabMelange, nbElem, melangePos1, melangePos2);

        }
        return tabMelange;


    }

        /**
     * testCasMelange()
     * Teste un cas de melange()
     * @param leTab - le tableau à mélanger
     * @param nbElem - le nombre d'entiers présents dans le tableau
     */
    void testCasMelange(int[] leTab, int nbElem){
        int[] resExec = melange(leTab, nbElem);

        afficherTab(resExec, nbElem);
    }

    /**
     * testMelange()
     * Teste la fonction melange
     */
    void testMelange(){
        System.out.println(" ----- Test de melange ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] tab1 = {1,3,5,7,9};
        testCasMelange(tab1, 5);
        int[] tab2 = {32,32,48,8823,2763,8};
        testCasMelange(tab2, 4);
        int[] tab3 = {2,2,3,3};
        testCasMelange(tab3, 4);
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] tab4 = {92};
        testCasMelange(tab4, 1);
    }
    /**
     * decalerGche()
     * Décale de une case de la droite vers la gauche toutes les cases d'un tableau à partir d'un indice "ind" et jusque nbElem-1 ([ind]-
     * [ind+1]-[ind+2]-...-[nbElem-2]-[nbElem-1]). Vérifier que ind est compris entre 0 et (nbElem-2) sinon afficher une erreur.
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @param ind - l'indice à partir duquel commence le décalage à gauche
     */
    void decalerGche(int[] leTab, int nbElem, int ind){ 
        if(ind >= 0 && ind <= (nbElem-2)){
            for (int i = ind; i < nbElem - 1; i++) {
                leTab[i] = leTab[i + 1]; 

            }

        }else{ 
            System.err.println("decalerGche() indice incorecte");
        }


    }
    /**
     * testCasDecalerGche()
     * Test un cas de CasDecalerGche
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @param ind - l'indice à partir duquel commence le décalage à gauche
     * @param casErreur - Si on attent une erreur ou pas 
     */
    void testCasDecalerGche(int[] leTab, int nbElem, int ind, boolean casErreur){ 
        if(!casErreur){
            decalerGche(leTab, nbElem, ind);
            afficherTab(leTab, nbElem);
        }else{
            System.out.println("Message d'erreur attendu");
            decalerGche(leTab, nbElem, ind);
        }
    } 

    /**
     * testDecalerGche()
     * Test la méthode DecalerGche
     */
    void testDecalerGche(){ 
        System.out.println(" ----- Test de DecalerGche ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] tab1 = {9,7,27,9283,2,56};
        testCasDecalerGche(tab1, 6, 4, false);
        int[] tab2 = {1,3,5};
        testCasDecalerGche(tab2, 3, 1, false);
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] tab4 = {4,8,15,16};
        testCasDecalerGche(tab4, 4, 2, false);    
        int[] tab5 = {4,8,15,16};
        testCasDecalerGche(tab5, 4, 0, false);   
        // cas d'erreur
        System.out.println(" -- Cas d'erreur --");
        int[] tab6 = {1,3,5};
        testCasDecalerGche(tab6, 3, 5, true);    
        int[] tab7 = {1,3,5};
        testCasDecalerGche(tab7, 3, -1, true); 

    }

    


    /**
     * supprimerUneValeur()
     * Supprime du tableau la première case rencontrée dont le contenu est égale à "valeur". La case du tableau est supprimée par
     * décalage à gauche des cases du tableau. L'appel de la méthode "decalerGche" est obligatoire. A l'issue de la suppression (si elle
     * existe) le nombre d'éléments dans le tableau est décrémenté et retourné.
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @param valeur - le contenu de la première case à supprimer
     * @return - le nombre d'éléments dans le tableau (éventuellement inchangé)
     */
    int supprimerUneValeur(int[] leTab, int nbElem, int valeur){


        boolean fini = false;
        int i = 0;  
        while(!fini && i < nbElem){ 
            if(leTab[i] == valeur){
                fini = true;
                if(i < nbElem-1){
                    decalerGche(leTab, nbElem, i);
                }
                nbElem = nbElem - 1;
            }
            i++; 
        }


        return nbElem;
    }


    /**
     * testCasSupprimerUneValeur()
     * Teste un cas de supprimerUneValeur()
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @param valeur - la valeur à supprimer
     * @param nbElemAttendu - le nombre d'éléments attendu après suppression
     * @param attendu - le contenu attendu du tableau après suppression
     */

    void testCasSupprimerUneValeur(int[] leTab, int nbElem, int valeur, int nbElemAttendu, int[] attendu){
        int resExec = supprimerUneValeur(leTab, nbElem, valeur);

        boolean ok = (resExec == nbElemAttendu);
        int i = 0;
        while(ok && i < nbElemAttendu){
            if(leTab[i] != attendu[i]){
                ok = false;
            }
            i++;
        }
        if(ok){
            System.out.println("Test réussi");
        }else{
            System.out.println("Echec du test");
        }
    }

    /**
     * testSupprimerUneValeur()
     * Teste la fonction supprimerUneValeur
     */
    void testSupprimerUneValeur(){
        System.out.println(" ----- Test de supprimerUneValeur ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] tab1 = {1,3,5,7};
        int[] att1 = {1,5,7};
        testCasSupprimerUneValeur(tab1, 4, 3, 3, att1);
        int[] tab2 = {4,8,4,16};
        int[] att2 = {8,4,16};
        testCasSupprimerUneValeur(tab2, 4, 4, 3, att2);   
        int[] tab3 = {1,3,5,7};
        int[] att3 = {1,3,5,7};
        testCasSupprimerUneValeur(tab3, 4, 9, 4, att3);   
        int[] tab4 = {1,3,5,7,9};
        int[] att4 = {1,3};
        testCasSupprimerUneValeur(tab4, 2, 5, 2, att4);   
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] tab5 = {92};
        int[] att5 = {};
        testCasSupprimerUneValeur(tab5, 1, 92, 0, att5);  
        int[] tab6 = {1,3,5,7};
        int[] att6 = {3,5,7};
        testCasSupprimerUneValeur(tab6, 4, 1, 3, att6);   
        int[] tab7 = {1,3,5,7};
        int[] att7 = {1,3,5};
        testCasSupprimerUneValeur(tab7, 4, 7, 3, att7);   
    }
    
    /**
     * inclusion()
     * Renvoie vrai ssi le tableau tab1 est inclus dans tab2. Autrement dit, si tous les éléments de tab1 se retrouvent intégralement dans
     * tab2 (y compris les doublons) mais pas nécessairement dans le même ordre. L'utilisation de méthodes déjà écrites est autorisé.
     * @param tab1 - le premier tableau
     * @param tab2 - le deuxième tableau
     * @param nbElem1 -  le nombre d'entiers présents dans le tableau1
     * @param nbElem2 - le nombre d'entiers présents dans le tableau2
     * @return - vrai ssi tableau1 est inclus dans tableau2
     */
    boolean inclusion(int[] tab1, int[] tab2, int nbElem1, int nbElem2) {
        boolean inc = true;

        int[] tab2Copie = copier(tab2, nbElem2);

        for (int i = 0; i < nbElem1; i++) {
            boolean trouve = false;
            for (int j = 0; j < nbElem2; j++) {
                if (tab1[i] == tab2Copie[j]) {
                    nbElem2 = supprimerUneValeur(tab2Copie, nbElem2, j);
                    trouve = true;
                }
            }
            if (!trouve) {
                inc = false;
            }
        }

        return inc;
    }


    /**
     * testCasInclusion()
     * Teste un cas de inclusion()
     * @param tab1 - le premier tableau
     * @param nbElem1 - le nombre d'entiers présents dans tab1
     * @param tab2 - le deuxième tableau
     * @param nbElem2 - le nombre d'entiers présents dans tab2
     * @param attendu - le résultat attendu
     */
    void testCasInclusion(int[] tab1, int nbElem1, int[] tab2, int nbElem2, boolean attendu){
        boolean resExec = inclusion(tab1, tab2, nbElem1, nbElem2);
        if(resExec == attendu){
            System.out.println("Test réussi");
        }else{
            System.out.println("Echec du test");
        }
    }

    /**
     * testInclusion()
     * Teste la fonction inclusion
     */
    void testInclusion(){
        System.out.println(" ----- Test de inclusion ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        int[] t1a = {1,3};
        int[] t1b = {3,5,1};
        testCasInclusion(t1a, 2, t1b, 3, true);           
        int[] t2a = {1,9};
        int[] t2b = {1,3,5};
        testCasInclusion(t2a, 2, t2b, 3, false);          
        int[] t3a = {3,3};
        int[] t3b = {3,5,3};
        testCasInclusion(t3a, 2, t3b, 3, true);                  
        int[] t5a = {1,3,99};
        int[] t5b = {3,1,5,99};
        testCasInclusion(t5a, 2, t5b, 4, true);           
        // cas limite
        System.out.println(" -- Cas limite --");
        int[] t6a = {92};
        int[] t6b = {92};
        testCasInclusion(t6a, 1, t6b, 1, true);
        int[] t7a = {1,3,5};
        int[] t7b = {5,3,1};
        testCasInclusion(t7a, 3, t7b, 3, true);           
        int[] t8a = {1,3,5};
        int[] t8b = {1,3};
        testCasInclusion(t8a, 3, t8b, 2, false);          
    }



    /**
     * remplirToutesDiff()
     * A partir d'un tableau déjà créé, remplir le tableau de nbElem valeurs saisies par l'utilisateur. Au fur et à mesure de la saisie, si la
     * nouvelle valeur saisie existe déjà dans le tableau alors ne pas l'insérer et demander de ressaisir. Tenir compte des cas d'erreurs
     * concernant leTab et nbElem (appel de verifTab).
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     */
    void remplirToutesDiff(int[] leTab, int nbElem){ 
        if(verifTab(leTab, nbElem)){ 
            for(int i =0; i < nbElem; i++){
                boolean valide = false;  
                int ajout = 0;
                while(!valide){
                    ajout = SimpleInput.getInt("Nombre a ajouter dans la liste : ");
                    int[] tab1 = {ajout};
                    if(inclusion(tab1, leTab, 1, nbElem)){ 
                        System.err.println("Erreur, veuiller reéseyer avec un nombre non présent dans la liste");
                        
                    }else{ 
                        valide = true; 
                    }
                }
                leTab[i] = ajout; 
            }
        }else { 
            System.err.println("remplirToutesDiff() - valeur de tableau incorecte"); 
        }   
    }

    /**
     * testCasRemplirToutesDiff()
     * Teste un cas de remplirToutesDiff()
     * @param leTab - le tableau à remplir
     * @param nbElem - le nombre d'entiers à saisir
     * @param casErreur - true si un message d'erreur est attendu
     */
    void testCasRemplirToutesDiff(int[] leTab, int nbElem, boolean casErreur){
        if(!casErreur){
            remplirToutesDiff(leTab, nbElem);
            afficherTab(leTab, nbElem);
        }else{
            System.out.println("Message d'erreur attendu");
            remplirToutesDiff(leTab, nbElem);
        }
    }

    /**
     * testRemplirToutesDiff()
     * Teste la fonction remplirToutesDiff
     */
    void testRemplirToutesDiff(){
        System.out.println(" ----- Test de remplirToutesDiff ----- ");
        // cas normal
        System.out.println(" -- Cas normaux --");
        System.out.println(" Saisir 4 valeur dont un doublon :");
        int[] tab1 = new int[4];
        testCasRemplirToutesDiff(tab1, 4, false);
        // cas limite
        System.out.println(" -- Cas limite --");
        System.out.println(" Saisir 1 valeur :");
        int[] tab2 = new int[1];
        testCasRemplirToutesDiff(tab2, 1, false);
        // cas d'erreur
        System.out.println(" -- Cas d'erreur --");
        int[] tab3 = null;
        testCasRemplirToutesDiff(tab3, 3, true);
        int[] tab4 = new int[3];
        testCasRemplirToutesDiff(tab4, 8, true);         
        int[] tab5 = {};
        testCasRemplirToutesDiff(tab5, 0, true);          
    }
    
}

