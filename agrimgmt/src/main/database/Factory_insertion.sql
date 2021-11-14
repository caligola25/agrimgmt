-- Factory Db data insertion

-- Connect to the DB
\c agrimgmt

-- Employee
INSERT INTO Factory.Employee VALUES
    ('58704ab4-722c-4c2b-9896-db5dfb688f12', 'Mario', 'Rossi', 'Administrator', 2000.00, NULL),
    ('0b78b55b-c81e-4cdc-9d11-45dc0c20d70e', 'Martin', 'Zanini', 'Designer', 1500.50, NULL),
    ('116f1a2a-ab95-4283-bad2-22a6ad4255f4', 'Marco', 'Giordani', 'Production planner', 5000.50, NULL),
    ('8a02a9ce-4435-4f15-9c1a-0d8bbba6f12c', 'Enrico', 'Martinuz', 'Accountant', 1100.00, NULL),
    ('a35d05ef-d1ad-40ff-8564-50234da36e9f', 'Andrea', 'Ricciotti', 'Production line worker', 1100.00, 2),
    ('0dfb882e-adb2-41f3-8b58-f2fddcf502d7', 'Mattia', 'Bonato', 'Production line worker', 1100.00, 0),
    ('1ef34993-e627-4454-b119-3035fe2a9674', 'Piero', 'Simonetto', 'Production line worker', 1100.00, 5),
    ('497f2fbc-0f54-4785-ad42-5a36592dd946', 'Marian', 'Bedini', 'Production line worker', 1100.00, 6),
    ('3384afea-51ff-476d-babb-4025200ebad7', 'Luca', 'Pietrobon', 'Production line worker', 1100.00, 0),
    ('f70ff2ef-8144-49bd-ac0c-087f49463e2b', 'Filippo', 'Marchetti', 'Warehouse worker', 1100.00, 0);

-- Product
INSERT INTO Factory.Product VALUES
    ('74ec5cee-0635-4756-8afc-e0ffc8bbdd57', 'Combinata ALITALIA Rullo Gommato', 55540, true),
    ('44c7f6b8-9eda-489f-bc86-9d5aada4dc24', 'Trincia TORNADI', 5735, true),
    ('aeacb83d-2d31-4f4b-9aa0-d4d0cf40594c', 'Trincia CHIARO', 6825, true),
    ('79d37850-a346-457a-add9-afedd8e81ffd', 'Kit martinetto idraulico TORNADI', 310, true),
    ('3e210a1b-c631-46e4-b590-7159c7ebcdb3', 'Lamiera antiusura TORNADI', 440, true),
    ('d02f9aa0-5284-4081-8921-690c724c8b81', 'Erpice rotante AQUILE', 30685, true),
    ('d38363e9-81dc-485a-8410-db14068135fd', 'Erpice rotante DRAGA', 10555, true),
    ('26038c26-0d39-4cd5-955f-221b35fc4668', 'Giunto cardanico DRAGA', 300, true),
    ('c370957e-d6fd-45ce-b8d4-4c95c0ea4193', 'Attacco quick coupler AQUILE', 530, true),
    ('7ca8bcb1-d17a-4975-bf55-2b456e762326', 'Braccio decespugliatore PALME', 13380, true),
    ('01f7acee-d656-49e9-8567-8ca12d357374', 'Scambiatore di calore PALME', 1045, true),
    ('a7635285-0de2-4b1a-bce0-4aec3e9ee36e', 'Semiantrice cereali PINTE', 26430, true),
    ('73f9cfe2-605a-47c7-b01c-107a9d5abf05', 'Disco di semina lattuga ORIETTINA', 90, true),
    ('8dc1725f-415d-4660-988d-f1648c56a2de', 'Disco di semina mais CHRONOS', 40, true),
    ('3d6a99bb-da36-4d58-8b41-36e883e79132', 'Monitor V1200', 2445, true),
    ('12ccb470-c858-4041-b2a3-e046acca3d33', 'Concimatore SPECIAL', 11860, true);
    
-- Customer
INSERT INTO Factory.Customer VALUES
    ('8040144a-23be-4b0f-8aa8-4acebac110df', 'M&C Agraria', 'Italy', 'Padova', 'Lungargine G. E. Fabre, 24'),
    ('ada7836d-0359-42e1-9c2b-7917ddf63482', 'Societa Agricola Sempre Primavera', 'Italy', 'Loreggia', 'Via Montegrappa, 6'),
    ('c4a28a89-d177-440a-b038-4084d6ed1f0b', 'Azienda Agricola Manera Luigi', 'Italy', 'Castelfranco Veneto', 'Via Sile, 36/A'),
    ('fcbb62c2-f911-4a25-8682-6b56f35318c5', 'Azienda Agricola Zen Stefano', 'Italy', 'Romano D_Ezzelino', 'Via Trieste, 20'),
    ('7c899f58-02d0-4f13-af02-da4f90822868', 'Società Agricola Alfalatte', 'Italy', 'Crespano del Grappa', 'Via Montenero, 82'),
    ('b9adabef-03ed-4355-b0e2-3533e66c8b8b', 'Azienda Agricola Bio Del Fosco', 'Italy', 'Avezzano', 'Via Pietro Perugino'),
    ('212bf3f2-c0de-4a58-9028-b0cab6f9535e', 'Azienda Agricola Biologica Borgo Serratonda', 'Italy', 'Caraffa di Catanzaro', 'Contrada Serratonda');

-- Supplier
INSERT INTO Factory.Supplier VALUES
    ('Beato materiali ferrosi', 'Italy'),
    ('Tecnoerre carpenteria metallica', 'Italy'),
    ('Siemens', 'Germany'),
    ('TecnoVict agriculture', 'Italy'),
    ('Andrighetti', 'Italy');

