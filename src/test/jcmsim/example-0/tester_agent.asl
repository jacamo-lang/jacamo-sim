//!test_double_join.

!main_test.
  
+!main_test
  <-  .println("started.");
      !test_console;
      !test_make_lookup_dispose;
      !test_use;
      !test_focus.
          
+!test_console <-
  println("this print is done by the console artifact").

  
+!test_make_lookup_dispose <-
  lookupArtifact("workspace",W);
  focus(W);
  makeArtifact("my_counter","ex0.Counter");
  lookupArtifact("my_counter",Id);
  println("artifact created ",Id);
  makeArtifact("my_counter2","ex0.Counter",[],Id2);
  println("artifact2 created ",Id2);
  // stopFocus(Id2);
  disposeArtifact(Id2);
  println("artifact2 disposed.").

+artifact(Name,Template,Id)
  <- println("new artifact available: ",Name).

-artifact(Name,Template,Id)
  <- println("Artifact removed: ",Name).
  
  
+!test_use <-
  inc;
  println("op inc executed.");
  inc [art("my_counter")];
  println("op inc executed specifying the artifact name.");
  lookupArtifact("my_counter",Id);
  inc [aid(Id)];
  println("op inc executed specifying the artifact Id").  
  
+!test_focus <-
  lookupArtifact("my_counter",Id);
  focus(Id);
  println("focus executed specifying the art name.");
  inc;
  inc;
  stopFocus(Id);
  println("stop focus executed specifying the art name.");
  inc;
  inc;
  focus(Id);
  println("focus executed specifying the artifact unique ID.").
  
+count(V)[art(Id,"my_counter")]
  <- println("count value: ",V," artifact: ",Id).
  
+incremented [percept_type("obs_ev"),art(_,Name)]
  <- println("new incremented event from ",Name).

+focused_art(Wsp,ArtName,ArtId) 
    <- println("Focused: ",ArtName," (id: ",ArtId," ) in ",Wsp).
     
+Ev [source(s)] : true <-
  .print(Ev," from ",S).
