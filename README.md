# OCR-Java-Projet3
OpenClassrooms - Parcours DA Java/JEE - Projet 3

/*
** Concept de l'application
*/

Vous avez le choix entre 2 jeux : CodeSearch et Mastermind

Choix 1 : Le CodeSearch
Le but du jeu est de deviner ou de faire deviner une combinaison de X chiffres (par défaut 4)

Choix 2 : Mastermind
Le but du jeu est de deviner ou de faire deviner une combinaison de X couleurs (par défaut 4)

Chaque jeu dispose de 4 modes :
- Challenger : vous devez deviner la combinaison
- Defender : l'ordinateur (NPC) doit deviner la combinaison
- Duel : A tour de rôle, le joueur et le NPC doivent deviner la combinaison adverse avant l'adversaire
- Match : lancement aléatoire entre les mdoes Challenger et Defender sur 5 manches

/*
** Import / Compilation
*/

Pour installer l'application dans votre IDE, vous pouvez télécharger l'archive du projet puis dans votre IDE (ici nous utilsons en exemple Eclipse) cliquez sur Import > General > Archive File puis séléctionnez l'archive et enfin cliquez sur Finish.

Le programme lèvera une Exception si vous le lancez car il ne trouvera pas log4j2. Pour contourner le problème, vous trouverez dans ce projet les fichiers JAR de Log4J2 qu'il vous faudra également importe en cliquant droit sur votre projet puis sur Build Path > Configure Build Path. Dans l'onglet Libraries, cliquez sur Add External JARs puis sélectionnez les 3 fichiers .jar

/*
** Propriétés
*/

Le programme se base sur le fichier config.properties (dans com.antazri.main.resources) pour configurer certains paramètres (nombre de tours, longueur des codes, mode développeur). Il est possible de les modifier puis de relancer le programme pour les appliquer automatiquement.

Le mode développeur permet d'afficher les codes générés (par l'utilisateur/joueur et l'ordinateur/NPC) pour vérifier le bon fonctionnement. 

/*
** Utilisation
*/

Le programme, et donc les jeux, s'utilisent en mode console. Au démarrage, un menu apparaitra et vous indiquera les différentes possibilités. Il suffira de taper le bon code puis sur Entrée. 