-- Raw Material
INSERT INTO Factory.Raw_material VALUES
    ('7060cc61-4bd5-4c15-a694-50ca35c37f03', 'Inserti filettati'),
    ('f84ecff5-1589-4a63-b736-dda563933c7e', 'Supporti'),
    ('01a3b538-58e8-4e3a-b37c-af840a73a1aa', 'Kit sportello'),
    ('dfbd2498-42a0-44c4-a2a4-047634dcbaa6', 'Kit piastra quadra'),
    ('89692ca0-3779-4dde-a106-729cb07b341f', 'Piastra caduta'),
    ('01f31192-33f1-4d3f-981c-f86e413dc1ae', 'Kit trasmissione'),
    ('c09317db-796c-40b8-bba5-9569d5af6ac8', 'Kit carter frontale'),
    ('3f5fcc83-16b7-45d8-922b-49b23a2b000b', 'Tubo sistema idraulico'),
    ('d3253c0a-9ecf-4f2b-8648-403f1ba7d5cd', 'Leva'),
    ('55bfe270-5acc-4541-9d21-4f6fe1b604db', 'Kit cavi 1'),
    ('eaaf3889-bba8-4229-a7db-536349645610', 'Etichette sicurezza'),
    ('fee9b180-9750-4db2-9642-fd12c93e584a', 'Kit griglie'),
    ('8affcd09-a7c6-4592-b467-3bf5c7cfd727', 'Vite M6'),
    ('6ea11bfd-280f-470a-9a86-42a6683bc625', 'Vite M10'),
    ('f25efb70-4f8a-40af-813f-3f1d4daf72c7', 'Piastre parafanghi'),
    ('1bf38d88-ea84-43ce-83cc-6eee9c9b0cae', 'Parafango concimatore'),
    ('62462437-2b12-4c89-b36f-efe8191702ea', 'Kit luci'),
    ('bcfcdf70-83a2-412d-85a9-65d6de8d0bcb', 'Set etichette USER'),
    ('72f21507-45d1-473b-9cf0-2151c1b8b8a5', 'Telo protezione concimatore'),
    ('a03feec7-ed72-4d93-b8d9-fa4c934401ed', 'Biella'),
    ('7ae10a38-65ee-4375-8396-127de5eed32f', 'Kit cavi 2'),
    ('263842c5-662d-4870-bf48-fda618f84a6b', 'Staffa cavi'),
    ('f1327907-af50-4971-ab43-b38c9e93c6b4', 'Attuatore concimatore'),
    ('e4eecad6-3752-4e5d-8be1-e372ac5b21f8', 'Accelerometro V10'),
    ('2e1c2bc5-5375-4539-a807-cf5ee1094e9b', 'Kit cavi luci'),
    ('a59b96df-ec2d-46d3-9c07-3798de457e87', 'Disco concimatore'),
    ('62bdfe1b-349e-4fab-9e15-c66dafc4cbdf', 'Kit cablaggio controllo'),
    ('1ec551a0-40e2-49c5-b6fa-4a88972af506', 'Fasciature'),
    ('948d57d6-b40c-448f-9717-4bc59b586610', 'Lubrificante'),
    ('c486d316-2e54-4f9a-8ee6-fb8e94315102', 'Scatola trasporto'),
    ('9211b14e-ae15-4db1-b8ea-9a7251a4dcc0', 'Nylon imballo');

-- Report
INSERT INTO Factory.Report VALUES
    ('2020-12-31', pg_read_binary_file('/home/ale/Documenti/webapp/agrimgmt-wa2021/monthly report templates/Monthly report 2020-12-31.pdf')::bytea),
    ('2020-11-30', pg_read_binary_file('/home/ale/Documenti/webapp/agrimgmt-wa2021/monthly report templates/Monthly report 2020-11-30.pdf')::bytea);
--  ('2020-12-31', '010101010'),
--  ('2020-11-30', '010101010');  -- IF THERE ARE ANY PROBLEM FOR THE INSERTION OF REPORTS, PLEASE COMMENT THE TWO LINES BELOW AND UNCOMMENT THESE TWO LINES

