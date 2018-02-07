
-- -----------------------------------------------------
-- Table `person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person` ;

CREATE  TABLE IF NOT EXISTS `person` (
  `idPerson` INT AUTO_INCREMENT ,
  `first_name` VARCHAR(45) NULL ,
  `last_name` VARCHAR(45) NULL ,
  `password` VARCHAR(45) NULL ,
  `isInstrustor` TINYINT(1) NULL DEFAULT 0 ,
  `isAdmin` TINYINT(1) NULL DEFAULT 0 ,
  `user_name` VARCHAR(45) NULL );
  
INSERT INTO person VALUES(1,'Buck','Helmke','test',0,0,'bhelmke');
INSERT INTO person VALUES(2,'Tom','Stone','test',0,0,'tstone');
INSERT INTO person VALUES(3,'Test','Profesor','test',1,0,'tprofesor');
INSERT INTO person VALUES(4,'Test','Prof','test',1,0,'tprof');
INSERT INTO person VALUES(5,'Jeanie','Vanorden','test',0,0,'jvanorden');
INSERT INTO person VALUES(6,'Javier','Frase','test',0,0,'jFrase');
INSERT INTO person VALUES(7,'Kurt','Bublitz','test',0,0,'kBublitz');
INSERT INTO person VALUES(8,'Harriett','Romig','test',0,0,'hRomig');
INSERT INTO person VALUES(9,'Earnestine','Zeh','test',0,0,'eZeh');
INSERT INTO person VALUES(10,'Jamie','Gotto','test',0,0,'jGotto');
INSERT INTO person VALUES(11,'Jami','Talerico','test',0,0,'jTalerico');
INSERT INTO person VALUES(12,'Althea','Kasprzak','test',0,0,'aKasprzak');
INSERT INTO person VALUES(13,'Clinton','Biederman','test',0,0,'cBiederman');
INSERT INTO person VALUES(14,'Darren','Cumberbatch','test',0,0,'dCumberbatch');
INSERT INTO person VALUES(15,'Mathew','Almon','test',0,0,'malmon');
INSERT INTO person VALUES(16,'A','Dmin','test',0,1,'admin');
INSERT INTO person VALUES(17,'S','Uper','test',1,1,'super');



-- -----------------------------------------------------
-- Table `course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `course` ;

CREATE  TABLE IF NOT EXISTS `course` (
  `idCourse` INT AUTO_INCREMENT ,
  `course_name` VARCHAR(45) NULL ,
  `idPerson` INT NOT NULL  ,
  `idScheme` INT NULL  ,
  `idScale` INT NULL ,
  `isArchive` TINYINT(1) NULL DEFAULT 0 );
  
 INSERT INTO course VALUES(1,'CMSC420',3,1,1,0);
 INSERT INTO course VALUES(2,'CMSC101',3,2,2,0);
 INSERT INTO course VALUES(3,'CMSC420 F10',3,3,3,1);
 INSERT INTO course VALUES(4,'CMSC101 F10',4,4,4,1);
 INSERT INTO course VALUES(5,'CMSC491',4,5,5,0);
 INSERT INTO course VALUES(6,'CMSC301',4,6,6,0);

 
 -- -----------------------------------------------------
-- Table `scheme`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheme` ;

CREATE  TABLE IF NOT EXISTS `scheme` (
  `idScheme` INT AUTO_INCREMENT ,
  `scheme_name` VARCHAR(45) NULL ,
  `idPerson` INT NULL );
  
  INSERT INTO scheme VALUES(1,'CMSC420 Scheme',3);
  INSERT INTO scheme VALUES(2,'CMSC101 Scheme',3);
  INSERT INTO scheme VALUES(3,'CMSC420 Scheme',3);
  INSERT INTO scheme VALUES(4,'CMSC101 Scheme',4);
  INSERT INTO scheme VALUES(5,'CMSC491 Scheme',4);
  INSERT INTO scheme VALUES(6,'CMSC301 Scheme',4);

