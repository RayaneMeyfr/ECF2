# **Sujet ECF 2 – CDA**

## **Contexte**

Une application Java permettant de signaler et de visualiser des observations d’espèces dans la nature a été en grande partie développée.  
Votre mission est de **compléter la modélisation et certaines parties du code** afin de finaliser l’application.  

---

## **Travail réalisé**

Pour cette ECF2, j’ai dû effectuer plusieurs tâches afin de compléter le travail déjà partiellement accompli.  

---

### **Outils utilisés**

- **Draw.io** : pour compléter les schémas (format initial déjà en `.drawio`).  
- **Git** : pour le dépôt et le suivi de version du projet.  
- **IntelliJ IDEA** : IDE pour le développement Java.  
- **MySQL & Wamp** : pour la gestion et l’hébergement de la base de données.  
- **Postman** : pour tester les différentes routes de l’API.  

⚠️ **Précision** :  
La version du projet a été adaptée à **Java 21** (initialement Java 22) car cette dernière causait des problèmes de lancement sur ma configuration locale.  

---

### **Diagramme UML**
- Complétion de la table `travel_log` qui était vide.  
- Ajout des cardinalités entre les différentes tables.  
- Ajout de verbes pour préciser les relations.  

---

### **Diagramme de classes**
- Complétion de la classe `Travellog` qui était totalement vide (ajout des variables et des différentes méthodes).  
- Complétion de la classe `SpecieDtoReceive` (ajout des variables et des méthodes) + définition de la relation et des cardinalités manquantes.  
- Complétion de la classe `TravellogStat` (ajout des variables et des méthodes).  
- Ajout de la méthode `create` dans la classe `TravellogsService`.  
- Ajout de deux méthodes dans la classe `TravelLogController` :  
  - `get` : pour récupérer tous les `travellogs`.  
  - `create` : pour ajouter un `travellog`.  
- Ajout des méthodes dans la classe `SpecieController` :  
  - `getSpecies`  
  - `getSpeciesById`  
  - `createSpecies`  
- Ajout des méthodes dans `ObservationRepository` :  
  - `findObservationByLocationIsLike`  
  - `findObservationBySpecieId`  

---

### **Code**

Pour la partie développement, j’ai procédé en plusieurs étapes :  

1. **Analyse du code existant** afin d’identifier les manques et les classes à compléter.  
2. Modification du fichier **`application.properties`** (configuration de la base de données et du mot de passe).  
3. Création de la classe **`Travellog`** (n’existait pas) en suivant le diagramme de classes + ajout des méthodes `entityToDto` et `calculateCO2`.  
4. Décommentaire des lignes dans **`TravellogRepository`** pour permettre l’utilisation des méthodes dans le service.  
5. Création du service **`TravellogsService`** avec l’ensemble des méthodes demandées :  
   - `create`  
   - `get`  
   - `getStat`  
   - `getStatForUserLastMonth`  
6. Ajout de la méthode manquante `create` dans la classe **`TravellogController`**.  
7. Création et complétion de **`ObservationRepository`** avec les méthodes manquantes :  
   - `findObservationByLocationIsLike`  
   - `findObservationBySpecieId`  
8. Création de la classe **`ObservationController`** avec toutes ses routes et méthodes associées.  
9. **Tests de l’application** : lancement du projet, création de différentes espèces, d’observations et de déplacements pour valider le bon fonctionnement.  

---

### **Fonctionnalités**

#### Espèces
- Voir toutes les espèces  
- Récupérer une espèce par son ID  
- Créer une espèce  

#### Observations
- Créer une observation  
- Récupérer toutes les observations  
- Récupérer une observation par son ID  
- Récupérer les observations par localisation  
- Récupérer les observations par espèce  

#### TravelLogs
- Créer un `travelLog`  
- Récupérer tous les `travelLogs`  
- Récupérer les statistiques d’un `travelLog` via son ID  
- Récupérer les statistiques de plusieurs `travelLogs` en fonction du nom de l’observateur  
