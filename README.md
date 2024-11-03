# MSIG back-end Application 

This code explain using CRUD with java spring boot as backend and react.js as frontend.

# Setup Instruction
1. Install JAVA JDK 11
2. Install Apache Maven 3.8.4
3. Install MySQL
4. Install IntelliJ IDEA Community Edtion 2024.2.3 or Above

# Depedencies
For more details on what dependencies are used, you can see the pom.xml file, but what you need attention to is the dependencies for Java Version, 

# Instruction for Deployment
1.  mvn clean package -DskipTests
2.  cd \target
3.  copy msigapp-backend-0.0.1-SNAPSHOT.jar to server (OS Linux) folder for example:  /opt/services/msigapp-backend-0.0.1-SNAPSHOT.jar 
4.  running application on background proccess : nohup java -jar /opt/services/msigapp-backend-0.0.1-SNAPSHOT.jar > /opt/log/msigapp-backend-0.0.1-SNAPSHOT.jar.log 2>&1 &
5.  Open browser http://server_ip:8080

# REST API
POST http://localhost:8080/product
GET http://localhost:8080/product/{id}
PUT http://localhost:8080/product/{id}
DELETE http://localhost:8080/product/{id}
PUT http://localhost:8080/product/approve/{id}
PUT http://localhost:8080/product/reject/{id}

