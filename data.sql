insert into clima values(1,'Sequia');
insert into clima values(2,'Lluvia');
insert into clima values(3,'Condición optima');
insert into clima values(4,'Indefinido');

CREATE VIEW consolidado
AS
SELECT  max(periodo) total_periodos, c.nombre 
FROM prediccion p
INNER JOIN clima c ON c.id_clima=p.clima_id_clima
GROUP BY p.clima_id_clima