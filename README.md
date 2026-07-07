**Attempted Tasks**

I have attempted the following:
- All of Task A (1 + 2)
- All of Task B
- All of Task C
- All of Task D.1

**IMPORTANT PLEASE MAKE SURE TO DO THIS**

If you do download the project from github there is for some reason an issue with the project as for some reason it doesnt have the folder "WEB-INF that I have tried to add but doesnt work for some reason so please follow these steps as the code wont run:

**EASIER OPTION: download the project from Dropbox as that has the missing folder and runs perfectly fine**

1. Open the project in netbeans.
 
2. Expand the project to find the Web Pages folder
   
3. Then make sure to doubl click this folder
   
4. Within it there is 2 items index.html and a folder named META-INF
   
5. Within the Web Pages folder add a new folder and name it the following: WEB-INF
   
6. Done the project should work now sorry for this weird issue I tried to upload the folder to git but it never worked and tried a while but it just never worked.






**How to setup Hard coded credentials + API key:**

Too be able to run these programs you will need to do the following
Go to your apache-tomcat server folder -> then go to the bin folder -> next you will need to add a new file (create) called setenv.bat -> then edit this file within text file and enter the below into the file:

@echo off

set APIKEY=password

set ENDPOINT= "Enter URL"

set KEY= "Enter DB Key"

make sure to save these changes and the program should then function properly, in the case you are unable to create this file do message me and I can send you the files on teams.
hope this helped.

**Curl Commands to use Endpoints:**

**Search Endpoint (location filtering):**

curl "http://localhost:8080/Cloud/webresources/items?location=Leeds"

**Search Endpoint for availble items:**

curl "http://localhost:8080/Cloud/webresources/items?available=true"

**Create Request Endpoint:**
The command has some test data it also requires the api key so to run this command you need to remove the brackets text to the newly set api key.

curl -v ^
  -X POST "http://localhost:8080/Cloud/webresources/requests" ^
  -H "Content-Type: application/json" ^
  -H "APIKey: (Requeires API Key)" ^
  -d "{ \"itemId\":\"i00002\", \"renterId\":\"u1\", \"startDate\":\"10/01/2025\", \"endDate\":\"20/01/2025\" }"

**Cancel Endpoint:**
Also requires the API Key.

curl -X PATCH "http://localhost:8080/Cloud/webresources/requests/(Replace with a requests ID)" -H "Content-Type: application/json" -H "APIKey: (Replace with API Key)" -d "{\"status\":\"Cancelled\"}"


Task B (Explaination why use Route)
Chose the OSRM Route over Table and Nearest.

The advantage of using Route is the feature that it returns the distance and travel time between two specific points in this case user and items (Longitude, Latitude).
Route is better due to it caculating the results using real drivable road data (it is more accurate for how a user would travel to the destination making it more realistic).
the issue with the other 2 methods Nearest and Table is the following:
- Nearest method only finds the closes point on a road which doesn't provide accurate information of the journey making it not suitable.
- Table Method provides the distance between many points all at once, in this scenario we only require the distance between 2 points so using this method is to complex.


**Note: To get access to the csv files make sure to only click on the .csv files as the other links are buggy and redirect you to random places also note I will add the testing folder into the ntu drop box so if it is difficult to view the files please do check the drop box thanks.**

**Task C Testing:**


**Configurations for all tests:**

Run 1 Baseline:

 <img width="529" height="182" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/3dce73d6-ef9f-4fd5-88bf-4bb78e7f4099" />

Run 2 Moderate:

<img width="579" height="182" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/3216d7c7-10a5-4a23-90ea-231c134b1f2e" />


Run 3 Heavy:

<img width="582" height="181" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/617fa9a4-0f81-434b-9c9c-9741441fde1a" />


Run 4 Stress test/Big Data:
 
<img width="410" height="185" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/855cfce1-6a0a-4b96-b3f2-1949833d21d0" />

Configurations for Search:

<img width="940" height="303" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/b45c47a9-f6bb-47a7-8297-2730dd172694" />

Location:

<img width="940" height="314" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/e4d08493-3f78-4606-bd18-c8e4ca5abf08" />

Create:

<img width="940" height="381" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/c15ee079-b0be-4c39-8d79-963b82c0078e" />

all requests created from the tests:

<img width="544" height="344" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/b0a5ae54-022c-4be3-844d-65644b394b1b" />

