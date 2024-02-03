# Nasa Imagery Android App

## Présentation :
Il s'agit d'une application Android MVVM qui va nous afficher des images de l'éspace prises par NASA. Cela sera fait en utilisant l'API 'APOD' fournie par NASA. 

## Présentation de l'API APOD : 
APOD (Astronomy Picture of the Day) est une Open API fournie par NASA qui nécessite une clé pour être accedée. Elle nous fournie une image de l'éspace unique associée à chaque jour.

##### Endpoint : 
https://api.nasa.gov/planetary/apod
##### Paramètres : 
- API_KEY
- date (default est la date du jour courant)

##### Réponse :
- copyright	
- date	
- explanation	
- hdurl	
- media_type	
- service_version	
- title	
- url

## Fonctionnement de l'application : 
Dans l'application on à trois fragments corréspandant à trois interfaces utilisateur, on navigue de l'un vers les autres en utilisant le Navigation Component.
### Les interfaces:
#### Interface Home
Interface qui contiendera l'image, son titre et un bouton qui permettera de les afficher.
<div style="text-align:right"><img src="https://github.com/aminechaabini/NASAImageryAndroidApp/assets/93048328/56d58cbe-1eef-41f5-83d3-7df5e47d73e7" /></div>

#### Interface Image Details
Interface qui contiendera les détails de l'image affiché dans Home.
<div style="text-align:right"><img src="https://github.com/aminechaabini/NASAImageryAndroidApp/assets/93048328/ed3b6a57-c17f-4f2f-8b0c-4fd76ef63f21" /></div>

#### Interface Settings
Interface qui nous permettera de choisir une date pour obtenir l'image qui lui correspend.
<div style="text-align:right"><img src="https://github.com/aminechaabini/NASAImageryAndroidApp/assets/93048328/e4bb5fc8-34ca-47d4-a4b0-b168d2a4a854" /></div>

### Fragments et ViewModel : 
On utilise trois fragement differents pour la gestion de l'interaction avec l'utilisateur et l'affichage des données. 
On utilise un seul Shared View Model qui contient deux LiveDatas selectedDate et Result (objet réponse de l'api).
On utilise le data binding pour les fragments.

#### Shared View Model : 
Implémente trois fonctionalités : 
- getResult : met à jour la live data Result en lui affectant l'objet response de l'image correspendant à la date choisie par l'utilisateur.
- getResultTodaysDate : met à jour la live data Result en lui affectant l'objet response de l'image du jour (default).
- setDate : met à jour la live data selectedDate.
- dateIsAfterCurrentDate : vérifie si la date sélectionnée dans le calandrier est supérieure à la date courante, elle retourne un booléen.

#### Fragment Home
Contient un setOnClickListener sur le bouton 'GET IMAGE' qui va appeler getResult si une date est choisie ou getResultTodaysDate sinon.
#### Fragment Settings
Contient un setOnClickListener sur le bouton 'SET DATE' qui va appeler setDate et setOnDateChangeListener sur le calendrier qui va assurer qu'on a toujours la date la plus récente que l'utilisateur a choisit.

### Partie Network : 
#### Requêtage de l'API : 
On a trois fichiers résponsables des requêtes API faites par l'application Interface APODApi, data class Result et objet RetrofitHelper. On utilise Retrofit pour le requêtage et Gson pour la conversion des données.
#### Gestion de problèmes de connexion Internet : 
Les problèmes de connexion Internet sont gérés au niveau de la Main Activity. On utilise la méthode NetworkCallBack du ConnectivityManager pour être alertés des changement au niveau de la connexion internet, à chaque fois qu'il y a un changement ActivityMain affiche un Toast expliquant l'état d'Internet.







