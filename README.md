# bidding-system

#### This bidding system enables the users to place the bid for running auctions.

#### An Auction have following attributes:
1. Item Code - for which auction is running
2. Minimum Base Price - This is starting bidding amount, no user can place the bid
lesser than this defined price
3. Step Rate - minimum amount difference b/w two consecutive bids.  
 
    For example, if a
user placed the bid of 1000 /- INR then the next acceptable bid will be a minimum of
1000 + Step Rate.  

    If the step rate is 250 /- INR then the next acceptable bid should
be >= 1250.
4. Status -  
a.   RUNNING: Only running auctions are the candidates of placing the bid   
b. OVER: Once auction is over then no user can place the bid on the
corresponding item

5. User Bids - All user bids should be recorded whether it was accepted or rejected.

### Tech Stack:
1. *Programming Language* - **Java (11)**

    Java is good choice for developing scalable REST APIs. Java scales well as it is multi-threaded.

2. *Framework* - **Spring Boot (2.3.2)**

    Spring Boot makes very easy to develop Spring Based applications with Java. 
    It reduces lots of development time and increases productivity. 
    It avoids writing lots of boilerplate Code, Annotations and XML Configuration. 
    It is very easy to integrate Spring Boot Application with its Spring Ecosystem like Spring JDBC, 
    Spring ORM, Spring Data, Spring Security etc. It provides lots of plugins to work with embedded and in-memory Databases very easily.

3. *Database* - **Embedded(In-memory) H2**  

    Since this is a demo application, its good to use in-memory database, 
    so that we have some seed data when we start the application.
    Also, we need to use any RDBMS so that we have ACID compliance out of the box.
    NoSQL will suffer from eventual consistency and this cannot suffice the requirement of a bidding system.    
    
4. *Build Tool* - **Maven**    

### System Requirements:
Your system should have following installed to run this application:

   1. **JRE 11**  
   2. **Maven 3.6.3+**

### Running this application locally on your development machine:
1. Clone the repository
`git clone https://github.com/rounakraj8/bidding-system.git`
2. cd into bidding-system `cd ~/bidding-system`
3. Run the application `./mvnw spring-boot:run`
4. Application will start running at `http://localhost:8080`
5. Run the unit test cases and build the artifact(JAR) file `./mvnw clean install`

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

    Method - `POST`,  URL - `/auction/{itemCode}/bid` 
      
     `201 - Bid is accepted,  
      404 - Auction not found,  
      406 - Bid is rejected`  
      
    CURL Request:  
     `curl --location --request POST 'localhost:8080/auction/ITEM0123/bid' \
      --header 'Content-Type: application/json' \
      --data-raw '1500
      '`   
      
    Sample Response:   
      `201 Created` / `406 Not Acceptable` / `401 Unauthorized`
      
#### Users

<table>
   <tr>
      <th>User ID</th>
      <th>User Name</th>
      <th>Status</th>
   </tr>
   <tr>
      <td>1</td>
      <td>USER_1</td>
      <td>LOGGED_IN</td>
   </tr>
   <tr>
      <td>2</td>
      <td>USER_2</td>
      <td>LOGGED_OUT</td>
   </tr>
   <tr>
      <td>3</td>
      <td>USER_3</td>
      <td>LOGGED_IN</td>
   </tr>
</table>     

#### Postman Collection
 <a href="https://github.com/rounakraj8/bidding-system/blob/main/BiddingSystem.postman_collection.json" target="_blank">Link</a>
 

#### Dockerize & Deploy the app in Kubernetes
 
 <a href="https://rounakraj8.github.io/kubernetes-101/">Link</a>
 