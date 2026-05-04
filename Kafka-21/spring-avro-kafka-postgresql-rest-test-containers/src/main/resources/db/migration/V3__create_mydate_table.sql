CREATE TABLE IF NOT EXISTS my_date (
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(256) not null,
    my_date TIMESTAMP not null,

    CONSTRAINT pk_my_date_table PRIMARY KEY (id)
)