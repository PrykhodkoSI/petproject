# Hotel
* Init database (create database {db.name};)
* Set env variable DB_PASSWORD
* Fill db.properties for main and for test (url, driver, username, password)
* Setup properties for liquibase plugin in pom
* Run:
> mvn liquibase:update
* mvn clean install
* Deploy "hotel.war" using Apache Tomcat 9.0.10

# Test profiles with credentials:
* Client: Login(client@gmail.com), Password(SecurePassword@01)
* Manager: Login(manager@gmail.com), Password(SecurePassword@01)
* Client+Manager: Login(prykhodko.s.i@gmail.com), Password(SecurePassword@01)

# Pages:   
    1) /login
    2) /rooms
    3) /user/profile
    4) /managers/profile
    5) /user/profile/request/create
    6) /user/profile/request/view
    7) /user/profile/request/attach

* "/login" - login/registration page 
    * different content for logged in user (logout button))
    * error handling
   
* "/rooms" - hotel main page 
    * available rooms for customer

* "/user/profile" - profile of currant user 
    * user info
    * available requests for client
    * available bills for client
    
* "/managers/profile" - profile of currant manager
    * manager info
    * available requests for manager
    * available bills for manager
        
* "/user/profile/request/create" - create request page 
    
* "/user/profile/request/view" - request view page 
    
* "/user/profile/request/attach" - add apartment to request page
    * available apartments attach for request