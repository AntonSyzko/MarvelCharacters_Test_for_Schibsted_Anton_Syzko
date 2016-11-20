# MarvelCharacters_Test_for_Schibsted_Anton_Syzko
test for Schinsted Marvel Api Characters Report

Prerequisites </br>
install mongodb  </br>
start mongodb </br>

Dependencies  required </br>
 - commons-codec 1.9</br>
 - com.google.code.gson 2.7</br>
 - org.mongodb 2.11.0</br>
 - org.springframework.data 1.2.0 RELEASE</br>
 - commons-collections 3.2.1</br>
 - com.github.jsimone  webapp-runner 8.0.30.2</br>
 - org.json 20150729</br>
 - org.codehaus.jackson 1.9.13 </br>
 

Approaches </br>
 1 - Rest call Parsing JSON using Gson - chunks of 100 ( limit by Marvel API ) -> offset ++100 till the end</br>
     storing data  objects to MongoDB Collection ( read and write for 1485 Objects made faster )</br>
     Two controllers </br>
        * - Mongo Loader Component to parse json calls and store data to DB </br>
        * - Mongo Report Component to retrieve data for the very report</br>
     Same  behaviour is available  in monoController</br>
        
 2 - Mono Controller - rest call parsing json   using Gson  - chunks  of 100 -> offset ++ 100 till the end</br>
     storing data objects in LRUMap for  cache  management ( base case LinkedHasMap ) - each rest call adds new Map to main storage </br>
     retrieving data from Map for reporting </br>
     
 3 - functional cut through approach - all in one  class  as  we  go - storing data in chunks of 55 ( total 1485 MOD 55 = 0 ) </br>
     Storage ArrayList - load / report   </br>
     
 Future Updates Storage </br>
   - storing data to .json file for local cache ( file )  </br>
   - concurrent approach using ExecutorsService with Runnable interfaces - for retrieving data in separate threads for each rest call  </br>
   
     
     
