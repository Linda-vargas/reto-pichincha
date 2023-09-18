create table `monedas` (
  id integer not null auto_increment,
  codigo varchar(255) not null,
  nombre varchar(255) not null,
  primary key (id)
);

CREATE TABLE `tipo_cambio` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `moneda_origen_codigo` VARCHAR(255) NOT NULL,
  `moneda_destino_codigo` VARCHAR(255) NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(10) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `roles` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `registro_eventos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `id_tipo_cambio` INT NOT NULL,
  `tipo` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`)
);




INSERT INTO `monedas` (id, codigo, nombre) VALUES (1, 'USD', 'Dólar');
INSERT INTO `monedas` (id, codigo, nombre) VALUES (2, 'EUR', 'Euro');
INSERT INTO `monedas` (id, codigo, nombre) VALUES (3, 'JPY', 'Yen Japonés');
INSERT INTO `monedas` (id, codigo, nombre) VALUES (4, 'PEN', 'Soles');

/*INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (1,1, 2,6.33);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (2,2, 1,0.33);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (3,1, 3,3.43);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (4,3, 1,6.33);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (5,1, 4,0.33);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (6,4, 1,3.43);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (7,2, 3,6.33);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (8,2, 4,0.33);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (9,3, 2,3.43);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (10,4, 2,6.33);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (11,3, 4,0.33);
INSERT INTO `tipo_cambio` (id,moneda_origen_codigo, moneda_destino_codigo, valor) VALUES (12,4, 3,3.43);
*/