
-- list procs (and functions?)

SELECT
    routine_schema,
    routine_name
FROM 
    information_schema.routines
WHERE 
    routine_type = 'PROCEDURE';
	
DROP PROCEDURE s2(plan_id int);

CREATE OR REPLACE FUNCTION s2(IN target_plan_id int)  
RETURNS INT
AS $s2_result$
DECLARE
	num_rows int;
BEGIN
	SELECT count(*) INTO num_rows 
	FROM bridge_reference_plan brp
	WHERE brp.plan_id = target_plan_id;
	return num_rows;
END;
$s2_result$
LANGUAGE plpgsql;

-- example call
SELECT  s2(150);

