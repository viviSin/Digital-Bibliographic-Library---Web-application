-- authors
INSERT INTO `comp9321Ass2`.`Authors` (`name`) VALUES ('author1');
INSERT INTO `comp9321Ass2`.`Authors` ( `name`) VALUES ( 'author2');
INSERT INTO `comp9321Ass2`.`Authors` ( `name`) VALUES ( 'author3');
INSERT INTO `comp9321Ass2`.`Authors` ( `name`) VALUES ( 'author4');
INSERT INTO `comp9321Ass2`.`Authors` ( `name`) VALUES ('author5');
-- items
INSERT INTO `comp9321Ass2`.`Items` ( `publtype`, `publyear`, `title`) VALUES ( 'book', '1234', '001');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'book', '1234', '002');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'www', '1234', '003');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'article', '1234', '004');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'article', '2222', '005');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'inproceeding', '1234', '006');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'proceeding', '3321', '007');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'book', '1234', '008');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'phdthesis', '1234', '009');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'book', '1232', '010');
INSERT INTO `comp9321Ass2`.`Items` (`id`, `publtype`, `publyear`, `title`) VALUES ('0', 'book', '1111', '011');

-- _write
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('1', '1');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('1', '2');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('2', '3');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('2', '4');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('2', '5');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('3', '1');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('4', '2');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('5', '3');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('6', '4');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('7', '1');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('8', '2');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('9', '5');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('10', '5');
INSERT INTO `comp9321Ass2`.`_write` (`Items_id`, `Authors_id`) VALUES ('11', '1');

