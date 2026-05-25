An assignment for simulating a university clinic booking system with SSMS as backend and Apache Tomcat as front end. Main purpose of this assignment is to apply basic database security frameworks.

In order to run this program some things to keep in mind:
1. The database used is in my local machine which I do not share in this repository. This repository is only the front-end and linking part of the complete application.
2. Do install Apache Tomcat first, and place this repository in the /webapp directory. In my system it looks like this (apache-tomcat-11.0.22\webapps\ClinicSystem)
3. You need to compile the .class files using (javac -cp "lib/*" -d WEB-INF/classes src/*.java)
4. Run .\startup.bat located in the apache-tomcat-11.0.22\bin directory. Then visit http://localhost:8080/ClinicSystem/add.html to access the appointment form.
