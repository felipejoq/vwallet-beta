-- Esta sentencia crea la base de datos:
CREATE DATABASE final_wallet_db;

-- Esta sentencia selecciona la db que queremos usar:
USE final_wallet_db;

-- Creación de la tabla Currency
CREATE TABLE IF NOT EXISTS currencies (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(255) NOT NULL,
    symbol VARCHAR(10)  NOT NULL
);

-- Creación de la tabla User
CREATE TABLE IF NOT EXISTS users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    active   BOOLEAN DEFAULT TRUE
);

-- Creación tabla de cuenta bancaria
CREATE TABLE IF NOT EXISTS bank_accounts (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(255)                NOT NULL,
    user_id        INT                         NOT NULL,
    currency_id    INT                         NOT NULL,
    balance        DECIMAL(10, 2) DEFAULT 0.00 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (currency_id) REFERENCES currencies (id) ON DELETE CASCADE
);

-- Creación de la tabla Roles
CREATE TABLE IF NOT EXISTS roles (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Creación de la tabla User_Roles para asignar roles a los usuarios
CREATE TABLE IF NOT EXISTS user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

-- Tabla de tipo de transacciones
CREATE TABLE IF NOT EXISTS transaction_types (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Creación de la tabla Transaction
CREATE TABLE IF NOT EXISTS transactions (
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    total                  DECIMAL(10, 2)                      NOT NULL,
    transaction_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    sender_bk_account_id   INT                                 NOT NULL,
    receiver_bk_account_id INT,
    transaction_type_id    INT                                 NOT NULL,
    FOREIGN KEY (sender_bk_account_id) REFERENCES bank_accounts (id),
    FOREIGN KEY (receiver_bk_account_id) REFERENCES bank_accounts (id),
    FOREIGN KEY (transaction_type_id) REFERENCES transaction_types (id)
);

-- INSERT DATA
INSERT INTO currencies (name, symbol)
VALUES ('Dólar estadounidense', 'US$'),
       ('Euro', '€'),
       ('Pesos Chilenos', '$');

INSERT INTO transaction_types (name)
VALUES ('Transferencia'),
       ('Depósito'),
       ('Retiro');

INSERT INTO users (name, email, password)
VALUES ('Felipe', 'felipe@example.com', '$2a$12$g.Bfz6u2Cg/KPPXP8ZFiLONclnUWmoEuq/ttNzFOBrsw3NdOEYctO'),
       ('María', 'maria@example.com', '$2a$12$g.Bfz6u2Cg/KPPXP8ZFiLONclnUWmoEuq/ttNzFOBrsw3NdOEYctO'),
       ('Pedro', 'pedro@example.com', '$2a$12$g.Bfz6u2Cg/KPPXP8ZFiLONclnUWmoEuq/ttNzFOBrsw3NdOEYctO'),
       ('Ana', 'ana@example.com', '$2a$12$g.Bfz6u2Cg/KPPXP8ZFiLONclnUWmoEuq/ttNzFOBrsw3NdOEYctO'),
       ('Elena', 'elena@example.com', '$2a$12$g.Bfz6u2Cg/KPPXP8ZFiLONclnUWmoEuq/ttNzFOBrsw3NdOEYctO');

-- Crear insert de prueba para bank_accounts
INSERT INTO bank_accounts (account_number, user_id, currency_id, balance)
VALUES ('123456789', 1, 1, 100.00),
       ('987654321', 2, 2, 150.00),
       ('456789123', 3, 3, 200.00),
       ('789123456', 4, 1, 50.00),
       ('321654987', 5, 2, 75.00);

INSERT INTO roles (name)
VALUES ('Admin'),
       ('User');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2);

-- Crear inserts de prueba para transacciones
INSERT INTO transactions (total, sender_bk_account_id, receiver_bk_account_id, transaction_type_id)
VALUES (50.00, 1, 2, 1),
       (25.00, 2, 3, 1),
       (10.00, 3, 4, 1),
       (5.00, 4, 5, 1),
       (15.00, 5, 1, 1);

-- 1. Consulta para obtener el nombre de la moneda elegida por los usuarios en cada transacción:
SELECT t.id   AS transaction_id,
       u.name AS user_name,
       c.name AS currency_name
FROM transactions t
         JOIN bank_accounts ba ON t.sender_bk_account_id = ba.id
         JOIN users u ON ba.user_id = u.id
         JOIN currencies c ON ba.currency_id = c.id;

-- 2. Consulta para obtener todas las transacciones registradas:
SELECT *
FROM transactions;

SELECT t.id               AS transaction_id,
       t.total,
       t.transaction_date,
       sba.account_number AS sender_account,
       rba.account_number AS receiver_account,
       tt.name            AS transaction_type
FROM transactions t
         JOIN
     bank_accounts sba ON t.sender_bk_account_id = sba.id
         LEFT JOIN
     bank_accounts rba ON t.receiver_bk_account_id = rba.id
         JOIN
     transaction_types tt ON t.transaction_type_id = tt.id
         JOIN
     users u ON sba.user_id = u.id OR rba.user_id = u.id
WHERE u.id = 1;

-- 4. Sentencia para modificar el campo correo electrónico de un usuario específico:
UPDATE users
SET email = ''
WHERE id = 1;
-- Esta es la ID del usuario que se desea modificar su correo.

-- 5. Sentencia para eliminar los datos de una transacción (eliminado de la fila completa):
DELETE
FROM transactions
WHERE id = 1;
-- Esta es la ID de la transacción que se desea borrar.

-- 6. Consulta para ver los usuarios y sus roles
SELECT u.name AS user_name,
       r.name AS role_name
FROM users u
         JOIN user_roles ur ON u.id = ur.user_id
         JOIN roles r ON ur.role_id = r.id;