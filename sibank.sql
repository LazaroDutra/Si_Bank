-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Tempo de geração: 06/12/2015 às 04:39
-- Versão do servidor: 10.0.17-MariaDB
-- Versão do PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `sibank`
--

DELIMITER $$
--
-- Procedimentos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Faz_deposito` (IN `valor` FLOAT(2), IN `nconta` INT(1))  BEGIN 

declare saldoant float;
 set saldoant = (select saldo from conta where conta.numeroconta=nconta);
  if (valor>0) then
    UPDATE CONTA SET SALDO= (SALDO + valor) WHERE conta.numeroconta=nconta; 
	INSERT INTO `transacoes`(`id_movimentacao`, `data`, `valor`, `id_tipo`, `numeroconta`, `saldoanterior`) VALUES (null,CURRENT_DATE,valor,2,nconta,saldoant);
  
  ELSE 
   SELECT 'Não foi possivel realizar o saque' AS Msg;
  END IF;

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Faz_saque` (IN `valor` FLOAT, IN `nconta` INT)  BEGIN 

declare saldoant float;
 set saldoant = (select saldo from conta where conta.numeroconta=nconta);
  if (valor>0) then
    UPDATE CONTA SET SALDO= (SALDO - valor) WHERE conta.numeroconta=nconta; 
	INSERT INTO `transacoes`(`id_movimentacao`, `data`, `valor`, `id_tipo`, `numeroconta`, `saldoanterior`) VALUES (null,CURRENT_DATE,valor,1,nconta,saldoant);
  
  ELSE 
   SELECT 'Não foi possivel realizar o saque' AS Msg;
  END IF;

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Faz_transferencia` (IN `numerocono` INT(1), IN `valor` FLOAT(22), IN `numerocond` INT(2))  NO SQL
    DETERMINISTIC
begin

declare SALDOO float;
declare  SALDOD float;
declare CODIGOO integer;
declare CODIGOD integer;


  
  SELECT C.SALDO FROM CONTA C WHERE (C.NUMEROCONTA=NUMEROCONO) INTO SALDOO;
  SELECT C.NUMEROCONTA FROM CONTA C WHERE (C.NUMEROCONTA=NUMEROCONO) INTO CODIGOO;
  
  SELECT C.SALDO FROM CONTA C WHERE (C.NUMEROCONTA=NUMEROCOND)  INTO SALDOD;
  
  SELECT C.NUMEROCONTA FROM CONTA C WHERE (C.NUMEROCONTA=NUMEROCOND)   INTO CODIGOD;
  
  set SALDOO= SALDOO - valor;
  set SALDOD= SALDOD + valor;
  
  UPDATE CONTA SET SALDO=SALDOO where (NUMEROCONTA=NUMEROCONO);
  
  UPDATE CONTA SET SALDO=SALDOD where (NUMEROCONTA=NUMEROCOND);
  
  INSERT INTO transacoes VALUES(NULL,CURRENT_DATE,SALDOO,3,CODIGOO,VALOR);

    INSERT INTO transacoes VALUES(NULL,CURRENT_DATE,SALDOD,3,CODIGOD,VALOR);
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `datanasc` date NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `conta`
--

CREATE TABLE `conta` (
  `numeroconta` int(11) NOT NULL,
  `saldo` float NOT NULL,
  `data_abertura` date NOT NULL,
  `id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `tipomov`
--

CREATE TABLE `tipomov` (
  `id_tipo` int(11) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `tipomov`
--

INSERT INTO `tipomov` (`id_tipo`, `descricao`) VALUES
(1, 'Saque'),
(2, 'Depósito'),
(3, 'Transferência');

-- --------------------------------------------------------

--
-- Estrutura para tabela `transacoes`
--

CREATE TABLE `transacoes` (
  `id_movimentacao` int(11) NOT NULL,
  `data` date NOT NULL,
  `valor` float NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `numeroconta` int(11) NOT NULL,
  `saldoanterior` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura stand-in para view `verconta`
--
CREATE TABLE `verconta` (
`numeroconta` int(11)
,`saldo` float
,`nome` varchar(50)
);

-- --------------------------------------------------------

--
-- Estrutura para view `verconta`
--
DROP TABLE IF EXISTS `verconta`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `verconta`  AS  select `conta`.`numeroconta` AS `numeroconta`,`conta`.`saldo` AS `saldo`,`cliente`.`nome` AS `nome` from ((`conta` join `cliente`) join `transacoes`) where (`conta`.`id_cliente` = `cliente`.`id_cliente`) order by `cliente`.`nome` ;

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Índices de tabela `conta`
--
ALTER TABLE `conta`
  ADD PRIMARY KEY (`numeroconta`,`id_cliente`),
  ADD KEY `fk_conta_cliente1_idx` (`id_cliente`);

--
-- Índices de tabela `tipomov`
--
ALTER TABLE `tipomov`
  ADD PRIMARY KEY (`id_tipo`);

--
-- Índices de tabela `transacoes`
--
ALTER TABLE `transacoes`
  ADD PRIMARY KEY (`id_movimentacao`,`id_tipo`,`numeroconta`),
  ADD KEY `fk_transacoes_tipomov1_idx` (`id_tipo`),
  ADD KEY `fk_transacoes_conta1_idx` (`numeroconta`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de tabela `conta`
--
ALTER TABLE `conta`
  MODIFY `numeroconta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de tabela `tipomov`
--
ALTER TABLE `tipomov`
  MODIFY `id_tipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de tabela `transacoes`
--
ALTER TABLE `transacoes`
  MODIFY `id_movimentacao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `conta`
--
ALTER TABLE `conta`
  ADD CONSTRAINT `fk_conta_cliente1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `transacoes`
--
ALTER TABLE `transacoes`
  ADD CONSTRAINT `fk_transacoes_conta1` FOREIGN KEY (`numeroconta`) REFERENCES `conta` (`numeroconta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_transacoes_tipomov1` FOREIGN KEY (`id_tipo`) REFERENCES `tipomov` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
