-- exercicio 2
SELECT * 
FROM carros 
WHERE disponibilidade = 'disponível';

-- exercicio 3
SELECT * 
FROM reserva;

-- exercicio 7
SELECT * 
FROM reserva
WHERE data_reserva = '2024-06-01';

-- exercicio 8
SELECT r.*
FROM reserva r
JOIN cliente c ON r.email = c.email
JOIN carros ca ON r.placa = ca.placa
WHERE c.email = 'cliente@example.com'
  AND r.data_reserva >= '2024-06-01'
  AND r.data_devolucao <= '2024-06-15';

-- exercicio 9
SELECT c.nome, c.email, c.telefone, c.cidade, c.estado, ca.modelo, ca.placa, r.data_reserva, r.data_devolucao
FROM reserva r
JOIN cliente c ON r.email = c.email
JOIN carros ca ON r.placa = ca.placa;

-- exercicio 10
SELECT r.id_reserva, r.data_reserva, r.data_devolucao, 
       c.nome AS cliente_nome, c.email AS cliente_email, c.telefone AS cliente_telefone, c.cidade AS cliente_cidade, c.estado AS cliente_estado,
       ca.modelo AS carro_modelo, ca.placa AS carro_placa, ca.ano AS carro_ano, ca.tipo AS carro_tipo, ca.valor AS carro_valor
FROM reserva r
JOIN cliente c ON r.email = c.email
JOIN carros ca ON r.placa = ca.placa;

-- exercicio 11
SELECT c.id_carro, c.placa, c.ano, c.modelo, c.disponibilidade, c.tipo, c.valor,
       COALESCE(m.descricao, 'Sem manutenção ainda') AS descricao,
       COALESCE(m.custo, 0) AS custo
FROM carros c
LEFT JOIN manutencao m ON c.id_carro = m.id_carro;

-- exercicio 13
SELECT r.id_reserva, r.data_reserva, r.data_devolucao,
       c.nome AS cliente_nome, c.email AS cliente_email, c.telefone AS cliente_telefone, c.cidade AS cliente_cidade, c.estado AS cliente_estado,
       ca.modelo AS carro_modelo, ca.placa AS carro_placa, ca.ano AS carro_ano, ca.tipo AS carro_tipo, ca.valor AS carro_valor
FROM reserva r
LEFT JOIN cliente c ON r.email = c.email
LEFT JOIN carros ca ON r.placa = ca.placa
UNION
SELECT r.id_reserva, r.data_reserva, r.data_devolucao,
       c.nome AS cliente_nome, c.email AS cliente_email, c.telefone AS cliente_telefone, c.cidade AS cliente_cidade, c.estado AS cliente_estado,
       ca.modelo AS carro_modelo, ca.placa AS carro_placa, ca.ano AS carro_ano, ca.tipo AS carro_tipo, ca.valor AS carro_valor
FROM reserva r
RIGHT JOIN cliente c ON r.email = c.email
RIGHT JOIN carros ca ON r.placa = ca.placa
WHERE r.id_reserva IS NULL;

-- exercicio 15
SELECT c.email, c.nome, COUNT(r.id_reserva) AS total_alugueis
FROM reserva r
JOIN cliente c ON r.email = c.email
GROUP BY c.email, c.nome
ORDER BY total_alugueis DESC
LIMIT 1;

-- exercicio 16
SELECT SUM((r.data_devolucao - r.data_reserva) * c.valor) AS receita_total
FROM reserva r
JOIN carros c ON r.placa = c.placa
WHERE r.data_reserva BETWEEN '2024-01-01' AND '2024-12-31';

-- exercicio 17