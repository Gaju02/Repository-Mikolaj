CREATE TABLE Content (
    id INTEGER AUT0_INCREMENT,
    title varchar(255) not null,
    desc text,
    status varchar(20) not null,
    content_type varchar(50) not null,
    date_created timestamp not null,
    date_updated timestamp,
    url varchar(255),
    primary key(id)
    );

INSERT INTO Content (title,desc,status,cotent_type,date_created) values
('test', 'test','test','test',current_timestamp();)