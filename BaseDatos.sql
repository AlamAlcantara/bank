create database DevsuBank;

--TABLA CLIENTE
create table cliente(
id int not null generated always as identity,
identificacion varchar not null unique,
nombre varchar(50) not null,
genero char not null check (Genero in ('M','F')),
fecha_nacimiento date not null,
direccion varchar(255) not null,
telefono varchar not null,
constraseña varchar not null,
estado boolean not null,

primary key (id));


--TABLA TIPO DE CUENTA
create table tipo_cuenta(
id int not null generated always as identity,
nombre varchar not null unique,

primary key (id));


--TABLA CUENTA
create table cuenta(
numero_cuenta int not null unique,
id_tipo int not null,
saldo numeric(10,2) not null default 0,
id_cliente int not null,
estado boolean not null default false,

primary key (numero_cuenta),
constraint fk_cliente foreign key(id_cliente) references cliente(id)
);

--TABLA TIPO DE MOVIMIENTO
create table tipo_movimiento(
id int not null generated always as identity,
nombre varchar not null unique,

primary key (id));

--TABLA MOVIMIENTO
create table movimiento(
id int not null generated always as identity,
id_cuenta int not null,
id_tipo_movimiento int not null,
saldo_inicial numeric(10,2) not null,
saldo_disponible numeric(10,2) not null,  
valor numeric(10,2) not null,
fecha date not null default now(),

primary key (id),
constraint fk_cuenta foreign key(id_cuenta) references cuenta(numero_cuenta),
constraint fk_tipo_movimiento foreign key(id_tipo_movimiento) references tipo_movimiento(id)
);