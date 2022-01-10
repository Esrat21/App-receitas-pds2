-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 20-Out-2021 às 21:05
-- Versão do servidor: 10.4.21-MariaDB
-- versão do PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `pds2`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `ingredientes`
--

CREATE TABLE `ingredientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `ingredientes`
--

INSERT INTO `ingredientes` (`id`, `nome`) VALUES
(114, ' creme de leite'),
(120, ' farinha de rosca'),
(121, ' limao'),
(112, ' maizena'),
(117, ' manjericao'),
(113, ' manteiga'),
(115, ' molho de tomate'),
(119, ' ovos'),
(116, ' sal a gosto'),
(107, 'cenoura'),
(106, 'farinha'),
(110, 'fermento'),
(111, 'frango'),
(109, 'manteiga'),
(108, 'ovos');

-- --------------------------------------------------------

--
-- Estrutura da tabela `lista_receitas`
--

CREATE TABLE `lista_receitas` (
  `id` int(11) NOT NULL,
  `id_receita` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `lista_receitas_favoritas`
--

CREATE TABLE `lista_receitas_favoritas` (
  `id` int(11) NOT NULL,
  `id_receita_favorita` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `receitas`
--

CREATE TABLE `receitas` (
  `id` int(11) NOT NULL,
  `imagem` text DEFAULT NULL,
  `texto` text DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `quant_likes` int(11) DEFAULT NULL,
  `tipo_receita` varchar(255) DEFAULT NULL,
  `nacionalidade` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `receitas`
--

INSERT INTO `receitas` (`id`, `imagem`, `texto`, `id_usuario`, `quant_likes`, `tipo_receita`, `nacionalidade`, `nome`) VALUES
(49, './upload/images./726045082-1633004192.jpeg', 'bata tudo email coloque no forno a 180 graus poor 1 hora', 1, 0, 'bolo', NULL, 'bolo de cenoura'),
(50, './upload/images./671477633-1634295596.jpeg', 'Derreta a Margarina Qualy Cremosa e use a cebola e o alho. Acrescente a farinha de trigo e mexa bem por 2 minutos. Acrescente o milho (sem o líquido) e refogue por 3 minutos;\nAcrescente o creme de leite e deixe cozinhar por mais 10 minutos em fogo baixo. Tempere com sal, pimenta e noz moscada;\nBata em um liquidificador para obter o creme.', 10, 0, '', NULL, 'fricasse de frango'),
(51, './upload/images./1773532143-1634296643.jpeg', 'temper bem of frango, molhe Elle no ovos email passed na farinha de rosca e frite bem', 10, 0, '', NULL, 'frango frito');

-- --------------------------------------------------------

--
-- Estrutura da tabela `receitas_denunciadas`
--

CREATE TABLE `receitas_denunciadas` (
  `id` int(11) NOT NULL,
  `id_receita` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `receitas_favoritas`
--

CREATE TABLE `receitas_favoritas` (
  `id` int(11) NOT NULL,
  `id_receita` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `receita_ingrediente`
--

CREATE TABLE `receita_ingrediente` (
  `id` int(11) NOT NULL,
  `id_receita` int(11) DEFAULT NULL,
  `id_ingrediente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `receita_ingrediente`
--

INSERT INTO `receita_ingrediente` (`id`, `id_receita`, `id_ingrediente`) VALUES
(81, 49, 106),
(82, 49, 107),
(83, 49, 108),
(84, 49, 109),
(85, 49, 110),
(86, 50, 111),
(87, 50, 112),
(88, 50, 113),
(89, 50, 114),
(90, 50, 115),
(91, 50, 116),
(92, 50, 117),
(93, 51, 111),
(94, 51, 119),
(95, 51, 120),
(96, 51, 121);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `ingredientes_ruim` varchar(255) DEFAULT NULL,
  `ingredientes_bom` varchar(255) DEFAULT NULL,
  `premium` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `login`, `senha`, `ingredientes_ruim`, `ingredientes_bom`, `premium`) VALUES
(1, 'teste1@email.com', 'senha1', NULL, NULL, 0),
(5, 'teste2@email.com', 'senha2', NULL, NULL, 0),
(6, 'email3@email.4', 'senha3', NULL, NULL, 0),
(7, 'email4@email.com', 'senha4', NULL, NULL, 0),
(8, 'email@email.com', 'senha', NULL, NULL, 0),
(10, 'user1', '123', NULL, NULL, 0);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `ingredientes`
--
ALTER TABLE `ingredientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`);

--
-- Índices para tabela `lista_receitas`
--
ALTER TABLE `lista_receitas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_receita` (`id_receita`);

--
-- Índices para tabela `lista_receitas_favoritas`
--
ALTER TABLE `lista_receitas_favoritas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_receita_favorita` (`id_receita_favorita`);

--
-- Índices para tabela `receitas`
--
ALTER TABLE `receitas`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `receitas_denunciadas`
--
ALTER TABLE `receitas_denunciadas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_receita` (`id_receita`);

--
-- Índices para tabela `receitas_favoritas`
--
ALTER TABLE `receitas_favoritas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_receita` (`id_receita`);

--
-- Índices para tabela `receita_ingrediente`
--
ALTER TABLE `receita_ingrediente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_ingrediente` (`id_ingrediente`),
  ADD KEY `id_receita` (`id_receita`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `ingredientes`
--
ALTER TABLE `ingredientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT de tabela `lista_receitas`
--
ALTER TABLE `lista_receitas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `lista_receitas_favoritas`
--
ALTER TABLE `lista_receitas_favoritas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `receitas`
--
ALTER TABLE `receitas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de tabela `receitas_denunciadas`
--
ALTER TABLE `receitas_denunciadas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `receitas_favoritas`
--
ALTER TABLE `receitas_favoritas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `receita_ingrediente`
--
ALTER TABLE `receita_ingrediente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `lista_receitas`
--
ALTER TABLE `lista_receitas`
  ADD CONSTRAINT `lista_receitas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `lista_receitas_ibfk_2` FOREIGN KEY (`id_receita`) REFERENCES `receitas` (`id`);

--
-- Limitadores para a tabela `lista_receitas_favoritas`
--
ALTER TABLE `lista_receitas_favoritas`
  ADD CONSTRAINT `lista_receitas_favoritas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `lista_receitas_favoritas_ibfk_2` FOREIGN KEY (`id_receita_favorita`) REFERENCES `receitas_favoritas` (`id`);

--
-- Limitadores para a tabela `receitas_denunciadas`
--
ALTER TABLE `receitas_denunciadas`
  ADD CONSTRAINT `receitas_denunciadas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `receitas_denunciadas_ibfk_2` FOREIGN KEY (`id_receita`) REFERENCES `receitas` (`id`);

--
-- Limitadores para a tabela `receitas_favoritas`
--
ALTER TABLE `receitas_favoritas`
  ADD CONSTRAINT `receitas_favoritas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `receitas_favoritas_ibfk_2` FOREIGN KEY (`id_receita`) REFERENCES `receitas` (`id`);

--
-- Limitadores para a tabela `receita_ingrediente`
--
ALTER TABLE `receita_ingrediente`
  ADD CONSTRAINT `receita_ingrediente_ibfk_1` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingredientes` (`id`),
  ADD CONSTRAINT `receita_ingrediente_ibfk_2` FOREIGN KEY (`id_receita`) REFERENCES `receitas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
