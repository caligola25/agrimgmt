-- Factory Db query

-- Connect to the DB
\c agrimgmt

-- Find workers to which it’s possible to assign new operations order by the number of already assigned operations
SELECT employee_id AS ID, employee_name AS Name, employee_surname AS Surname, operations AS assigned_operations
    FROM Factory.Employee AS Employee
    WHERE Employee.operations < 10
    ORDER BY Employee.operations ASC, Employee.employee_surname ASC, Employee.employee_name ASC;

-- Compute the total income of the month
SELECT SUM(P.price) AS total_income_of_november
    FROM Factory.Product_order AS P
    WHERE (P.product_order_date >= '2020-11-1') AND (P.product_order_date <= '2020-11-30') AND (P.order_status = 'paid' OR P.order_status = 'shipped');

-- Compute the total outcome of the month
SELECT SUM(outcome) AS total_outcome_of_november
    FROM(
        SELECT SUM(Factory.Employee.salary) AS outcome
            FROM Factory.Employee
        UNION
        SELECT SUM(Factory.Material_order.price) AS outcome
            FROM Factory.Material_order
            WHERE (material_order_date >= '2020-11-1') AND (material_order_date <= '2020-11-30')
        UNION
        SELECT SUM(Factory.Fixed_cost.price) AS outcome
            FROM Factory.Fixed_cost
            WHERE (fixed_cost_date >= '2020-11-1') AND (fixed_cost_date <= '2020-11-30')
    ) AS outcome;

-- Find all the order of a customer
SELECT product_order_id, product_order_date, price, order_status
    FROM Factory.Product_order
    WHERE customer_id = (SELECT C.customer_id FROM Factory.Customer AS C WHERE C.customer_name = 'M&C Agraria')
    ORDER BY product_order_date DESC;

-- Compare estimated and actual time for the production of an item
SELECT sequence_number AS n, PR.process_name AS process_name, estimated_time, actual_time
    FROM (
        Factory.Item AS I
        INNER JOIN Factory.Production_phase AS PP
        ON I.item_id = PP.item_id
        INNER JOIN Factory.Process AS PR
        ON PR.process_id = PP.process_id
    )
    WHERE I.item_id = '956d07a9-97ee-4878-87e7-cb1cee7a2d16'
    ORDER BY sequence_number ASC;

-- Retrieve the number of products sold grouped by type
SELECT product_name, COUNT(*)
    FROM Factory.Product_order AS PO
        INNER JOIN Factory.Item AS I
        ON PO.product_order_id = I.product_order_id
        INNER JOIN Factory.Product AS P
        ON I.product_id = P.product_id
    WHERE PO.order_status = 'paid' OR PO.order_status = 'shipped'
    GROUP BY product_name;

-- Retrieve raw materials stored in the warehouse
SELECT MAT_U.material_id, material_name, SUM(mat_bought) - SUM(mat_used) AS quantity
    FROM (
        SELECT material_id, SUM(quantity) AS mat_used
            FROM Factory.Production_phase AS PP
            INNER JOIN Factory.Process AS PR
            ON PP.process_id = PR.process_id
        GROUP BY material_id
    ) AS MAT_U
    INNER JOIN (
        SELECT RM.material_id, material_name, SUM(S.quantity) AS mat_bought
            FROM Factory.Supplying AS S
            INNER JOIN Factory.Raw_Material AS RM
            ON S.material_id = RM.material_id
            GROUP BY RM.material_id
    ) AS MAT_B
    ON MAT_U.material_id = MAT_B.material_id
    GROUP BY MAT_U.material_id, material_name;

-- Find where have been shipped sold items
SELECT product_name, country, city
    FROM (
        Factory.Product AS PD
        INNER JOIN Factory.Item AS I
        ON PD.product_id = I.product_id
        INNER JOIN Factory.Product_order AS PO
        ON I.product_order_id = PO.product_order_id
        INNER JOIN Factory.Customer AS C
        ON PO.customer_id = C.customer_id
    )
    WHERE order_status = 'paid' OR order_status = 'shipped'
    ORDER BY product_name ASC, country ASC, city ASC;

--Find which employee worked in which process with what material for the production of a product sold to customers

--Subquery to retrieve which employee worked on which process of what product using what materials
SELECT employee_surname, employee_name, process_name, item_id, sequence_number, material_name
    FROM (
        Factory.Employee AS E
        INNER JOIN Factory.Production_phase AS PP
        ON E.employee_id = PP.employee_id
        INNER JOIN Factory.Process AS PR
        ON PP.process_id = PR.process_id
        INNER JOIN Factory.Product AS PD
        ON PR.product_id = PD.product_id
        INNER JOIN Factory.Raw_Material AS RM
        ON PR.material_id = RM.material_id
    )
    WHERE PP.phase_status = 'completed'
    ORDER BY employee_surname ASC, employee_name ASC, item_id ASC, sequence_number ASC, process_name ASC;

--Subquery to retrieve which product has been sold to which customer
SELECT product_name, item_id, customer_name
    FROM (
        Factory.Product AS PD
        INNER JOIN Factory.Item AS I
        ON PD.product_id = I.product_id
        INNER JOIN Factory.Product_order AS PO
        ON I.product_order_id = PO.product_order_id
        INNER JOIN Factory.Customer AS C
        ON PO.customer_id = C.customer_id
    )
    WHERE order_status = 'paid' OR order_status = 'shipped';

--Complete query
SELECT 
    employee_surname AS e_surname, 
    employee_name AS e_name, 
    product_name,
    process_name, 
    sequence_number AS seq_n,
    material_name, 
    customer_name
    FROM(
        SELECT employee_surname, employee_name, process_name, sequence_number, PP.item_id, material_name
            FROM
                Factory.Employee AS E
                INNER JOIN Factory.Production_phase AS PP
                ON E.employee_id = PP.employee_id
                INNER JOIN Factory.Process AS PR
                ON PP.process_id = PR.process_id
                INNER JOIN Factory.Product AS PD
                ON PR.product_id = PD.product_id
                INNER JOIN Factory.Raw_Material AS RM
                ON PR.material_id = RM.material_id
                WHERE PP.phase_status = 'completed'
    ) AS EP
    INNER JOIN (
        SELECT product_name, I.item_id, customer_name
            FROM
                Factory.Product AS PD
                INNER JOIN Factory.Item AS I
                ON PD.product_id = I.product_id
                INNER JOIN Factory.Product_order AS PO
                ON I.product_order_id = PO.product_order_id
                INNER JOIN Factory.Customer AS C
                ON PO.customer_id = C.customer_id
            WHERE order_status = 'paid' OR order_status = 'shipped'
    ) AS PC
    ON EP.item_id = PC.item_id
ORDER BY employee_surname ASC, employee_name ASC, product_name ASC, PC.item_id ASC, sequence_number ASC;