-- Credential
INSERT INTO Factory.Credential VALUES
    ('ma.rossi', md5('M.reaper12'), '58704ab4-722c-4c2b-9896-db5dfb688f12'),
    ('ma.zanini', md5('j.NG7iwze'), '0b78b55b-c81e-4cdc-9d11-45dc0c20d70e'),
    ('ma.giordani', md5('AU6.nvfPrt'), '116f1a2a-ab95-4283-bad2-22a6ad4255f4'),
    ('en.martinuz', md5('wdox.C291'), '8a02a9ce-4435-4f15-9c1a-0d8bbba6f12c'),
	('an.ricciotti', md5('khdakeiW'), 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('fi.marchetti', md5('rsw.CA8a'), 'f70ff2ef-8144-49bd-ac0c-087f49463e2b');

-- Define_2
INSERT INTO Factory.Define_2 VALUES
    ('58704ab4-722c-4c2b-9896-db5dfb688f12', '2020-11-30'),
    ('0b78b55b-c81e-4cdc-9d11-45dc0c20d70e', '2020-11-30'),
    ('116f1a2a-ab95-4283-bad2-22a6ad4255f4', '2020-11-30'),
    ('8a02a9ce-4435-4f15-9c1a-0d8bbba6f12c', '2020-11-30'),
    ('a35d05ef-d1ad-40ff-8564-50234da36e9f', '2020-11-30'),
    ('0dfb882e-adb2-41f3-8b58-f2fddcf502d7', '2020-11-30'),
    ('1ef34993-e627-4454-b119-3035fe2a9674', '2020-11-30'),
    ('497f2fbc-0f54-4785-ad42-5a36592dd946', '2020-11-30'),
    ('3384afea-51ff-476d-babb-4025200ebad7', '2020-11-30'),
    ('58704ab4-722c-4c2b-9896-db5dfb688f12', '2020-12-31'),
    ('0b78b55b-c81e-4cdc-9d11-45dc0c20d70e', '2020-12-31'),
    ('116f1a2a-ab95-4283-bad2-22a6ad4255f4', '2020-12-31'),
    ('8a02a9ce-4435-4f15-9c1a-0d8bbba6f12c', '2020-12-31'),
    ('a35d05ef-d1ad-40ff-8564-50234da36e9f', '2020-12-31'),
    ('0dfb882e-adb2-41f3-8b58-f2fddcf502d7', '2020-12-31'),
    ('1ef34993-e627-4454-b119-3035fe2a9674', '2020-12-31'),
    ('497f2fbc-0f54-4785-ad42-5a36592dd946', '2020-12-31'),
    ('3384afea-51ff-476d-babb-4025200ebad7', '2020-12-31');

-- Process
INSERT INTO Factory.Process VALUES
    ('969e9319-4a6c-41a1-950a-9d83096c02b6', 'Assemblaggio', 30, '15 minutes', 'c370957e-d6fd-45ce-b8d4-4c95c0ea4193', '8affcd09-a7c6-4592-b467-3bf5c7cfd727', 20),
    ('4bfecac1-2a1e-48fe-b2a1-3a6f64990cb6', 'Preparazione e inserti filettati', 1, '2 minutes', '12ccb470-c858-4041-b2a3-e046acca3d33', '7060cc61-4bd5-4c15-a694-50ca35c37f03', 40),
    ('bef78918-69ba-412b-86d7-97126424b027', 'Supporti', 2, '3 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'f84ecff5-1589-4a63-b736-dda563933c7e', 7),
    ('cefc5974-1bb5-478e-8647-c95f1262e385', 'Sportello ispezione', 3, '3 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '01a3b538-58e8-4e3a-b37c-af840a73a1aa', 1),
    ('3f89306b-b9fb-4acd-abe2-8f3feee42d0b', 'Piastra quadra', 4, '1 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'dfbd2498-42a0-44c4-a2a4-047634dcbaa6', 1),
    ('f2c38182-e070-4f16-bdeb-9ccf51f69d0c', 'Piastre punto caduta', 5, '8 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '89692ca0-3779-4dde-a106-729cb07b341f', 3),
    ('4a713673-ddfa-40a8-b25f-9b86fccdc82f', 'Trasmissione', 6, '20 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '01f31192-33f1-4d3f-981c-f86e413dc1ae', 1),
    ('a52e8947-52e4-4a96-a8b9-a61cf7ea4410', 'Carter frontale', 7, '7 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'c09317db-796c-40b8-bba5-9569d5af6ac8', 1),
    ('114786e7-3f0f-4278-a634-097eea47d9b5', 'Tubi idraulici', 8, '15 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '3f5fcc83-16b7-45d8-922b-49b23a2b000b', 5),
    ('3d491ae2-1c9a-4d30-92fe-0e2da576410b', 'Leva regolazione manuale', 9, '2 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'd3253c0a-9ecf-4f2b-8648-403f1ba7d5cd', 1),
    ('91fc6e8d-3ac7-403c-92a6-49c56168136f', 'Cablatura', 10, '10 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '55bfe270-5acc-4541-9d21-4f6fe1b604db', 1),
    ('14f85ec2-cf25-4685-ae3d-62a555384ab4', 'Etichette', 11, '2 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'eaaf3889-bba8-4229-a7db-536349645610', 1),
    ('d75571f7-7717-4c44-b7f0-a3d586928b8e', 'Griglie', 12, '5 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'fee9b180-9750-4db2-9642-fd12c93e584a', 1),
    ('6aa3d405-0480-4be1-be93-77a427e09523', 'Viti opzionali', 13, '4 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '8affcd09-a7c6-4592-b467-3bf5c7cfd727', 50),
    ('3849dbbd-4147-4ed0-8642-5e19a7c44beb', 'Fissaggio Piastra', 14, '10 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '6ea11bfd-280f-470a-9a86-42a6683bc625', 15),
    ('96357856-1059-429e-9920-b72ee15103be', 'Piastre fissaggio parafanghi', 15, '10 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'f25efb70-4f8a-40af-813f-3f1d4daf72c7', 4),
    ('b0ca9fcd-c1b7-4555-a85a-3859d77e998a', 'Parafanghi', 16, '10 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '1bf38d88-ea84-43ce-83cc-6eee9c9b0cae', 2),
    ('4330e71a-c2cc-4fc9-825e-32eb1040fedc', 'Tabelle luci e stradali', 17, '15 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '62462437-2b12-4c89-b36f-efe8191702ea', 2),
    ('5beb89e2-9634-4b67-88cc-e82fce21c4e4', 'Etichette', 18, '3 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'bcfcdf70-83a2-412d-85a9-65d6de8d0bcb', 1),
    ('c2f76985-761d-4b2f-a576-0c89795ce669', 'Telo', 19, '20 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '72f21507-45d1-473b-9cf0-2151c1b8b8a5', 1),
    ('31e94cb4-838c-465d-8f8c-ab936046773e', 'Bielle', 20, '5 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'a03feec7-ed72-4d93-b8d9-fa4c934401ed', 10),
    ('14aa55e9-31dd-49a8-bcfd-f9370b893eaa', 'Cablaggio e sistemazione cavi', 21, '5 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '7ae10a38-65ee-4375-8396-127de5eed32f', 1),
    ('bd8e8fd3-8a6e-45f1-8043-15d4f553c691', 'Staffa porta cavi', 22, '3 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '263842c5-662d-4870-bf48-fda618f84a6b', 1),
    ('130363ac-6c6e-4f52-b01e-63f6c958108b', 'Attuatori', 23, '10 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'f1327907-af50-4971-ab43-b38c9e93c6b4', 16),
    ('48b6c567-49c0-4edd-83e7-64177e7b1110', 'Accelerometro e cablaggio', 24, '15 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'e4eecad6-3752-4e5d-8be1-e372ac5b21f8', 1),
    ('a0fe92dd-1672-4a25-bd5d-e701db3e388c', 'Collegamento luci', 25, '5 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '2e1c2bc5-5375-4539-a807-cf5ee1094e9b', 2),
    ('97de551f-2865-4058-b887-c9543db066ec', 'Dischi', 26, '10 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'a59b96df-ec2d-46d3-9c07-3798de457e87', 16),
    ('38db73c1-5dfa-49d3-ba00-ea3357471611', 'Cablaggio controllo', 27, '5 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '62bdfe1b-349e-4fab-9e15-c66dafc4cbdf', 1),
    ('6afa7f3c-def8-4653-a2a1-f3322b84f251', 'Sistemazione tubi e fascettare', 28, '10 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '1ec551a0-40e2-49c5-b6fa-4a88972af506', 20),
    ('36a6b1d6-bc1a-4e5c-a6df-15458665d701', 'Collaudo funzionale', 29, '35 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '948d57d6-b40c-448f-9717-4bc59b586610', 1),
    ('b47d528e-0083-45b7-b672-95ce50481f9f', 'Preparazione scatola', 30, '10 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', 'c486d316-2e54-4f9a-8ee6-fb8e94315102', 1),
    ('eb0a547e-13b1-419b-986e-dd3b42edfe92', 'Nylon e bindello', 31, '15 minutes',  '12ccb470-c858-4041-b2a3-e046acca3d33', '9211b14e-ae15-4db1-b8ea-9a7251a4dcc0', 1),    
    ('8b47d64f-a26e-4d04-9bc0-71ab0c1ab0b9', 'Preparazione e inserti filettati', 1, '2 minutes',  '74ec5cee-0635-4756-8afc-e0ffc8bbdd57', '7060cc61-4bd5-4c15-a694-50ca35c37f03', 40),
    ('3c01785e-a459-4a96-9a9b-7fdaffb72bed', 'Supporti', 2, '3 minutes',  '74ec5cee-0635-4756-8afc-e0ffc8bbdd57', 'f84ecff5-1589-4a63-b736-dda563933c7e', 7),
    ('b7fccd35-ee4e-4c68-9557-fbb3c26d7d92', 'Sportello ispezione', 3, '3 minutes',  '74ec5cee-0635-4756-8afc-e0ffc8bbdd57', '01a3b538-58e8-4e3a-b37c-af840a73a1aa', 1),
    ('2ecbb423-56ee-4d82-8d16-5fef7ed64bc0', 'Piastra quadra', 4, '1 minutes',  '74ec5cee-0635-4756-8afc-e0ffc8bbdd57', 'dfbd2498-42a0-44c4-a2a4-047634dcbaa6', 1),
    ('92dfae15-7d69-47dd-9c9f-690628a7abb5', 'Piastre punto caduta', 5, '8 minutes',  '74ec5cee-0635-4756-8afc-e0ffc8bbdd57', '89692ca0-3779-4dde-a106-729cb07b341f', 3),    
    ('930a32b8-619d-4912-bd4e-ade147827dc4', 'Preparazione e inserti filettati', 1, '2 minutes',  'd02f9aa0-5284-4081-8921-690c724c8b81', '7060cc61-4bd5-4c15-a694-50ca35c37f03', 40),
    ('c183fd75-34e2-42fc-8ec4-b53405cbfce1', 'Supporti', 2, '3 minutes',  'd02f9aa0-5284-4081-8921-690c724c8b81', 'f84ecff5-1589-4a63-b736-dda563933c7e', 7),
    ('ca39636c-23c1-473f-a61a-000af7b59562', 'Sportello ispezione', 3, '3 minutes',  'd02f9aa0-5284-4081-8921-690c724c8b81', '01a3b538-58e8-4e3a-b37c-af840a73a1aa', 1),
    ('65eb13d9-6b77-4f3d-9c87-2d06e60e578a', 'Piastra quadra', 4, '1 minutes', 'd02f9aa0-5284-4081-8921-690c724c8b81', 'dfbd2498-42a0-44c4-a2a4-047634dcbaa6', 1);

-- Product Order
INSERT INTO Factory.Product_order VALUES
    ('7a80efe2-1295-464e-b009-c1faec5bb33a', '2020-12-07', 'shipped', 11860, '8040144a-23be-4b0f-8aa8-4acebac110df', NULL),
    ('554bb8ee-2b79-4b14-931e-01854a154635', '2020-12-01', 'in_production', 11860, 'ada7836d-0359-42e1-9c2b-7917ddf63482', NULL),
    ('01c45ec3-08d0-464b-805c-15ddfc00d55f', '2020-11-20', 'paid', 55540, '7c899f58-02d0-4f13-af02-da4f90822868', '2020-11-30'),
    ('681ccb87-9801-4980-a019-99bc49457eaf', '2020-11-15', 'shipped', 30685, 'ada7836d-0359-42e1-9c2b-7917ddf63482', '2020-11-30');

-- Item
INSERT INTO Factory.Item VALUES
    ('956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'shipped', '7a80efe2-1295-464e-b009-c1faec5bb33a', '12ccb470-c858-4041-b2a3-e046acca3d33'),
    ('db135c35-2586-4d20-8240-601eee1f0d36', 'in_construction', '554bb8ee-2b79-4b14-931e-01854a154635', '12ccb470-c858-4041-b2a3-e046acca3d33'),
    ('95a2c8cf-aab8-4632-9787-cda01ca54ba2', 'stored', '01c45ec3-08d0-464b-805c-15ddfc00d55f', '74ec5cee-0635-4756-8afc-e0ffc8bbdd57'),
    ('45412f2a-ffae-49da-bfed-42a412b6c5f2', 'shipped', '681ccb87-9801-4980-a019-99bc49457eaf', 'd02f9aa0-5284-4081-8921-690c724c8b81');

-- Production Phase
INSERT INTO Factory.Production_phase VALUES
    ('049199f8-7fb2-41b4-b030-9fd03e8b5f27', 'completed', '2 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '4bfecac1-2a1e-48fe-b2a1-3a6f64990cb6', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('2ef08e58-b12a-4c14-a92e-b0580f216ff0', 'completed', '2 minutes 10 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'bef78918-69ba-412b-86d7-97126424b027', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('52c418cf-9332-4174-acbd-f1a30fedb75e', 'completed', '4 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'cefc5974-1bb5-478e-8647-c95f1262e385', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('9eaf1ca9-970e-44fd-ba06-d3ebbebb5fd4', 'completed', '1 minutes 32 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '3f89306b-b9fb-4acd-abe2-8f3feee42d0b', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('4b525926-5552-47cc-aedf-c40cd5206dde', 'completed', '8 minutes 58 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'f2c38182-e070-4f16-bdeb-9ccf51f69d0c', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('d6fc5d2e-c8ee-45b1-a9f0-86416fec8cef', 'completed', '21 minutes 15 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '4a713673-ddfa-40a8-b25f-9b86fccdc82f', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('7c481677-7bf4-4e2a-b41d-7574cd827867', 'completed', '6 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'a52e8947-52e4-4a96-a8b9-a61cf7ea4410', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('a03700a5-219c-4284-bd80-2a3840bdbdc2', 'completed', '16 minutes 45 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '114786e7-3f0f-4278-a634-097eea47d9b5', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('3df396db-45fe-4977-8795-c8734bbf4ea9', 'completed', '1 minutes 40 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '3d491ae2-1c9a-4d30-92fe-0e2da576410b', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('82fe9fd8-0be2-485d-a59c-8d6c6fc247ca', 'completed', '12 minutes 50 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '91fc6e8d-3ac7-403c-92a6-49c56168136f', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('d2fbfd1b-49e2-45b4-ada9-0eef445c8667', 'completed', '3 minutes 32 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '14f85ec2-cf25-4685-ae3d-62a555384ab4', '3384afea-51ff-476d-babb-4025200ebad7'),
    ('ccf047ca-e680-4b86-ad3a-9be95ac3eee6', 'completed', '5 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'd75571f7-7717-4c44-b7f0-a3d586928b8e', '3384afea-51ff-476d-babb-4025200ebad7'),
    ('22e2515d-2d9b-442f-bc9a-1bb9dd33c329', 'completed', '5 minutes 5 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '6aa3d405-0480-4be1-be93-77a427e09523', '3384afea-51ff-476d-babb-4025200ebad7'),
    ('23e5f65b-238f-4afe-b25e-262311f3c3d3', 'completed', '10 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '3849dbbd-4147-4ed0-8642-5e19a7c44beb', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('bf3c68fc-8407-4e34-b7bf-a9c938f9d347', 'completed', '12 minutes 47 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '96357856-1059-429e-9920-b72ee15103be', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('5eeac17d-fd92-454e-8b35-a18435303a53', 'completed', '11 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'b0ca9fcd-c1b7-4555-a85a-3859d77e998a', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('6dd292df-6e3b-428d-ad5c-971ccae97102', 'completed', '13 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '4330e71a-c2cc-4fc9-825e-32eb1040fedc', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('ec38627b-874e-4380-a279-5a9d1e1dc267', 'completed', '3 minutes 11 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '5beb89e2-9634-4b67-88cc-e82fce21c4e4', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('e06c773e-2012-407c-9c93-e393625de568', 'completed', '24 minutes 39 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'c2f76985-761d-4b2f-a576-0c89795ce669', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('7d515ac6-0d66-426f-9111-ea6194eea7d8', 'completed', '5 minutes 10 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '31e94cb4-838c-465d-8f8c-ab936046773e', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('0d43c468-00da-4515-a7ec-b4d11948201d', 'completed', '8 minutes 1 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '14aa55e9-31dd-49a8-bcfd-f9370b893eaa', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('8976b683-782b-4899-bb49-35f5154d01c6', 'completed', '4 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'bd8e8fd3-8a6e-45f1-8043-15d4f553c691', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('75e3a2c8-63b3-41ee-8563-2712f751c4eb', 'completed', '12 minutes 9 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '130363ac-6c6e-4f52-b01e-63f6c958108b', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('58bcf301-a0f9-48f7-91de-00d0b137065e', 'completed', '16 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '48b6c567-49c0-4edd-83e7-64177e7b1110', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('ce30117b-337a-4992-a970-df74d4b7ffbd', 'completed', '6 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'a0fe92dd-1672-4a25-bd5d-e701db3e388c', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('d7d8c6af-82ab-42b0-9490-31aeb24cc321', 'completed', '9 minutes 52 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '97de551f-2865-4058-b887-c9543db066ec', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('e3c15721-35a7-44d1-87d1-3f8a8397f5d4', 'completed', '7 minutes', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '38db73c1-5dfa-49d3-ba00-ea3357471611', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('6924d588-9b0f-4e92-bb67-3e5c7f3582d5', 'completed', '9 minutes 7 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '6afa7f3c-def8-4653-a2a1-f3322b84f251', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('773a7c86-9bd8-44d8-bcb9-1f58857b4158', 'completed', '49 minutes 46 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', '36a6b1d6-bc1a-4e5c-a6df-15458665d701', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('9361fc88-9c20-4fa7-9819-e11c25e8db96', 'completed', '8 minutes 3 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'b47d528e-0083-45b7-b672-95ce50481f9f', '497f2fbc-0f54-4785-ad42-5a36592dd946'),    
    ('3a2ae36b-c996-409b-9a15-98c97778324d', 'completed', '18 minutes 37 seconds', '956d07a9-97ee-4878-87e7-cb1cee7a2d16', 'eb0a547e-13b1-419b-986e-dd3b42edfe92', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('2a37eb62-6563-4109-a90e-aa8b4a414d46', 'completed', '2 minutes', 'db135c35-2586-4d20-8240-601eee1f0d36', '4bfecac1-2a1e-48fe-b2a1-3a6f64990cb6', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('a6cb02ed-ffb2-4cc0-ab6e-f57c1c851a58', 'completed', '2 minutes 10 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', 'bef78918-69ba-412b-86d7-97126424b027', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('959abf23-d900-471a-b1e7-d932b4884fd7', 'completed', '4 minutes', 'db135c35-2586-4d20-8240-601eee1f0d36', 'cefc5974-1bb5-478e-8647-c95f1262e385', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('1f30a0b6-f620-4e4a-8128-9ed39bad17a8', 'completed', '1 minutes 32 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', '3f89306b-b9fb-4acd-abe2-8f3feee42d0b', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('d20ff152-2cf4-4b3f-9234-cc70661176a2', 'completed', '8 minutes 58 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', 'f2c38182-e070-4f16-bdeb-9ccf51f69d0c', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('1e82a31e-e04b-401d-995a-84817a52aac5', 'completed', '21 minutes 15 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', '4a713673-ddfa-40a8-b25f-9b86fccdc82f', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('286f0448-1c40-41d2-b82d-4c93459769e0', 'completed', '6 minutes', 'db135c35-2586-4d20-8240-601eee1f0d36', 'a52e8947-52e4-4a96-a8b9-a61cf7ea4410', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('7504def7-a5c3-48e9-a146-c816f63d7468', 'completed', '16 minutes 45 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', '114786e7-3f0f-4278-a634-097eea47d9b5', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('6255b9c3-5a39-49ff-97b1-82397ecf4887', 'completed', '1 minutes 40 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', '3d491ae2-1c9a-4d30-92fe-0e2da576410b', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('7579d347-5289-46f1-b8d4-8949409abe1b', 'completed', '12 minutes 50 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', '91fc6e8d-3ac7-403c-92a6-49c56168136f', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('66fc2a7f-7604-4ec5-b780-81ae3c69f79b', 'completed', '3 minutes 32 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', '14f85ec2-cf25-4685-ae3d-62a555384ab4', '3384afea-51ff-476d-babb-4025200ebad7'),
    ('5851ee66-4ac2-482a-8dea-00fd3c93eb93', 'completed', '5 minutes', 'db135c35-2586-4d20-8240-601eee1f0d36', 'd75571f7-7717-4c44-b7f0-a3d586928b8e', '3384afea-51ff-476d-babb-4025200ebad7'),
    ('a44dbb1d-2538-4699-aec9-e5e21cd35937', 'completed', '5 minutes 5 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', '6aa3d405-0480-4be1-be93-77a427e09523', '3384afea-51ff-476d-babb-4025200ebad7'),
    ('56e0e850-498d-40b5-a0ca-aec02e28c8a5', 'completed', '10 minutes', 'db135c35-2586-4d20-8240-601eee1f0d36', '3849dbbd-4147-4ed0-8642-5e19a7c44beb', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('b48d980f-6d44-42f0-abed-639495d0bbc0', 'completed', '12 minutes 47 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', '96357856-1059-429e-9920-b72ee15103be', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('0e658fdd-e459-4d69-8e3f-85519dd1d057', 'completed', '11 minutes', 'db135c35-2586-4d20-8240-601eee1f0d36', 'b0ca9fcd-c1b7-4555-a85a-3859d77e998a', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('562c5470-a6c3-4154-a328-087b9e1f9f8b', 'completed', '13 minutes', 'db135c35-2586-4d20-8240-601eee1f0d36', '4330e71a-c2cc-4fc9-825e-32eb1040fedc', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('fe233712-a9d2-440c-b36e-e538b2c4a21a', 'completed', '3 minutes 11 seconds', 'db135c35-2586-4d20-8240-601eee1f0d36', '5beb89e2-9634-4b67-88cc-e82fce21c4e4', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('82486c6f-4912-4cb8-8d8c-c06e37fee97f', 'running', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', 'c2f76985-761d-4b2f-a576-0c89795ce669', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('58ab6a5f-d900-45a4-8e69-51d4b214ec0f', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', '31e94cb4-838c-465d-8f8c-ab936046773e', 'a35d05ef-d1ad-40ff-8564-50234da36e9f'),
    ('e3dd8476-24d5-4d19-b374-46db9cd4aa30', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', '14aa55e9-31dd-49a8-bcfd-f9370b893eaa', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('8f3ece49-387b-44c8-8204-ba211b618d3e', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', 'bd8e8fd3-8a6e-45f1-8043-15d4f553c691', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('c6706b59-90d0-4349-b313-6857d027e935', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', '130363ac-6c6e-4f52-b01e-63f6c958108b', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('eacce1fd-c1a2-4823-a34e-6504a6089d46', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', '48b6c567-49c0-4edd-83e7-64177e7b1110', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('d8d3ebbd-6b50-46ed-8b1f-39dccfa8659d', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', 'a0fe92dd-1672-4a25-bd5d-e701db3e388c', '1ef34993-e627-4454-b119-3035fe2a9674'),
    ('53cb9406-4630-4964-bb3b-8a0244669282', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', '97de551f-2865-4058-b887-c9543db066ec', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('903b07e7-08a6-49cf-95b4-4b5300fd742b', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', '38db73c1-5dfa-49d3-ba00-ea3357471611', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('09f2d353-5ae4-4776-acf4-126ac7aaa7a9', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', '6afa7f3c-def8-4653-a2a1-f3322b84f251', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('ef7c0ed3-bdb8-412d-b6ed-007efcd7cae5', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', '36a6b1d6-bc1a-4e5c-a6df-15458665d701', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('ed3beb1d-92ba-459c-a56c-bf0ddc1cfb59', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', 'b47d528e-0083-45b7-b672-95ce50481f9f', '497f2fbc-0f54-4785-ad42-5a36592dd946'),    
    ('cda62c26-4450-40a9-91e9-5069eaa2f8ea', 'queued', NULL, 'db135c35-2586-4d20-8240-601eee1f0d36', 'eb0a547e-13b1-419b-986e-dd3b42edfe92', '497f2fbc-0f54-4785-ad42-5a36592dd946'),
    ('15cffe32-dbca-401a-b48b-8c6ea95a295f', 'completed', '2 minutes', '95a2c8cf-aab8-4632-9787-cda01ca54ba2', '8b47d64f-a26e-4d04-9bc0-71ab0c1ab0b9', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('85e37a08-a62a-4fab-ab4c-a1097762efe6', 'completed', '2 minutes 10 seconds', '95a2c8cf-aab8-4632-9787-cda01ca54ba2', '3c01785e-a459-4a96-9a9b-7fdaffb72bed', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('9d52e6d8-a705-4f87-9b89-4fc1e9040392', 'completed', '4 minutes', '95a2c8cf-aab8-4632-9787-cda01ca54ba2', 'b7fccd35-ee4e-4c68-9557-fbb3c26d7d92', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('5df524f9-72b7-4fb0-92d4-6da45a4b98fa', 'completed', '1 minutes 32 seconds', '95a2c8cf-aab8-4632-9787-cda01ca54ba2', '2ecbb423-56ee-4d82-8d16-5fef7ed64bc0', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('c1c197a6-e6b7-4800-98a7-8a12e33f3589', 'completed', '8 minutes 58 seconds', '95a2c8cf-aab8-4632-9787-cda01ca54ba2', '92dfae15-7d69-47dd-9c9f-690628a7abb5', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('131abe20-10d5-4807-af9f-0b09f5f79e51', 'completed', '2 minutes', '45412f2a-ffae-49da-bfed-42a412b6c5f2', '930a32b8-619d-4912-bd4e-ade147827dc4', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('ab8c119f-0fc5-434d-8161-f7de00696ea0', 'completed', '2 minutes 10 seconds', '45412f2a-ffae-49da-bfed-42a412b6c5f2', 'c183fd75-34e2-42fc-8ec4-b53405cbfce1', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('873ae670-36a3-4d29-a657-41da58ae4ceb', 'completed', '4 minutes', '45412f2a-ffae-49da-bfed-42a412b6c5f2', 'ca39636c-23c1-473f-a61a-000af7b59562', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7'),
    ('fc118764-b299-4add-bc4f-07eae300c53e', 'completed', '1 minutes 32 seconds', '45412f2a-ffae-49da-bfed-42a412b6c5f2', '65eb13d9-6b77-4f3d-9c87-2d06e60e578a', '0dfb882e-adb2-41f3-8b58-f2fddcf502d7');
    
-- Material Order
INSERT INTO Factory.Material_order VALUES
    ('47326289-5cb8-4409-a2fe-049e7abb96b2', 2450, '2020-12-02', 'completed', NULL),
    ('317804f2-af04-4668-a13b-6b9909d70d52', 1356.30, '2020-12-06', 'completed', NULL),
    ('dfcbe64d-c7e4-4130-8ea0-35b491002474', 1200, '2020-11-20', 'received', '2020-11-30'),
    ('c6c1fad1-2a42-44d5-9871-31e77673465e', 1500, '2020-11-10', 'received', '2020-11-30'),
    ('a3173888-24e6-417b-b56f-f2ede7a79687', 800, '2020-11-01', 'received', '2020-11-30'),
    ('1c7058f9-ff5f-4721-a6da-47a017cc73a1', 10000, '2020-11-29', 'received', '2020-11-30');

-- Supplying
INSERT INTO Factory.Supplying VALUES
    ('988464f7-b982-4a17-a9e4-2a7263ba89f3', '8affcd09-a7c6-4592-b467-3bf5c7cfd727', 800, '47326289-5cb8-4409-a2fe-049e7abb96b2', 'Andrighetti'),
    ('57772874-fe2e-4c41-8541-d59e1aced5ec', '7060cc61-4bd5-4c15-a694-50ca35c37f03', 400, '317804f2-af04-4668-a13b-6b9909d70d52', 'Beato materiali ferrosi'),
    ('e2252b41-81d5-4e4b-8a6e-40fbe674c57b', '01a3b538-58e8-4e3a-b37c-af840a73a1aa', 20, 'dfcbe64d-c7e4-4130-8ea0-35b491002474', 'TecnoVict agriculture'),
    ('3876f71b-9273-451c-9e3e-6ce1ba010289', 'dfbd2498-42a0-44c4-a2a4-047634dcbaa6', 250, 'c6c1fad1-2a42-44d5-9871-31e77673465e', 'Siemens'),
    ('9241c220-58be-424b-924b-db3fd569f792', '89692ca0-3779-4dde-a106-729cb07b341f', 300, 'dfcbe64d-c7e4-4130-8ea0-35b491002474', 'TecnoVict agriculture'),
    ('72ab513e-2802-4a73-8f73-9351901a97fe', '01f31192-33f1-4d3f-981c-f86e413dc1ae', 1000, '47326289-5cb8-4409-a2fe-049e7abb96b2', 'Andrighetti'),
    ('6af5bf62-46f0-476f-b80b-2250c889dfec', 'c09317db-796c-40b8-bba5-9569d5af6ac8', 30, 'c6c1fad1-2a42-44d5-9871-31e77673465e', 'Siemens'),
    ('bcf74380-8384-487a-b35f-e25a76cb8c58', '3f5fcc83-16b7-45d8-922b-49b23a2b000b', 500, 'a3173888-24e6-417b-b56f-f2ede7a79687', 'Tecnoerre carpenteria metallica'),
    ('fd019019-6e60-4bea-8f85-242ab52c67ab', 'd3253c0a-9ecf-4f2b-8648-403f1ba7d5cd', 10, 'dfcbe64d-c7e4-4130-8ea0-35b491002474', 'TecnoVict agriculture'),
    ('3d1f8b05-155c-4056-87a5-235cd7793549', '55bfe270-5acc-4541-9d21-4f6fe1b604db', 320, 'a3173888-24e6-417b-b56f-f2ede7a79687', 'Tecnoerre carpenteria metallica'),
    ('ee44e49f-2236-42de-a695-f0e396a00f55', 'f84ecff5-1589-4a63-b736-dda563933c7e', 450, '317804f2-af04-4668-a13b-6b9909d70d52', 'Beato materiali ferrosi'),
    ('0082df60-34be-4e59-b283-adf55b92846b', 'e4eecad6-3752-4e5d-8be1-e372ac5b21f8', 550, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('21334b84-869e-484e-8e90-6455a202d549', 'f1327907-af50-4971-ab43-b38c9e93c6b4', 850, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('e7985ab7-e51e-4a65-a4b0-e299d5eeacf6', 'a03feec7-ed72-4d93-b8d9-fa4c934401ed', 950, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('552a806e-43f4-4185-91eb-cba8e4b60000', 'a59b96df-ec2d-46d3-9c07-3798de457e87', 350, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('19e9b133-fbbf-4126-bc53-91029df965dd', 'eaaf3889-bba8-4229-a7db-536349645610', 450, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('87f3070f-694c-448c-8aea-60fe6a5eb6be', '1ec551a0-40e2-49c5-b6fa-4a88972af506', 550, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('bc640be6-ed3b-4055-9c53-0673d4a017c9', '62bdfe1b-349e-4fab-9e15-c66dafc4cbdf', 250, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('80fc1a26-6084-4620-adfd-9fead4510b6e', '7ae10a38-65ee-4375-8396-127de5eed32f', 750, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('9008e60c-f642-464a-9049-b91bae4e4045', '2e1c2bc5-5375-4539-a807-cf5ee1094e9b', 850, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('67cde58f-e1e9-4b71-8841-f6b2cc597098', 'fee9b180-9750-4db2-9642-fd12c93e584a', 650, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('5dd5c6f2-2807-4675-ac24-456235e6d66b', '62462437-2b12-4c89-b36f-efe8191702ea', 550, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('72a33308-c8d0-46fc-9237-ed66ad0f43eb', '948d57d6-b40c-448f-9717-4bc59b586610', 1450, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('58b5e5a5-0ca5-478a-bf41-8483e3254572', '9211b14e-ae15-4db1-b8ea-9a7251a4dcc0', 650, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('1e099ed7-9656-4cd6-a385-f4e62e5f9e2f', '1bf38d88-ea84-43ce-83cc-6eee9c9b0cae', 950, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('8774c734-33c9-4346-a826-44a635b44986', 'f25efb70-4f8a-40af-813f-3f1d4daf72c7', 750, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('4572b3e9-51d1-4fbf-9300-5b90ec624d77', 'c486d316-2e54-4f9a-8ee6-fb8e94315102', 550, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('1048e139-ff6e-4476-8414-ed6d07f92b12', 'bcfcdf70-83a2-412d-85a9-65d6de8d0bcb', 550, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('55a28e40-273f-4122-949b-88a4adb84fff', '263842c5-662d-4870-bf48-fda618f84a6b', 650, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('cf8ba242-2c18-48d6-8859-ca95db29b3a7', '72f21507-45d1-473b-9cf0-2151c1b8b8a5', 450, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi'),
    ('986f7f56-cd0c-4654-a898-d0d771560153', '6ea11bfd-280f-470a-9a86-42a6683bc625', 350, '1c7058f9-ff5f-4721-a6da-47a017cc73a1', 'Beato materiali ferrosi');

-- Fixed Cost
INSERT INTO Factory.Fixed_cost VALUES
    ('2020-12-21', 'rent', 5000, NULL),
    ('2020-12-15', 'electrical_bill', 3000, NULL),
    ('2020-12-10', 'gas_bill', 450, NULL),
    ('2020-12-20', 'water_bill', 790, NULL),
    ('2020-12-15', 'tax', 2800, NULL),
    ('2020-11-21', 'rent', 5000, '2020-11-30'),
    ('2020-11-15', 'electrical_bill', 4000, '2020-11-30'),
    ('2020-11-10', 'gas_bill', 400, '2020-11-30'),
    ('2020-11-20', 'water_bill', 800, '2020-11-30'),
    ('2020-11-15', 'tax', 3000, '2020-11-30');
