!main_test.
  
+!main_test
  <-  makeArtifact("counter","ex1.Counter", [], Id);
  	  focus(Id);
      inc;
      inc.
        
+count(V)
  <- .println("count value: ",V).


+value(V)
  <- println("GUI value: ",V).
  
+!main_test2
  <-  println("start");
  	  makeArtifact("counter","ex1.Counter", [], Id1);
  	  focus(Id1);
  	  makeArtifact("gui","ex1.GUI", [], Id2);
  	  focus(Id2);
      inc;
      inc.
  