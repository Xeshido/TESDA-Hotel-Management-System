-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 07, 2024 at 09:02 PM
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
-- Database: `db_hotel_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_billings`
--

CREATE TABLE `tbl_billings` (
  `BillID` int(11) NOT NULL,
  `RegistrationID` int(11) NOT NULL,
  `BillType` varchar(30) NOT NULL,
  `BillDate` date NOT NULL,
  `BillAmount` varchar(30) NOT NULL,
  `PaymentDate` date DEFAULT NULL,
  `PaymentStatus` varchar(30) NOT NULL,
  `PaymentMethod` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_customer`
--

CREATE TABLE `tbl_customer` (
  `CustomerID` int(11) NOT NULL,
  `CustomerName` varchar(25) DEFAULT 'Uknown',
  `CellNum` varchar(12) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_registration`
--

CREATE TABLE `tbl_registration` (
  `RegistrationID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `RoomID` int(11) NOT NULL,
  `CheckInDate` date NOT NULL,
  `PeriodOfStay` varchar(30) NOT NULL,
  `CheckOutDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_roomprice`
--

CREATE TABLE `tbl_roomprice` (
  `RoomType` varchar(30) NOT NULL,
  `RoomPrice` int(30) NOT NULL,
  `RoomDescription` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_roomprice`
--

INSERT INTO `tbl_roomprice` (`RoomType`, `RoomPrice`, `RoomDescription`) VALUES
('Deluxe', 5000, 'Level 1 Luxury'),
('Economy', 2000, 'Entry-Level'),
('Executive', 7000, 'Level 2 Luxury'),
('Standard', 3000, 'Comfort'),
('Suite', 10000, 'Level 3 Luxury');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rooms`
--

CREATE TABLE `tbl_rooms` (
  `RoomID` int(11) NOT NULL,
  `RoomType` varchar(30) NOT NULL,
  `RoomStatus` varchar(25) NOT NULL,
  `ServiceID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_serviceprice`
--

CREATE TABLE `tbl_serviceprice` (
  `ServiceType` varchar(30) NOT NULL,
  `ServicePrice` int(30) NOT NULL,
  `ServiceDescription` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_serviceprice`
--

INSERT INTO `tbl_serviceprice` (`ServiceType`, `ServicePrice`, `ServiceDescription`) VALUES
('Dining', 700, 'BLD Service'),
('Housekeeping', 300, 'Room Maintenance'),
('Laundry', 200, 'Laundry Services'),
('Massage & Spa', 500, 'It is what it is');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_services`
--

CREATE TABLE `tbl_services` (
  `ServiceID` int(11) NOT NULL,
  `ServiceType` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_billings`
--
ALTER TABLE `tbl_billings`
  ADD PRIMARY KEY (`BillID`),
  ADD KEY `Registration_RegistrationID` (`RegistrationID`);

--
-- Indexes for table `tbl_customer`
--
ALTER TABLE `tbl_customer`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `tbl_registration`
--
ALTER TABLE `tbl_registration`
  ADD PRIMARY KEY (`RegistrationID`),
  ADD KEY `Customer_CustomerID` (`CustomerID`),
  ADD KEY `Room_RoomID` (`RoomID`);

--
-- Indexes for table `tbl_roomprice`
--
ALTER TABLE `tbl_roomprice`
  ADD PRIMARY KEY (`RoomType`);

--
-- Indexes for table `tbl_rooms`
--
ALTER TABLE `tbl_rooms`
  ADD PRIMARY KEY (`RoomID`),
  ADD KEY `Rooms_RoomsType` (`RoomType`),
  ADD KEY `Service_ServiceID` (`ServiceID`);

--
-- Indexes for table `tbl_serviceprice`
--
ALTER TABLE `tbl_serviceprice`
  ADD PRIMARY KEY (`ServiceType`);

--
-- Indexes for table `tbl_services`
--
ALTER TABLE `tbl_services`
  ADD PRIMARY KEY (`ServiceID`),
  ADD KEY `Service_ServiceType` (`ServiceType`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_billings`
--
ALTER TABLE `tbl_billings`
  MODIFY `BillID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_customer`
--
ALTER TABLE `tbl_customer`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_registration`
--
ALTER TABLE `tbl_registration`
  MODIFY `RegistrationID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_rooms`
--
ALTER TABLE `tbl_rooms`
  MODIFY `RoomID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_services`
--
ALTER TABLE `tbl_services`
  MODIFY `ServiceID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_billings`
--
ALTER TABLE `tbl_billings`
  ADD CONSTRAINT `Registration_RegistrationID` FOREIGN KEY (`RegistrationID`) REFERENCES `tbl_registration` (`RegistrationID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_registration`
--
ALTER TABLE `tbl_registration`
  ADD CONSTRAINT `Customer_CustomerID` FOREIGN KEY (`CustomerID`) REFERENCES `tbl_customer` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Room_RoomID` FOREIGN KEY (`RoomID`) REFERENCES `tbl_rooms` (`RoomID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_rooms`
--
ALTER TABLE `tbl_rooms`
  ADD CONSTRAINT `Rooms_RoomsType` FOREIGN KEY (`RoomType`) REFERENCES `tbl_roomprice` (`RoomType`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Service_ServiceID` FOREIGN KEY (`ServiceID`) REFERENCES `tbl_services` (`ServiceID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_services`
--
ALTER TABLE `tbl_services`
  ADD CONSTRAINT `Service_ServiceType` FOREIGN KEY (`ServiceType`) REFERENCES `tbl_serviceprice` (`ServiceType`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
