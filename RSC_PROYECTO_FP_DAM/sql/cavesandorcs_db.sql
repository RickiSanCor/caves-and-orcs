-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.38-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para cavesandorcs_db
CREATE DATABASE IF NOT EXISTS `cavesandorcs_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cavesandorcs_db`;

-- Volcando estructura para tabla cavesandorcs_db.atributos
CREATE TABLE IF NOT EXISTS `atributos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agilidad` int(11) NOT NULL,
  `fuerza` int(11) NOT NULL,
  `labia` int(11) NOT NULL,
  `magia` int(11) NOT NULL,
  `vigor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla cavesandorcs_db.atributos: 10 rows
DELETE FROM `atributos`;
/*!40000 ALTER TABLE `atributos` DISABLE KEYS */;
INSERT INTO `atributos` (`id`, `agilidad`, `fuerza`, `labia`, `magia`, `vigor`) VALUES
	(1, 3, 4, 0, 0, 4),
	(2, 3, 4, 0, 0, 4),
	(3, 4, 6, 0, 0, 6),
	(4, 2, 1, 2, 6, 4),
	(5, 7, 3, 0, 1, 5),
	(6, 3, 0, 2, 14, 1),
	(7, 2, 5, 0, 0, 13),
	(8, 5, 0, 0, 9, 6),
	(9, 8, 1, 2, 5, 4),
	(10, 5, 7, 8, 0, 5);
/*!40000 ALTER TABLE `atributos` ENABLE KEYS */;

-- Volcando estructura para tabla cavesandorcs_db.clase
CREATE TABLE IF NOT EXISTS `clase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agilidad` int(11) NOT NULL,
  `fuerza` int(11) NOT NULL,
  `labia` int(11) NOT NULL,
  `magia` int(11) NOT NULL,
  `nombreClase` varchar(255) DEFAULT NULL,
  `vigor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla cavesandorcs_db.clase: 8 rows
DELETE FROM `clase`;
/*!40000 ALTER TABLE `clase` DISABLE KEYS */;
INSERT INTO `clase` (`id`, `agilidad`, `fuerza`, `labia`, `magia`, `nombreClase`, `vigor`) VALUES
	(1, 3, 3, 0, 0, 'soldado', 4),
	(2, 2, 3, 0, 0, 'nomada', 5),
	(3, 2, 3, 0, 2, 'cazador', 3),
	(4, 5, 1, 2, 0, 'ladron', 2),
	(5, 3, 0, 5, 0, 'juglar', 2),
	(6, 2, 0, 2, 5, 'mago', 1),
	(7, 2, 0, 3, 4, 'brujo', 1),
	(8, 2, 1, 1, 3, 'ermitanio', 3);
/*!40000 ALTER TABLE `clase` ENABLE KEYS */;

