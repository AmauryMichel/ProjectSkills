-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 06, 2021 at 02:48 PM
-- Server version: 8.0.23
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `drill`
--

DROP TABLE IF EXISTS `drill`;
CREATE TABLE IF NOT EXISTS `drill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `man_id` int NOT NULL,
  `drill_name` varchar(50) DEFAULT NULL,
  `drill_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `man_id` (`man_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `drill_player`
--

DROP TABLE IF EXISTS `drill_player`;
CREATE TABLE IF NOT EXISTS `drill_player` (
  `player_id` int NOT NULL,
  `drill_id` int NOT NULL,
  `drill_rating` int NOT NULL,
  KEY `player_id` (`player_id`),
  KEY `drill_id` (`drill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `group_drill`
--

DROP TABLE IF EXISTS `group_drill`;
CREATE TABLE IF NOT EXISTS `group_drill` (
  `group_id` int NOT NULL,
  `drill_id` int NOT NULL,
  PRIMARY KEY (`group_id`,`drill_id`),
  KEY `FK_GD_D` (`drill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE IF NOT EXISTS `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `pass` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`id`, `username`, `pass`) VALUES
(14, 'testaa', '$2y$10$oszWGtIU1qc1sZ5krCcXU.ev75LxkzLLLL/6EqrtPQu9kdhbxZJdG'),
(19, 'test3', '$2y$10$S3YOQ8mm2lK72DU9R71VqOmvXDCiWwtkN6WzK4lBptaDj37EB0VBW'),
(21, 'test', '$2y$10$pxZKXvpf/xhrve6E1GT9T.SMJigRomsiPyHhrvIoNz6ynpQxXM7wW');

-- --------------------------------------------------------

--
-- Table structure for table `player_group`
--

DROP TABLE IF EXISTS `player_group`;
CREATE TABLE IF NOT EXISTS `player_group` (
  `player_id` int NOT NULL,
  `group_id` int NOT NULL,
  `is_manager` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`player_id`,`group_id`),
  KEY `man_id` (`player_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `thegroup`
--

DROP TABLE IF EXISTS `thegroup`;
CREATE TABLE IF NOT EXISTS `thegroup` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `drill`
--
ALTER TABLE `drill`
  ADD CONSTRAINT `FK_D_M` FOREIGN KEY (`man_id`) REFERENCES `player` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `drill_player`
--
ALTER TABLE `drill_player`
  ADD CONSTRAINT `FK_DP_D` FOREIGN KEY (`drill_id`) REFERENCES `drill` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_DP_P` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `group_drill`
--
ALTER TABLE `group_drill`
  ADD CONSTRAINT `FK_GD_D` FOREIGN KEY (`drill_id`) REFERENCES `drill` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_GD_G` FOREIGN KEY (`group_id`) REFERENCES `thegroup` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `player_group`
--
ALTER TABLE `player_group`
  ADD CONSTRAINT `FK_MG_G` FOREIGN KEY (`group_id`) REFERENCES `thegroup` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_MG_M` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
