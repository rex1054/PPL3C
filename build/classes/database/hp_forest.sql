-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 18, 2018 at 04:33 AM
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
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventori`
--

INSERT INTO `inventori` (`id`, `barang`, `jumlah`) VALUES
(1, 'uang', 9000),
(2, 'damar', 3),
(3, 'gaharu', 0),
(4, 'jati', 4),
(5, 'mahoni', 5),
(6, 'pupuk', 17),
(7, 'pestisida', 8);

-- --------------------------------------------------------

--
-- Table structure for table `lahan`
--

DROP TABLE IF EXISTS `lahan`;
CREATE TABLE IF NOT EXISTS `lahan` (
`lahan` bigint(20) unsigned NOT NULL,
  `pohon` int(11) DEFAULT NULL,
  `air` int(11) DEFAULT NULL,
  `umur` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `img` varchar(300) DEFAULT NULL,
  `maxAir` int(11) NOT NULL,
  `maxUmur` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `pupuk` int(11) NOT NULL,
  `pestisida` int(11) NOT NULL,
  `critical` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lahan`
--

INSERT INTO `lahan` (`lahan`, `pohon`, `air`, `umur`, `status`, `img`, `maxAir`, `maxUmur`, `level`, `pupuk`, `pestisida`, `critical`) VALUES
(1, 1, 20, 6, 'mati', '/main/IMG/pohon/mati1.png', 200, 11, 1, 0, 0, 2),
(2, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(3, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(4, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(5, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(6, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(7, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(8, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(9, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(10, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(11, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(12, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(13, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(14, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(15, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0),
(16, 0, 0, 0, NULL, NULL, 0, 0, 0, 0, 0, 0);

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
