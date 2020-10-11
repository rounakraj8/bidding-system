# bidding-system

#### This bidding system enables the users to place the bid for running auctions.

### Tech Stack:
*Programming Language* - **Java (11)**

Java is good choice for developing scalable REST APIs.

*Framework* - **Spring Boot (2.3.2)**

Spring Boot makes very easy to develop Spring Based applications with Java. 
It reduces lots of development time and increases productivity. 
It avoids writing lots of boilerplate Code, Annotations and XML Configuration. 
It is very easy to integrate Spring Boot Application with its Spring Ecosystem like Spring JDBC, 
Spring ORM, Spring Data, Spring Security etc. It provides lots of plugins to work with embedded and in-memory Databases very easily.

*Database* - **Embedded(In-memory) H2**  

Since this is a demo application, its good to use in-memory database, 
so that we have some seed data when we start the application.

### System Requirements:
Your system should have following installed to run this application:

**JRE 11**  
**Maven 3.6.3+**

### Running this application locally on your development machine:
1. Clone the repository
`git clone https://github.com/rounakraj8/bidding-system.git`
2. cd into bidding-system `cd ~/bidding-system`
3. Run the application `./mvnw spring-boot:run`
4. Application will start running at `http://localhost:8080`

### APIs

1. FETCH RUNNING AUCTIONS  

    Method - `GET`,  URL - `/auction?status=RUNNING`  
    
    This API returns all running auction.  
    
    CURL Request:  
    `curl --location --request GET 'localhost:8080/auction?status=RUNNING'`
    
    Sample Response:  
    `[
         {
             "itemCode": "ITEM0123",
             "stepPrice": 100.0,
             "highestBidAmount": 1100.0
         },   
         {
             "itemCode": "ITEM0125",
             "stepPrice": 50.0,
             "highestBidAmount": 0.0
         }
     ]`
     
2. PLACE BID    

    Method - `POST`,  URL `/auction/{itemCode}/bid` 
      
    CURL Request:  
     `curl --location --request POST 'localhost:8080/auction/ITEM0123/bid' \
      --header 'Content-Type: application/json' \
      --data-raw '1500
      '`   
      
    Sample Response:   
      `201 Created` / `406 Not Acceptable` / `401 Unauthorized`
