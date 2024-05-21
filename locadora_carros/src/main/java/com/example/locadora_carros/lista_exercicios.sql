-- exercicio 1
INSERT INTO Cliente (id_cliente, nome, email, telefone, endereco, cidade, estado) VALUES
(1,'João Silva', 'joao@example.com', '(11) 1234-5678', 'Rua A, 123', 'São Paulo', 'SP'),
(2, 'Maria Souza', 'maria@example.com', '(21) 9876-5432', 'Avenida B, 456', 'Rio de Janeiro', 'RJ'),
(3, 'Carlos Oliveira', 'carlos@example.com', '(31) 8765-4321', 'Rua C, 789', 'Belo Horizonte', 'MG'),
(4, 'Ana Santos', 'ana@example.com', '(41) 1357-2468', 'Avenida D, 987', 'Curitiba', 'PR'),
(5,'Pedro Pereira', 'pedro@example.com', '(51) 2468-1357', 'Rua E, 246', 'Porto Alegre', 'RS');

select * from cliente;

-- exercicio 4
INSERT INTO Funcionario (id_funcionario, nome, email, telefone, cargo, data_contratacao) VALUES
(1, 'João Silva', 'joao@example.com', '(11) 1234-5678', 'Gerente', '2024-01-15'),
(2, 'Maria Souza', 'maria@example.com', '(21) 9876-5432', 'Atendente', '2023-07-20'),
(3, 'Carlos Oliveira', 'carlos@example.com', '(31) 8765-4321', 'Técnico de Manutenção', '2023-12-10'),
(4, 'Ana Santos', 'ana@example.com', '(41) 1357-2468', 'Contador', '2024-03-05'),
(5, 'Pedro Pereira', 'pedro@example.com', '(51) 2468-1357', 'Motorista', '2023-11-30');

select * from funcionario;

-- exercicio 5
INSERT INTO Reserva (id_reserva, data_reserva, data_devolucao, placa, id_cliente)
VALUES (1, '2024-05-21', '2024-05-28', 'ABC1234', 1);

INSERT INTO Carros (id_carro, placa, ano, modelo, disponibilidade, tipo) VALUES
(1, 'ABC1234', 2023, 'Toyota Corolla', 'Disponível', 'Sedan'),
(2, 'XYZ5678', 2022, 'Honda Civic', 'Indisponível', 'Sedan'),
(3, 'LMN9012', 2024, 'Ford Mustang', 'Disponível', 'Esportivo');

SELECT nome, email
FROM cliente
WHERE id_cliente IN (SELECT id_cliente
             FROM reserva
             WHERE id_cliente = (SELECT id_carro
                               FROM carros
                               WHERE placa = 'AST-900'));

-- exercicio 6
SELECT Carros.placa, Carros.ano, Carros.modelo, Carros.disponibilidade, Carros.tipo
FROM Carros
JOIN Reserva ON Carros.placa = Reserva.placa
JOIN Cliente ON Reserva.id_cliente = Cliente.id_cliente
WHERE Cliente.id_cliente = 1;  

-- exercicio 12
SELECT Cliente.id_cliente, Cliente.nome, Cliente.email, Carros.placa, Carros.ano, Carros.modelo, Carros.disponibilidade, Carros.tipo
FROM Cliente
LEFT JOIN Reserva ON Cliente.id_cliente = Reserva.id_cliente
LEFT JOIN Carros ON Reserva.placa = Carros.placa;

-- exercicio 14
SELECT Reserva.id_reserva, Reserva.data_reserva, Reserva.data_devolucao, Carros.placa, Carros.ano, Carros.modelo, Carros.disponibilidade, Carros.tipo
FROM Reserva
LEFT JOIN Carros ON Reserva.placa = Carros.placa;
