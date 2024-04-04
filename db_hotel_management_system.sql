-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2024 at 04:29 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

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
  `PaymentDate` date NOT NULL,
  `PaymentStatus` varchar(30) NOT NULL,
  `PaymentMethod` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_billings`
--

INSERT INTO `tbl_billings` (`BillID`, `RegistrationID`, `BillType`, `BillDate`, `BillAmount`, `PaymentDate`, `PaymentStatus`, `PaymentMethod`) VALUES
(1, 1, 'Peso', '2024-03-27', 'PHP 2,000', '2024-03-27', 'Paid', 'Cash'),
(2, 3, 'Dollar', '2024-04-10', 'PHP 1,000', '2024-04-10', 'Paid', 'G-Cash');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_customer`
--

INSERT INTO `tbl_customer` (`CustomerID`, `CustomerName`, `CellNum`, `Email`, `Age`) VALUES
(1, 'Kurt Lopez', '09557856198', 'sellabsu9@gmail.com', 19),
(2, 'Dominic Aldas', '0985697856', 'dominic@gmail.com', 19);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_registration`
--

INSERT INTO `tbl_registration` (`RegistrationID`, `CustomerID`, `RoomID`, `CheckInDate`, `PeriodOfStay`, `CheckOutDate`) VALUES
(1, 1, 1, '2024-03-28', '2 Days', '2024-03-29'),
(3, 2, 2, '2024-04-10', '3 Days', '2024-04-13');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_roomprice`
--

CREATE TABLE `tbl_roomprice` (
  `RoomType` varchar(30) NOT NULL,
  `RoomPrice` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_roomprice`
--

INSERT INTO `tbl_roomprice` (`RoomType`, `RoomPrice`) VALUES
('Deluxe', 'PHP 5,000'),
('Economy', 'PHP 2,000'),
('Executive', 'PHP 7,000'),
('Standard', 'PHP 3,000'),
('Suite', 'PHP 10,000');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rooms`
--

CREATE TABLE `tbl_rooms` (
  `RoomID` int(11) NOT NULL,
  `RoomType` varchar(30) NOT NULL,
  `RoomStatus` varchar(25) NOT NULL,
  `RoomDescription` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_rooms`
--

INSERT INTO `tbl_rooms` (`RoomID`, `RoomType`, `RoomStatus`, `RoomDescription`) VALUES
(1, 'Standard', 'Single EME', 'wala lang'),
(2, 'Economy', 'Available', 'Okay lang');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_serviceprice`
--

CREATE TABLE `tbl_serviceprice` (
  `ServiceType` varchar(30) NOT NULL,
  `ServicePrice` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_services`
--

CREATE TABLE `tbl_services` (
  `ServiceID` int(11) NOT NULL,
  `ServiceType` varchar(30) NOT NULL,
  `ServiceDescription` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  ADD KEY `Rooms_RoomsType` (`RoomType`);

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
  MODIFY `BillID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_customer`
--
ALTER TABLE `tbl_customer`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_registration`
--
ALTER TABLE `tbl_registration`
  MODIFY `RegistrationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_rooms`
--
ALTER TABLE `tbl_rooms`
  MODIFY `RoomID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_services`
--
ALTER TABLE `tbl_services`
  MODIFY `ServiceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
  ADD CONSTRAINT `Room_RoomID` FOREIGN KEY (`RoomID`) REFERENCES `tbl_rooms` (`RoomID`);

--
-- Constraints for table `tbl_rooms`
--
ALTER TABLE `tbl_rooms`
  ADD CONSTRAINT `Rooms_RoomsType` FOREIGN KEY (`RoomType`) REFERENCES `tbl_roomprice` (`RoomType`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_services`
--
ALTER TABLE `tbl_services`
  ADD CONSTRAINT `Service_ServiceType` FOREIGN KEY (`ServiceType`) REFERENCES `tbl_serviceprice` (`ServiceType`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
