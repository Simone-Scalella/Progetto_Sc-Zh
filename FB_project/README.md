# OOP_Project_2020 (Progetto di due studenti)
In questa Repository è presente il progetto per il corso di Programmazione ad Oggetti dell'UNIVPM.
Il seguente ReadMe vuole dare una breve spiegazione del funzionamento dell'applicazione creata.

## Dataset
Metric utilizzate per la pagina facebook:
page_consumptions :

Il numero di volte in cui le persone hanno cliccato su uno dei tuoi contenuti.

page_consumptions_unique :

Il numero di persone che hanno cliccato su uno dei tuoi contenuti.

page_negative_feedback :

Il numero di volte in cui le persone hanno eseguito un'azione negativa (ad es. annullato un "Mi piace" o nascosto un post).

page_negative_feedback_unique :

Il numero di persone che hanno eseguito un'azione negativa (ad es. annullato un "Mi piace" o nascosto un post).

page_impressions :

Il numero di volte in cui un contenuto della tua Pagina o relativo alla tua Pagina è apparso nello schermo di una persona. Sono inclusi i post, le storie, le registrazioni, le inserzioni, le informazioni social delle persone che hanno interagito con la tua Pagina e altro.

page_impressions_unique :

Il numero di persone a cui è apparso nello schermo un contenuto della tua Pagina o relativo ad essa. Sono inclusi i post, le storie, le registrazioni, le inserzioni, le informazioni social delle persone che interagiscono con la tua Pagina e altro.

page_impressions_unique :

Il numero di persone a cui è apparso nello schermo un contenuto della tua Pagina o relativo ad essa. Sono inclusi i post, le storie, le registrazioni, le inserzioni, le informazioni social delle persone che interagiscono con la tua Pagina e altro.

page_fans  :

Il numero totale di persone a cui piace la tua Pagina.

Metric utilizzati per i post di facebook:

post_impressions:

Il numero di volte in cui il post della tua Pagina è apparso nello schermo di una persona.

post_impressions_unique:
	
Il numero di persone a cui è apparso nello schermo il post della tua Pagina.

post_reactions_by_type_total :

Il numero totale giornaliero di reazioni a un post di una Pagina, per tipo.

## Descrizione
L'applicazione sviluppata dagli studenti Yihang Zhang e Scalella Simone serve per effettuare analisi e statistiche sugli insight di una pagina facebook.
Una volta avviata l'applicazione bisogna necessariamente effettuare il Login, tramite l'inserimento, utilizzando una richiesta di tipo Get con un programma come ad esempio postman, dei valori Id_page,Acces_token,period(opzionale, il valore di default è days_28).
Il Login è obbligatorio, serve per autenticare l'utente, senza il Login ogni richiesta fatta restituisce un messaggio di errore.
Successivamente all'operazione di Login, si possono effettuare varie richieste, due di queste restituiscono gli insight principali della pagina e dei vari post in essa contenuti più alcune statistiche.
Una richiesta restituisce informazioni filtrate dei vari post (le metric utilizzate usano come periodo "lifetime"), i filtri si possono applicare sulle varie reaction ("like","wow"....etc) e il loro valore e vanno inseriti nel body della richiesta.
Una richiesta permette di aggiornare l'attributo period, grazie al quale è possibile filtrare per data le metric della pagina facebook e le relative statistiche.
Le ultime due richieste ci restituiscono ognuna delle due una statistica specifica.

Il programma è stato sviluppato utilizzando Java tramite l'utilizzo dell'IDE Eclipse e del framework Spring.

E' stato utilizzato Postman per inoltare le richieste all'applicazione così da poterne verificare il corretto funzionamento.
Altri software utilizzati sono stati Apache Maven e Github.

## Il progetto

### Struttura

Il progetto è organizzato in 6 package per agevolarne lo svolgimento e la manutenzione:

**univpm.OOP2020.App** : al suo interno si trova la classe contenente il main che si occupa dell'avvio del programma.

**univpm.OOP2020.App.controllers** : al suo interno c'è la classe che rappresenta il controller che gestisce l'interazione con l'utente.

**univpm.OOP2020.Body** : al suo interno contiene la classe per effettuare il request body.

