create database Almacen;
use Almacen;
drop table users;
create table users(
idUsuer int auto_increment key,
nameUser varchar (20),
lastNameUser varchar(30) not null,
emailUser varchar(50) not null,
passUser varchar(50) not null, 
imagenUser longblob
);

create table material(
idMaterial int key,
posicionMaterial int (10),
nombre varchar(20),
categoria varchar(20),
tipo varchar(20),
descripcion varchar(100),
enStock int(20),
imagenMaterial longblob
);

create table salidasMaterial(
idSalida int auto_increment key,
idMaterial int 
);

create table entradasMaterial(
idEntrada int auto_increment key
);