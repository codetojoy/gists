
### Notes

* concerns are vacuum, indexes, table bloat, index bloat

### section 23 

- vid 248
- index: structured relation
- point to the page where tuple is stored 
- updating index impacts CUD from CRUD

- vid 249
- create index
- too many indexes is a problem
- one index can handle 32 columns
    - put 'most selective' first col first
- two types
    - index on col values
    - unique col values
- using (type), sorted, etc
- basic example
- default type: btree

- vid 250
- create unique indexes
- for multiple cols: values cannot be repeated 
    - duplicate key value error !!!

- vid 251
- list num 

- vid 252
- size of indexes for table T

- vid 253
- list counts

- vid 254
- drop index
- concurrently

- vid 255
- execution process
- declarative
- motivation for query optimizer/planner
- balance planning vs execution time

- vid 256
- execution stages 
- parser
    - handle text of statement
- rewriter
    - re-arrange
- optimizer
    - fastest path
- executor
    - implement path
    - use storage

- vid 257
- query optimizer
- 'cost' is crucial 
- 9.6+ has parallel: threads
- nodes
    - represents an action/worker
    - different types

- vid 258
- node types
    - operations, access methods
    - stackable: parent/child
- also: read from inside and go out
    - p 
        - ch1 
            - ch2
        - output of ch2 is input for ch1
- types
    - seq, index, index only, bitmap
    - nested loop, hash join, merge join
    - gather, merge parallel
- main point is lowest cost

- vid 259
- node: seq scan
- when no alternatives
    - even when filtering clauses have little effect
- expensive
- to be avoided 

- vid 260
- index scan
- index -> get tuples -> get data
- index only -> get values from index file
- bitmap -> build bitmap in memory 

- vid 261
- join nodes
- types
    - hash join
        - build hash table from inner table
    - merge join
        - join two children already sorted by join key
    - nested loop
        - for each row in outer, scan inner table
        - index on inner can help
        - seems like last resort 
- Q which is inner and which is outer ? 

- vid 262
- B-tree index
- default and common
- operators: <, >=, IN, BETWEEN, IS NULL, LIKE
- con: copies the whole column

- vid 263
- hash index
- equality `=` operator
- larger than B-tree

- vid 264
- BRIN index
- block range index
- data block to min/max value
- smaller than B-tree
- useful for linear sort order 

- vid 265
- GIN index
- generalized inverted index
- full-text search
- point to multiple tuples
- multiple values stored in cols at same time

- vid 266
- explain
- execution nodes have cost/time etc
    - e.g. `Seq Scan on foobar (cost=s..f rows=n width=w)
    - cost startup..final
        - cost will increase working from inner to outer
        - final cost is outer parent
    - rows estimated N rows 
    - width avg # of bits (w)
        - rows, width is network traffic

- vid 267
- explain output
- skip

- vid 268
- explain analyze
- skip

- vid 269
- query cost model
- costs
- seq_page_cost vs cpu_tuple_cost vs cpu_operator_cost
- formula: 
    relation-size * seq_page_cost +
    num_tuples * cpu_tuple_cost + 
    num_tuples * cpu_operator_cost
- cost is just an estimate

- vid 270
- skip 

- vid 271

- vid 272
- multiple indexes on a single query 
- e.g. `SELECT * FROM T WHERE id = 10 or id = 20`
- uses index twice
- bitmap index
    - scan index and build blocks (or pages)

- vid 276
- partial index
- improve perf of query with smaller index size
- e.g. is_active = 'N' or name IN ('Alice','Bob')

- vid 277
- expression index
- expensive for INSERT/UPDATE 

### sec 24 : views

- vid 281
- intro to view
- shortcut
- database object is 'stored query'
- normal views are dynamic
- materialized views store data

[SKIP]

- vid 287
- multiple tables

- vid 296
- materialized view
- store physically and update periodically
- query is run once and then stored
- simple view is fresh but query runs often

- vid 302
- why not a table vs materialized view
- proc: refresh m-view without locking

- vid 303
- cons of m-views
- dependencies on base tables
- changes to tables must account for m-view