CREATE TABLE tb_address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255),
    zip_code VARCHAR(10),
    number VARCHAR(10),
    city VARCHAR(255),
    state VARCHAR(2),
    type VARCHAR(20),
    person_id BIGINT,
    CONSTRAINT fk_address_person FOREIGN KEY (person_id) REFERENCES tb_person(id)
);
