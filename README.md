# ConwayGame

--run on command prompt
open command promp
CD to where conway.jar is
java -cp conway.jar com.conwaygame.app.App --> hit enter

Instructions

	1 click on a cell to make it live(cell becomes green)
	2 click on start button to update and it keep updating next generation till reset button is clicked
	3 at bottom show no of generations iterated

==================================== For Developers===============================================

Conway's Game
--Tech Notes
following props and internationalization could be changed
Class com.conwaygame.constants.Constants.java(could be defined as Enums also for elegance)
Also same could be set in property file
	i.e.
		no of rows
		no of columns 
		name on buttons  internationalization
		frame 
			width
			height 
		next generation time
		Live color
		etc..
		
	--for next generation processing
		 graph could be used but not right choice since only to look for close next neighbour only 

--code coverage
	Note: Only main game rules tested
	Not taken actual method names and under which packages	  
		
--user instruction to run app
	CD to where proj copied (till target dir is there)
	java -cp target\conway-0.0.1-SNAPSHOT.jar com.conwaygame.app.App
	
	1 click on a cell to make it live(cell becomes green)
	2 click on start button to update and it keep updating next generation till reset button is clicked
	3 at bottom show no of generations iterated
	
--compile n run
	--From IDE
	right click on project conway 
		select run as run configuration or maven build...
		write compile or package in goal field

	right click on project conway and select run as java application
		will popup with choices 
			select App - com.conwaygame.app (probably first one)
			and app will start
		Or 
		right click on com.conwaygame.app.App -> popup menu select run as java application
	--From command prompt
		cd dir to where project is copied/installed
		mvn compile
		mvn package 
		mvn exec:java -Dexec.mainClass="com.conwaygame.app.App"
	
--software requirement
	
	For any users
	java 8x
	
	For developer to review code and compile
	Eclipse
	maven 3X
	java 5x or greater


