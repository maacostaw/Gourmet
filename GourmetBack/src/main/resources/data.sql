INSERT INTO RECETARIOS (id, imagen, nombre) VALUES
	(1,'http://localhost:3000/images/Recetarios/Recetario de Pastas.jpg','Recetario de Pastas'),
	(2,'http://localhost:3000/images/Recetarios/Recetario de Malteadas.jpg','Recetario de Malteadas');
	
INSERT INTO RECETAS (id, imagen, nombre) VALUES
	(1,'http://localhost:3000/images/Recetas/Pasta con carne.jpg','Pasta con carne'),
	(2,'http://localhost:3000/images/Recetas/Pasta con arroz y chile.jpg','Pasta con arroz y chile'),
	(3,'http://localhost:3000/images/Recetas/Pasta con Queso.jpg','Pasta con Queso'),
	(4,'http://localhost:3000/images/Recetas/Malteada de Fresa.jpg','Malteada de Fresa'),
	(5,'http://localhost:3000/images/Recetas/Malteada de Vainilla.jpg','Malteada de Vainilla'),
	(6,'http://localhost:3000/images/Recetas/Malteada de Chocolate.jpg','Malteada de Chocolate');
	
INSERT INTO RECETARIOS_RECETAS (recetario_id, recetas_id) VALUES
	--Recetas de pasta al recetario de pastas
	(1,1),
	(1,2),
	(1,3),
	--Se añaden las malteadas al recetario de malteadas
	(2,4),
	(2,5),
	(2,6);
	
INSERT INTO ALIMENTOS  (id,nombre, calorias, medida_de_referencia, grupo_alimenticio, unidad_de_medida) VALUES 
	(1,'Pasta',50,100,'cereales','gr'),
	(2,'Carne',143,100,'carnes','gr'),
	(3,'Arroz',130,100,'cereales','gr'),
	(4,'Chile',100,1,'frutas','unidad'),
	(5,'Queso',402,100,'lácteos','gr'),
	(6,'Salsa de tomate',500,100,'vegetales','gr'),
	(7,'Leche',42,100,'lácteos','gr'),
	(8,'Fresa',4,1,'frutas','unidad'),
	(9,'Vainilla',288,100,'vegetales','gr'),
	(10,'Chocolate',546,100,'vegetales','gr'),
	(11,'Crema Chantilly',275,100,'vegetales','gr');

INSERT INTO INGREDIENTE_ABSTRACT (id, alimento_id) VALUES
	--Pasta y carne
	(1,1),
	(2,2),
	--Pasta carne y chile
	(3,1),
	(4,3),
	(5,4),
	--Pasta queso y salsa de tomate
	(6,1),
	(7,5),
	(8,6),
	--Leche, fresas y chantilly
	(9,7),
	(10,8),
	(11,11),
	--Leche, vainilla y chantilly
	(12,7),
	(13,9),
	(14,11),
	--Leche, chocolate y chantilly
	(15,7),
	(16,10),
	(17,11);

INSERT INTO INGREDIENTE_NECESARIO (id, medida, unidad_de_medida) VALUES
	--Pasta y carne
	(1,100,'gr'),
	(2,50,'gr'),
	--Pasta carne y chile
	(3,150,'gr'),
	(4,100,'gr'),
	(5,1,'unidad'),
	--Pasta y queso
	(6,200,'gr'),
	(7,30,'gr'),
	--Leche y fresas
	(9,250,'gr'),
	(10,15,'unidad'),
	--Leche y Vainilla
	(12,250,'gr'),
	(13,200,'gr'),
	--Leche y chocolate
	(15,250,'gr'),
	(16,200,'gr');

INSERT INTO INGREDIENTE_OPCIONAL (id) VALUES
	--Salsa de tomate
	(8),
	--Crema chantilly x3
	(11),
	(14),
	(17);

INSERT INTO RECETAS_INGREDIENTES (receta_id,ingredientes_id) VALUES
	--Pasta con carne
	(1,1),
	(1,2),
	--Pasta carne y chile
	(2,3),
	(2,4),
	(2,5),
	--Pasta y queso
	(3,6),
	(3,7),
	(3,8),
	--Malteada de fresa
	(4,9),
	(4,10),
	(4,11),
	--Malteada de vainilla
	(5,12),
	(5,13),
	(5,14),
	--Malteada de chocolate
	(6,15),
	(6,16),
	(6,17);

