/*
SQLyog Ultimate v9.50 
MySQL - 5.0.51b-community-nt : Database - airlines
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`airlines` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `airlines`;

/*Table structure for table `flights` */

DROP TABLE IF EXISTS `flights`;

CREATE TABLE `flights` (
  `id` bigint(11) NOT NULL auto_increment,
  `year` int(11) default NULL,
  `quarter` int(11) default NULL,
  `month` int(11) default NULL,
  `dayofmonth` int(11) default NULL,
  `dayofweek` int(11) default NULL,
  `flightdate` datetime default NULL,
  `uniquecarrier` varchar(2) default NULL,
  `airlineid` int(11) default NULL,
  `carrier` varchar(2) default NULL,
  `tailnum` varchar(6) default NULL,
  `flightnum` int(11) default NULL,
  `originairportid` int(11) default NULL,
  `originairportseqid` int(11) default NULL,
  `origincitymarketid` int(11) default NULL,
  `origin` varchar(3) default NULL,
  `origincityname` varchar(30) default NULL,
  `originstate` varchar(2) default NULL,
  `originstatefips` int(11) default NULL,
  `originstatename` varchar(14) default NULL,
  `originwac` int(11) default NULL,
  `destairportid` int(11) default NULL,
  `destairportseqid` int(11) default NULL,
  `destcitymarketid` int(11) default NULL,
  `dest` varchar(3) default NULL,
  `destcityname` varchar(21) default NULL,
  `deststate` varchar(2) default NULL,
  `deststatefips` int(11) default NULL,
  `deststatename` varchar(14) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2704 DEFAULT CHARSET=utf8;

/*Data for the table `flights` */

insert  into `flights`(`id`,`year`,`quarter`,`month`,`dayofmonth`,`dayofweek`,`flightdate`,`uniquecarrier`,`airlineid`,`carrier`,`tailnum`,`flightnum`,`originairportid`,`originairportseqid`,`origincitymarketid`,`origin`,`origincityname`,`originstate`,`originstatefips`,`originstatename`,`originwac`,`destairportid`,`destairportseqid`,`destcitymarketid`,`dest`,`destcityname`,`deststate`,`deststatefips`,`deststatename`) values (1,2017,1,1,17,2,'2017-01-17 00:00:00','AA',19805,'AA','N583AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona'),(2,2017,1,1,18,3,'2017-01-18 00:00:00','AA',19805,'AA','N544AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona'),(3,2017,1,1,19,4,'2017-01-19 00:00:00','AA',19805,'AA','N553AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona'),(4,2017,1,1,20,5,'2017-01-20 00:00:00','AA',19805,'AA','N191AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona'),(5,2017,1,1,21,6,'2017-01-21 00:00:00','AA',19805,'AA','N170AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona'),(6,2017,1,1,22,7,'2017-01-22 00:00:00','AA',19805,'AA','N179AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona'),(7,2017,1,1,23,1,'2017-01-23 00:00:00','AA',19805,'AA','N579AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona'),(8,2017,1,1,24,2,'2017-01-24 00:00:00','AA',19805,'AA','N583AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona'),(9,2017,1,1,25,3,'2017-01-25 00:00:00','AA',19805,'AA','N167AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona'),(10,2017,1,1,26,4,'2017-01-26 00:00:00','AA',19805,'AA','N551AA',494,11057,1105703,31057,'CLT','Charlotte, NC','NC',37,'North Carolina',36,14107,1410702,30466,'PHX','Phoenix, AZ','AZ',4,'Arizona');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
