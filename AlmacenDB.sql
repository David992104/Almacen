
create database Almacen;
use Almacen;

drop table users;
create table users(
idUsuer int auto_increment key,
nameUser varchar (20) not null,
lastNameUser varchar(30) not null,
emailUser varchar(50) not null,
passUser varchar(50) not null, 
imagenUser longblob
);

drop table material;
create table material(
idMaterial int key,
posicionMaterial int (10),
nombre varchar(20),
categoria varchar(20),
tipo varchar(20),
descripcion varchar(100),
enStock int(20),
maximaCantidad int(10),
minimaCantidad int(10),
imagenMaterial longblob
);
 drop table salidasMaterial;
create table salidasMaterial(
idSalida int auto_increment key,
idMaterial int ,
fecha date,
usuario varchar(50),
cantidadAnterior int(10),
cantidadSalida int(10)
);

drop table entradasmaterial;
create table entradasMaterial(
idEntrada int auto_increment key,
idMaterial int ,
fecha date,
usuario varchar(50),
cantidadAnterior int (10),
cantidadEntrada int(20)
);

create table categoria(
idCategoria int auto_increment key,
nombreCategoria varchar (20),
descripcion varchar (100)
);

create table tipo(
idTipo int auto_increment key,
idCategoria int (10),
nombreTipo varchar (20),
descripcion varchar (100)
);