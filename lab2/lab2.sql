USE labor_sql;

-- task 1
SELECT * 
FROM outcome_o
WHERE outcome_o.out > 2000
ORDER BY date DESC;

-- task 2
SELECT name 
FROM Ships
WHERE name LIKE '%e%e%' and name NOT LIKE '%e%e%e%';

-- task 3
SELECT maker, type, speed, hd
FROM product join laptop ON product.model = laptop.model
WHERE type = 'Laptop' AND hd >= 10;

-- task 4
SELECT DISTINCT maker
FROM product
WHERE type = 'PC' and maker NOT IN (SELECT maker FROM product WHERE type = 'laptop');

-- task 5
SELECT DISTINCT maker
FROM product
WHERE model IN (SELECT model FROM laptop WHERE speed <= 500);

-- task 6
SELECT ship, battle,
	CASE result
		WHEN result = 'damaged' THEN 'пошкоджений'
        WHEN result = 'sunk' THEN 'затонув'
        WHEN result = 'OK' THEN 'ОК'
	END AS result
FROM Outcomes;

-- task 7
SELECT model, price
FROM printer
WHERE price IN (SELECT MAX(price) FROM printer);

-- task 8
SELECT @maker:= maker, (SELECT MIN(price) 
						FROM pc JOIN product ON pc.model = product.model 
                        WHERE maker = @maker)
FROM product
GROUP BY maker;

-- task 9 
SELECT name, numGuns, bore, displacement, type, country, launched, ships.class
FROM ships JOIN classes ON ships.class = classes.class
WHERE CASE WHEN numGuns=12 THEN 1 ELSE 0 END +
CASE WHEN bore=16 THEN 1 ELSE 0 END +
CASE WHEN displacement=46000 THEN 1 ELSE 0 END + 
CASE WHEN type='bc' THEN 1 ELSE 0 END +
CASE WHEN country='Gt.Britain' THEN 1 ELSE 0 END +
CASE WHEN launched=1941 THEN 1 ELSE 0 END +
CASE WHEN ships.class='North Carolina' THEN 1 ELSE 0 END >= 4;

-- task 10
SELECT @class := class class, (SELECT COUNT(*) FROM ships WHERE class = @class) num
FROM ships
GROUP BY 1
ORDER BY 1;











