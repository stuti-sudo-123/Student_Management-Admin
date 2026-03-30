-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: svnit
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaints` (
  `complaint_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `description` text,
  `status` enum('pending','resolved') DEFAULT 'pending',
  `resolution_details` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`complaint_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `complaints_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` VALUES (1,1,'Schedule clash between CS201 and MA102','pending',NULL,'2025-11-05 03:53:14'),(2,2,'Unable to register for OOP course','resolved','Course seat was added for the student','2025-11-12 09:15:32'),(3,3,'Professor not available during office hours','pending',NULL,'2025-12-03 05:47:08'),(4,4,'Grade not updated for completed course','resolved','Grade has been updated by admin','2025-12-18 11:22:41'),(5,5,'Course prerequisites not clearly listed','pending',NULL,'2026-01-07 03:04:55');
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_code` varchar(20) DEFAULT NULL,
  `course_name` varchar(100) NOT NULL,
  `department` varchar(50) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `credits` int DEFAULT NULL,
  `professor_id` int DEFAULT NULL,
  `schedule` varchar(100) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `prerequisites` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `professor_id` (`professor_id`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`professor_id`) REFERENCES `professors` (`professor_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'MA101','Maths-I','AI',1,4,1,'Mon-Wed 9:00-10:30, Room A101',60,NULL),(2,'CS101','Programming','AI',1,4,2,'Tue-Thu 10:30-12:00, Room A102',60,NULL),(3,'PH101','Physics','AI',1,3,3,'Mon-Wed 11:00-12:30, Room A103',60,NULL),(4,'MA102','Maths-II','AI',2,4,4,'Tue-Thu 9:00-10:30, Room A101',60,NULL),(5,'CS102','OOP','AI',2,4,2,'Mon-Wed 2:00-3:30, Room A104',60,NULL),(6,'EE101','Basic Electrical','AI',2,3,5,'Fri 9:00-12:00, Room A105',60,NULL),(7,'CS201','Data Structures','AI',3,4,1,'Mon-Wed 10:30-12:00, Room B201',60,NULL),(8,'AI201','ML Basics','AI',3,4,3,'Tue-Thu 2:00-3:30, Room B202',60,NULL),(9,'CS301','Algorithms','AI',4,4,2,'Mon-Wed 9:00-10:30, Room B203',60,NULL),(10,'AI301','Deep Learning','AI',4,4,4,'Tue-Thu 11:00-12:30, Room B204',60,NULL),(11,'AI401','NLP','AI',5,4,5,'Mon-Wed 3:30-5:00, Room B205',60,NULL),(12,'CS401','DBMS','AI',5,4,1,'Tue-Thu 9:00-10:30, Room B206',60,NULL),(13,'AI501','Computer Vision','AI',6,4,3,'Mon-Wed 2:00-3:30, Room C301',60,NULL),(14,'CS402','Operating Systems','AI',6,4,2,'Tue-Thu 3:30-5:00, Room C302',60,NULL),(15,'AI601','AI Project','AI',7,6,4,'Mon-Fri 10:00-11:00, Room C303',60,NULL),(16,'AI701','Internship','AI',8,6,5,'Mon-Fri 11:00-12:00, Room C304',60,NULL),(17,'MA101','Maths-I','CSE',1,4,1,'Mon-Wed 9:00-10:30, Room D101',60,NULL),(18,'CS101','Programming','CSE',1,4,2,'Tue-Thu 10:30-12:00, Room D102',60,NULL),(19,'CS102','OOP','CSE',2,4,3,'Mon-Wed 2:00-3:30, Room D103',60,NULL),(20,'MA102','Maths-II','CSE',2,4,4,'Tue-Thu 9:00-10:30, Room D101',60,NULL),(21,'CS201','Data Structures','CSE',3,4,2,'Mon-Wed 11:00-12:30, Room D104',60,NULL),(22,'CS202','Discrete Math','CSE',3,4,5,'Tue-Thu 2:00-3:30, Room D105',60,NULL),(23,'CS301','DBMS','CSE',4,4,1,'Mon-Wed 3:30-5:00, Room D106',60,NULL),(24,'CS302','OS','CSE',4,4,3,'Tue-Thu 11:00-12:30, Room D107',60,NULL),(25,'CS401','CN','CSE',5,4,4,'Mon-Wed 9:00-10:30, Room E201',60,NULL),(26,'CS402','Software Engg','CSE',5,4,2,'Tue-Thu 3:30-5:00, Room E202',60,NULL),(27,'CS501','Compiler','CSE',6,4,5,'Mon-Wed 2:00-3:30, Room E203',60,NULL),(28,'CS502','AI','CSE',6,4,1,'Tue-Thu 10:30-12:00, Room E204',60,NULL),(29,'CS601','Project','CSE',7,6,3,'Mon-Fri 10:00-11:00, Room E205',60,NULL),(30,'CS701','Internship','CSE',8,6,4,'Mon-Fri 11:00-12:00, Room E206',60,NULL),(31,'MA101','Maths-I','ELECTRICAL',1,4,5,'Mon-Wed 9:00-10:30, Room F101',60,NULL),(32,'EE101','Basic Electrical','ELECTRICAL',1,4,1,'Tue-Thu 10:30-12:00, Room F102',60,NULL),(33,'EE201','Circuits','ELECTRICAL',2,4,2,'Mon-Wed 2:00-3:30, Room F103',60,NULL),(34,'MA102','Maths-II','ELECTRICAL',2,4,3,'Tue-Thu 9:00-10:30, Room F101',60,NULL),(35,'EE301','Machines','ELECTRICAL',3,4,4,'Mon-Wed 11:00-12:30, Room F104',60,NULL),(36,'EE302','Electronics','ELECTRICAL',3,4,5,'Tue-Thu 2:00-3:30, Room F105',60,NULL),(37,'EE401','Power Systems','ELECTRICAL',4,4,1,'Mon-Wed 3:30-5:00, Room F106',60,NULL),(38,'EE402','Signals','ELECTRICAL',4,4,2,'Tue-Thu 11:00-12:30, Room F107',60,NULL),(39,'EE501','Control Systems','ELECTRICAL',5,4,3,'Mon-Wed 9:00-10:30, Room G201',60,NULL),(40,'EE601','Power Electronics','ELECTRICAL',6,4,4,'Tue-Thu 3:30-5:00, Room G202',60,NULL),(41,'EE701','Project','ELECTRICAL',7,6,5,'Mon-Fri 10:00-11:00, Room G203',60,NULL),(42,'EE801','Internship','ELECTRICAL',8,6,1,'Mon-Fri 11:00-12:00, Room G204',60,NULL),(43,'MA101','Maths-I','ECE',1,4,2,'Mon-Wed 9:00-10:30, Room H101',60,NULL),(44,'EC101','Basic Electronics','ECE',1,4,3,'Tue-Thu 10:30-12:00, Room H102',60,NULL),(45,'EC301','Analog','ECE',3,4,4,'Mon-Wed 11:00-12:30, Room H103',60,NULL),(46,'EC302','Digital','ECE',3,4,5,'Tue-Thu 2:00-3:30, Room H104',60,NULL),(47,'EC401','Signals','ECE',4,4,1,'Mon-Wed 2:00-3:30, Room H105',60,NULL),(48,'EC402','Communication','ECE',4,4,2,'Tue-Thu 9:00-10:30, Room H106',60,NULL),(49,'EC501','Microprocessors','ECE',5,4,3,'Mon-Wed 3:30-5:00, Room H107',60,NULL),(50,'EC601','VLSI','ECE',6,4,4,'Tue-Thu 11:00-12:30, Room H108',60,NULL),(51,'EC701','Project','ECE',7,6,5,'Mon-Fri 10:00-11:00, Room H109',60,NULL),(52,'EC801','Internship','ECE',8,6,1,'Mon-Fri 11:00-12:00, Room H110',60,NULL),(53,'MA101','Maths-I','MECHANICAL',1,4,2,'Mon-Wed 9:00-10:30, Room I101',60,NULL),(54,'ME101','Engineering Mechanics','MECHANICAL',1,4,3,'Tue-Thu 10:30-12:00, Room I102',60,NULL),(55,'ME201','Thermodynamics','MECHANICAL',2,4,4,'Mon-Wed 2:00-3:30, Room I103',60,NULL),(56,'ME301','Fluid Mechanics','MECHANICAL',3,4,5,'Tue-Thu 11:00-12:30, Room I104',60,NULL),(57,'ME401','Machine Design','MECHANICAL',4,4,1,'Mon-Wed 11:00-12:30, Room I105',60,NULL),(58,'ME501','Heat Transfer','MECHANICAL',5,4,2,'Tue-Thu 3:30-5:00, Room I106',60,NULL),(59,'ME601','Manufacturing','MECHANICAL',6,4,3,'Mon-Wed 3:30-5:00, Room I107',60,NULL),(60,'ME701','Project','MECHANICAL',7,6,4,'Mon-Fri 10:00-11:00, Room I108',60,NULL),(61,'ME801','Internship','MECHANICAL',8,6,5,'Mon-Fri 11:00-12:00, Room I109',60,NULL),(62,'MA101','Maths-I','CIVIL',1,4,1,'Mon-Wed 9:00-10:30, Room J101',60,NULL),(63,'CE101','Basic Civil','CIVIL',1,4,2,'Tue-Thu 10:30-12:00, Room J102',60,NULL),(64,'CE201','Surveying','CIVIL',2,4,3,'Mon-Wed 2:00-3:30, Room J103',60,NULL),(65,'CE301','Structural Analysis','CIVIL',3,4,4,'Tue-Thu 9:00-10:30, Room J104',60,NULL),(66,'CE401','Geotechnical','CIVIL',4,4,5,'Mon-Wed 11:00-12:30, Room J105',60,NULL),(67,'CE501','Transportation','CIVIL',5,4,1,'Tue-Thu 2:00-3:30, Room J106',60,NULL),(68,'CE601','Environmental Engg','CIVIL',6,4,2,'Mon-Wed 3:30-5:00, Room J107',60,NULL),(69,'CE701','Project','CIVIL',7,6,3,'Mon-Fri 10:00-11:00, Room J108',60,NULL),(70,'CE801','Internship','CIVIL',8,6,4,'Mon-Fri 11:00-12:00, Room J109',60,NULL),(71,'MA101','Maths-I','CHEMICAL',1,4,5,'Mon-Wed 9:00-10:30, Room K101',60,NULL),(72,'CH101','Chemistry','CHEMICAL',1,4,1,'Tue-Thu 10:30-12:00, Room K102',60,NULL),(73,'CH201','Process Calc','CHEMICAL',2,4,2,'Mon-Wed 2:00-3:30, Room K103',60,NULL),(74,'CH301','Thermodynamics','CHEMICAL',3,4,3,'Tue-Thu 9:00-10:30, Room K104',60,NULL),(75,'CH401','Fluid Flow','CHEMICAL',4,4,4,'Mon-Wed 11:00-12:30, Room K105',60,NULL),(76,'CH501','Heat Transfer','CHEMICAL',5,4,5,'Tue-Thu 2:00-3:30, Room K106',60,NULL),(77,'CH601','Mass Transfer','CHEMICAL',6,4,1,'Mon-Wed 3:30-5:00, Room K107',60,NULL),(78,'CH701','Project','CHEMICAL',7,6,2,'Mon-Fri 10:00-11:00, Room K108',60,NULL),(79,'CH801','Internship','CHEMICAL',8,6,3,'Mon-Fri 11:00-12:00, Room K109',60,NULL);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollments`
--

DROP TABLE IF EXISTS `enrollments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollments` (
  `enrollment_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `enrollment_date` date DEFAULT NULL,
  `status` enum('enrolled','dropped','completed') DEFAULT NULL,
  PRIMARY KEY (`enrollment_id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `enrollments_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE,
  CONSTRAINT `enrollments_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollments`
--

LOCK TABLES `enrollments` WRITE;
/*!40000 ALTER TABLE `enrollments` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrollments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grades` (
  `grade_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `grade` varchar(2) DEFAULT NULL,
  `grade_points` decimal(3,1) DEFAULT NULL,
  `semester` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`grade_id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE,
  CONSTRAINT `grades_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES (1,1,17,'A',10.0,'1'),(2,1,18,'B',8.0,'1'),(3,1,20,'A',10.0,'2'),(4,1,21,'B',8.0,'2'),(5,3,33,'A',10.0,'1'),(6,3,34,'B',8.0,'1'),(7,3,35,'A',10.0,'3'),(8,3,36,'B',8.0,'3');
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professors`
--

DROP TABLE IF EXISTS `professors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professors` (
  `professor_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `office` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`professor_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `professors_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professors`
--

LOCK TABLES `professors` WRITE;
/*!40000 ALTER TABLE `professors` DISABLE KEYS */;
INSERT INTO `professors` VALUES (1,2,'CSE','Room 101','Dr. Rajesh Kumar','rajesh@svnit.ac.in','prof123'),(2,3,'AI','Room 102','Dr. Priya Sharma','priya@svnit.ac.in','prof123'),(3,4,'ECE','Room 103','Dr. Amit Patel','amit@svnit.ac.in','prof123'),(4,5,'MECHANICAL','Room 104','Dr. Sunita Verma','sunita@svnit.ac.in','prof123'),(5,6,'CIVIL','Room 105','Dr. Vikram Singh','vikram@svnit.ac.in','prof123');
/*!40000 ALTER TABLE `professors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `current_semester` int DEFAULT '1',
  PRIMARY KEY (`student_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,7,'Aarav Shah','aarav@svnit.ac.in','stu123','CSE',2,3),(2,8,'Priya Patel','priya.s@svnit.ac.in','stu123','AI',1,1),(3,9,'Rohit Mehta','rohit@svnit.ac.in','stu123','ECE',3,5),(4,10,'Sneha Joshi','sneha@svnit.ac.in','stu123','MECHANICAL',2,3),(5,11,'Karan Desai','karan@svnit.ac.in','stu123','CIVIL',1,2);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('student','professor','admin') NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Admin','admin@svnit.ac.in','admin123','admin','2026-03-30 19:05:47'),(2,'Dr. Rajesh Kumar','rajesh@svnit.ac.in','prof123','professor','2026-03-30 19:05:47'),(3,'Dr. Priya Sharma','priya@svnit.ac.in','prof123','professor','2026-03-30 19:05:47'),(4,'Dr. Amit Patel','amit@svnit.ac.in','prof123','professor','2026-03-30 19:05:47'),(5,'Dr. Sunita Verma','sunita@svnit.ac.in','prof123','professor','2026-03-30 19:05:47'),(6,'Dr. Vikram Singh','vikram@svnit.ac.in','prof123','professor','2026-03-30 19:05:47'),(7,'Aarav Shah','aarav@svnit.ac.in','stu123','student','2026-03-30 19:05:48'),(8,'Priya Patel','priya.s@svnit.ac.in','stu123','student','2026-03-30 19:05:48'),(9,'Rohit Mehta','rohit@svnit.ac.in','stu123','student','2026-03-30 19:05:48'),(10,'Sneha Joshi','sneha@svnit.ac.in','stu123','student','2026-03-30 19:05:48'),(11,'Karan Desai','karan@svnit.ac.in','stu123','student','2026-03-30 19:05:48');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-31  1:10:10