-- -----------------------------------------------------
-- Table `schemeCategories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `schemeCategories` ;

CREATE  TABLE IF NOT EXISTS `schemeCategories` (
  `idSchemeCategories` INT AUTO_INCREMENT ,
  `idScheme` INT NULL ,
  `name` VARCHAR(45) NULL ,
  `hasPrefix` TINYINT(1) NULL DEFAULT 0,
  `name_prefix` VARCHAR(45) NULL ,
  `weight` INT NULL ,
  `strategy` VARCHAR(45) NULL ,
  `has_max_score` TINYINT(1) NULL DEFAULT 0,
  `max_score` VARCHAR(45) NULL ,
  `num_drops` INT NULL );
  
 INSERT INTO schemeCategories VALUES(1,1,'Quiz',1,'Q',30,'Drops Allowed',1,'100',1);
 INSERT INTO schemeCategories VALUES(2,1,'Test',1,'T',40,'Standard',1,'100',0);
 INSERT INTO schemeCategories VALUES(3,1,'Home Work',1,'HW',30,'Drops Allowed',1,'40',2);
 INSERT INTO schemeCategories VALUES(4,2,'Quiz',1,'Qz',20,'Drops Allowed',1,'100',1);
 INSERT INTO schemeCategories VALUES(5,2,'Test',0,'',40,'Standard',1,'100',0);
 INSERT INTO schemeCategories VALUES(6,2,'Project',0,'',40,'Standard',1,'100',0);
 INSERT INTO schemeCategories VALUES(7,3,'Quiz',1,'Q',30,'Drops Allowed',1,'100',1);
 INSERT INTO schemeCategories VALUES(8,3,'Test',1,'T',40,'Standard',1,'100',0);
 INSERT INTO schemeCategories VALUES(9,3,'Home Work',1,'HW',30,'Drops Allowed',1,'40',2);
 INSERT INTO schemeCategories VALUES(10,4,'Quiz',1,'Qz',20,'Drops Allowed',1,'100',1);
 INSERT INTO schemeCategories VALUES(11,4,'Test',0,'',40,'Standard',1,'100',0);
 INSERT INTO schemeCategories VALUES(12,4,'Project',0,'',40,'Standard',1,'100',0);
 INSERT INTO schemeCategories VALUES(13,5,'Quiz',1,'Q',50,'Drops Allowed',1,'100',1);
 INSERT INTO schemeCategories VALUES(14,5,'Project',0,'',50,'Standard',1,'20',0);
 INSERT INTO schemeCategories VALUES(15,6,'Quiz',1,'Qz',20,'Drops Allowed',1,'100',1);
 INSERT INTO schemeCategories VALUES(16,6,'Test',0,'',40,'Standard',1,'100',0);
 INSERT INTO schemeCategories VALUES(17,6,'Project',0,'',40,'Standard',1,'100',0);

 
-- -----------------------------------------------------
-- Table `scale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scale` ;

CREATE  TABLE IF NOT EXISTS `scale` (
  `idScale` INT AUTO_INCREMENT ,
  `scale_name` VARCHAR(45) NULL);
  
  INSERT INTO scale VALUES(1,'CMSC420 Scale');
  INSERT INTO scale VALUES(2,'CMSC101 Scale');
  INSERT INTO scale VALUES(3,'CMSC420 Scale');
  INSERT INTO scale VALUES(4,'CMSC101 Scale');
  INSERT INTO scale VALUES(5,'CMSC491 Scale');
  INSERT INTO scale VALUES(6,'CMSC301 Scale');

-- -----------------------------------------------------
-- Table `scaleDivision`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scaleDivision` ;

CREATE  TABLE IF NOT EXISTS `scaleDivision` (
  `idScaleDivision` INT AUTO_INCREMENT ,
  `idScale` INT NULL ,
  `grade_name` VARCHAR(45) NULL ,
  `grade_cutoff` DOUBLE NULL  );
  
INSERT INTO scaleDivision VALUES(1,1,'A',90);
INSERT INTO scaleDivision VALUES(2,1,'B',80);
INSERT INTO scaleDivision VALUES(3,1,'C',70);
INSERT INTO scaleDivision VALUES(4,1,'D',60);
INSERT INTO scaleDivision VALUES(5,1,'F',0);

INSERT INTO scaleDivision VALUES(6,2,'A',85);
INSERT INTO scaleDivision VALUES(7,2,'B',75);
INSERT INTO scaleDivision VALUES(8,2,'C',65);
INSERT INTO scaleDivision VALUES(9,2,'F',0);

INSERT INTO scaleDivision VALUES(10,3,'A',90);
INSERT INTO scaleDivision VALUES(11,3,'B',80);
INSERT INTO scaleDivision VALUES(12,3,'C',70);
INSERT INTO scaleDivision VALUES(13,3,'D',60);
INSERT INTO scaleDivision VALUES(14,3,'F',0);

INSERT INTO scaleDivision VALUES(15,4,'A',85);
INSERT INTO scaleDivision VALUES(16,4,'B',75);
INSERT INTO scaleDivision VALUES(17,4,'C',65);
INSERT INTO scaleDivision VALUES(18,4,'F',0);

INSERT INTO scaleDivision VALUES(19,5,'Pass',60);
INSERT INTO scaleDivision VALUES(20,5,'Fail',0);

INSERT INTO scaleDivision VALUES(21,6,'A',94);
INSERT INTO scaleDivision VALUES(22,6,'A-',90);
INSERT INTO scaleDivision VALUES(23,6,'B+',87);
INSERT INTO scaleDivision VALUES(24,6,'B',84);
INSERT INTO scaleDivision VALUES(25,6,'B-',80);
INSERT INTO scaleDivision VALUES(26,6,'C+',77);
INSERT INTO scaleDivision VALUES(27,6,'C',74);
INSERT INTO scaleDivision VALUES(28,6,'C-',70);
INSERT INTO scaleDivision VALUES(29,6,'D+',67);
INSERT INTO scaleDivision VALUES(30,6,'D',64);
INSERT INTO scaleDivision VALUES(31,6,'D-',60);
INSERT INTO scaleDivision VALUES(32,6,'F',0);



