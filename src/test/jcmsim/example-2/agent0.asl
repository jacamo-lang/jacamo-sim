!go.
  
+!go <-
  makeArtifact("my_counter","test.Counter", [], Id);
  focus(Id);
  !use_it.
  
+!use_it <-
  inc;
  .wait(100);
  !use_it.  
  
+count(V)
  <- println("count value: ",V).
  
