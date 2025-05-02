-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2025 at 07:49 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flyair`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL,
  `booking_status` varchar(255) DEFAULT NULL,
  `extra_luggage` bit(1) NOT NULL,
  `flight_id` bigint(20) DEFAULT NULL,
  `hotel_id` bigint(20) DEFAULT NULL,
  `meal_included` bit(1) NOT NULL,
  `total_cost` double NOT NULL,
  `booking_date` datetime(6) DEFAULT NULL,
  `cancelled` bit(1) NOT NULL,
  `trip_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `booking_status`, `extra_luggage`, `flight_id`, `hotel_id`, `meal_included`, `total_cost`, `booking_date`, `cancelled`, `trip_date`) VALUES
(2, 'Paid', b'1', 3, 5, b'1', 15500, '2025-04-28 16:48:41.000000', b'0', '2025-05-05 16:48:41.000000'),
(3, 'Pending', b'1', 3, 5, b'1', 14569, '2025-04-28 17:29:25.000000', b'0', '2025-05-05 17:29:25.000000'),
(4, 'Pending', b'0', 5, 5, b'1', 15019, '2025-04-28 18:42:03.000000', b'0', '2025-05-05 18:42:03.000000');

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE `flight` (
  `id` bigint(20) NOT NULL,
  `airline` varchar(255) DEFAULT NULL,
  `arrival_time` varchar(255) DEFAULT NULL,
  `available` bit(1) NOT NULL,
  `departure_time` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `source` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`id`, `airline`, `arrival_time`, `available`, `departure_time`, `destination`, `price`, `source`) VALUES
(1, 'Air India', '12:00 PM', b'1', '10:00 AM', 'Mumbai', 4500, 'Delhi'),
(2, 'IndiGo', '4:30 PM', b'1', '2:00 PM', 'Bangalore', 5500, 'Delhi'),
(3, 'SpiceJet', '8:00 AM', b'0', '6:00 AM', 'Mumbai', 4200, 'Delhi'),
(4, 'Vistara', '7:00 PM', b'1', '5:00 PM', 'Delhi', 5000, 'Mumbai'),
(5, 'AirAsia', '11:30 AM', b'1', '9:00 AM', 'Goa', 3500, 'Delhi');

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price_per_night` double NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`id`, `available`, `location`, `name`, `price_per_night`, `rating`) VALUES
(1, b'1', 'Mumbai', 'Taj Hotel', 8000, 5),
(2, b'1', 'Delhi', 'OYO Rooms', 1500, 3),
(3, b'1', 'Bangalore', 'ITC Grand', 6000, 5),
(4, b'1', 'Goa', 'Radisson Blu', 4500, 4),
(5, b'1', 'Delhi', 'The Leela Palace', 9500, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `flight`
--
ALTER TABLE `flight`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
