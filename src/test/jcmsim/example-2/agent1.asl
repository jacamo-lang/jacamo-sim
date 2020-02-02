!go.
  
+!go <-
  !discoverArt("my_counter").
  
+!discoverArt(Name) <-
    lookupArtifact(Name,Id);
    focus(Id).
    
-!discoverArt(Name) <-
    .wait(100);
    !discoverArt(Name).

  
+count(V)
  <- println("count value: ",V).
  
