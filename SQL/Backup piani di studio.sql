-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Lug 15, 2019 alle 22:43
-- Versione del server: 10.3.16-MariaDB
-- Versione PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pianidistudio`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `corsidistudio`
--

CREATE TABLE `corsidistudio` (
  `nome` varchar(25) NOT NULL,
  `dataCreazione` date NOT NULL,
  `tipo` enum('triennale','magistrale') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `corsidistudio`
--

INSERT INTO `corsidistudio` (`nome`, `dataCreazione`, `tipo`) VALUES
('Ingegneria Civile	', '2015-08-28', 'triennale'),
('Ingegneria Energetica	', '2015-08-28', 'triennale'),
('Ingegneria Informatica', '2016-10-27', 'triennale'),
('Ingegneria Informatica', '2015-08-28', 'magistrale');

-- --------------------------------------------------------

--
-- Struttura della tabella `corsiinsegnamenti`
--

CREATE TABLE `corsiinsegnamenti` (
  `insegnamento` char(5) NOT NULL,
  `nomeCorso` varchar(25) NOT NULL,
  `tipoCorso` enum('triennale','magistrale') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `corsiinsegnamenti`
--

INSERT INTO `corsiinsegnamenti` (`insegnamento`, `nomeCorso`, `tipoCorso`) VALUES
('86301', 'Ingegneria Informatica', 'triennale'),
('86302', 'Ingegneria Informatica', 'triennale'),
('86306', 'Ingegneria Informatica', 'triennale'),
('86307', 'Ingegneria Informatica', 'triennale'),
('86308', 'Ingegneria Informatica', 'triennale'),
('86309', 'Ingegneria Informatica', 'triennale'),
('86310', 'Ingegneria Informatica', 'triennale'),
('86311', 'Ingegneria Informatica', 'triennale'),
('86312', 'Ingegneria Informatica', 'triennale'),
('86313', 'Ingegneria Informatica', 'triennale'),
('86314', 'Ingegneria Informatica', 'triennale'),
('86315', 'Ingegneria Informatica', 'triennale'),
('86317', 'Ingegneria Informatica', 'triennale'),
('86320', 'Ingegneria Informatica', 'triennale'),
('86323', 'Ingegneria Informatica', 'triennale'),
('86324', 'Ingegneria Informatica', 'triennale'),
('86325', 'Ingegneria Informatica', 'triennale'),
('86326', 'Ingegneria Informatica', 'triennale'),
('86327', 'Ingegneria Informatica', 'triennale'),
('86328', 'Ingegneria Informatica', 'triennale'),
('86329', 'Ingegneria Informatica', 'triennale'),
('86330', 'Ingegneria Informatica', 'triennale'),
('86345', 'Ingegneria Informatica', 'triennale'),
('86355', 'Ingegneria Informatica', 'triennale'),
('86356', 'Ingegneria Informatica', 'triennale'),
('86383', 'Ingegneria Informatica', 'triennale'),
('86384', 'Ingegneria Informatica', 'triennale');

-- --------------------------------------------------------

--
-- Struttura della tabella `insegnamenti`
--

CREATE TABLE `insegnamenti` (
  `idInsegnamento` char(5) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `CFU` int(2) NOT NULL,
  `SettoreScientifico` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `insegnamenti`
--

INSERT INTO `insegnamenti` (`idInsegnamento`, `nome`, `CFU`, `SettoreScientifico`) VALUES
('39618', 'ASPETTI NORMATIVI,TECNICI ED ECONOMICI PER LA REALIZZAZIONE DELLE OPERE CIVILI', 6, NULL),
('39809', 'ANALISI E SIMULAZIONE DEI PROCESSI DI COMBUSTIONE', 6, NULL),
('39819', 'STORIA DELL\'INDUSTRIA', 6, 'SECS-P/12 - STORIA ECONOMICA	'),
('39902', 'ARCHITETTURE E SISTEMI SOFTWARE DISTRIBUITI', 9, NULL),
('86301', 'PROGRAMMAZIONE 1', 12, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86302', 'MATEMATICA', 12, 'MAT/05 - ANALISI MATEMATICA'),
('86306', 'ECONOMIA E ORGANIZZAZIONE AZIENDALE', 6, 'ING-IND/35 - INGEGNERIA ECONOMICO-GESTIONALE'),
('86307', 'INGLESE', 3, 'L-LIN/12 - LINGUA E TRADUZIONE - LINGUA INGLESE'),
('86308', 'PROGRAMMAZIONE 2', 9, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86309', 'ELETTRONICA', 9, 'ING-INF/01 - ELETTRONICA'),
('86310', 'ARCHITETTURA DEI CALCOLATORI', 9, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86311', 'BASI DI DATI', 6, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86312', 'SISTEMI DINAMICI', 9, 'ING-INF/04 - AUTOMATICA'),
('86313', 'CONTROLLI AUTOMATICI', 6, 'ING-INF/04 - AUTOMATICA'),
('86314', 'FONDAMENTI DI TELECOMUNICAZIONI', 9, 'ING-INF/03 - TELECOMUNICAZIONI'),
('86315', 'MISURE ELETTRONICHE', 6, 'ING-INF/07 - MISURE ELETTRICHE ED ELETTRONICHE'),
('86316', 'PROGRAMMAZIONE DI SISTEMI IN RETE', 9, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86317', 'INGEGNERIA DEL SOFTWARE', 9, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86320', 'PROVA FINALE', 3, 'PROFIN_S - Prova finale per settore senza discipline'),
('86323', 'SISTEMI DI PRODUZIONE', 6, NULL),
('86324', 'SOFTWARE DI MISURA', 6, NULL),
('86325', 'COMPUTAZIONE PERVASIVA', 6, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86326', 'ELEMENTI DI INTELLIGENZA ARTIFICIALE', 6, NULL),
('86327', 'SISTEMI INFORMATIVI AZIENDALI', 6, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86328', 'AUTOMAZIONE INDUSTRIALE', 6, NULL),
('86329', 'TECNOLOGIE DEI SISTEMI DI AUTOMAZIONE', 6, NULL),
('86330', 'SISTEMI DI CONTROLLO DIGITALE', 6, NULL),
('86345', 'MATEMATICA PER L\'INGEGNERIA DELL\'INFORMAZIONE', 9, 'MAT/09 - RICERCA OPERATIVA'),
('86355', 'FISICA', 12, 'FIS/01 - FISICA SPERIMENTALE'),
('86356', 'ELETTROTECNICA', 6, 'ING-IND/31 - ELETTROTECNICA'),
('86383', 'PROGETTAZIONE DIGITALE ', 6, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86384', 'PROGETTAZIONE DEL SOFTWARE', 6, 'ING-INF/05 - SISTEMI DI ELABORAZIONE DELLE INFORMAZIONI'),
('86451', 'INGEGNERIA DELLA SICUREZZA', 6, 'NN - Indefinito/Interdisciplinare'),
('86486', 'ALGEBRA LINEARE, GEOMETRIA E RICERCA OPERATIVA', 9, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `iscrizioni`
--

CREATE TABLE `iscrizioni` (
  `studente` char(9) NOT NULL,
  `nomeCorso` varchar(25) NOT NULL,
  `tipoCorso` enum('triennale','magistrale') NOT NULL,
  `dataIscrizione` date NOT NULL,
  `annoCorso` int(11) NOT NULL,
  `annoAccademico` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `iscrizioni`
--

INSERT INTO `iscrizioni` (`studente`, `nomeCorso`, `tipoCorso`, `dataIscrizione`, `annoCorso`, `annoAccademico`) VALUES
('861000432', 'Ingegneria Civile	', 'triennale', '2017-07-01', 2, '2018-2019'),
('863000666', 'Ingegneria Informatica', 'triennale', '2017-09-05', 2, '2018-2019'),
('863000726', 'Ingegneria Informatica', 'triennale', '2018-09-07', 1, '2018-2019'),
('863000756', 'Ingegneria Informatica', 'triennale', '2016-09-10', 4, '2018-2019'),
('863000757', 'Ingegneria Informatica', 'triennale', '2016-09-04', 4, '2018-2019'),
('863000793', 'Ingegneria Informatica', 'triennale', '2016-09-07', 3, '2018-2019'),
('863000856', 'Ingegneria Informatica', 'triennale', '2016-09-14', 4, '2018-2019'),
('863000964', 'Ingegneria Informatica', 'triennale', '2016-09-12', 4, '2018-2019'),
('863000997', 'Ingegneria Informatica', 'triennale', '2016-11-08', 3, '2016-2017');

-- --------------------------------------------------------

--
-- Struttura della tabella `pianidistudio`
--

CREATE TABLE `pianidistudio` (
  `IDPiano` int(11) NOT NULL,
  `stato` enum('approvato','inApprovazione','nonApprovato') NOT NULL,
  `dataApprovazione` date DEFAULT NULL,
  `nomeCorso` varchar(25) NOT NULL,
  `tipoCorso` enum('triennale','magistrale') NOT NULL,
  `studente` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `pianidistudio`
--

INSERT INTO `pianidistudio` (`IDPiano`, `stato`, `dataApprovazione`, `nomeCorso`, `tipoCorso`, `studente`) VALUES
(1, 'approvato', '2016-09-11', 'Ingegneria Informatica', 'triennale', '863000756'),
(2, 'inApprovazione', NULL, 'Ingegneria Informatica', 'triennale', '863000757'),
(3, 'inApprovazione', NULL, 'Ingegneria Informatica', 'triennale', '863000793'),
(5, 'inApprovazione', NULL, 'Ingegneria Civile	', 'triennale', '861000432'),
(6, 'approvato', '2019-07-14', 'Ingegneria Informatica', 'triennale', '863000997'),
(7, 'approvato', '2019-07-14', 'Ingegneria Informatica', 'triennale', '863000726'),
(8, 'inApprovazione', NULL, 'Ingegneria Informatica', 'triennale', '863000666');

-- --------------------------------------------------------

--
-- Struttura della tabella `pianoinsegnamenti`
--

CREATE TABLE `pianoinsegnamenti` (
  `piano` int(11) NOT NULL,
  `insegnamento` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `pianoinsegnamenti`
--

INSERT INTO `pianoinsegnamenti` (`piano`, `insegnamento`) VALUES
(1, '39819'),
(1, '86301'),
(1, '86302'),
(1, '86306'),
(1, '86307'),
(1, '86308'),
(1, '86309'),
(1, '86310'),
(1, '86311'),
(1, '86312'),
(1, '86313'),
(1, '86314'),
(1, '86315'),
(1, '86316'),
(1, '86317'),
(1, '86320'),
(1, '86325'),
(1, '86327'),
(1, '86345'),
(1, '86355'),
(1, '86356'),
(1, '86383'),
(1, '86384'),
(1, '86451'),
(2, '39819'),
(2, '86301'),
(2, '86302'),
(2, '86306'),
(2, '86307'),
(2, '86308'),
(2, '86309'),
(2, '86310'),
(2, '86311'),
(2, '86312'),
(2, '86313'),
(2, '86314'),
(2, '86315'),
(2, '86316'),
(2, '86317'),
(2, '86320'),
(2, '86327'),
(2, '86345'),
(2, '86355'),
(2, '86356'),
(2, '86383'),
(2, '86384'),
(6, '39819'),
(6, '86301'),
(6, '86302'),
(6, '86306'),
(6, '86307'),
(6, '86308'),
(6, '86309'),
(6, '86310'),
(6, '86311'),
(6, '86312'),
(6, '86313'),
(6, '86314'),
(6, '86315'),
(6, '86316'),
(6, '86317'),
(6, '86320'),
(6, '86325'),
(6, '86327'),
(6, '86345'),
(6, '86355'),
(6, '86356'),
(6, '86383'),
(6, '86384'),
(6, '86451'),
(7, '39809'),
(7, '39902'),
(7, '86301'),
(7, '86302'),
(7, '86306'),
(7, '86307'),
(7, '86308'),
(7, '86309'),
(7, '86311'),
(7, '86312'),
(7, '86313'),
(7, '86314'),
(7, '86315'),
(7, '86316'),
(7, '86317'),
(7, '86320'),
(7, '86325'),
(7, '86326'),
(7, '86328'),
(7, '86330'),
(7, '86345'),
(7, '86355'),
(7, '86356'),
(7, '86451'),
(8, '39809'),
(8, '86301'),
(8, '86302'),
(8, '86306'),
(8, '86307'),
(8, '86308'),
(8, '86309'),
(8, '86310'),
(8, '86311'),
(8, '86312'),
(8, '86313'),
(8, '86314'),
(8, '86315'),
(8, '86316'),
(8, '86317'),
(8, '86320'),
(8, '86325'),
(8, '86326'),
(8, '86345'),
(8, '86355'),
(8, '86356'),
(8, '86383'),
(8, '86384'),
(8, '86451');

-- --------------------------------------------------------

--
-- Struttura della tabella `propedeuticita`
--

CREATE TABLE `propedeuticita` (
  `insegnamento` char(5) NOT NULL,
  `propedeuticita` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `propedeuticita`
--

INSERT INTO `propedeuticita` (`insegnamento`, `propedeuticita`) VALUES
('86308', '86301'),
('86309', '86356'),
('86310', '86383'),
('86311', '86308'),
('86312', '86302'),
('86313', '86312'),
('86314', '86302'),
('86315', '86309'),
('86316', '86310'),
('86317', '86308'),
('86325', '86308'),
('86325', '86310'),
('86356', '86302'),
('86356', '86355'),
('86383', '86345'),
('86384', '86308');

-- --------------------------------------------------------

--
-- Struttura della tabella `studenti`
--

CREATE TABLE `studenti` (
  `matricola` char(9) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `cognome` varchar(25) NOT NULL,
  `dataDiNascita` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `studenti`
--

INSERT INTO `studenti` (`matricola`, `nome`, `cognome`, `dataDiNascita`) VALUES
('861000432', 'Giacomo', 'Miele', '1997-03-21'),
('863000666', 'Sarha', 'Palermo', '1999-10-01'),
('863000726', 'Nicola', 'Calabrese', '1996-04-03'),
('863000756', 'Cristian', 'Spagnuolo', '1996-01-26'),
('863000757', 'Stefano', 'De Lorenzo', '1996-09-12'),
('863000793', 'Roberto', 'Garzone', '1997-08-13'),
('863000856', 'Pasquale', 'De Vito', '1996-03-07'),
('863000964', 'Maria', 'Rossi', '1997-06-03'),
('863000997', 'Valerio', 'Massaro', '1998-02-18');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `corsidistudio`
--
ALTER TABLE `corsidistudio`
  ADD PRIMARY KEY (`nome`,`tipo`);

--
-- Indici per le tabelle `corsiinsegnamenti`
--
ALTER TABLE `corsiinsegnamenti`
  ADD PRIMARY KEY (`insegnamento`,`nomeCorso`,`tipoCorso`),
  ADD KEY `nomeCorso` (`nomeCorso`,`tipoCorso`);

--
-- Indici per le tabelle `insegnamenti`
--
ALTER TABLE `insegnamenti`
  ADD PRIMARY KEY (`idInsegnamento`);

--
-- Indici per le tabelle `iscrizioni`
--
ALTER TABLE `iscrizioni`
  ADD PRIMARY KEY (`studente`),
  ADD KEY `nomeCorso` (`nomeCorso`,`tipoCorso`);

--
-- Indici per le tabelle `pianidistudio`
--
ALTER TABLE `pianidistudio`
  ADD PRIMARY KEY (`IDPiano`),
  ADD KEY `nomeCorso` (`nomeCorso`,`tipoCorso`),
  ADD KEY `studente` (`studente`);

--
-- Indici per le tabelle `pianoinsegnamenti`
--
ALTER TABLE `pianoinsegnamenti`
  ADD PRIMARY KEY (`piano`,`insegnamento`),
  ADD KEY `insegnamento` (`insegnamento`);

--
-- Indici per le tabelle `propedeuticita`
--
ALTER TABLE `propedeuticita`
  ADD PRIMARY KEY (`insegnamento`,`propedeuticita`),
  ADD KEY `propedeuticita` (`propedeuticita`);

--
-- Indici per le tabelle `studenti`
--
ALTER TABLE `studenti`
  ADD PRIMARY KEY (`matricola`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `pianidistudio`
--
ALTER TABLE `pianidistudio`
  MODIFY `IDPiano` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `corsiinsegnamenti`
--
ALTER TABLE `corsiinsegnamenti`
  ADD CONSTRAINT `corsiinsegnamenti_ibfk_1` FOREIGN KEY (`nomeCorso`,`tipoCorso`) REFERENCES `corsidistudio` (`nome`, `tipo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `corsiinsegnamenti_ibfk_2` FOREIGN KEY (`insegnamento`) REFERENCES `insegnamenti` (`idInsegnamento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `iscrizioni`
--
ALTER TABLE `iscrizioni`
  ADD CONSTRAINT `iscrizioni_ibfk_1` FOREIGN KEY (`studente`) REFERENCES `studenti` (`matricola`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `iscrizioni_ibfk_2` FOREIGN KEY (`nomeCorso`,`tipoCorso`) REFERENCES `corsidistudio` (`nome`, `tipo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `pianidistudio`
--
ALTER TABLE `pianidistudio`
  ADD CONSTRAINT `pianidistudio_ibfk_1` FOREIGN KEY (`nomeCorso`,`tipoCorso`) REFERENCES `corsidistudio` (`nome`, `tipo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pianidistudio_ibfk_2` FOREIGN KEY (`studente`) REFERENCES `studenti` (`matricola`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `pianoinsegnamenti`
--
ALTER TABLE `pianoinsegnamenti`
  ADD CONSTRAINT `pianoinsegnamenti_ibfk_1` FOREIGN KEY (`piano`) REFERENCES `pianidistudio` (`IDPiano`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pianoinsegnamenti_ibfk_2` FOREIGN KEY (`insegnamento`) REFERENCES `insegnamenti` (`idInsegnamento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `propedeuticita`
--
ALTER TABLE `propedeuticita`
  ADD CONSTRAINT `propedeuticita_ibfk_1` FOREIGN KEY (`insegnamento`) REFERENCES `insegnamenti` (`idInsegnamento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `propedeuticita_ibfk_2` FOREIGN KEY (`propedeuticita`) REFERENCES `insegnamenti` (`idInsegnamento`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
