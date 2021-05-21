-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: ssaquizauth
-- ------------------------------------------------------
-- Server version	8.0.25-0ubuntu0.20.04.1

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `email_verified` bit(1) NOT NULL,
  `image_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `provider` varchar(255) COLLATE utf8_bin NOT NULL,
  `provider_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (21,'sunshine4429@gmail.com',_binary '\0','https://ssaquiz.s3.ap-northeast-2.amazonaws.com/f4078b51-05b2-4841-ad05-c5f1d72c71fa.jpg','JBJB','$2a$10$NXs1VKZCcADN0tHz.csxIuoJYhK3mTYxiTzbSHnm85dA4arejbPg.','local',NULL),(28,'cnnnnh@gmail.com',_binary '\0','https://ssaquiz.s3.ap-northeast-2.amazonaws.com/da30ce2d-c638-40cd-8ace-c835f7786e61.jpg','최나나나','$2a$10$rfBnxc6Ena/KX907LCuBeuIWcTqd392aa0wlrD/s8GqOR6vlU14GC','local',NULL),(38,'cheche@cheche',_binary '\0','https://ssaquiz.s3.ap-northeast-2.amazonaws.com/d83f164b-434f-4630-9323-ade26ea4d5f2.jpg','채웡','$2a$10$Vjtj9KBL1OnKIlhhpjaxmupvkBrcZ8xCa2YXACo8tI5SHd/gHb0AC','local',NULL),(44,'tjdwls3469@naver.com',_binary '\0','https://ssaquiz.s3.ap-northeast-2.amazonaws.com/2281a4ad-cc38-4099-b483-862639c71869.jpg','성진','$2a$10$dy3JBGgDiofn8eAxI8fKc.TC8xTCoN3kSLeyuXHKGotzA4wbeV0qK','local',NULL),(46,'chechekk0@gmail.com',_binary '\0','https://lh3.googleusercontent.com/a/AATXAJx2HRzP_5ADiF4BYYA8QlsFZledvwILTq34-Eyl=s96-c','chaewon kang',NULL,'google','116715428621288876161'),(47,'tjdwls3469@gmail.com',_binary '\0','https://lh3.googleusercontent.com/a/AATXAJwiDE-1Q89bnQeQb74jJRxX9UKnNIz_3HwYf6oZ=s96-c','김성진',NULL,'google','105764954355300460832'),(48,'ssaquiz.a304@gmail.com',_binary '\0','https://lh3.googleusercontent.com/a/AATXAJyHcTtNNI2ht2Gj0anrgvC9JSNYXuynWFhJ1X55=s96-c','ssquiz ssaquiz',NULL,'google','110599807467038484806'),(50,'nanana@nana',_binary '\0','https://ssaquiz.s3.ap-northeast-2.amazonaws.com/2281a4ad-cc38-4099-b483-862639c71869.jpg','nana','$2a$10$AToahYQ2ngwM65kBMo/w8.2zNESk4PgF8EJs.rxuOWUb4HpVD7TKe','local',NULL),(51,'jbjb4467@gmail.com',_binary '\0','https://ssaquiz.s3.ap-northeast-2.amazonaws.com/2281a4ad-cc38-4099-b483-862639c71869.jpg','','$2a$10$niHnI8VA3/cR69vxtx1DTu17eTMmRwnSpuXdp7st2YgnJ6Nww2yTO','local',NULL),(52,'ssafy@ssafy.com',_binary '\0','https://ssaquiz.s3.ap-northeast-2.amazonaws.com/2281a4ad-cc38-4099-b483-862639c71869.jpg','ssafy','$2a$10$9tdgPbMgOQdtw.zsi/mCXelJ6P4TdCOZAJnRuHULORR1wm0keeZ.6','local',NULL),(53,'phoenix9373@gmail.com',_binary '\0','https://lh3.googleusercontent.com/a/AATXAJyRjQ_zFqDmoGcnNPQ4bL6RaOAoVFpklyKTPfei=s96-c','JW Y',NULL,'google','102369981745907151587'),(54,'wjsdmltntn@gmail.com',_binary '\0','https://lh3.googleusercontent.com/a/AATXAJxTh-S0Jh7yAxu596iQHPXqNZBGmBXJJeWnRGcm=s96-c','J Euisss',NULL,'google','112600862918918890131'),(55,'1693013@hansung.ac.kr',_binary '\0','https://lh3.googleusercontent.com/a/AATXAJx4gsOcejEvsopfAkgDoRhOWZ1jc4zMDhYV8NLr=s96-c','(전자정보공학과)박유정',NULL,'google','104840590654854930711'),(56,'megahawk88@gmail.com',_binary '\0','https://lh3.googleusercontent.com/a/AATXAJycYCClB2Inuqs06ITuqjiRH-wPfrEmzhzydvACzA=s96-c','이상현',NULL,'google','110323146446838766833'),(57,'roedeerbutt@gmail.com',_binary '\0','https://lh3.googleusercontent.com/a-/AOh14GjpnqsKow2NZV9A33OubOApM2LysRZW4xJduqHO=s96-c','찬이',NULL,'google','101712658902791368777');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-20 23:03:29
