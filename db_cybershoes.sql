-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: db_cybershoes
-- ------------------------------------------------------
-- Server version	8.4.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `ape_cliente` varchar(255) DEFAULT NULL,
  `dni_cliente` varchar(255) DEFAULT NULL,
  `email_cliente` varchar(255) DEFAULT NULL,
  `fec_nacim_cliente` date DEFAULT NULL,
  `nom_cliente` varchar(255) DEFAULT NULL,
  `sexo_cliente` varchar(255) DEFAULT NULL,
  `telef_cliente` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Gómez','45012345','gomez.juan@gmail.com','1985-03-22','Juan','Hombre','1123456789'),(2,'Pérez','40123456','perez.luisa@hotmail.com','1990-07-15','Luisa','Mujer','1134567890'),(3,'Fernández','38111222','fernandez.carlos@yahoo.com','1978-11-09','Carlos','Hombre','1145678901'),(4,'Martínez','42098765','martinez.ana@gmail.com','1995-06-30','Ana','Mujer','1156789012'),(5,'López','39987654','lopez.diego@outlook.com','1982-02-18','Diego','Hombre','1167890123'),(6,'Rodríguez','41045678','rodriguez.maria@gmail.com','1988-10-25','María','Mujer','1178901234'),(7,'Sánchez','43011234','sanchez.javier@hotmail.com','1993-01-12','Javier','Hombre','1189012345'),(8,'Díaz','37012345','diaz.sofia@yahoo.com','1999-09-05','Sofía','Mujer','1190123456'),(9,'Romero','39056789','romero.leonardo@gmail.com','1980-04-10','Leonardo','Hombre','1101234567'),(10,'Alvarez','44078901','alvarez.julieta@live.com','1996-12-03','Julieta','Mujer','1112345678');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_venta`
--

DROP TABLE IF EXISTS `detalle_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_venta` (
  `cantidad_vta` int NOT NULL,
  `importe_vta` decimal(38,2) DEFAULT NULL,
  `precio_compra` decimal(38,2) DEFAULT NULL,
  `precio_vta` decimal(38,2) DEFAULT NULL,
  `id_producto` bigint NOT NULL,
  `nro_venta` bigint NOT NULL,
  PRIMARY KEY (`id_producto`,`nro_venta`),
  KEY `FKgpjpqclq9dxil9cbv5g6ansd7` (`nro_venta`),
  CONSTRAINT `FKgpjpqclq9dxil9cbv5g6ansd7` FOREIGN KEY (`nro_venta`) REFERENCES `venta` (`nro_venta`),
  CONSTRAINT `FKsntaik0t9jxcky777753wytsx` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_venta`
--

LOCK TABLES `detalle_venta` WRITE;
/*!40000 ALTER TABLE `detalle_venta` DISABLE KEYS */;
INSERT INTO `detalle_venta` VALUES (2,720.00,280.00,360.00,1,1),(1,360.00,280.00,360.00,1,3),(1,360.00,280.00,360.00,1,4),(1,390.00,300.00,390.00,2,2),(1,410.00,320.00,410.00,3,2),(1,260.00,200.00,260.00,5,3),(1,380.00,290.00,380.00,6,5),(1,210.00,160.00,210.00,8,3),(2,580.00,220.00,290.00,9,3),(1,330.00,250.00,330.00,10,6);
/*!40000 ALTER TABLE `detalle_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` bigint NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `marca_prod` varchar(255) DEFAULT NULL,
  `modelo_prod` varchar(255) DEFAULT NULL,
  `precio_compra` decimal(38,2) DEFAULT NULL,
  `precio_venta` decimal(38,2) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'ZAP001','Urbana','Nike','Air Max 90',280.00,360.00,11),(2,'ZAP002','Running','Adidas','Ultraboost 22',300.00,390.00,9),(3,'ZAP003','Trekking','Salomon','X Ultra 4',320.00,410.00,7),(4,'ZAP004','Casual','Puma','Smash V2',180.00,240.00,20),(5,'ZAP005','Urbana','Reebok','Classic Leather',200.00,260.00,17),(6,'ZAP006','Running','Asics','Gel-Nimbus 25',290.00,380.00,11),(7,'ZAP007','Trekking','Merrell','Moab 3',270.00,350.00,9),(8,'ZAP008','Casual','Converse','Chuck Taylor',160.00,210.00,24),(9,'ZAP009','Urbana','New Balance','574 Core',220.00,290.00,12),(10,'ZAP010','Running','Under Armour','HOVR Sonic 4',250.00,330.00,10);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` bigint NOT NULL AUTO_INCREMENT,
  `nom_rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'admin'),(2,'recepcionista');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `ape_usuario` varchar(255) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `nom_usuario` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `rol_id_rol` bigint DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `FK53p14xu8lx79r6lyweraumgqt` (`rol_id_rol`),
  CONSTRAINT `FK53p14xu8lx79r6lyweraumgqt` FOREIGN KEY (`rol_id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Diaz','adm','David','adm',1),(2,'Mendez','rec','Jorge','rec',2),(3,'Ceballos','123','Jose','joce',2),(4,'Chavez','123','Luis','luch',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `nro_venta` bigint NOT NULL AUTO_INCREMENT,
  `fecha_venta` date DEFAULT NULL,
  `ganancia` decimal(38,2) DEFAULT NULL,
  `igv` decimal(38,2) DEFAULT NULL,
  `subtotal` decimal(38,2) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `id_cliente` bigint DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`nro_venta`),
  KEY `FKsor2qmi3thao7a8or49vlohp9` (`id_cliente`),
  KEY `FKoilu1fdfgmu7sfe0spen005ms` (`id_usuario`),
  CONSTRAINT `FKoilu1fdfgmu7sfe0spen005ms` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FKsor2qmi3thao7a8or49vlohp9` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (1,'2025-07-01',160.00,129.60,590.40,720.00,1,2),(2,'2025-07-01',180.00,144.00,656.00,800.00,6,2),(3,'2025-07-02',330.00,253.80,1156.20,1410.00,8,2),(4,'2025-07-02',80.00,64.80,295.20,360.00,4,2),(5,'2025-07-02',90.00,68.40,311.60,380.00,10,2),(6,'2025-07-02',80.00,59.40,270.60,330.00,7,1);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-02 15:36:23
