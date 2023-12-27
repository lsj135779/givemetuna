create table if not exists user
(
    id          bigint auto_increment
    primary key,
    account     varchar(255) null,
    description varchar(255) null,
    email       varchar(255) null,
    github      varchar(255) null,
    nickname    varchar(255) null,
    password    varchar(255) null,
    constraint UK_dnq7r8jcmlft7l8l4j79l1h74
    unique (account)
    );

create table if not exists board
(
    id      bigint auto_increment
    primary key,
    name    varchar(255) null,
    user_id bigint       null,
    constraint FKfyf1fchnby6hndhlfaidier1r
    foreign key (user_id) references user (id)
    );

create table if not exists stage
(
    id       bigint auto_increment
    primary key,
    category varchar(255) null,
    board_id bigint       null,
    user_id  bigint       null,
    constraint FK6b68ybqwo1te4b59sioaqpwud
    foreign key (user_id) references user (id),
    constraint FKjhkmo1a9424466thjlri8uich
    foreign key (board_id) references board (id)
    );

create table if not exists card
(
    id         bigint auto_increment
    primary key,
    closed_at  datetime(6)  null,
    is_done    bit          null,
    priority   int          null,
    started_at datetime(6)  null,
    title      varchar(255) null,
    stage_id   bigint       null,
    constraint FK8cwsiorsv5gohjkwnvsmqc99i
    foreign key (stage_id) references stage (id)
    );

create table if not exists issue
(
    id        bigint auto_increment
    primary key,
    closed_at datetime(6)  null,
    contents  varchar(255) null,
    is_closed bit          null,
    title     varchar(255) null,
    card_id   bigint       null,
    user_id   bigint       null,
    constraint FKes381ej4aaydv9lxvgxjjuy1x
    foreign key (card_id) references card (id),
    constraint FKip9bige7u8us6u9wtqd2r0th7
    foreign key (user_id) references user (id)
    );

create table if not exists issue_comment
(
    id       bigint auto_increment
    primary key,
    contents varchar(255) null,
    issue_id bigint       null,
    user_id  bigint       null,
    constraint FK7oiyufdigsfevlnbft6ag181r
    foreign key (user_id) references user (id),
    constraint FK8wy5rxggrte2ntcq80g7o7210
    foreign key (issue_id) references issue (id)
    );

create table if not exists user_card
(
    id      bigint auto_increment
    primary key,
    card_id bigint null,
    user_id bigint null,
    constraint FK441bl0wnj3hxcj8c18lyxw9e1
    foreign key (card_id) references card (id),
    constraint FKmeit1ul0skwyx74bewpxx8gml
    foreign key (user_id) references user (id)
    );

create table if not exists user_role
(
    id        bigint auto_increment
    primary key,
    g_manager bit null,
    manager   bit null,
    user      bit null
);

create table if not exists board_user_role
(
    id           bigint auto_increment
    primary key,
    board_id     bigint null,
    user_id      bigint null,
    user_role_id bigint null,
    constraint FKf7hnytiqqlsdhgsr6rdaa81vq
    foreign key (board_id) references board (id),
    constraint FKi7695k5p6wwvef6ynh3jnlu06
    foreign key (user_role_id) references user_role (id),
    constraint FKiwg347juw9m851fxmkwvjtcgf
    foreign key (user_id) references user (id)
    );

