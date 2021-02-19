CREATE TABLE `GameData` (
                            `discriminatorType` char NOT NULL,
                            `id` bigint AUTO_INCREMENT,
                            `gameId` smallint(6) NOT NULL,
                            `gameName` varchar(255) NOT NULL,
                            `gameVersion` varchar(5) NULL,
                            `hash` varchar(40) NOT NULL,
                            `rtp` varchar(8) NOT NULL,
                            `enabled` bit default b'0' NOT NULL,
                            `memoryFeatureGame` bit default b'0' NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `IDX_GameId_RTP` (`gameId`,`rtp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PlayerGameContext` (
                                     `contextId` bigint AUTO_INCREMENT,
                                     `playerId` varchar(255),
                                     `gameId` smallint(6),
                                     `historyId` bigint,
                                     `sessionId` varchar(36),
                                     `memoryCtxId` smallint(6) DEFAULT 0,
                                     `totalBet` bigint(20) NOT NULL DEFAULT -1,
                                     `lastModifiedDate` timestamp default CURRENT_TIMESTAMP NOT NULL on update CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`contextId`),
                                     KEY `IDX_PlayerGame` (`playerId`, `gameId`),
                                     KEY `IDX_PlayerGameSession` (`playerId`, `gameId`, `sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `RmgUser` (
                           `userId` varchar(36) NOT NULL,
                           `userName` varchar(36) NOT NULL,
                           `password` varchar(100) NOT NULL,
                           `createdDate` datetime,
                           `lastModifiedDate` datetime,
                           `isActive` tinyint(1) default 1,
                           PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `UserSession` (
                               `discriminatorType` char NOT NULL,
                               `id` bigint AUTO_INCREMENT,
                               `ventureCode` varchar(255) NOT NULL,
                               `userId` varchar(255) NOT NULL,
                               `gameId` smallint(6) NOT NULL,
                               `gameVersion` varchar(5) NULL,
                               `sessionId` varchar(36) NOT NULL,
                               `externalSessionId` varchar(255) null,
                               `authenticated` bit NOT NULL,
                               `playForFun` bit NOT NULL,
                               `deviceType` varchar(255),
                               `os` varchar(255),
                               `playerVersion` varchar(255),
                               `resolution` varchar(255),
                               `browser` varchar(255),
                               `flashFrameRate` int,
                               `ipAddress` varchar(16),
                               `country` varchar(255),
                               `language` varchar(50),
                               `currency` varchar(50),
                               `jurisdiction` varchar(10),
                               `startDate` datetime NOT NULL,
                               `endDate` datetime,
                               `operator_customerId` VARCHAR(255),
                               `operator_birthdate` VARCHAR(50),
                               `operator_gender` int,
                               `operator_lastLogin` VARCHAR(50),
                               `operator_username` VARCHAR(255),
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `IDX_SessionId` (`sessionId`),
                               KEY `IDX_SessionIdAuthPFF` (`sessionId`, `authenticated`, `playForFun`),
                               KEY `IDX_UIdGameIdDate` (`userId`, `gameId`, `startDate`),
                               KEY `IDX_VentureCode` (`ventureCode`),
                               KEY `IDX_UserId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FreeRounds` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `playerId` bigint(20) NOT NULL,
                              `partnerId` int(11) NOT NULL,
                              `gameId` smallint(6) NOT NULL,
                              `externalBonusId` varchar(64),
                              `availableRounds` bigint(20) NOT NULL,
                              `initialRounds` bigint(20) NOT NULL,
                              `coinValue` bigint(20) NOT NULL,
                              `expirationDateTime` datetime NOT NULL,
                              `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `lastModifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `createdBy` varchar(255) NOT NULL,
                              `currencyCode` varchar(3) NOT NULL,
                              `totalWin` bigint(20) NOT NULL DEFAULT '0',
                              `payoutFinalSent` bit default b'0' NOT NULL,
                              PRIMARY KEY (`id`),
                              INDEX `idx_player_partner_game` (`playerId`, `partnerId`, `availableRounds`, `expirationDateTime`, `gameId`)
                                  USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PlayerHistory` (
                                 `discriminatorType` char NOT NULL,
                                 `id` bigint AUTO_INCREMENT,
                                 `transactionId` varchar(36) NULL,
                                 `externalTransactionId` varchar(255),
                                 `userSessionId` bigint NOT NULL,
                                 `gameId` smallint(6) NOT NULL,
                                 `totalBet` bigint NOT NULL,
                                 `startingBalance` bigint NOT NULL,
                                 `spinRequest` longtext NOT NULL,
                                 `spinContext` text,
                                 `bonusSpinContext` text,
                                 `spinResponse` longtext NOT NULL,
                                 `pickResponse` longtext,
                                 `rtp` varchar(8) NOT NULL,
                                 `baseCreditsWon` bigint NOT NULL,
                                 `bonusCreditsWon` bigint NOT NULL,
                                 `wagerState` varchar(50),
                                 `wagerStateReason` varchar(50),
                                 `createdDate` datetime NOT NULL,
                                 `baseSpinTransactionId` varchar(36),
                                 `spinMemoryContext` longtext,
                                 `consumedFreeRoundId` bigint(20),
                                 `gaffe` bit,
                                 `promotionId` bigint(20) DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `FK_UserSessionId` (`userSessionId`),
                                 KEY `IDX_CDate` (`createdDate`),
                                 KEY `FK_consumedFreeRoundId` (`consumedFreeRoundId`),
                                 CONSTRAINT `FK_UserSession` FOREIGN KEY (`userSessionId`) REFERENCES `UserSession` (`id`),
                                 CONSTRAINT `FK_FreeRounds` FOREIGN KEY (`consumedFreeRoundId`) REFERENCES `FreeRounds` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `schemainfo` (
                              `id` bigint AUTO_INCREMENT,
                              `created` datetime,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PlayerGameLastContext` (
  `operatorId` varchar(255) NOT NULL,
  `playerId` varchar(255) NOT NULL,
  `gameId` smallint(6) NOT NULL,
  `totalBet` bigint(20) NOT NULL,
  `spinMemoryContext` longtext NOT NULL,
  `lastModifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`operatorId`,`playerId`,`gameId`,`totalBet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PromotionConfig` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gameId` smallint(6) NOT NULL,
  `gameName` varchar(100) NOT NULL,
  `promoName` varchar(100) NOT NULL,
  `criteria` varchar(100) NOT NULL,
  `terminationNodeId` bigint(20) NOT NULL,
  `coverBetCost` varchar(100) NOT NULL,
  `createdBy` varchar(25) NOT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expirationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE `PromotionEvent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `configId` bigint(20) NOT NULL,
  `betMultiplier` bigint(20) DEFAULT '1',
  `name` varchar(240) NOT NULL,
  `operatorId` varchar(128) NOT NULL,
  `createdBy` varchar(25) NOT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expirationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_JourneyPromotionConfigId` (`configId`),
  CONSTRAINT `FK_JourneyPromotionConfig` FOREIGN KEY (`configId`) REFERENCES `PromotionConfig` (`id`)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE `PromotionPlayer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `eventId` bigint(20) NOT NULL,
  `playerId` varchar(100) NOT NULL,
  `historyId` bigint(20) DEFAULT NULL,
  `consumed` bit(1) NOT NULL DEFAULT b'0',
  `createdBy` varchar(25) NOT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expirationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_PlayerPromotionEventId` (`eventId`),
  CONSTRAINT `FK_PlayerPromotionEvent` FOREIGN KEY (`eventId`) REFERENCES `PromotionEvent` (`id`)
) CHARSET=utf8mb4;

CREATE TABLE `PromotionCurrencyMultiplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `eventId` bigint(20) NOT NULL,
  `currency` varchar(128) NOT NULL,
  `betMultiplier` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_CurrencyPromotionEventId` (`eventId`),
  CONSTRAINT `FK_CurrencyPromotionEvent` FOREIGN KEY (`eventId`) REFERENCES `PromotionEvent` (`id`)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE `GameFile` (
    `id`                bigint(20)   NOT NULL AUTO_INCREMENT,
    `gameId`            smallint(6)  NOT NULL,
    `gameName`          varchar(255) NOT NULL,
    `fileName`          varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `IDX_GameId_RTP` (`gameId`, `fileName`)
) DEFAULT CHARSET=utf8mb4;