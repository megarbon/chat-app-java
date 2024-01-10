-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-01-2024 a las 10:32:33
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `e-commerce-esbd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `correoElectronico` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `codigoPostal` varchar(20) DEFAULT NULL,
  `numeroTarjeta` varchar(16) DEFAULT NULL,
  `fotoPerfilURL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nombre`, `correoElectronico`, `contrasena`, `apellidos`, `direccion`, `codigoPostal`, `numeroTarjeta`, `fotoPerfilURL`) VALUES
(1, 'Juan', 'juan@example.com', 'contrasena1', 'López', 'Calle 123', '12345', '1234567812345678', 'https://imgur.com/aGu6vtg'),
(2, 'Maria', 'maria@example.com', 'contrasena2', 'Gómez', 'Avenida 456', '54321', '2345678923456789', 'https://imgur.com/3E7hxWr'),
(3, 'Carlos', 'carlos@example.com', 'contrasena3', 'Perez', 'Plaza 789', '67890', '3456789034567890', 'https://imgur.com/hQCzDd8'),
(4, 'Laura', 'laura@example.com', 'contrasena4', 'Rodriguez', 'Calle 987', '09876', '4567890145678901', 'https://imgur.com/0Nziy5N'),
(5, 'Pedro', 'pedro@example.com', 'contrasena5', 'Martinez', 'Avenida 654', '56789', '5678901256789012', 'https://imgur.com/xA7UEAD');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historialpedidos`
--

CREATE TABLE `historialpedidos` (
  `idHistorial` int(11) NOT NULL,
  `idPedido` int(11) DEFAULT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `fechaPedido` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historialpedidos`
--

INSERT INTO `historialpedidos` (`idHistorial`, `idPedido`, `idCliente`, `fechaPedido`) VALUES
(1, 1, 1, '2022-01-08 11:00:00'),
(2, 2, 3, '2022-01-08 12:30:00'),
(3, 3, 2, '2022-01-08 13:45:00'),
(4, 4, 4, '2022-01-08 15:00:00'),
(5, 5, 5, '2022-01-08 16:15:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidoproducto`
--

CREATE TABLE `pedidoproducto` (
  `idPedidoProducto` int(11) NOT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `idProducto` int(11) DEFAULT NULL,
  `cantidad` int(11) NOT NULL,
  `fechaPedido` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedidoproducto`
--

INSERT INTO `pedidoproducto` (`idPedidoProducto`, `idCliente`, `idProducto`, `cantidad`, `fechaPedido`) VALUES
(1, 1, 1, 2, '2022-01-08 11:00:00'),
(2, 2, 3, 1, '2022-01-08 12:30:00'),
(3, 3, 2, 3, '2022-01-08 13:45:00'),
(4, 4, 4, 1, '2022-01-08 15:00:00'),
(5, 5, 5, 2, '2022-01-08 16:15:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `imagenUrl` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `nombre`, `descripcion`, `precio`, `stock`, `imagenUrl`) VALUES
(0, 'Philipp Plein Hi-Top Sneaker Money Kick$ Golden', 'El oro, uno de los elementos más valiosos del mundo, es el protagonista de estas zapatillas de caña alta. La parte superior de ante está cubierta por cristales de imitación dorados, lo que convierte estos zapatos en una auténtica joya. El icónico cráneo y el logotipo hexagonal están grabados en la banda de goma que va desde la suela hasta la parte superior. Las letras de la marca se aplican alrededor del contrafuerte.', 6000.00, 5, 'https://imgur.com/LEMYLg6'),
(1, 'Nike Air MAX 1', 'Te presentamos al líder de la manada. Camina por las nubes sin que nada te moleste con estas Air Max 1, que combinan un diseño atemporal con una amortiguación cómoda. Con un look trepidante, una bandeleta ondulada y Nike Air, este icono clásico entró en escena en 1987 y sigue siendo el alma de la franquicia en la actualidad.', 129.99, 50, 'https://imgur.com/ZQvjUFs'),
(2, 'Yeezy 700', 'La línea Yeezy 700 se presentó al público en febrero de 2017 como parte de la colección Yeezy Season 5, que presentó una variedad de combinaciones en colores polarizantes de inspiración militar.', 299.95, 30, 'https://imgur.com/c5w6yh9'),
(3, 'Nike Dunk Purple Lobster', 'La tienda Concepts de Cambridge (Massachusetts) y Nike SB son famosas por sus SB Dunk inspiradas en las langostas. El linaje incluye la combinación de colores en rojo de las originales de 2008, junto con una versión en azul y otra en amarillo para familiares y amigos lanzada en 2009. En su último trabajo conjunto han creado las Purple Lobster, un diseño que celebra el décimo aniversario de la colaboración aunando las combinaciones de colores en rojo y azul langosta.', 399.99, 20, 'https://imgur.com/KpTlLG3'),
(4, 'Jordan 1 Chicago', 'Las AJ1 \"Chicago\" están inspiradas en el diseño de perfil alto y la combinación de colores originales de las Air Jordan 1 que salieron a la venta en 1985. Las zapatillas recuerdan a la época en que las cajas solían perderse en los almacenes de inventario para reaparecer años después.', 999.99, 40, 'https://imgur.com/mSQjYhI'),
(5, 'Philipp Plein Moccasin Skull&Bones', 'La gente poderosa necesita zapatos poderosos. Zapatillas de deporte Trainer Predator confeccionadas en pieles y malla. El PP Hexagonal se aplica en el costado exterior. Cordones tubulares. Bordes acolchados. La suela de goma debe su forma y estructura al moldeo por inyección. Forro interior y plantilla extraíble.', 329.99, 25, 'https://imgur.com/xbEATiO'),
(6, 'Philipp Plein Hi-Top Sneakers Money Kick$ Gold Skull', 'El oro, uno de los elementos más valiosos del mundo, es el protagonista de estas zapatillas de caña alta. La parte superior de ante está cubierta por cristales de imitación dorados, lo que convierte estos zapatos en una auténtica joya. El icónico cráneo y el logotipo hexagonal están grabados en la banda de goma que va desde la suela hasta la parte superior. Las letras de la marca se aplican alrededor del contrafuerte.', 6000.00, 5, 'https://imgur.com/LEMYLg6');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `historialpedidos`
--
ALTER TABLE `historialpedidos`
  ADD PRIMARY KEY (`idHistorial`),
  ADD KEY `idPedido` (`idPedido`),
  ADD KEY `idCliente` (`idCliente`);

--
-- Indices de la tabla `pedidoproducto`
--
ALTER TABLE `pedidoproducto`
  ADD PRIMARY KEY (`idPedidoProducto`),
  ADD KEY `idCliente` (`idCliente`),
  ADD KEY `idProducto` (`idProducto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `historialpedidos`
--
ALTER TABLE `historialpedidos`
  MODIFY `idHistorial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pedidoproducto`
--
ALTER TABLE `pedidoproducto`
  MODIFY `idPedidoProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historialpedidos`
--
ALTER TABLE `historialpedidos`
  ADD CONSTRAINT `historialpedidos_ibfk_1` FOREIGN KEY (`idPedido`) REFERENCES `pedidoproducto` (`idPedidoProducto`),
  ADD CONSTRAINT `historialpedidos_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`);

--
-- Filtros para la tabla `pedidoproducto`
--
ALTER TABLE `pedidoproducto`
  ADD CONSTRAINT `pedidoproducto_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `pedidoproducto_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
