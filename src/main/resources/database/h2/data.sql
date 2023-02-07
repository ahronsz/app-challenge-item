INSERT INTO Clients(name) VALUES
('Ahron'),
('Luis');

INSERT INTO Types(name) VALUES
('Bebida'),
('Comida'),
('Salsas'),
('Especias');

INSERT INTO Items(client_id, type_id, name, container, capacity, require_fridge, status, timestamp) VALUES
(1, 1, 'Inca Kola', 'Botella', '1000gr', true, '1', now());
//(1, 2, 'Fideos', 'Caja', '100gr', false, '1', now()),
//(2, 3, 'Aceituna', 'Botella', '100gr', true, '1', now()),
//(2, 4, 'Pimienta', 'Caja', '100gr', false, '1', now());