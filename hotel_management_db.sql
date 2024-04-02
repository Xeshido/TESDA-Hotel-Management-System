-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2024 at 04:10 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblbillings`
--

CREATE TABLE `tblbillings` (
  `BillID` int(11) NOT NULL,
  `BillType` varchar(25) NOT NULL,
  `BillDate` date NOT NULL,
  `BillAmount` decimal(8,0) DEFAULT NULL,
  `PaymentDate` date NOT NULL,
  `PaymentStatus` varchar(25) NOT NULL DEFAULT 'N/A',
  `PaymentMethod` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tblcustomer`
--

CREATE TABLE `tblcustomer` (
  `CustomerID` int(11) NOT NULL,
  `CustomerName` varchar(25) NOT NULL,
  `CelNum` varchar(13) NOT NULL,
  `Email` varchar(25) DEFAULT 'N/A',
  `Age` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tblregistrations`
--

CREATE TABLE `tblregistrations` (
  `RegistrationID` int(11) NOT NULL,
  `CheckInDate` datetime DEFAULT NULL,
  `PeriodOfStay` datetime DEFAULT NULL,
  `CheckOutDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tblroomprice`
--

CREATE TABLE `tblroomprice` (
  `RoomType` varchar(25) NOT NULL,
  `RoomPrice` decimal(8,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tblrooms`
--

CREATE TABLE `tblrooms` (
  `RoomID` int(11) NOT NULL,
  `RoomType` varchar(25) NOT NULL,
  `RoomStatus` varchar(25) DEFAULT 'N/A',
  `RoomDescription` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tblservices`
--

CREATE TABLE `tblservices` (
  `ServiceID` int(11) NOT NULL,
  `ServiceType` varchar(25) NOT NULL,
  `ServiceDescription` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblbillings`
--
ALTER TABLE `tblbillings`
  ADD PRIMARY KEY (`BillID`);

--
-- Indexes for table `tblcustomer`
--
ALTER TABLE `tblcustomer`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `tblregistrations`
--
ALTER TABLE `tblregistrations`
  ADD PRIMARY KEY (`RegistrationID`);

--
-- Indexes for table `tblroomprice`
--
ALTER TABLE `tblroomprice`
  ADD PRIMARY KEY (`RoomType`);

--
-- Indexes for table `tblrooms`
--
ALTER TABLE `tblrooms`
  ADD PRIMARY KEY (`RoomID`),
  ADD KEY `rooms_roomprice_fk` (`RoomType`);

--
-- Indexes for table `tblservices`
--
ALTER TABLE `tblservices`
  ADD PRIMARY KEY (`ServiceID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblbillings`
--
ALTER TABLE `tblbillings`
  MODIFY `BillID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tblcustomer`
--
ALTER TABLE `tblcustomer`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2024002;

--
-- AUTO_INCREMENT for table `tblregistrations`
--
ALTER TABLE `tblregistrations`
  MODIFY `RegistrationID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tblrooms`
--
ALTER TABLE `tblrooms`
  MODIFY `RoomID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tblservices`
--
ALTER TABLE `tblservices`
  MODIFY `ServiceID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblrooms`
--
ALTER TABLE `tblrooms`
  ADD CONSTRAINT `rooms_roomprice_fk` FOREIGN KEY (`RoomType`) REFERENCES `tblroomprice` (`RoomType`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
