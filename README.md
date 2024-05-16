# Bar'App API

## Configuration requise

- Java 17
- Node
- Apache Maven
- Docker

## Installation

1. Cloner le repository depuis GitHub : git clone https://github.com/SamG10/bar-app-api.git
2. Accéder au répertoire du projet : cd bar-app-api 
3. Construire le projet avec Maven : mvn clean package 
4. Lancer l'application : java -jar target/bar-app-api-0.0.1-SNAPSHOT.jar 
5. L'API sera accessible à l'adresse : http://localhost:8080

## Script initialisation base de donnée

1. Faire un `docker compose up -d` pour lancer la base de donnée
2. Exécuter : `npm install pg dotenv`
3. Exécuter la commande `node init.js` dans le dossier bar-app-api qui va créer les tables et ajouter des données.

Un user avec l'email: admin@admin.fr et un password: password sera créer ainsi qu'un user : user@user.fr avec un password: password 
Pour accéder au détails d'une commande dans le front il faudrait être connecté avec le user admin@admin.fr

Enjoy !