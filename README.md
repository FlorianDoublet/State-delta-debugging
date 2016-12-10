# State-delta-debugging
Don't forget that you need Maven to run the project.<br />
All the work made is just some exploration / suposition.<br />
<br />
<br />
Currently, the Challenge .java file need to be in the folder "ressources"<br/>
<br />
All the CtAssignement and CtVariableRead are captured thanks to the implentation of the "capture" method arround them. The implentation was made in main/java/spoon/utils/ChallengeProcessor, wish call CtAssignmentOperations and CtVariableReadOperations.
<br />
The capture Method is wrote in FancyDDebbuger in the package debug.<br />
<br />
*Now, the Spoon Processor approach is replaced by the "object" approach as you can see in ChallengeProcessor, CtAssignmentOperations, etc.. Please respect this aspect as possible*
