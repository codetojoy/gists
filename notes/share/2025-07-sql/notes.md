
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