**univpm.OOP2020.Model** : al suo interno ci sono le classi che mappano gli elementi dei vari file json con i loro attributi.

**univpm.OOP2020.Service** : al suo interno si trovano 2 classi e una interfaccia:
  
  -Download: è l'interfaccia.
  
  -FB_page_info: classe che implementa il metodo dell'interfaccia, si occupa di scaricare il file json e assegnare i valori agli attributi relativi alla pagina.
  
  -ALL_post: classe che implementa il metodo dell'interfaccia, si occupa di scaricare il file json e assegnare i valori agli attributi relativi a tutti i post,    all'interno della classe abbiamo un vettore di oggetti della classe page_post in maniera tale da poter collezionare tutte le informazioni di tutti i post della pagina.  

 **univpm.OOP2020.Test** : al suo interno c'è la classe OOP2020_Test che si occupa di effettuare alcuni controlli e di lanciare eventualmente le relative eccezioni.
 
 ### Attributi da inserire 
 
 Gli attributi che è possibile inserire nella richiesta  "login" sono: **id_page** , **access_token** , **period**.

 Gli attributi che è possibile inserire nella richiesta "update_period" sono: **days_28** , **week** , **day**.

 Gli attributi che è possibile inserire nel body della richiesta "filter_method" sono: 
 
 -- un qualsiasi numero intero per l'attributo "value",
 -- un qualsiasi operatore come "< _ > _ == _ => _ <=" per l'attributo "operator",
 -- uno qualsiasi degli attributi presenti nella classe Reactions per l'attributo "attribute".
 
 
 
 #### Statistiche
 
 media_click = Il numero di volte in cui le persone hanno cliccato su uno dei tuoi contenuti diviso il numero di persone che hanno cliccato su uno dei tuoi contenuti.
 
 media_negativa = Il numero di volte in cui le persone hanno eseguito un'azione negativa diviso il numero di persone che hanno eseguito un'azione negativa.

 media_virale = Il numero di volte in cui un contenuto della tua Pagina o relativo alla tua Pagina è apparso nello schermo di una persona diviso Il numero di persone a  cui è apparso nello schermo un contenuto della tua Pagina o relativo ad essa.

 impressions_per_person = Il numero di volte in cui il post della tua Pagina è apparso nello schermo di una persona diviso Il numero di persone a cui è apparso nello schermo il post della tua Pagina.

 average_post_reaction = numero totale di reazioni diviso il numero di post.

 impression_per_post = Il numero di volte in cui un contenuto della tua Pagina o relativo alla tua Pagina è apparso nello schermo di una persona diviso il numero di  post.

 Nota degli sviluppatori:
 Per evitare l'uso degli switch all'interno del metodo "filter_method" abbiamo utilizzato la classe "ScriptEngine" che permette di generare un'espressione logica a  partire da delle stringhe, che sono appunto i parametri passati alla classe request_body; per evitare troppi assegnamenti abbiamo costruito oggetti a cui abbiamo asssegnato direttamente tutto il jsonobject, utilizzando la libreria "Gson", utilizzando "java.lang.reflect" è stato possibile invocare metodi dinamicamente (sempre nella costruzione del metodo "filter_method") in quanto il metodo get viene completato dopo che gli viene passato un parametro dopo l'esecuzione dell'applicazione, per gestire le eccezioni di questo metodo abbiamo usato java.lang.reflect.InvocationTargetException.

 ##Autori:
 Yihang Zhang = FB_page_info,simpleRestController,Statics, Request Body,Metric_values,property.
  Scalella Simone = page_post,ALL_post,Download,OOP2020_Test,Reactions,Impressions.
 insieme abbiamo generato i diagrammi UML, il codice javadoc e tutta la documentazione.

Riferimenti esterni :

 -https://stackoverflow.com/questions/19383953/is-it-possible-to-evaluate-a-boolean-expression-for-string-comparions
 -https://stackoverflow.com/questions/7746158/how-to-read-object-attribute-dynamically-in-java
 -https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
 -https://stackoverflow.com/questions/24322776/how-to-convert-a-java-object-to-a-jsonobject#:~:text=Instead%20of%20saving%20to%20a,json%20representation%20of%20your   %20POJO.&text=If%20we%20are%20parsing%20all,gets%20converted%20to%20the%20JSONObject.
