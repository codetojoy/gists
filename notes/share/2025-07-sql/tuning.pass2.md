
### Notes

* concerns are vacuum, indexes, table bloat, index bloat
* not sure how i will do this but see main file for notes
    - maybe log new stuff or surprises, not what i have grokked

- vid 8
- vacuum full
    - risk: index bloat

- vid 9
- update creates new rows  
- really good demo
- no perf tuning without knowledge of storage

- vid 10
- finding bloated tables
- index bloat, excessive dead tuples

- vid 11
- balance
- default vac values are conservative
    - small installs such as Raspberry-Pi
    - a long time ago
    - what are defaults in RDS ? 

- vid 12
- common vac problems
    - running too often
    - num DBs in cluster
    - too disruptive
    - OR can't keep up 

- vid 13
- indexes
- basic demo
- `analyze;` and `/timing` commands
- MEGA: writing to table will be slower for index maintenance
- compare size of table to index

- vid 14
- index cardinality
- poor: gender
- boolean ? 
- example with two names
- selectivity
    - using small dataset vs prod is a problem
    - pg can be finicky about when it uses an index

- vid 15
- bitmap scan

- vid 16
- index-only scans are supreme
- e.g. CREATE INDEX idx_xy ON tab(x) INCLUDE y;

- vid 17
- FKs
- create index on child table 
    - e.g. orders -> items (parent -> child)
    - e.g. CREATE INDEX idx_ord on items(order_no);

- vid 18
- partial index
- e.g. done vs open
- MEGA: maybe this would work for deleted??
    - this is counter to the point about selectivity
- reduce space consumption
- repeated point on INSERT/UPDATE
- CREATE INDEX i ON t WHERE foo NOT IN ('a','b')

- vid 19
- clustered tables
- correlation
- c: 1 means values are in order

- vid 20
- CLUSTER command
- store date in the same order as the index

- vid 21
- fill factor
- how much of a page is used

- vid 22
- combined index vs independent
- CREATE INDEX i ON T (abc, def, ijk)
- can take more space
- using N indexes
    - likely bitmap scan
    - updating can be slower

### section 5 stats

- vid 23
- pg_stat_statements
- in 8.x
- requires installation in each database 
    - modifies conf file 
- pgbench: create clients
    - MEGA
- stddev_time
    - stable or flucuating runtime
    - unstable: not cached in memory
    - shared_blks_hit vs shared_blks_read
        - hit is cache
        - read is disk
- OLTP shouldn't have `temp_blks_x` values

- vid 24
- top-10 time

- vid 25
- table stats

- vid 26
- missing indexes

- vid 27
- hot updates
- `n_top_hot_upd`
- hot update: keeps rows in the same page 

- vid 28
- useless indexes

### sec 6 query problems

- vid 29
- explain vs explain analyze
- nodes
    - lower level: seq scan, index scan
    - higher: order, limit
- basics
    - read plan from inside to outside 
    - look at 'actual time' blocks and look for jumps

- vid 30 
- explain verbose

- vid 31
- costs
- various values

- vid 32
- spotting query problems
- Q1: is runtime justified?
    - check estimates
    - bad plans are possible 
- Q2: where does time jump ? 

### sec 7 query fixes

-- vid 33
-- JOIN
-- common problems
    - semantic mismatch 
    - see example

-- vid 34
- forcing JOIN order
- `join_collapse_limit`
- reduce planning time?