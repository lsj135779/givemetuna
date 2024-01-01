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
    constraint FKe8bj5yd6jrd9e5326rm8yk2ls
    foreign key (user_id) references user (id)
    );

create table if not exists board_user_role
(
    id       bigint auto_increment
    primary key,
    role     enum ('GENERAL_MANAGER', 'TEAM_MANAGER', 'WORKER') null,
    board_id bigint                                             null,
    user_id  bigint                                             null,
    constraint FKkca6g31gdmkit2ffjbrgmh94
    foreign key (user_id) references user (id),
    constraint FKot7f023bcm5d81vjwow7otr7u
    foreign key (board_id) references board (id)
    );

create table if not exists stage
(
    id       bigint auto_increment
    primary key,
    category varchar(255) null,
    board_id bigint       null,
    user_id  bigint       null,
    constraint FKa7olphjphjyhr0y1eegprnipd
    foreign key (board_id) references board (id),
    constraint FKef7dpjok2e96bxsavyfq2c65s
    foreign key (user_id) references user (id)
    );

create table if not exists card
(
    id            bigint auto_increment
    primary key,
    created_at    datetime(6)                           not null,
    updated_at    datetime(6)                           null,
    card_priority enum ('HIGH', 'MIDDLE', 'LOW', 'NON') not null,
    closed_at     datetime(6)                           null,
    started_at    datetime(6)                           null,
    title         varchar(255)                          not null,
    assignor_id   bigint                                null,
    creator_id    bigint                                null,
    stage_id      bigint                                null,
    constraint FK83d2rwv9dnu4dd0j6m84khn40
    foreign key (stage_id) references stage (id),
    constraint FKiyn2x55ie8v4dwdps7qif3p7h
    foreign key (assignor_id) references user (id),
    constraint FKkfhjirstor98ng1lbsx3c7bjo
    foreign key (creator_id) references user (id)
    );

create table if not exists checklist
(
    id        bigint auto_increment
    primary key,
    `check`   bit                           null,
    contents  varchar(255)                  null,
    deletable bit                           null,
    priority  enum ('높음', '중간', '낮음', '없음') null,
    card_id   bigint                        null,
    assignee  bigint                        null,
    constraint FKb5iu2ls69wtnpnhq9k8nyx9cv
    foreign key (assignee) references user (id),
    constraint FKl18c46f576k3nb0odml49teom
    foreign key (card_id) references card (id)
    );

create table if not exists issue
(
    id           bigint auto_increment
    primary key,
    created_at   datetime(6)             not null,
    updated_at   datetime(6)             null,
    contents     varchar(255)            null,
    issue_status enum ('OPEN', 'CLOSED') null,
    title        varchar(255)            null,
    card_id      bigint                  null,
    user_id      bigint                  null,
    constraint FK982imwo9u48aat97rx6hkwfnj
    foreign key (card_id) references card (id),
    constraint FKolnmum5mws2x3nb2hh9vk523b
    foreign key (user_id) references user (id)
    );

create table if not exists issue_comment
(
    id         bigint auto_increment
    primary key,
    created_at datetime(6)  not null,
    updated_at datetime(6)  null,
    contents   varchar(255) null,
    issue_id   bigint       null,
    user_id    bigint       null,
    constraint FK5s0jovho27tbjigdkaljwi8mo
    foreign key (issue_id) references issue (id),
    constraint FKhlri7178dby6oe2kqf3tfmed6
    foreign key (user_id) references user (id)
    );

