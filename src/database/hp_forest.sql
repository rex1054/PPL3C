-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2018 at 09:00 AM
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
  `id` bigint(20) NOT NULL,
  `hari` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hari`
--

INSERT INTO `hari` (`id`, `hari`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `inventori`
--

DROP TABLE IF EXISTS `inventori`;
CREATE TABLE IF NOT EXISTS `inventori` (
`id` bigint(20) unsigned NOT NULL,
  `barang` varchar(30) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `kayu` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventori`
--

INSERT INTO `inventori` (`id`, `barang`, `jumlah`, `kayu`) VALUES
(1, 'uang', 3000, 0),
(2, 'damar', 5, 0),
(3, 'gaharu', 0, 0),
(4, 'jati', 0, 0),
(5, 'mahoni', 0, 0),
(6, 'pupuk', 5, 0),
(7, 'pestisida', 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `lahan`
--

DROP TABLE IF EXISTS `lahan`;
CREATE TABLE IF NOT EXISTS `lahan` (
`lahan` bigint(20) unsigned NOT NULL,
  `pohon` int(11) NOT NULL,
  `air` int(11) NOT NULL,
  `umur` int(11) NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  `img` varchar(300) DEFAULT NULL,
  `maxAir` int(11) NOT NULL,
  `maxUmur` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `pupuk` int(11) NOT NULL,
  `pestisida` int(11) NOT NULL,
  `critical` int(11) NOT NULL,
  `berpohon` varchar(5) NOT NULL,
  `siapPanen` varchar(5) NOT NULL DEFAULT 'false'
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lahan`
--

INSERT INTO `lahan` (`lahan`, `pohon`, `air`, `umur`, `status`, `img`, `maxAir`, `maxUmur`, `level`, `pupuk`, `pestisida`, `critical`, `berpohon`, `siapPanen`) VALUES
(1, 14, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(2, 14, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(3, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(4, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(5, 14, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(6, 14, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(7, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(8, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(9, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(10, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(11, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(12, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(13, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(14, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(15, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false'),
(16, 14, 0, 0, 'kering', NULL, 0, 0, 0, 0, 0, 0, 'false', 'false');

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
  `h_jual` int(11) NOT NULL,
  `airMin` int(11) NOT NULL,
  `maxPupuk` int(11) NOT NULL,
  `maxPest` int(11) NOT NULL,
  `hari` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pohon`
--

INSERT INTO `pohon` (`id`, `pohon`, `umur`, `maxair`, `h_beli`, `h_jual`, `airMin`, `maxPupuk`, `maxPest`, `hari`) VALUES
(1, 'damar', 10, 200, 100, 2000, 70, 1, 0, 3),
(2, 'gaharu', 11, 250, 120, 3000, 80, 1, 0, 3),
(3, 'jati', 14, 350, 160, 5000, 100, 1, 1, 4),
(4, 'mahoni', 12, 300, 140, 4000, 90, 1, 1, 3),
(5, 'damar', 10, 400, 100, 2000, 170, 3, 1, 6),
(6, 'gaharu', 11, 450, 120, 3000, 180, 3, 1, 7),
(7, 'jati', 14, 550, 160, 5000, 200, 3, 3, 9),
(8, 'mahoni', 12, 500, 140, 4000, 190, 3, 2, 7),
(9, 'damar', 10, 600, 100, 2000, 270, 6, 2, 10),
(10, 'gaharu', 11, 650, 120, 3000, 280, 6, 3, 11),
(11, 'jati', 14, 750, 160, 5000, 300, 6, 5, 14),
(12, 'mahoni', 12, 700, 140, 4000, 290, 6, 4, 12),
(14, '0', 0, 0, 0, 0, 0, 0, 0, 0);

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
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