-- -----------------------------------------------------
-- Table `roll`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roll` ;

CREATE  TABLE IF NOT EXISTS `roll` (
  `idRoll` INT AUTO_INCREMENT ,
  `idCourse` INT NULL ,
  `idPerson` INT NULL  );
  
  INSERT INTO roll VALUES(1,1,1);
  INSERT INTO roll VALUES(2,1,2);
  INSERT INTO roll VALUES(3,1,7);
  INSERT INTO roll VALUES(4,1,8);
  INSERT INTO roll VALUES(5,1,5);
  INSERT INTO roll VALUES(6,1,6);
  
  INSERT INTO roll VALUES(7,2,9);
  INSERT INTO roll VALUES(8,2,10);
  INSERT INTO roll VALUES(9,2,11);
  INSERT INTO roll VALUES(10,2,12);
  INSERT INTO roll VALUES(11,2,13);
  INSERT INTO roll VALUES(12,2,14);
  
  INSERT INTO roll VALUES(13,3,9);
  INSERT INTO roll VALUES(14,3,10);
  INSERT INTO roll VALUES(15,3,11);
  INSERT INTO roll VALUES(16,3,12);
  INSERT INTO roll VALUES(17,3,13);
  INSERT INTO roll VALUES(18,3,14);
  
  INSERT INTO roll VALUES(19,4,1);
  INSERT INTO roll VALUES(20,4,2);
  INSERT INTO roll VALUES(21,4,7);
  INSERT INTO roll VALUES(22,4,8);
  INSERT INTO roll VALUES(23,4,5);
  INSERT INTO roll VALUES(24,4,6);
  
  INSERT INTO roll VALUES(25,5,1);
  INSERT INTO roll VALUES(26,5,14);
  INSERT INTO roll VALUES(27,5,2);
  INSERT INTO roll VALUES(28,5,13);
  INSERT INTO roll VALUES(29,5,5);
  INSERT INTO roll VALUES(30,5,12);
  
  INSERT INTO roll VALUES(31,6,6);
  INSERT INTO roll VALUES(32,6,11);
  INSERT INTO roll VALUES(33,6,7);
  INSERT INTO roll VALUES(34,6,10);
  INSERT INTO roll VALUES(35,6,8);
  INSERT INTO roll VALUES(36,6,9);

  
  


-- -----------------------------------------------------
-- Table `assignment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignment` ;

CREATE  TABLE IF NOT EXISTS `assignment` (
  `idAssignment` INT AUTO_INCREMENT ,
  `idCourse` INT NULL ,
  `idSchemeCategories` INT NULL ,
  `assignment_name` VARCHAR(45) NULL,
  `assignment_prefix` VARCHAR(45) NULL,
  `assignment_num` INT NULL  );
  
INSERT INTO `assignment` VALUES(1,1,1,'Q1','Q',1);
INSERT INTO `assignment` VALUES(2,1,1,'Q2','Q',2);
INSERT INTO `assignment` VALUES(3,1,1,'Q3','Q',3);
INSERT INTO `assignment` VALUES(4,1,2,'Test1','Test',1);
INSERT INTO `assignment` VALUES(5,1,2,'Test2','Test',2);
INSERT INTO `assignment` VALUES(6,1,3,'HW1','Q',1);
INSERT INTO `assignment` VALUES(7,1,3,'HW2','Q',2);
INSERT INTO `assignment` VALUES(8,1,3,'HW3','Q',3);
INSERT INTO `assignment` VALUES(9,1,3,'HW4','Q',4);
INSERT INTO `assignment` VALUES(10,1,3,'HW5','Q',5);

INSERT INTO `assignment` VALUES(11,2,4,'Qz1','Qz',1);
INSERT INTO `assignment` VALUES(12,2,4,'Qz2','Qz',2);
INSERT INTO `assignment` VALUES(13,2,4,'Qz3','Qz',3);
INSERT INTO `assignment` VALUES(14,2,5,'Final','Test',1);
INSERT INTO `assignment` VALUES(15,2,6,'Pr 1','',1);
INSERT INTO `assignment` VALUES(16,2,6,'Pr 2','',2);
INSERT INTO `assignment` VALUES(17,2,6,'Pr 3','',3);
INSERT INTO `assignment` VALUES(18,2,6,'Group 1','',4);
INSERT INTO `assignment` VALUES(19,2,6,'Group 2','',5);
INSERT INTO `assignment` VALUES(20,2,6,'Final Project','',6);

INSERT INTO `assignment` VALUES(21,3,7,'Q1','Q',1);
INSERT INTO `assignment` VALUES(22,3,7,'Q2','Q',2);
INSERT INTO `assignment` VALUES(23,3,7,'Q3','Q',3);
INSERT INTO `assignment` VALUES(24,3,8,'Test1','Test',1);
INSERT INTO `assignment` VALUES(25,3,8,'Test2','Test',2);
INSERT INTO `assignment` VALUES(26,3,9,'HW1','Q',1);
INSERT INTO `assignment` VALUES(27,3,9,'HW2','Q',2);
INSERT INTO `assignment` VALUES(28,3,9,'HW3','Q',3);
INSERT INTO `assignment` VALUES(29,3,9,'HW4','Q',4);
INSERT INTO `assignment` VALUES(30,3,9,'HW5','Q',5);

INSERT INTO `assignment` VALUES(31,4,10,'Qz1','Qz',1);
INSERT INTO `assignment` VALUES(32,4,10,'Qz2','Qz',2);
INSERT INTO `assignment` VALUES(33,4,10,'Qz3','Qz',3);
INSERT INTO `assignment` VALUES(34,4,11,'Final','Test',1);
INSERT INTO `assignment` VALUES(35,4,12,'Pr 1','',1);
INSERT INTO `assignment` VALUES(36,4,12,'Pr 2','',2);
INSERT INTO `assignment` VALUES(37,4,12,'Pr 3','',3);
INSERT INTO `assignment` VALUES(38,4,12,'Group 1','',4);
INSERT INTO `assignment` VALUES(39,4,12,'Group 2','',5);
INSERT INTO `assignment` VALUES(40,4,12,'Final Project','',6);

INSERT INTO `assignment` VALUES(41,5,13,'Quiz 1','Q',1);
INSERT INTO `assignment` VALUES(42,5,13,'Quiz 2','Q',2);
INSERT INTO `assignment` VALUES(43,5,13,'Quiz 3','Q',3);
INSERT INTO `assignment` VALUES(44,5,14,'Project 1','',1);
INSERT INTO `assignment` VALUES(45,5,14,'Project 2','',2);
INSERT INTO `assignment` VALUES(46,5,14,'Project 3','',3);

INSERT INTO `assignment` VALUES(47,6,15,'Qz 1','Qz',1);
INSERT INTO `assignment` VALUES(48,6,15,'Qz 2','Qz',2);
INSERT INTO `assignment` VALUES(49,6,16,'Test','',1);
INSERT INTO `assignment` VALUES(50,6,12,'Project','',1);


-- -----------------------------------------------------
-- Table `grade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grade` ;

CREATE  TABLE IF NOT EXISTS `grade` (
  `idGrade` INT AUTO_INCREMENT ,
  `idAssignment` INT NULL ,
  `idPerson` INT NULL ,
  `grade` VARCHAR(45) NULL,
  `excuse` TINYINT(1) NULL DEFAULT 0,
  `drop_grade` TINYINT(1) NULL DEFAULT 0,
  `max_grade` INT NULL);
  

INSERT INTO `grade` VALUES(1,1,1,'100',0,0,100);
INSERT INTO `grade` VALUES(2,1,2,'95',0,0,100);
INSERT INTO `grade` VALUES(3,1,5,'80',0,0,100);
INSERT INTO `grade` VALUES(4,1,6,'78',0,0,100);
INSERT INTO `grade` VALUES(5,1,7,'86',0,0,100);
INSERT INTO `grade` VALUES(6,1,8,'92',0,0,100);
INSERT INTO `grade` VALUES(7,2,1,'89',0,0,100);
INSERT INTO `grade` VALUES(8,2,2,'34',0,0,100);
INSERT INTO `grade` VALUES(9,2,5,'98',0,0,100);
INSERT INTO `grade` VALUES(10,2,6,'79',0,0,100);
INSERT INTO `grade` VALUES(11,2,7,'85',0,0,100);
INSERT INTO `grade` VALUES(12,2,8,'83',0,0,100);
INSERT INTO `grade` VALUES(13,3,1,'99',0,0,100);
INSERT INTO `grade` VALUES(14,3,2,'97',0,0,100);
INSERT INTO `grade` VALUES(15,3,5,'67',0,0,100);
INSERT INTO `grade` VALUES(16,3,6,'100',0,0,100);
INSERT INTO `grade` VALUES(17,3,7,'99',0,0,100);
INSERT INTO `grade` VALUES(18,3,8,'81',0,0,100);
INSERT INTO `grade` VALUES(19,4,1,'95',0,0,100);
INSERT INTO `grade` VALUES(20,4,2,'92',0,0,100);
INSERT INTO `grade` VALUES(21,4,5,'88',0,0,100);
INSERT INTO `grade` VALUES(22,4,6,'87',0,0,100);
INSERT INTO `grade` VALUES(23,4,7,'86',0,0,100);
INSERT INTO `grade` VALUES(24,4,8,'86',0,0,100);
INSERT INTO `grade` VALUES(25,5,1,'98',0,0,100);
INSERT INTO `grade` VALUES(26,5,2,'94',0,0,100);
INSERT INTO `grade` VALUES(27,5,5,'86',0,0,100);
INSERT INTO `grade` VALUES(28,5,6,'88',0,0,100);
INSERT INTO `grade` VALUES(29,5,7,'70',0,0,100);
INSERT INTO `grade` VALUES(30,5,8,'69',0,0,100);
INSERT INTO `grade` VALUES(31,6,1,'40',0,0,40);
INSERT INTO `grade` VALUES(32,6,2,'40',0,0,40);
INSERT INTO `grade` VALUES(33,6,5,'40',0,0,40);
INSERT INTO `grade` VALUES(34,6,6,'40',0,0,40);
INSERT INTO `grade` VALUES(35,6,7,'35',0,0,40);
INSERT INTO `grade` VALUES(36,6,8,'35',0,0,40);
INSERT INTO `grade` VALUES(37,7,1,'40',0,0,40);
INSERT INTO `grade` VALUES(38,7,2,'30',0,0,40);
INSERT INTO `grade` VALUES(39,7,5,'35',0,0,40);
INSERT INTO `grade` VALUES(40,7,6,'40',0,0,40);
INSERT INTO `grade` VALUES(41,7,7,'40',0,0,40);
INSERT INTO `grade` VALUES(42,7,8,'35',0,0,40);
INSERT INTO `grade` VALUES(43,8,1,'39',0,0,40);
INSERT INTO `grade` VALUES(44,8,2,'39',0,0,40);
INSERT INTO `grade` VALUES(45,8,5,'40',0,0,40);
INSERT INTO `grade` VALUES(46,8,6,'30',0,0,40);
INSERT INTO `grade` VALUES(47,8,7,'16',0,0,40);
INSERT INTO `grade` VALUES(48,8,8,'20',0,0,40);
INSERT INTO `grade` VALUES(49,9,1,'40',0,0,40);
INSERT INTO `grade` VALUES(50,9,2,'18',0,0,40);
INSERT INTO `grade` VALUES(51,9,5,'0',0,0,40);
INSERT INTO `grade` VALUES(52,9,6,'40',0,0,40);
INSERT INTO `grade` VALUES(53,9,7,'38',0,0,40);
INSERT INTO `grade` VALUES(54,9,8,'32',0,0,40);
INSERT INTO `grade` VALUES(55,10,1,'39',0,0,40);
INSERT INTO `grade` VALUES(56,10,2,'39',0,0,40);
INSERT INTO `grade` VALUES(57,10,5,'39',0,0,40);
INSERT INTO `grade` VALUES(58,10,6,'20',0,0,40);
INSERT INTO `grade` VALUES(59,10,7,'39',0,0,40);
INSERT INTO `grade` VALUES(60,10,8,'15',0,0,40);
INSERT INTO `grade` VALUES(61,11,9,'89',0,0,100);
INSERT INTO `grade` VALUES(62,11,10,'98',0,0,100);
INSERT INTO `grade` VALUES(63,11,11,'78',0,0,100);
INSERT INTO `grade` VALUES(64,11,12,'98',0,0,100);
INSERT INTO `grade` VALUES(65,11,13,'95',0,0,100);
INSERT INTO `grade` VALUES(66,11,14,'85',0,0,100);
INSERT INTO `grade` VALUES(67,12,9,'76',0,0,100);
INSERT INTO `grade` VALUES(68,12,10,'94',0,0,100);
INSERT INTO `grade` VALUES(69,12,11,'85',0,0,100);
INSERT INTO `grade` VALUES(70,12,12,'73',0,0,100);
INSERT INTO `grade` VALUES(71,12,13,'94',0,0,100);
INSERT INTO `grade` VALUES(72,12,14,'75',0,0,100);
INSERT INTO `grade` VALUES(73,13,9,'92',0,0,100);
INSERT INTO `grade` VALUES(74,13,10,'86',0,0,100);
INSERT INTO `grade` VALUES(75,13,11,'94',0,0,100);
INSERT INTO `grade` VALUES(76,13,12,'82',0,0,100);
INSERT INTO `grade` VALUES(77,13,13,'99',0,0,100);
INSERT INTO `grade` VALUES(78,13,14,'75',0,0,100);
INSERT INTO `grade` VALUES(79,14,9,'94',0,0,100);
INSERT INTO `grade` VALUES(80,14,10,'85',0,0,100);
INSERT INTO `grade` VALUES(81,14,11,'76',0,0,100);
INSERT INTO `grade` VALUES(82,14,12,'75',0,0,100);
INSERT INTO `grade` VALUES(83,14,13,'69',0,0,100);
INSERT INTO `grade` VALUES(84,14,14,'98',0,0,100);
INSERT INTO `grade` VALUES(85,15,9,'78',0,0,100);
INSERT INTO `grade` VALUES(86,15,10,'86',0,0,100);
INSERT INTO `grade` VALUES(87,15,11,'60',0,0,100);
INSERT INTO `grade` VALUES(88,15,12,'68',0,0,100);
INSERT INTO `grade` VALUES(89,15,13,'75',0,0,100);
INSERT INTO `grade` VALUES(90,15,14,'98',0,0,100);
INSERT INTO `grade` VALUES(91,16,9,'49',0,0,100);
INSERT INTO `grade` VALUES(92,16,10,'94',0,0,100);
INSERT INTO `grade` VALUES(93,16,11,'86',0,0,100);
INSERT INTO `grade` VALUES(94,16,12,'76',0,0,100);
INSERT INTO `grade` VALUES(95,16,13,'94',0,0,100);
INSERT INTO `grade` VALUES(96,16,14,'86',0,0,100);
INSERT INTO `grade` VALUES(97,17,9,'91',0,0,100);
INSERT INTO `grade` VALUES(98,17,10,'96',0,0,100);
INSERT INTO `grade` VALUES(99,17,11,'85',0,0,100);
INSERT INTO `grade` VALUES(100,17,12,'76',0,0,100);
INSERT INTO `grade` VALUES(101,17,13,'96',0,0,100);
INSERT INTO `grade` VALUES(102,17,14,'75',0,0,100);
INSERT INTO `grade` VALUES(103,18,9,'94',0,0,100);
INSERT INTO `grade` VALUES(104,18,10,'91',0,0,100);
INSERT INTO `grade` VALUES(105,18,11,'86',0,0,100);
INSERT INTO `grade` VALUES(106,18,12,'76',0,0,100);
INSERT INTO `grade` VALUES(107,18,13,'55',0,0,100);
INSERT INTO `grade` VALUES(108,18,14,'75',0,0,100);
INSERT INTO `grade` VALUES(109,19,9,'77',0,0,100);
INSERT INTO `grade` VALUES(110,19,10,'79',0,0,100);
INSERT INTO `grade` VALUES(111,19,11,'98',0,0,100);
INSERT INTO `grade` VALUES(112,19,12,'76',0,0,100);
INSERT INTO `grade` VALUES(113,19,13,'84',0,0,100);
INSERT INTO `grade` VALUES(114,19,14,'61',0,0,100);
INSERT INTO `grade` VALUES(115,20,9,'80',0,0,100);
INSERT INTO `grade` VALUES(116,20,10,'74',0,0,100);
INSERT INTO `grade` VALUES(117,20,11,'80',0,0,100);
INSERT INTO `grade` VALUES(118,20,12,'95',0,0,100);
INSERT INTO `grade` VALUES(119,20,13,'76',0,0,100);
INSERT INTO `grade` VALUES(120,20,14,'99',0,0,100);
INSERT INTO `grade` VALUES(121,21,9,'85',0,0,100);
INSERT INTO `grade` VALUES(122,21,10,'96',0,0,100);
INSERT INTO `grade` VALUES(123,21,11,'78',0,0,100);
INSERT INTO `grade` VALUES(124,21,12,'96',0,0,100);
INSERT INTO `grade` VALUES(125,21,13,'84',0,0,100);
INSERT INTO `grade` VALUES(126,21,14,'96',0,0,100);
INSERT INTO `grade` VALUES(127,22,9,'85',0,0,100);
INSERT INTO `grade` VALUES(128,22,10,'76',0,0,100);
INSERT INTO `grade` VALUES(129,22,11,'94',0,0,100);
INSERT INTO `grade` VALUES(130,22,12,'85',0,0,100);
INSERT INTO `grade` VALUES(131,22,13,'96',0,0,100);
INSERT INTO `grade` VALUES(132,22,14,'97',0,0,100);
INSERT INTO `grade` VALUES(133,23,9,'65',0,0,100);
INSERT INTO `grade` VALUES(134,23,10,'84',0,0,100);
INSERT INTO `grade` VALUES(135,23,11,'90',0,0,100);
INSERT INTO `grade` VALUES(136,23,12,'97',0,0,100);
INSERT INTO `grade` VALUES(137,23,13,'94',0,0,100);
INSERT INTO `grade` VALUES(138,23,14,'91',0,0,100);
INSERT INTO `grade` VALUES(139,24,9,'96',0,0,100);
INSERT INTO `grade` VALUES(140,24,10,'80',0,0,100);
INSERT INTO `grade` VALUES(141,24,11,'70',0,0,100);
INSERT INTO `grade` VALUES(142,24,12,'85',0,0,100);
INSERT INTO `grade` VALUES(143,24,13,'75',0,0,100);
INSERT INTO `grade` VALUES(144,24,14,'74',0,0,100);
INSERT INTO `grade` VALUES(145,25,9,'79',0,0,100);
INSERT INTO `grade` VALUES(146,25,10,'85',0,0,100);
INSERT INTO `grade` VALUES(147,25,11,'94',0,0,100);
INSERT INTO `grade` VALUES(148,25,12,'59',0,0,100);
INSERT INTO `grade` VALUES(149,25,13,'69',0,0,100);
INSERT INTO `grade` VALUES(150,25,14,'88',0,0,100);
INSERT INTO `grade` VALUES(151,26,9,'40',0,0,40);
INSERT INTO `grade` VALUES(152,26,10,'39',0,0,40);
INSERT INTO `grade` VALUES(153,26,11,'38',0,0,40);
INSERT INTO `grade` VALUES(154,26,12,'18',0,0,40);
INSERT INTO `grade` VALUES(155,26,13,'27',0,0,40);
INSERT INTO `grade` VALUES(156,26,14,'38',0,0,40);
INSERT INTO `grade` VALUES(157,27,9,'15',0,0,40);
INSERT INTO `grade` VALUES(158,27,10,'11',0,0,40);
INSERT INTO `grade` VALUES(159,27,11,'37',0,0,40);
INSERT INTO `grade` VALUES(160,27,12,'29',0,0,40);
INSERT INTO `grade` VALUES(161,27,13,'40',0,0,40);
INSERT INTO `grade` VALUES(162,27,14,'39',0,0,40);
INSERT INTO `grade` VALUES(163,28,9,'25',0,0,40);
INSERT INTO `grade` VALUES(164,28,10,'16',0,0,40);
INSERT INTO `grade` VALUES(165,28,11,'22',0,0,40);
INSERT INTO `grade` VALUES(166,28,12,'23',0,0,40);
INSERT INTO `grade` VALUES(167,28,13,'19',0,0,40);
INSERT INTO `grade` VALUES(168,28,14,'39',0,0,40);
INSERT INTO `grade` VALUES(169,29,9,'39',0,0,40);
INSERT INTO `grade` VALUES(170,29,10,'33',0,0,40);
INSERT INTO `grade` VALUES(171,29,11,'21',0,0,40);
INSERT INTO `grade` VALUES(172,29,12,'38',0,0,40);
INSERT INTO `grade` VALUES(173,29,13,'34',0,0,40);
INSERT INTO `grade` VALUES(174,29,14,'31',0,0,40);
INSERT INTO `grade` VALUES(175,30,9,'30',0,0,40);
INSERT INTO `grade` VALUES(176,30,10,'39',0,0,40);
INSERT INTO `grade` VALUES(177,30,11,'35',0,0,40);
INSERT INTO `grade` VALUES(178,30,12,'25',0,0,40);
INSERT INTO `grade` VALUES(179,30,13,'16',0,0,40);
INSERT INTO `grade` VALUES(180,30,14,'37',0,0,40);
INSERT INTO `grade` VALUES(181,31,1,'100',0,0,100);
INSERT INTO `grade` VALUES(182,31,2,'95',0,0,100);
INSERT INTO `grade` VALUES(183,31,5,'94',0,0,100);
INSERT INTO `grade` VALUES(184,31,6,'96',0,0,100);
INSERT INTO `grade` VALUES(185,31,7,'92',0,0,100);
INSERT INTO `grade` VALUES(186,31,8,'85',0,0,100);
INSERT INTO `grade` VALUES(187,32,1,'100',0,0,100);
INSERT INTO `grade` VALUES(188,32,2,'89',0,0,100);
INSERT INTO `grade` VALUES(189,32,5,'96',0,0,100);
INSERT INTO `grade` VALUES(190,32,6,'75',0,0,100);
INSERT INTO `grade` VALUES(191,32,7,'85',0,0,100);
INSERT INTO `grade` VALUES(192,32,8,'76',0,0,100);
INSERT INTO `grade` VALUES(193,33,1,'99',0,0,100);
INSERT INTO `grade` VALUES(194,33,2,'89',0,0,100);
INSERT INTO `grade` VALUES(195,33,5,'75',0,0,100);
INSERT INTO `grade` VALUES(196,33,6,'90',0,0,100);
INSERT INTO `grade` VALUES(197,33,7,'96',0,0,100);
INSERT INTO `grade` VALUES(198,33,8,'94',0,0,100);
INSERT INTO `grade` VALUES(199,34,1,'93',0,0,100);
INSERT INTO `grade` VALUES(200,34,2,'90',0,0,100);
INSERT INTO `grade` VALUES(201,34,5,'89',0,0,100);
INSERT INTO `grade` VALUES(202,34,6,'88',0,0,100);
INSERT INTO `grade` VALUES(203,34,7,'87',0,0,100);
INSERT INTO `grade` VALUES(204,34,8,'85',0,0,100);
INSERT INTO `grade` VALUES(205,35,1,'95',0,0,100);
INSERT INTO `grade` VALUES(206,35,2,'98',0,0,100);
INSERT INTO `grade` VALUES(207,35,5,'100',0,0,100);
INSERT INTO `grade` VALUES(208,35,6,'96',0,0,100);
INSERT INTO `grade` VALUES(209,35,7,'78',0,0,100);
INSERT INTO `grade` VALUES(210,35,8,'96',0,0,100);
INSERT INTO `grade` VALUES(211,36,1,'99',0,0,100);
INSERT INTO `grade` VALUES(212,36,2,'98',0,0,100);
INSERT INTO `grade` VALUES(213,36,5,'98',0,0,100);
INSERT INTO `grade` VALUES(214,36,6,'98',0,0,100);
INSERT INTO `grade` VALUES(215,36,7,'77',0,0,100);
INSERT INTO `grade` VALUES(216,36,8,'45',0,0,100);
INSERT INTO `grade` VALUES(217,37,1,'96',0,0,100);
INSERT INTO `grade` VALUES(218,37,2,'99',0,0,100);
INSERT INTO `grade` VALUES(219,37,5,'78',0,0,100);
INSERT INTO `grade` VALUES(220,37,6,'100',0,0,100);
INSERT INTO `grade` VALUES(221,37,7,'87',0,0,100);
INSERT INTO `grade` VALUES(222,37,8,'96',0,0,100);
INSERT INTO `grade` VALUES(223,38,1,'90',0,0,100);
INSERT INTO `grade` VALUES(224,38,2,'84',0,0,100);
INSERT INTO `grade` VALUES(225,38,5,'92',0,0,100);
INSERT INTO `grade` VALUES(226,38,6,'86',0,0,100);
INSERT INTO `grade` VALUES(227,38,7,'97',0,0,100);
INSERT INTO `grade` VALUES(228,38,8,'93',0,0,100);
INSERT INTO `grade` VALUES(229,39,1,'97',0,0,100);
INSERT INTO `grade` VALUES(230,39,2,'56',0,0,100);
INSERT INTO `grade` VALUES(231,39,5,'100',0,0,100);
INSERT INTO `grade` VALUES(232,39,6,'88',0,0,100);
INSERT INTO `grade` VALUES(233,39,7,'76',0,0,100);
INSERT INTO `grade` VALUES(234,39,8,'94',0,0,100);
INSERT INTO `grade` VALUES(235,40,1,'88',0,0,100);
INSERT INTO `grade` VALUES(236,40,2,'73',0,0,100);
INSERT INTO `grade` VALUES(237,40,5,'71',0,0,100);
INSERT INTO `grade` VALUES(238,40,6,'70',0,0,100);
INSERT INTO `grade` VALUES(239,40,7,'69',0,0,100);
INSERT INTO `grade` VALUES(240,40,8,'80',0,0,100);
INSERT INTO `grade` VALUES(241,41,1,'90',0,0,100);
INSERT INTO `grade` VALUES(242,41,2,'95',0,0,100);
INSERT INTO `grade` VALUES(243,41,5,'0',0,0,100);
INSERT INTO `grade` VALUES(244,41,12,'88',0,0,100);
INSERT INTO `grade` VALUES(245,41,13,'76',0,0,100);
INSERT INTO `grade` VALUES(246,41,14,'94',0,0,100);
INSERT INTO `grade` VALUES(247,42,1,'79',0,0,100);
INSERT INTO `grade` VALUES(248,42,2,'92',0,0,100);
INSERT INTO `grade` VALUES(249,42,5,'75',0,0,100);
INSERT INTO `grade` VALUES(250,42,12,'46',0,0,100);
INSERT INTO `grade` VALUES(251,42,13,'0',0,0,100);
INSERT INTO `grade` VALUES(252,42,14,'99',0,0,100);
INSERT INTO `grade` VALUES(253,43,1,'100',0,0,100);
INSERT INTO `grade` VALUES(254,43,2,'69',0,0,100);
INSERT INTO `grade` VALUES(255,43,5,'78',0,0,100);
INSERT INTO `grade` VALUES(256,43,12,'48',0,0,100);
INSERT INTO `grade` VALUES(257,43,13,'100',0,0,100);
INSERT INTO `grade` VALUES(258,43,14,'88',0,0,100);
INSERT INTO `grade` VALUES(259,44,1,'20',0,0,20);
INSERT INTO `grade` VALUES(260,44,2,'20',0,0,20);
INSERT INTO `grade` VALUES(261,44,5,'19',0,0,20);
INSERT INTO `grade` VALUES(262,44,12,'15',0,0,20);
INSERT INTO `grade` VALUES(263,44,13,'10',0,0,20);
INSERT INTO `grade` VALUES(264,44,14,'8',0,0,20);
INSERT INTO `grade` VALUES(265,45,1,'16',0,0,20);
INSERT INTO `grade` VALUES(266,45,2,'19',0,0,20);
INSERT INTO `grade` VALUES(267,45,5,'13',0,0,20);
INSERT INTO `grade` VALUES(268,45,12,'9',0,0,20);
INSERT INTO `grade` VALUES(269,45,13,'20',0,0,20);
INSERT INTO `grade` VALUES(270,45,14,'20',0,0,20);
INSERT INTO `grade` VALUES(271,46,1,'20',0,0,20);
INSERT INTO `grade` VALUES(272,46,2,'20',0,0,20);
INSERT INTO `grade` VALUES(273,46,5,'19',0,0,20);
INSERT INTO `grade` VALUES(274,46,12,'16',0,0,20);
INSERT INTO `grade` VALUES(275,46,13,'18',0,0,20);
INSERT INTO `grade` VALUES(276,46,14,'14',0,0,20);
INSERT INTO `grade` VALUES(277,47,6,'98',0,0,100);
INSERT INTO `grade` VALUES(278,47,7,'95',0,0,100);
INSERT INTO `grade` VALUES(279,47,8,'91',0,0,100);
INSERT INTO `grade` VALUES(280,47,9,'94',0,0,100);
INSERT INTO `grade` VALUES(281,47,10,'75',0,0,100);
INSERT INTO `grade` VALUES(282,47,11,'82',0,0,100);
INSERT INTO `grade` VALUES(283,48,6,'90',0,0,100);
INSERT INTO `grade` VALUES(284,48,7,'81',0,0,100);
INSERT INTO `grade` VALUES(285,48,8,'76',0,0,100);
INSERT INTO `grade` VALUES(286,48,9,'82',0,0,100);
INSERT INTO `grade` VALUES(287,48,10,'91',0,0,100);
INSERT INTO `grade` VALUES(288,48,11,'94',0,0,100);
INSERT INTO `grade` VALUES(289,49,6,'85',0,0,100);
INSERT INTO `grade` VALUES(290,49,7,'93',0,0,100);
INSERT INTO `grade` VALUES(291,49,8,'94',0,0,100);
INSERT INTO `grade` VALUES(292,49,9,'75',0,0,100);
INSERT INTO `grade` VALUES(293,49,10,'92',0,0,100);
INSERT INTO `grade` VALUES(294,49,11,'100',0,0,100);
INSERT INTO `grade` VALUES(295,50,6,'94',0,0,100);
INSERT INTO `grade` VALUES(296,50,7,'0',0,0,100);
INSERT INTO `grade` VALUES(297,50,8,'97',0,0,100);
INSERT INTO `grade` VALUES(298,50,9,'77',0,0,100);
INSERT INTO `grade` VALUES(299,50,10,'85',0,0,100);
INSERT INTO `grade` VALUES(300,50,11,'91',0,0,100);

  
 