Cancel:

<img width="940" height="244" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/69fab6ca-95ff-466a-8db1-054dea47764d" />



**Cancel Endpoint Testing:**

Run 1:

[Run1.csv](https://olympus.ntu.ac.uk/user-attachments/files/155/Run1.csv)
<img width="936" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/1ab729c4-44cf-432d-ae54-ecb00d157ec4" />
<img width="936" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/3ed27d14-b472-4797-9bde-105fac6cc74c" />

Run 2:
[Uploadi<img width="936" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/a3135aec-6906-4876-9db7-688277539c1f" /><img width="936" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/a5a38135-dccf-4de6-a2bd-c6cddbd58616" />
ng Run2.csv…]()
[Run2.csv](https://olympus.ntu.ac.uk/user-attachments/files/164/Run2.csv)

Run 3:

[Uploading Run3.<img width="987" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/11a95cfe-6353-4228-87e4-e1d444afbed5" /><img width="987" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/c1c3c284-eb6d-40db-9651-30510cea2157" />
csv…]()
[Run3.csv](https://olympus.ntu.ac.uk/user-attachments/files/165/Run3.csv)

Run 4:

<img width="1056" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/63038a96-b7e6-4dd6-ad42-f6e3cb7cd4aa" />
[Run4.csv](https://olympus.ntu.ac.uk/user-attachments/files/166/Run4.csv)<img width="1056" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/5470f3db-9895-4a6a-a07f-f418448435f5" />

**Create Endpoint Testing:**

Run 1:

<img width="1056" height="650" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/55749513-ac33-42ce-875b-c33e59906d48" />
[Create1.csv](https://olympus.ntu.ac.uk/user-attachments/files/167/Create1.csv)<img width="1056" height="650" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/fa4a95db-f76f-4143-aa64-7ad5f072186a" />

Run 2:

[Create2.csv](https://olympus.ntu.ac.uk/user-attachments/files/168/Create2.csv)
<img width="936" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/9d6f411c-83dd-4a45-83d6-644e105c76b3" /><img width="936" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/d8f64a56-2a82-42e8-af5e-d7bcc9cf1e61" />

Run 3:

[U<img width="936" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/cdd26965-5a69-4b05-b969-63cd0bc49891" /><img width="936" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/ce204fff-60a9-4285-89fa-a9e5c122be02" />
ploading Create3.csv…]()
[Create3.csv](https://olympus.ntu.ac.uk/user-attachments/files/169/Create3.csv)

Run 4:

[<img width="1056" height="650" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/17248e85-b07c-4455-9142-0847f2d26f4e" /><img width="1056" height="650" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/e742886c-5701-40e2-aff5-3e7a627c3a26" />
Uploading Create4.csv…]()
[Create4.csv](https://olympus.ntu.ac.uk/user-attachments/files/170/Create4.csv)


**Location Endpoint Testing:**

Run 1:

[<img width="936" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/dbd95a48-23b7-4b82-8808-03f749b7b1f8" /><img width="936" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/9ae9b537-582f-4788-9104-cab506d58bb8" />
Uploading location1.csv…]()
[location1.csv](https://olympus.ntu.ac.uk/user-attachments/files/175/location1.csv)

Run 2:

<img width="936" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/17eb2f41-ff71-4ea0-8bbe-f0c0d0280250" /><img width="936" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/76317081-effb-47eb-9717-be47e882d283" />
[location2.csv](https://olympus.ntu.ac.uk/user-attachments/files/172/location2.csv)

Run 3:

[Upl<img width="936" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/6de5dc4e-b85e-4481-b0c4-1afc4d85a6aa" /><img width="936" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/2122cd0b-94b9-4669-8b50-fbfbb3933238" />
oading location3.csv…]()
[location3.csv](https://olympus.ntu.ac.uk/user-attachments/files/173/location3.csv)

Run 4:

[Uploading L<img width="1056" height="650" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/06f3275e-e767-4791-b16b-5c4d7f777b28" /><img width="1056" height="650" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/5ab3a4f3-21bc-4808-b563-b7c0c2b55518" />
ocation4.csv…]()
[Location4.csv](https://olympus.ntu.ac.uk/user-attachments/files/174/Location4.csv)


**Search Endpoint Tests:**

Run 1:

[U<img width="936" height="667" alt="Graph Run2 with DATA" src="https://olympus.ntu.ac.uk/user-attachments/assets/a6f2ee98-0c4f-4ce8-ac19-8d6c53a83488" /><img width="936" height="667" alt="Graph Run2" src="https://olympus.ntu.ac.uk/user-attachments/assets/7ad415d2-517e-4d6f-a7a0-18efa2ac92d5" />
ploading Run1.csv…]()
[Run1.csv](https://olympus.ntu.ac.uk/user-attachments/files/176/Run1.csv)

Run 2:

[Run2.csv](https://olympus.ntu.ac.uk/user-attachments/files/177/Run2.csv)
<img width="1056" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/edabc30f-5d21-469b-ad3a-7b1d45de7260" /><img width="1056" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/e84d8b5e-1339-4946-bd7c-f95d4beebe76" />

Run 3:

[<img width="936" height="667" alt="Graph Run3 Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/2f1bf9c9-c330-4a6f-8672-ea37b5afd240" /><img width="936" height="667" alt="Graph Run3" src="https://olympus.ntu.ac.uk/user-attachments/assets/fceb739f-a943-400e-846d-80b14c104aae" />
Uploading Run3.csv…]()
[Run3.csv](https://olympus.ntu.ac.uk/user-attachments/files/178/Run3.csv)

Run 4:

[<img width="1056" height="667" alt="Graph Results Data" src="https://olympus.ntu.ac.uk/user-attachments/assets/4c6ca8f8-f354-4a06-80e2-179e134d11aa" /><img width="1056" height="667" alt="Graph Results" src="https://olympus.ntu.ac.uk/user-attachments/assets/04a33c87-1aae-4376-8f89-11f7864b1b29" />
Uploading Run4.csv…]()
[Run4.csv](https://olympus.ntu.ac.uk/user-attachments/files/179/Run4.csv)



**Task C – QoS Testing Documentation:**

**Introduction:**

Within this task the goal to achieve is to perform a QoS test of the Orchestrator project using increasing users, loads, identify bottlenecks within project and implement a fix to improve issues which were identified within the testing.
Quality of Service testing will be carried out using JMeter which will follow lab 9, evidence of testing will be captured by taking screen shots of produced graphs of each run and CSV file which contains an in depth look of all requests sent to the project capturing exact data which was produced from JMeters (result table).
There will be 4 parts of the orchestrator project which will be tested (Item searching, create rental request, cancelation of request, and distance calculator).
For each endpoint 4 tests were carried out to provide a more valid conclusion.
Run   Threads Ramp Up  Loop   Total requests sent
1       1         1     10           100
2       10         10    10          250
3       50        50    10           500
4       100        100   10           1000
Each CSV file contains the testing and will be submitted as evidence as shown in this readme.md and in the dropbox.

Summary:
1. Search:

Total Requests - Summary of Results
100 - Reasonably quick at searching and had zero error with search.
250 - Still no errors and stable with requests.
500 - Latency spikes can be identified.
1000 - Starts producing errors and large response time errors where around 30%.

2. Create:

Total Requests - Summary of Results
100 - Stable with no errors produced.
250 - Latency begins to increase with increased requests.
500 - Latency grows even more significantly.
1000 - Starts to produce errors and the throughput starts to fall.

3. Cancel:

Total Requests - Summary of Results
100 - Stays stable with no errors.
250 - Same as run 1.
500 - There are some failures.
1000 - Large sum of request fails and time out.

4. Location:

Total Requests - Summary of Results
100 - Somewhat stable but can see failures.
250 - Can see the start of failures of requests.
500 - major test failures (external API).
1000 - Majority of test was failure (due to the OSRM throttling).

For test failures accross all endpoints:

run 1 - all tests for endpoints where successful except for location there where some failures that can be identified
run 2 - similar outcome as run 1 location endpoint because worser then run 1
run 3 - all endpoints show error a ruff average of 20% from all endpoints (location has around 80% of fails)
run 4 - all endpoints have a large amount of failure around (35% - 40%), the tests also took longer on average each test took arount 20 to 30 minutes long to be completed which is terrible and very inconvienant for real world situations were software must be fast, efficient and not resource intensive.

**Bottleneck 1:**
After viewing both the CSV files and graphs a conclusion of the bottleneck can be identified which has affected the search, create and cancel endpoints.
One of main reasonings behind why bottleneck can be identified is due to this part of the code within Cosmos.java:

<img width="940" height="290" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/91dd2053-6905-41ac-b290-b0a1afa68190" />

Within this code for every request a new cosmos client is also created. Due to cosmos client being designed to be thread safe and reused across application lifecycle, this leads to redundant connection pool creation, repeated metadata and routing cache initialisation, and unnecessary background resource allocation. This results in increased latency, increased CPU/memory usage which leads to performance degradation.

**Potential Solution:**

To fix this issue for the search, cancel and create endpoints the implementation of the reuse a single shared cosmos client.
- A single static cosmos client instance is created at application startup
- Containers are cached and reused
- No per request client creation

**Advantage of implementation:**

Helps remove unnecessary resource allocation which reduces the latency and improves the throughput due to the reuse of the container and closing only when shutting down.
Note: within the testing documents it shows that the new implementation does solve the issues especially ensuring that requests are completed successfully no fails, lower RU consumption, lower latency and it ensures that large loads of requests (1000) are completed quickly as when the original code processed 1000 requests it took the system 20 minutes to complete while with this new implementation it took an estimate of 8 minutes which is a huge time difference.

**Costs:**

With each test that was carried out within JMeter (search, create and cancel) the requests interacted with the cosmos DB which consumed some RU. Reading and writing all draw from the allocated throughput capacity. When the system is under a high load it can cause the RU limit to be exceeded which causes issues like throttling and high latency. This was viewed when performing all endpoint tests on cosmos DB.

**Bottleneck 2:**

Another bottleneck identified was the distance calculator which integrates with the external OSRM routing service.
As identified within the CSV files there is a large number off failed requests during high loads of requests sent to do REST service.
The main reasoning behind why this issue occurred was due to the OSRM service being external dependency, lack of strict timeouts, and no isolation of OSRM call.

Potential solution:

- Strict timeouts.
- External failures were isolated to not affect endpoints.
- Error handling was improved to fail fast.

Baseline Testing:

- I will attach all tests graphs and CSV result table which contain each endpoint tests within the testing zip file which is attached to the git repository.
- Doing this testing will act as a baseline (latency, throughput and error rate of failed requests)

**References which helped understand:**

Anon., n.d. OSMR API. [Online]
Available at: https://project-osrm.org/docs/v5.24.0/api/#
Microsoft, 2025. Best practices for Azure Cosmos DB .NET SDK. [Online]
Available at: https://learn.microsoft.com/en-us/azure/cosmos-db/best-practice-dotnet
[Accessed December 2025].
Microsoft, 2025. Performance tips for Azure Cosmos DB and .NET. [Online]
Available at: https://learn.microsoft.com/en-us/azure/cosmos-db/performance-tips-dotnet-sdk-v3?tabs=trace-net-core
[Accessed December 2025].


**Part C commit messages made for the implementations (this is already viewable within the git comments this is just in case)
Bottleneck 1:**

**Technical solution implemented to address issue**

with the JMeter testings it was identified that the search, create, cancel endpoints increased with the number of errors as more requests were submitted ruffle 30% of errors where created and a significant increase in latency can be viewed within the 3rd and 4th run of tests for each endpoint. With this information gained from JMeter table (CSV) and graphs, the bottleneck can be identified from this section of the code:

    // Item
    public static List<Item> searchItems(String search, String location) {
        try (CosmosClient client = newClient()) {
            CosmosContainer container = client.getDatabase(DATABASE).getContainer(ITEMS_CONTAINER);
            StringBuilder sql = new StringBuilder("SELECT * FROM c WHERE 1=1");
            List<SqlParameter> params = new ArrayList<>();

This code causes many issues for the 3 endpoints as it increases CPU and memory usage causing performance degradation, redundant connection pool creation and repeated metadata and routing cache initialisation.

For the implemented fix the following was used:

the reuse of singleton cosmos client and the cache container rather then reinitialised for every request. doing this will reduce total number of errors, reduce latency, increase the throughput and response time is more stable.

Note: This can be visually seen in the newer test file of the implemented fix which shows zero production of errors and significantly reduced latency of the 1000 requests, when compared to the previous test done on the original search feature (original had peaks in 5000s while the new test shows less peaks). This test highlights the significant improvements of the newer solution.

Costs:
Another advantage which is added by this new implementation is the reduced consumption of RU because of the connection setup which lowers RU throttling. which helps address the issue of cost efficiency

- Went from per request to reuse a singleton and container references which = to lower latency a higher throughput and 0 error production.

**Bottleneck 2:**

Implemented both timeout and retry system due to large errors created by test in Jmeter (can be viewed in csv file). doing this reduceses the errors created by the endpoint by rate limit failures.

Note: Please Do check the code too see the fixes.

**Search Function Retest:**

This is retest of the new implementation fix for part c and visually when you look through this test it shows proof that the fix implemented in bottleneck 1 actually works as it fixes many issues (no errors, less ru, quicker as it was completed within 5 mins) all of which where explained in the commit above.


[Retest Search.csv](https://olympus.ntu.ac.uk/user-attachments/files/182/Retest.Search.csv)


**Task D Documentation:**

Do note I have uploaded the following documentation within the git messages of the added java files.

**Update Cosmos.java**

**D.1 Documentation – Security Considerations:**

**Security Issue 1 - Hardcoded Credentials:**

Risk: High

Impact: High, as unauthorised users can have access to databases data and functions

Likelihood: Medium, Credentials can easily be leaked through git hub code or commits.

A significant issue which can be identified within the project is the hardcoded cosmos db credentials stored within Cosmos.jave. This can become a significant issue if the repository is accessible to outside users (unauthorised personnel). These sensitive data can be permanently exposed to the public which can enable:

- Unauthorised access to the cosmos database (can carry out unauthorised read, write, delete functions).
- Uncontrolled RU consumption
- Theft of sensitive data (In the real world if sensitive data like banking credentials were to be stored within the code and a malicious user was able to have access to the sourcecode they could hold these details for ransom to not release the data or could use it for other malicious acts) all of which would affect the owner and customers of the system.

Mitigation:
For this code a method to mitigate this is by removing the hardcoded details from the code, making it so that the user must load the credentials from an environment variable.
Implementing this ensures that sensitive credentials are not hardcoded within source code. Instead, the details of the database can be entered securely within an environment.

This was inspired by the fact that I also mistakenly uploaded credentials to the git repo which could be dangerous if someone had access to the repo, as they could misuse the cosmos db (modify data, excessive ru consumption, hold data for ransom, perform malicous activities with stolen data).

**Create ApiKey.java**

Same issues with security issue 1 with (github)

**Security Issue 2 – No authentication system for REST functions**

Risk: High

Impact: High, as unauthorised users can modify database data using the available functions create and cancel, these functions can be misused to cause a range issues.

Likelihood: High

With the current system any user can use the databases functions (create and cancel) due to this issue it can allow any user to misuse the system this could include the malicious modification of data an attack which violates the integrity principle.
To mitigate this issue to ensure only authorised users have access to the system an API key can be implemented.
Note: API key is a unique passcode used to identify whether the user is a valid user who can have access to the application.
Implementing this ensures that user must create a unique key, if the unique key is not added to the request the request will be rejected by the program.

Mitigation:
Created an API key which authenticates the user to identify whether they should have access to REST endpoint.
This implementation ensures that no unauthorised access to database can occur by requiring the API key.
Reference which inspired Implementation:
https://cdn.zapier.com/storage/learn_ebooks/e06a35cfcf092ec6dd22670383d9fd12.pdf

**Security Issue 3 – DoS Risk:**

Risk: High

Impact: High, can cause issues like the waste of RU

Likelihood: High

With the REST endpoints there where no limits on how many requests a client could send at a time. If a user where to continuously call the services, it could cause many issues for the project this can include:

-          Slower response time or system failure which occurs due to the server threads overload with requests.

-          Large sum of cosmos RU consumption.

-          And the reduction of availability for actual users.

Mitigation:

The new feature will introduce a fixed time window. Each client IP is allowed a maximum number of requests within a set time period. When this limit has gone over, the program will return an error notifying that there are too many requests which where entered.

Why:
Rate limiting prevents request flooding, protects the application from denial of service attack.

Inspiration:

https://www.youtube.com/watch?v=oFo-Tbyy_mo

**proof it works:**

I set the total reuqests of a user to 20 I know it says it only accepted 19 but it works as I had to do 1 test to see if my code worked which is why it stopped at 19 requests.

<img width="940" height="420" alt="image" src="https://olympus.ntu.ac.uk/user-attachments/assets/f72abfd9-301b-49a1-ae7c-0e154f50a079" />
