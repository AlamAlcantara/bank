--clean
delete from cliente;

--insert
insert into cliente(identificacion, nombre, genero,fecha_nacimiento,direccion,telefono,constraseña,estado)
values('123456', 'Jose Lema', 'M', '1999-04-05','Otavalo sn y principal', '098254785', '1234', true),
      ('678901', 'Marianela Montalvo', 'F', '1999-04-05','Amazonas y NNUU', '097548965', '5678', true);