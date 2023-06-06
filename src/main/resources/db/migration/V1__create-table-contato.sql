create table contato(
    id bigint not null auto_increment,
    email varchar(150),
    telefone varchar(13),
    pessoa_id bigint not null,
    constraint contatopk primary key(id)
);