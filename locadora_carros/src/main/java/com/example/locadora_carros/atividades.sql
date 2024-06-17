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
SELECT c.*
FROM carros c
LEFT JOIN reserva r ON c.id_carro = r.id_reserva
WHERE r.id_reserva IS NULL;

-- exercicio 18
SELECT c.id_carro, c.placa, c.ano, c.modelo, c.disponibilidade, c.tipo, c.valor,
       m.data_manutencao, m.descricao, m.custo
FROM carros c
JOIN manutencao m ON c.id_carro = m.id_carro
WHERE m.data_manutencao = (
    SELECT MAX(m2.data_manutencao)
    FROM manutencao m2
    WHERE m2.id_carro = c.id_carro
)
ORDER BY m.data_manutencao DESC;

-- exercicio 19
SELECT c.email, c.nome, COUNT(DISTINCT r.id_reserva) AS total_carros
FROM cliente c
JOIN reserva r ON c.email = r.email
GROUP BY c.email, c.nome
HAVING COUNT(DISTINCT r.id_reserva) > 1;

-- exercicio 20
SELECT AVG(EXTRACT(DAY FROM (r.data_devolucao - r.data_reserva))) AS media_dias_alugados
FROM reserva r;
