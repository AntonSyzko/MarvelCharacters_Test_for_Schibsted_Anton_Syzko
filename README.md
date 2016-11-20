# MarvelCharacters_Test_for_Schibsted_Anton_Syzko
test for Schinsted Marvel Api Characters Report

Approaches 
 1 - Rest call Parsing JSON using Gson - chunks of 100 ( limit by Marvel API ) -> offset ++100 till the end
     storing data  objects to MongoDB Collection ( read and write for 1485 Objects made faster )
     Two controllers 
        * - Mongo Loader Component to parse json calls and store data to DB 
        * - Mongo Report Component to retrieve data for the very report
     Same  behaviour is available  in monoController
        
 2 - Mono Controller - rest call parsing json   using Gson  - chunks  of 100 -> offset ++ 100 till the end
     storing data objects in LRUMap for  cache  management ( base case LinkedHasMap ) - each rest call adds new Map to main storage
     retrieving data from Map for reporting 
     
 3 - functional cut through approach - all in one  class  as  we  go - storing data in chunks of 55 ( total 1485 MOD 55 = 0 )
     Storage ArrayList - load / report
     
 Future Updates Storage 
   - storing data to .json file for local cache ( file )
   - concurrent approach using ExecutorsService with Runnable interfaces - for retrieving data in separate threads for each rest call
   
     
     
