
-- vid 23 

SELECT round((100 * total_time / sum(total_time)                       
           OVER ())::numeric, 2) percent,                        
           round(total_time::numeric, 2) AS total,                  
           calls,                                                   
           round(mean_time::numeric, 2) AS mean,                    
           substring(query, 1, 200)                                  
 FROM  pg_stat_statements                                               
           ORDER BY total_time DESC                                               
           LIMIT 10;                                                              

-- vid 25

SELECT schemaname, relname,seq_scan, idx_scan,
       cast(idx_scan AS numeric) / (idx_scan + seq_scan)
       AS idx_scan_pct 
FROM pg_stat_user_tables 
       WHERE (idx_scan + seq_scan) > 0 ORDER BY idx_scan_pct;

SELECT relname, seq_tup_read, idx_tup_fetch,
       cast(idx_tup_fetch AS numeric) / (idx_tup_fetch + seq_tup_read) 
       AS idx_tup_pct 
FROM pg_stat_user_tables 
       WHERE (idx_tup_fetch + seq_tup_read) > 0 ORDER BY idx_tup_pct;

-- vid 26
-- detecting missing indexes

SELECT schemaname, relname, seq_scan, seq_tup_read, 
       seq_tup_read / seq_scan AS avg, idx_scan 
FROM   pg_stat_user_tables 
WHERE  seq_scan > 0 
ORDER BY seq_tup_read DESC  
LIMIT  25; 

-- hot updates

SELECT relname,
       cast(n_tup_ins AS numeric) / (n_tup_ins + n_tup_upd + n_tup_del) AS ins_pct,
       cast(n_tup_upd AS numeric) / (n_tup_ins + n_tup_upd + n_tup_del) AS upd_pct,
       cast(n_tup_del AS numeric) / (n_tup_ins + n_tup_upd + n_tup_del) AS del_pct 
FROM pg_stat_user_tables 
       ORDER BY relname;

SELECT relname,n_tup_upd, n_tup_hot_upd,
       cast(n_tup_hot_upd AS numeric) / n_tup_upd AS hot_pct 
FROM pg_stat_user_tables 
       WHERE n_tup_upd>0 ORDER BY hot_pct;

-- useless index

SELECT schemaname, relname, indexrelname, idx_scan,
       pg_size_pretty(pg_relation_size(indexrelid)) AS idx_size
FROM   
	   pg_stat_user_indexes;


SELECT indexrelname,
       cast(idx_tup_read AS numeric) / idx_scan AS avg_tuples,
       idx_scan,idx_tup_read 
FROM pg_stat_user_indexes 
       WHERE idx_scan > 0;

-- vid 30

`explain (analyze, verbose, costs, timing, buffers) ...` 

### Bootcamp

- vid 251
- list index
- `select * from pg_indexes where tablename = 'orders'`

- vid 252
- size of indexes
- `select pg_size_pretty(pg_indexes_size('orders'))`

- vid 253
- count
- `select * from pg_stat_all_indexes order by relname`

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

- vid 269
- query cost model
- costs
- seq_page_cost vs cpu_tuple_cost vs cpu_operator_cost
- formula: 
    relation-size * seq_page_cost +
    num_tuples * cpu_tuple_cost + 
    num_tuples * cpu_operator_cost
- cost is just an estimate
