-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 18 Jan 2020 pada 15.14
-- Versi server: 10.3.16-MariaDB
-- Versi PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sipb`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `pid` int(11) NOT NULL,
  `kode` varchar(100) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `harga` int(9) NOT NULL DEFAULT 0,
  `merk` varchar(200) NOT NULL,
  `spesifikasi` varchar(200) NOT NULL,
  `pemasok` varchar(200) NOT NULL,
  `stok` int(7) NOT NULL,
  `time` bigint(20) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`pid`, `kode`, `nama`, `harga`, `merk`, `spesifikasi`, `pemasok`, `stok`, `time`) VALUES
(1, 'prod1', 'Kamera CCTV', 379000, 'Xiaomi Yi Dome Yi IOT Max 1080P IP Camera International Version', 'Resolusi: 1920x1080Lens: 110 DegreeAudio: Built in Speaker mic. Full duplexPan tilt: 340 / 95Motion DetectionTwo-Way AudioYi IOT CloudEnhanced Night visionProduk ini tidak support RTSP', 'PT Citra Nusantara Jaya', 45, 1579355675),
(2, 'prod2', 'Kamera DSLR', 4445000, 'Canon EOS 3000D Kit 18-55mm III', '18 MP yang di dukung Processor DIGIC 4+ dan Sensor Size APS-C', 'PT. Datascrip', 30, 1579355819),
(3, 'prod3', 'kabel', 15000, 'kabelio', 'panjang dan kuat 10 m', 'PT Properti Maju', 120, 1579356097);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `level` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id`, `nama`, `username`, `password`, `level`) VALUES
(68, 'Shafira Almawadah', 'shafira', '2ec4b0bdf35a294f7b42496e0a19ceea', 'NORMAL'),
(69, 'moxspoy', 'moxspoy', 'b47650104f0eb84ecc2aa046d642a8a5', 'ADMINISTRATOR'),
(70, 'Administrator', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMINISTRATOR');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE `penjualan` (
  `id` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `initial_price` int(9) NOT NULL,
  `price` int(9) NOT NULL,
  `margin` int(9) NOT NULL,
  `time` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `penjualan`
--

INSERT INTO `penjualan` (`id`, `pid`, `amount`, `initial_price`, `price`, `margin`, `time`) VALUES
(1, 1, 5, 379000, 400000, -378995, 1579280401000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`pid`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `barang`
--
ALTER TABLE `barang`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
