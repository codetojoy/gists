
### Resources

* Chris R [book](https://www.amazon.com/Microservices-Patterns-examples-Chris-Richardson/dp/1617294543/ref=sr_1_1)
* SE-Radio w/ Chris R [here](https://www.se-radio.net/2019/06/episode-370-chris-richardson-on-microservice-patterns/)
* SE-Radio w/ James L [here](https://www.se-radio.net/2014/10/episode-213-james-lewis-on-microservices/)
* Conway's Law [here](https://en.wikipedia.org/wiki/Conway%27s_law)
* Transactional Outbox [here](https://microservices.io/patterns/data/transactional-outbox.html)
* Patterns [here](https://microservices.io)
* Martin Fowler talk [here](https://www.youtube.com/watch?v=wgdBVIX9ifA)

### Notes 

### Resources

* Chris Richardson
* Microservices Patterns

### TODO

* listen to podcast and confirm "micro doesn't mean small"
* confirm "not a micro-service but rather micro-service architecture"
* investigate argument that it has been eclipsed by serverless-functions
* strangler fig pattern ??? 
* consider API versus interface 
    * logical seperation
        * problem: monolith
    * run-time seperation
        * problem: distributed monolith
    * reverse Conway: team/process seperation 

### Notes

* definition
    * Adrian Cockcroft of Netflix:
        * SOA of loosely-coupled elements that have bounded contexts
    * Chris R:
        * 3-D scaling cube 
        * `ms` is an architectural style that functionally decomposes
          an application into a set of services 
            * "micro" is not the size of the code-base
            * focused, cohesive set of responsibilities 
            * minimal lead time and minimal collaboration with other teams
        * a form of modularity (run-time, not just build time) 
        * each service has its own DB 
        * `ms` vs SOA
            * SOA has global data model or database
            * SOA has 'smart pipes' like ESB
            * SOA has monolithic deployment 
            * in enterprise, a dozen SOA services vs 100s `ms`
* value proposition: 
    * ultimately: feature throughput is higher

* metaphors
    * train vs semi trucks
    * shipping containers? 
* colour commentary
    * integration pod
    * microservice?
        * yes: deployment flexiblity, scalability, tech-stack independence
    * CRIS, BOB, NASA, CIS, IS 
    * Hype Cycle
* sober second thought
    * no: database autonomy, cloud ready (?) 
    * what is the difference between SoA, modular, etc 
    * micro doesn't mean small 
    * new patterns -> Transactional OutBox Pattern 
* pros:
    * CD is possible
    * maintainable, deployable
    * scalable
        * X and Z scaling 
        * hardware requirements 
    * enables teams to be autonomous
    * technology independence
    * fault isolation 
        * memory leak is contained 
    * ultimately: feature through-put is higher
* cons: 
    * defining scope
    * distributed systems are hard
    * deploying features that span multiple systems is tricky 
    * when to adopt?
    * distibuted monolith 
* reverse Conway 
* "move fast and break things" - Mark Zuckerberg 
    * or not

### ch 1 

* review this rather than re-transcribe 
* themes
    * problems:
        * big ball of mud
        * team dependencies long release-cycle
        * logically modular but not operationally modular 
        * these were initially easy but became difficult
            * dev
                * too complex to understand 
                * dev errors -> testing delays -> missed deadlines
                * localdev is impossible to install/work with day-to-day
                * separate teams with feature branches and impossible merges
                * locked-in to an old tech stack/framework 
                    * hard to innovate
                    * eventually hard to find staff 
            * testing
                * some of it manual
                * takes days 
            * deploying
                * usually once a month/quarter 
                * no hope for CI/CD 
            * scaling 
                * hardware configuration has to be compromised
                * what if multiple read-only facets ? 
                * lack of fault isolation ... esp. if in one process
    * what `ms` offers
        * note that this is not about functional requirements 
        * quality of service requirements: the -ilities
            * maintainability, extensibility, testability, scalibility 

* Food To Go: FTGO
    * order from local restaurants
    * uses Stripe, Twilio, AWS 
    * single Java WAR file (Hello!)
    * monolithic hell
        * long deployment delays
        * intra-team dependencies  
    * Big Ball of Mud 
    * hexagonal architecture with adapters
        * web UI, REST, mobile, Twilio, etc
        * core elements:
            * order management
            * restaurant management
            * delivery management
            * payments, billing
            * notification
    * amazing parallel to work, both MVA and SARA
* there were benefits with monolithic originally, when it was small
* problems grow as the app/complexity grows
    * now, no one understands the app in its entirety 
    * CD is impossible
    * different components have different hardware requirements
    * fragile due to a single point of failure 
    * outdated frameworks, lock-in
    * devs would like to experiment with new languages/platforms 
* book covers:
    * testing
    * refactoring from mono to micro
    * deploying
    * distributed patterns
    * implement queries across services
    * sagas
* this addresses non-functional requirements
    * aka quality of service reqs
    * ilities or quality attributes
* microservice definition
    * not LoC
    * not size of functionality
    * "loosely coupled elements with bounded contexts" - Adam Cockcroft
    * scale cube model
        * x is horizontal scaling
        * y is functional decomposition
        * z is request partitioning/sharding  
    * fascinating that mva is following X-axis, as author predicts
    * author: architectural style that decomposes an app into set of services
        * size of service is unspecified
        * instead, it is a focused set of responsabilities 
* modularity
    * in micro, the service is building block, the module
    * services should have their own database
    * Fig 1.7 is a good diagram of FTGO in micro 
* vs SOA
    * SOA had heavyweight ESB or WS-*
    * SOA had global database
    * SOA had no opinion on monoliths (maybe?) 
* benefits
    * the converse of the drawbacks to monoliths above
    * deploy, test, extend, understand, new tech, points of failure, etc
    * can be scaled along X, Z axis independently
    * hardware can be fine-tailored 
* drawbacks
    * finding right set of services
        * granularity
        * distributed monolith !
    * distributed is complex
        * data consistency (saga)
        * queries (API composition and/or CQRS views) 
    * IDEs don't cover multiple services
    * deployment tools need to be sophisticated
        * e.g. orchestration 
    * when to adopt
        * startups are actually better off with monolith
        * later on, move to micro 
* 1.6.1 no silver bullet
* 1.6.2 pattern language
    * usual Design Pattern stuff 
    * e.g. Strategy pattern
    * OO design patterns vs high-level patterns
* high-level are for microservices 
* three aspects of high-level pattern
    * clearly setting the template for the entire book
* forces
    * concerns/issues 
    * may be in conflict, so priortize 
* resulting context
    * benefits, drawbacks, issues
    * forces handled, not handled, and new forces 
* related patterns 
    * pre, succ, alt, generalization, specialization 
* these relationships form a pattern language
* layers
    * application
        * database querying
    * app / infra
        * security, cross-cutting concerns
    * infrastructure 
        * deployment 
* decomposition
    * by business capability
    * by subdomain (in the sense of DDD)
* communication patterns
    * communication style
    * discovery
    * reliability
    * txn messaging
    * external API 
* also
    * transaction/data patterns
    * deployment patterns 
    * observability patterns
* test patterns
    * consumer-driven contract test
    * consumer-side contract test
    * service component test
* externalized configuration
    * use a cross-cutting pattern for multiple microservices
    * Microservice Chassis
* security
    * authentication handled by API gateway
    * JWT ! chapter 11 
 
* 1.7.1 team organization 
* agile, small teams
* Conway's law  
* CI/CD
* "move fast without breaking things"

* 1.7.3
* human-side of transitions
* rejection, confusion, enthusiasm

### ch 2 

* decomposition strategies
* (a) by business functionality (b) by subdomain
* from DDD: bounded context 
    * eliminate 'deity' classes

* define microservice architecture
* what is a service? size of a service?
* architecture is multi-dimensional view of the -ilities
    * not just realiability, scalability, security
    * testability, deployability, maintainability 
* his definition:
    * set of structures to reason about a system
    * and relationships between them
* 4+1 
    * logical view: code
    * process view: runtime processes
    * impl view: WAR, JAR etc
    * deployment view: networking
    * scenarios: animate the views
* application has:
    * functional requirements (not relevant to arch)
    * quality of service aka quality attributes aka *-ilities
* architectural style
    * palette of elements and relationships that can be used
    * e.g. monolith: impl view is a single executable
    * e.g. micro: set of loosely-coupled services 
    * classic is layered architecture 
        * various drawbacks
    * alt is hexagonal architecture 
        * fig 2.2 is great
        * key is that business logic is at center  
        * ports (interfaces) with inbound/outbound adapters
* more on monolith and microservice style
* very similar figures etc to chapter 1 (!) 
* service: standalone, independently deployable component
    * service API encapsulates implementation
    * potentially its own stack
    * typically a hexagonal architecture 
    * database tables are like private fields in a class
    * DB operations have new problems though 
    * don't share libraries
        * e.g. instead of a common Order object, have an Order service
        * unless there is no business change, like a Money object 
        * the goal is to reduce the need to deploy A because of changes to B
* size of service
    * micro means size of team or functionality 

* 2.2
* define a microservice arch
    * 1. identify system operations (functional requirements)
    * 2. identify services
        * business functionality vs DDD subdomain
    * 3. defince service APIs and collaborations 
* obstacles to decomposition
    * network latency
    * sync communication reduces availability (self-contained services?) 
    * data consistency (saga)
    * deity classes (DDD techniques) 

* 2.2.1 - identify system operations
* a. define high-level objects (UML approach)
* b. define operations as behaviour in terms of domain model
* (a) is nouns; (b) is verbs
* alt. to (b) is event storming technique
* note: each service will have a domain model 
* user stories written in Gherkin style !!
* result: Consumer, Order, Restaurant, Courier, DeliveryInfo, Address, Location
* interesting that he has 'Info' objects 
* two types of sys-ops: commands, queries
* command spec has input, pre-conditions, post-conditions

* 2.2.2 - define services (business capability pattern)
* business capability: something the business does to generate value
* domain functions
* decoupled from implementation: e.g. deposit for banks
* each b-cap is a service, but business-oriented and not technical
    * this might be the right way to think about VAC
* e.g. order managemnt, courier availability, consumer accounting, etc
* map b-caps to services
    * appears to be a service per noun/domain model (!)

* 2.2.3 - define services (via sub-domain pattern)
* DDD by Eric Evans
* two concepts: subdomains and bounded context
* also: universal language 
* idea: don't have a universal domain
* the domain is the application space, which is partitioned into neighbourhoods
* have a subdomain model, which is roughly the same as a b-cap
* bounded context seems perfect for a microservice 'space'
* the subdomains eliminate deity classes and make decomposition easier

* 2.2.4 decompsition guidelines from OO design 
* Uncle Bob's book
* SRP and CCP
* SRP: a class should have only one reason to change 
* CCP: a change that affects the package should affect everything in the pkg
* author applies this to services
* SRP is obvious. CCP mitigates the "distributed monolith" anti-pattern

* 2.2.5 obstacles
* network latency
* reduced availability due to sync comm
* maintaining data consistency across services
* obtaining a consistent view of data
* deity classes
* most of the above are already covered in chapter 1
* halt at "G-d classes prevent decomposition" in this section

### podcast: James Lewis

* SE Radio 
* https://www.se-radio.net/2014/10/episode-213-james-lewis-on-microservices/

*  

### podcast: Chris Richardson 

* SE Radio 
* https://www.se-radio.net/2019/06/episode-370-chris-richardson-on-microservice-patterns/ 

* database fields are now like private fields in a class   
    * encapsulation 
* the damn saga pattern again
    * txns that span services/databases 
    * ACID with 2-phase commit/XA is out of fashion 
        * 2-phase commit is synch comm 
    * e.g. to create an order, reduce customer's available credit  
        * two actions, one per service
        * create an order in pending state
        * send message to customer service to reserve credit
        * send message to order service, which continues 
        * break it down into local steps 
        * "eventually consistent" model for transactions 
    * note:
        * within each service, there are 2 operations: update DB and send message
        * these operations have to be atomic
        * near 26m00s this introduces the Transactional Outbox Pattern 
* API Gateway 
    * external API
    * similar to Facade pattern in OODB
    * e.g. there could be 100s of services and not all public 
    * (1) reverse proxy 
    * (2) edge functions such as security 
    * (3) API composition: status of an order may be distributed  
        * arguably a JOIN in the legacy database 
    * consistency
        * monolith: simple and transactional
        * `ms`: multiple transactions, possibly inconsistent
            * may have to re-design service boundaries 
        * monolith: SQL query planner is sophisticated
            * host: are we taking a step backwards?
            * CR: moving referential integrity from DB to app  
            * CR: hopefully benefits outweigh the drawbacks 


    










 