-- Volcando estructura para tabla cavesandorcs_db.genero
CREATE TABLE IF NOT EXISTS `genero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombreGenero` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla cavesandorcs_db.genero: 2 rows
DELETE FROM `genero`;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` (`id`, `nombreGenero`) VALUES
	(1, 'masculino'),
	(2, 'femenino');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;

-- Volcando estructura para tabla cavesandorcs_db.personaje
CREATE TABLE IF NOT EXISTS `personaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `derrotas` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `puntos` int(11) NOT NULL,
  `salud` int(11) NOT NULL,
  `victorias` int(11) NOT NULL,
  `atributos_id` int(11) NOT NULL,
  `clase_id` int(11) NOT NULL,
  `genero_id` int(11) NOT NULL,
  `raza_id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8w8mcvt7m6alr309e229q7pqe` (`atributos_id`),
  KEY `FKk04eucpnk2g33h6b5urv1thlm` (`clase_id`),
  KEY `FKcnlon4vhm4yd62whgwy5mxwxm` (`genero_id`),
  KEY `FK6dqu2erjb4txm7dipfywvypgq` (`raza_id`),
  KEY `FK6pxf3gsrqg97o4skfptvklc55` (`usuario_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla cavesandorcs_db.personaje: 10 rows
DELETE FROM `personaje`;
/*!40000 ALTER TABLE `personaje` DISABLE KEYS */;
INSERT INTO `personaje` (`id`, `derrotas`, `nombre`, `puntos`, `salud`, `victorias`, `atributos_id`, `clase_id`, `genero_id`, `raza_id`, `usuario_id`) VALUES
	(1, 0, 'orco1', 0, 11, 0, 1, 1, 1, 1, NULL),
	(2, 0, 'orco2', 0, 11, 0, 2, 1, 1, 1, NULL),
	(3, 0, 'jefeOrco', 0, 15, 0, 3, 1, 1, 1, NULL),
	(4, 2, 'Goku', -2, 11, 0, 4, 8, 1, 1, 2),
	(5, 1, 'Leia', -1, 13, 0, 5, 1, 2, 2, 2),
	(6, 0, 'Morgana', 0, 5, 0, 6, 6, 2, 5, 2),
	(7, 2, 'Albatros', -2, 29, 0, 7, 2, 1, 3, 2),
	(8, 1, 'Elminster', 0, 15, 1, 8, 8, 1, 5, 2),
	(9, 0, 'Aerie', 0, 11, 0, 9, 4, 2, 2, 2),
	(10, 0, 'Chayanne', 1, 13, 1, 10, 5, 1, 4, 2);
/*!40000 ALTER TABLE `personaje` ENABLE KEYS */;

-- Volcando estructura para tabla cavesandorcs_db.raza
CREATE TABLE IF NOT EXISTS `raza` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agilidad` int(11) NOT NULL,
  `fuerza` int(11) NOT NULL,
  `labia` int(11) NOT NULL,
  `magia` int(11) NOT NULL,
  `nombreRaza` varchar(255) DEFAULT NULL,
  `vigor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla cavesandorcs_db.raza: 5 rows
DELETE FROM `raza`;
/*!40000 ALTER TABLE `raza` DISABLE KEYS */;
INSERT INTO `raza` (`id`, `agilidad`, `fuerza`, `labia`, `magia`, `nombreRaza`, `vigor`) VALUES
	(1, 1, 1, 2, 0, 'humano', 1),
	(2, 3, 0, 0, 2, 'elfo', 0),
	(3, 0, 2, 0, 0, 'enano', 3),
	(4, 2, 0, 3, 0, 'mediano', 0),
	(5, 1, 0, 0, 4, 'gnomo', 0);
/*!40000 ALTER TABLE `raza` ENABLE KEYS */;

-- Volcando estructura para tabla cavesandorcs_db.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `idioma` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla cavesandorcs_db.usuario: 10 rows
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `apellidos`, `email`, `idioma`, `nombre`, `password`, `rol`, `username`) VALUES
	(1, 'Admin', 'admin@cavesandorcs.com', 'es', 'Admin', 'tCK/yDV7YFLMpZz6CVYMsp3o21vDBxtmDt83icld2GHIsUIrdL1JEyHNhPqi0Gi5', 'Admin', 'admin'),
	(2, 'González', 'pepe@cavesandorcs.com', 'es', 'Pepe', 'sWqUJ26Xn3JWsv2VU2NHAtT1J57ltqzFW0XwX59KSv8yCnOcMffJAqR0MEfyavuX', 'Normal', 'pepe'),
	(3, 'Pérez', 'maria@cavesandorcs.com', 'es', 'María', 'bkUnYVHMrZlDSqG6uPhciWkziVpSM8uM4sYmmf57l1OmQjrqJLAcc8ALGoU7bqSU', 'Normal', 'mariaLaJefa'),
	(4, 'Álvarez', 'lucia@cavesandorcs.com', 'es', 'Lucía', 'bzS3CGlAbuuo8jYyJMeQ95wS7ihJwIJBKTRbCzV5jpojZJThbMfvH+411ghFexXB', 'Normal', 'luciaSoyYo'),
	(5, 'Ruíz', 'alberto@cavesandorcs.com', 'es', 'Alberto', 'jLmo/amB+jz7jdarv/LPfiwguPkpd15roGqLUNasPh6EL+qhVo7uRGVW3jvG89fR', 'Normal', 'alberto231'),
	(6, 'Sanz', 'miguel@cavesandorcs.com', 'es', 'Miguel', 'tihdxm3CyeuOxEgJnA6LvDf5GU4ulOwqQHyylKX3eZDWr1uXD2CSBRtj9VD3Jpn3', 'Normal', 'miguelon'),
	(7, 'Álvarez', 'felipe@cavesandorcs.com', 'es', 'Felipe', 'iXX+QL4PvoFS1js1bd5XaxyFImGbk1zsljf5Pax8k/KZx1kXAkF741M8/fvA3XBb', 'Normal', 'phiilip'),
	(8, 'Admin2', 'admin2@cavesandorcs.com', 'es', 'Admin2', '9PkeN+zcukb9TSYHfN8Xouw5S831z97OVoAo9MlhD+yHaEtQHO+Ie214q3uXoYV5', 'Admin', 'admin2'),
	(9, 'Sánchez', 'elenaleia@cavesandorcs.com', 'es', 'Elena', 'NhzSIiSS9a1e5/ororlYEqaN2wDa705xNwv69JLs0B5wzzG0Vnh9fw38q6k2BZ1d', 'Normal', 'leiaOrgana'),
	(10, 'Ramírez', 'ire321@cavesandorcs.com', 'es', 'Irene', 'QDkhCu3YRBiOTyJe0v8l0nva/qWvJvV/TrlKrUfUNfVfcV55KlfCdLMccZ2RMvoB', 'Normal', 'ire321');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
