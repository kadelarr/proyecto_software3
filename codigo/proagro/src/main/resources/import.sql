--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data insert insert into  the database using SQL statements
--insert insert into  Member_pro (id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212');
insert into usuario (identificacion,nombre,apellido,email,password,rol, telefono,sesionIniciada) values ('12345','Camilo','Hoyos','camilo@gmail.com','12345','ADMINISTRADOR','54321', true);
insert into lote (id_lote, area) values ('1',	2.16);
insert into lote (id_lote, area) values ('3',	1.4);
insert into lote (id_lote, area) values ('4',	3.4);
insert into lote (id_lote, area) values ('5',	3.8);
insert into lote (id_lote, area) values ('6',	2.38);
insert into lote (id_lote, area) values ('7',	2.16);
insert into lote (id_lote, area) values ('8',	3.26);
insert into lote (id_lote, area) values ('9',	1.89);
insert into lote (id_lote, area) values ('10',	1.2);
insert into lote (id_lote, area) values ('11',	1.3);
insert into lote (id_lote, area) values ('12',	1.11);
insert into lote (id_lote, area) values ('13',	3.8);
insert into lote (id_lote, area) values ('14',	2.03);
insert into lote (id_lote, area) values ('15',	1.88);
insert into lote (id_lote, area) values ('16',	2.34);
insert into lote (id_lote, area) values ('17',	1.87);
insert into lote (id_lote, area) values ('18',	0.94);
insert into lote (id_lote, area) values ('19',	2.11);
insert into lote (id_lote, area) values ('20',	0.87);
insert into lote (id_lote, area) values ('21',	1.9);
insert into lote (id_lote, area) values ('22',	2.86);
insert into lote (id_lote, area) values ('23',	1.94);
insert into lote (id_lote, area) values ('24',	1.61);
insert into lote (id_lote, area) values ('25',	3.55);
insert into lote (id_lote, area) values ('26',	4.5);
insert into lote (id_lote, area) values ('26A',	1.5);
insert into lote (id_lote, area) values ('27',	2.4);
insert into lote (id_lote, area) values ('28',	3.0);
insert into lote (id_lote, area) values ('28A',	2.05);
insert into lote (id_lote, area) values ('29',	1.26);
insert into lote (id_lote, area) values ('30',	3.0);
insert into lote (id_lote, area) values ('31',	1.2);
insert into lote (id_lote, area) values ('32',	1.5);
insert into lote (id_lote, area) values ('33',	1.7);
insert into lote (id_lote, area) values ('34',	1.0);
insert into lote (id_lote, area) values ('35',	1.2);
insert into lote (id_lote, area) values ('36',	1.0);
insert into lote (id_lote, area) values ('37',	1.5);

insert into variedad (id_variedad,nombre) values (57603,'CANAL POINT');
insert into variedad (id_variedad,nombre) values (8475,'SENICAÑA');
insert into variedad (id_variedad,nombre) values (933895,'SENICAÑA');
insert into variedad (id_variedad,nombre) values (934223,'SENICAÑA');
insert into variedad (id_variedad,nombre) values (011940,'CANAL POINT');
