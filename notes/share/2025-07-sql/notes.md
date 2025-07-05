
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
