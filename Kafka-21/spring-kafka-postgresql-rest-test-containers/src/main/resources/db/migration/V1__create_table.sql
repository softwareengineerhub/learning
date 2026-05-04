CREATE TABLE IF NOT EXISTS my_table (
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(256) not null,
    description VARCHAR(256) not null,
    age int not null,
    version BIGINT not null,

    CONSTRAINT pk_mytable PRIMARY KEY (id),
    CONSTRAINT unique_pair UNIQUE (name, age)
)