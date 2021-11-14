# README #

This repository contains the code and the reports for the Web Application's course of University of Padua homework assignment.

### The Web Application ###

Our web application consists in a management app for an agricultural equipment factory. The backend and mockup interface is developed using Java, PostgreSQL, HTML and JSP.

### How do I get set up? ###
To setup the application it is required to have installed and working on your system:

* [JDK][java] >1.8
* [PostgreSQL][psql] >11
* [Maven][mvn] >3.6.0
* [Tomcat][tom] 9.x.x

Before starting the web application be mindful that a database has to be in place in your system.
There are database setup files in `agrimgmt/database`. 

**BEFORE RUNNING THE SQL FILES: please modify the `Factory_insertion.sql` at lines 92-93 with the path in your own computer to the files stored in the `monthly report templates` folder that can be found in the root of this repository.**

To correctly setup the database, please run the files with psql in this order:

1. Factory_setup.sql
2. Factory_insertion.sql


To create the WAR file open a terminal in `agrimgmt` folder and run

	mvn package	
Then simply deploy the application in the Tomcat Manager.

To generate Javadoc open a terminal in `agrimgmt` folder and run

	mvn javadoc:javadoc
    
### Utilise the web application ###

In the welcome page click on the link `Reserved area` and insert the following credentials to gain privileges according to the role in the factory you want to impersonate.

Then, use the links in the profile page to access to the functionalities of that role.

#### Credentials for the login ####

These are the credentials for each role:

  - Administrator
    
        username: ma.rossi
        psw: M.reaper12 
        
  - ProductionPlanner:  
    
        username: ma.giordani
        psw: AU6.nvfPrt
        
  - Designer:
    
        username: ma.zanini
        psw: j.NG7iwze
        
  - WarehouseWorker:
    
        username: fi.marchetti
        psw: rsw.CA8a
        
  - ProductionLlineWorker:  
    
        username: an.ricciotti   
        psw: khdakeiW
        
  - Accountant:
    
        username: en.martinuz   
        psw: wdox.C291


[java]: https://www.oracle.com/it/java/technologies/javase-downloads.html
[psql]: https://www.postgresql.org/download/
[mvn]: https://maven.apache.org/download.cgi
[tom]: https://tomcat.apache.org/download-90.cgi
