drop table if exists temp_brp;

create temporary table temp_brp (
	reference_id bigint,
	plan_id bigint
);

set session vars.plan_id = 150;

-- insert a
-- todo: qualify by plan_id
INSERT INTO temp_brp
(reference_id, plan_id)
SELECT reference_id, plan_id FROM bridge_reference_plan;

-- insert b
INSERT INTO temp_brp
(reference_id, plan_id)
SELECT r.id, current_setting('vars.plan_id')::int FROM customer c
JOIN bridge_customer_reference brc on brc.customer_id = c.id
JOIN reference r on r.id = brc.reference_id;


INSERT INTO bridge_reference_plan (reference_id, plan_id) 
     SELECT DISTINCT reference_id, plan_id
     FROM temp_brp
     WHERE NOT EXISTS (
         SELECT 'X' 
         FROM bridge_reference_plan brp
         WHERE 
             temp_brp.reference_id = brp.reference_id
             AND temp_brp.plan_id = brp.plan_id
     );
	 
select * from bridge_reference_plan;
select count(*) from bridge_reference_plan;
select count(*) from bridge_reference_plan brp where brp.plan_id = 150;
select count(*) from customer;
select count(*) from reference;
select count(*) from bridge_customer_reference bcr where bcr.customer_id = 301;
select count(*), reference_id, plan_id from temp_brp
group by (reference_id, plan_id)
order by count(*) desc;

rollback;
