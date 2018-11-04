-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2018 at 05:03 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hp_forest`
--
CREATE DATABASE IF NOT EXISTS `hp_forest` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `hp_forest`;

-- --------------------------------------------------------

--
-- Table structure for table `hari`
--

DROP TABLE IF EXISTS `hari`;
CREATE TABLE IF NOT EXISTS `hari` (
`id` bigint(20) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hari`
--

INSERT INTO `hari` (`id`) VALUES
(1),
(2);

-- --------------------------------------------------------

--
-- Table structure for table `inventori`
--

DROP TABLE IF EXISTS `inventori`;
CREATE TABLE IF NOT EXISTS `inventori` (
`id` bigint(20) unsigned NOT NULL,
  `barang` varchar(30) NOT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventori`
--

INSERT INTO `inventori` (`id`, `barang`, `jumlah`) VALUES
(1, 'uang', 10000),
(2, 'damar', NULL),
(3, 'gaharu', NULL),
(4, 'jati', NULL),
(5, 'mahoni', NULL),
(6, 'pupuk', NULL),
(7, 'pestisida', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `lahan`
--

DROP TABLE IF EXISTS `lahan`;
CREATE TABLE IF NOT EXISTS `lahan` (
`lahan` bigint(20) unsigned NOT NULL,
  `pohon` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `umur` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lahan`
--

INSERT INTO `lahan` (`lahan`, `pohon`, `jumlah`, `umur`) VALUES
(1, NULL, NULL, NULL),
(2, NULL, NULL, NULL),
(3, NULL, NULL, NULL),
(4, NULL, NULL, NULL),
(5, NULL, NULL, NULL),
(6, NULL, NULL, NULL),
(7, NULL, NULL, NULL),
(8, NULL, NULL, NULL),
(9, NULL, NULL, NULL),
(10, NULL, NULL, NULL),
(11, NULL, NULL, NULL),
(12, NULL, NULL, NULL),
(13, NULL, NULL, NULL),
(14, NULL, NULL, NULL),
(15, NULL, NULL, NULL),
(16, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pohon`
--

DROP TABLE IF EXISTS `pohon`;
CREATE TABLE IF NOT EXISTS `pohon` (
`id` bigint(20) unsigned NOT NULL,
  `pohon` varchar(20) NOT NULL,
  `umur` int(11) NOT NULL,
  `maxair` int(11) DEFAULT NULL,
  `h_beli` int(11) NOT NULL,
  `h_jual` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pohon`
--

INSERT INTO `pohon` (`id`, `pohon`, `umur`, `maxair`, `h_beli`, `h_jual`) VALUES
(1, 'damar', 11, 200, 100, 5000),
(2, 'gaharu', 14, 250, 140, 7000),
(3, 'jati', 15, 350, 160, 8000),
(4, 'mahoni', 12, 300, 120, 6000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hari`
--
ALTER TABLE `hari`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `inventori`
--
ALTER TABLE `inventori`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `lahan`
--
ALTER TABLE `lahan`
 ADD PRIMARY KEY (`lahan`), ADD UNIQUE KEY `lahan` (`lahan`);

--
-- Indexes for table `pohon`
--
ALTER TABLE `pohon`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hari`
--
ALTER TABLE `hari`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `inventori`
--
ALTER TABLE `inventori`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `lahan`
--
ALTER TABLE `lahan`
MODIFY `lahan` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `pohon`
--
ALTER TABLE `pohon`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
