CREATE DATABASE system_commerce;

\c system_commerce;

CREATE SEQUENCE seq_service;
CREATE TABLE service (
    id_service INTEGER DEFAULT nextval('seq_service'),
    service VARCHAR(50),
    fonction VARCHAR(50),
    creation_date DATE,
    status INTEGER,
    PRIMARY KEY(id_service)
);

CREATE SEQUENCE seq_utilisateur;
CREATE TABLE utilisateur (
    id_utilisateur INTEGER DEFAULT nextval('seq_utilisateur'),
    id_service INTEGER,
    username VARCHAR(50),
    password VARCHAR(20),
    mail VARCHAR(50),
    admin BOOLEAN,
    photo VARCHAR(50),
    status INTEGER,
    PRIMARY KEY(id_utilisateur),
    FOREIGN KEY(id_service) REFERENCES service(id_service)
);

ALTER SEQUENCE seq_service RESTART WITH 1;
INSERT INTO service(service, fonction, creation_date, status ) VALUES ('Administration', 'Responsable de l''administration du societe', '2022-01-01', 1);
INSERT INTO service(service, fonction, creation_date, status ) VALUES ('Ressources humaines', 'Responsable des ressources humaines', '2022-01-01', 1);
INSERT INTO service(service, fonction, creation_date, status ) VALUES ('Production', 'Responsable des productions du societe', '2022-01-01', 1);
INSERT INTO service(service, fonction, creation_date, status ) VALUES ('Approvisionnement', 'Responsable des achats du societe', '2022-01-01', 1);
INSERT INTO service(service, fonction, creation_date, status ) VALUES ('Finance', 'Responsable du finance du societe', '2022-01-01', 1);

ALTER SEQUENCE seq_utilisateur RESTART WITH 1;
INSERT INTO utilisateur(id_service, username, password, mail, status, admin, photo ) VALUES (1, 'INSSA Chalman', 'chalman', 'inssa.chalman@gmail.com', 1, true, 'chalman.png');
INSERT INTO utilisateur(id_service, username, password, mail, status, admin, photo ) VALUES (4, 'To MAMIARILAZA', 'to', 'mamiarilaza.to@gmail.com', 1, true, 'to.png');
INSERT INTO utilisateur(id_service, username, password, mail, status, admin, photo ) VALUES (3, 'Fy Michael', 'fy', 'fy.michael@gmail.com', 1, true, 'fy.png');
INSERT INTO utilisateur(id_service, username, password, mail, status, admin, photo ) VALUES (3, 'Finoana RAKOTO', 'finoana', 'finoanaRAKOTO@gmail.com', 1, false, 'finoana.png');
INSERT INTO utilisateur(id_service, username, password, mail, status, admin, photo ) VALUES (3, 'Solo RATSIVAHINY', 'solo', 'soloRATSIVAHINY@gmail.com', 1, false, 'solo.png');
INSERT INTO utilisateur(id_service, username, password, mail, status, admin, photo ) VALUES (5, 'Mialy RIANTSOA', 'mialy', 'mialy.RIANTSOA@gmail.com', 1, true, 'mialy.png');

CREATE SEQUENCE seq_category;
CREATE TABLE category (
    id_category int PRIMARY KEY DEFAULT nextval('seq_category') NOT NULL,
    code VARCHAR(30),
    designation VARCHAR(100),
    description VARCHAR(1000),
    status INTEGER
);

CREATE SEQUENCE seq_unity;
CREATE TABLE unity (
    id_unity int PRIMARY KEY DEFAULT nextval('seq_unity') NOT NULL,
    name VARCHAR(50)
);

CREATE SEQUENCE seq_article;
CREATE TABLE article (
    id_article int PRIMARY KEY DEFAULT nextval('seq_article') NOT NULL,
    code VARCHAR(30),
    description VARCHAR(1000),
    designation VARCHAR(100),
    id_category INTEGER,
    tva DECIMAL(10, 2),
    id_unity int,
    status INTEGER,
    FOREIGN KEY(id_category) REFERENCES category(id_category),
    FOREIGN KEY(id_unity) REFERENCES unity(id_unity)
);


-- Insertion des donnees de test dans la table unity
INSERT INTO unity(name) VALUES 
    ('Kg'),
    ('Litres'),
    ('Piece');

-- Insertion des données de test dans la table 'category'
ALTER SEQUENCE seq_category RESTART WITH 1;
INSERT INTO category (code, designation, description, status) VALUES
    ('CAT001', 'Fournitures de bureau', 'Les fournitures necessaire aux bureaux', 1),
    ('CAT002', 'Matiere premiere', 'Matiere premiere pour la production', 1),
    ('CAT003', 'Fournitures menagere', 'Fournitures pour le menage', 1),
    ('CAT004', 'Automobile', 'Piece, Entretien, carburant des voitures', 1);



-- Insertion des données de test dans la table 'article'
ALTER SEQUENCE seq_article RESTART WITH 1;
-- Fournitures de bureau
INSERT INTO article (code, description, designation, id_category, tva, id_unity, status) VALUES
    ('ART001', 'Stylos a encre noire de qualite professionnelle', 'Stylos a encre', 1, 20.0, 3, 1),
    ('ART002', 'Cahiers a spirale avec couverture rigide', 'Cahiers a spirale', 1, 20.0, 3, 1),
    ('ART003', 'Classeurs de bureau pour l''organisation des documents', 'Classeurs de bureau', 1, 20.0, 3, 1);

-- Matiere premiere
INSERT INTO article (code, description, designation, id_category, tva, id_unity, status) VALUES
    ('ART004', 'Bobine de fil d''acier inoxydable pour la production', 'Fil d''acier inoxydable', 2, 20.0, 3, 1),
    ('ART005', 'Tissu en coton de haute qualite', 'Tissu en coton', 2, 20.0, 1, 1),
    ('ART006', 'Blocs de bois pour la sculpture et la production', 'Blocs de bois', 2, 20.0, 1, 1);

-- Fournitures menagere
INSERT INTO article (code, description, designation, id_category, tva, id_unity, status) VALUES
    ('ART007', 'Ensemble de couverts en acier inoxydable', 'Couverts en acier inoxydable', 3, 20.0, 3, 1),
    ('ART008', 'Serviettes en papier de haute qualite', 'Serviettes en papier', 3, 20.0, 3, 1),
    ('ART009', 'Produits de nettoyage ecologiques', 'Produits de nettoyage ecologiques', 3, 20.0, 3, 1);

-- Automobile
INSERT INTO article (code, description, designation, id_category, tva, id_unity, status) VALUES
    ('ART010', 'Filtre a huile de rechange pour voitures', 'Filtre a huile', 4, 20.0, 3, 1),
    ('ART011', 'Essuie-glace de remplacement haute performance', 'Essuie-glace', 4, 20.0, 3, 1),
    ('ART012', 'Huile moteur synthetique de qualite superieure', 'Huile moteur synthetique', 4, 20.0, 2, 1);


-- TABLE REQUIS POUR LE DEMANDE D'ACHAT

CREATE SEQUENCE seq_purchase_request;
CREATE TABLE purchase_request (
    id_purchase_request INTEGER DEFAULT nextval('seq_purchase_request'),
    sending_date DATE,
    id_utilisateur INTEGER,
    id_service INTEGER,
    title VARCHAR(50),
    description VARCHAR(200),
    status INTEGER,
    PRIMARY KEY(id_purchase_request),
    FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id_utilisateur),
    FOREIGN KEY(id_service) REFERENCES service(id_service)
);

CREATE SEQUENCE seq_article_quantity;
CREATE TABLE article_quantity (
    id_article_quantity INTEGER DEFAULT nextval('seq_article_quantity'),
    id_purchase_request INTEGER,
    id_article INTEGER,
    quantity DECIMAL(8, 2),
    id_purchase_order INTEGER,      -- A reviser lorsqu'on arrive dans le purchase order
    amount DECIMAL(10, 2),          -- En attente du purchase order
    status INTEGER,
    PRIMARY KEY(id_article_quantity),
    FOREIGN KEY(id_purchase_request) REFERENCES purchase_request(id_purchase_request),
    FOREIGN KEY(id_article) REFERENCES article(id_article)
);

ALTER SEQUENCE seq_purchase_request RESTART WITH 1;
INSERT INTO purchase_request (sending_date, id_utilisateur, id_service, title, description, status) VALUES 
('2023-11-18', 1, 1, 'Besoin mensuel du departement', 'Comme pour chaque mois, nous avons besoins de ces bien.', 1),
('2023-11-18', 3, 3, 'Achat de matiere premiere', 'La production d''huile a augmente et nous avons besoins de plus d''arachide.', 1);

ALTER SEQUENCE seq_article_quantity RESTART WITH 1;
INSERT INTO article_quantity (id_purchase_request, id_article, quantity, id_purchase_order, amount, status) VALUES
(1, 1, 10, NULL, 0, 1),
(1, 2, 10, NULL, 0, 1),
(1, 8, 20, NULL, 0, 1),
(2, 12, 2, NULL, 0, 1),
(2, 5, 6, NULL, 0, 1),
(2, 8, 8, NULL, 0, 1);

-- TEMPORARY TABLE FOR SUPPLIER
CREATE SEQUENCE seq_supplier;
CREATE TABLE supplier (
    id_supplier INTEGER PRIMARY KEY DEFAULT nextval('seq_supplier'),
    supplier_name VARCHAR(50),
    supplier_address VARCHAR(50),
    responsable_contact VARCHAR(20),
    mail VARCHAR(30),
    status INTEGER
);

ALTER SEQUENCE seq_supplier RESTART WITH 1;
INSERT INTO supplier (supplier_name, supplier_address, responsable_contact, mail, status) VALUES
('Leader Price', 'Tanjombato', '+261 32 125 63', 'leaderPrice@example.com', 1),
('Jumbo Score', 'Mahamasina', '+261 34 238 14', 'jumboScore@example.com', 1),
('Hazo Vato', 'Ambohidranandriana', '+261 20 120 32', 'hazoVato@example.com', 1),
('Magasin U', 'Analakely', '+261 33 125 62', 'magasinU@example.com', 1);

CREATE SEQUENCE seq_supplier_category_product;
CREATE TABLE supplier_category_product (
    id_supplier_category_product INTEGER PRIMARY KEY DEFAULT nextval('seq_supplier_category_product'),
    id_supplier INTEGER,
    id_category INTEGER,
    FOREIGN KEY(id_supplier) REFERENCES supplier(id_supplier),
    FOREIGN KEY(id_category) REFERENCES category(id_category)
);

ALTER SEQUENCE seq_supplier_category_product RESTART WITH 1;
INSERT INTO supplier_category_product (id_supplier, id_category) VALUES
(1, 1),
(1, 3),
(2, 1),
(2, 4),
(2, 3),
(3, 2),
(4, 1),
(4, 3);

-- TABLE ET VIEW REQUIS POUR L'INSERTION DE PRIX PAR FOURNISSEUR
CREATE SEQUENCE seq_supplier_article_price;
CREATE TABLE supplier_article_price (
    id_supplier_article_price INTEGER PRIMARY KEY DEFAULT nextval('seq_supplier_article_price'),
    date DATE,
    id_article INTEGER,
    id_supplier INTEGER,
    unit_price DECIMAL(10, 2),
    chosen BOOLEAN DEFAULT false,
    status INTEGER DEFAULT 1,
    FOREIGN KEY(id_article) REFERENCES article(id_article),
    FOREIGN KEY(id_supplier) REFERENCES supplier(id_supplier)
);

ALTER SEQUENCE seq_supplier_article_price RESTART WITH 1;
-- Prix pour l'article 1 stylos a encre
INSERT INTO supplier_article_price (date, id_article, id_supplier, unit_price, chosen, status) VALUES 
('2023-11-01', 1, 1, 800, false, 1),
('2023-11-01', 1, 2, 1000, false, 1),
('2023-11-01', 1, 4, 700, true, 1);

-- Prix pour l'article 2 cahiers a spirale
INSERT INTO supplier_article_price (date, id_article, id_supplier, unit_price, chosen, status) VALUES 
('2023-11-10', 2, 1, 1200, false, 1),
('2023-11-10', 2, 2, 900, false, 1),
('2023-11-10', 2, 4, 1000, true, 1);

-- Prix pour l'article 2 cahiers a spirale
INSERT INTO supplier_article_price (date, id_article, id_supplier, unit_price, chosen, status) VALUES 
('2023-11-10', 5, 3, 18000, true, 1);

-- View pour avoir les article quantity encore valide 
-- et le demande maitre est validé ie status = 2 et l'article est encore en phase demande ie status = 1
CREATE VIEW v_article_quantity_valid AS
SELECT a.* FROM article_quantity a 
JOIN purchase_request p ON a.id_purchase_request = p.id_purchase_request
WHERE p.status = 2 AND a.status = 1;

-- View pour grouper les articles et leurs quantite pour avoir le besoin general par nature
CREATE VIEW v_article_request AS 
SELECT id_article, SUM(quantity) FROM v_article_quantity_valid GROUP BY (id_article);

-- View pour avoir les prix des fournisseurs qui sont encore valide et duree inférieur à 1 mois
CREATE OR REPLACE VIEW v_supplier_article_price_valid AS
SELECT * FROM supplier_article_price WHERE status = 1 AND date + 30 >= CURRENT_DATE;

-- View pour avoir les fournisseurs choisi
CREATE VIEW v_chosen_supplier AS
SELECT s.* FROM 
(SELECT id_supplier FROM v_supplier_article_price_valid WHERE chosen IS TRUE GROUP BY id_supplier) AS cs JOIN
supplier s ON cs.id_supplier = s.id_supplier;

-- Fonction pour avoir les articles et leurs quantite et unit_price dans la quelle le fournisseur est choisi
CREATE OR REPLACE FUNCTION get_supplier_article_quantity(supplier INTEGER)
RETURNS TABLE (
    id_article INTEGER,
    quantity DECIMAL(8, 2),
    unit_price DECIMAL(10, 2)
)  AS $$
BEGIN
RETURN QUERY
    SELECT  
        ar.id_article,
        ar.sum,
        sv2.unit_price
    FROM 
        (
            SELECT 
                ar.id_article,
                ar.sum
            FROM v_article_request ar 
            WHERE ar.id_article IN
            (   SELECT 
                    sv.id_article 
                FROM 
                    v_supplier_article_price_valid sv
                WHERE 
                    sv.id_supplier = supplier
                AND chosen IS TRUE
            ) 
        ) AS ar
    JOIN 
        v_supplier_article_price_valid sv2 
    ON ar.id_article = sv2.id_article AND sv2.id_supplier = supplier;
END;
$$ LANGUAGE plpgsql;

-- Fonction pour avoir le proforma pour un fournisseur donnée
CREATE OR REPLACE FUNCTION get_supplier_proforma(supplier INTEGER)
RETURNS TABLE (
    id_article INTEGER,
    quantity DECIMAL(8, 2),
    unit_price DECIMAL(10, 2),
    tva DECIMAL(10, 2),
    tva_amount DECIMAL(10, 2),
    ht_amount DECIMAL(10, 2),
    ttc_amount DECIMAL(10, 2)
)  AS $$
BEGIN
RETURN QUERY
    SELECT  
        a.id_article,
        aq.quantity,
        aq.unit_price,
        a.tva,
        aq.quantity * (aq.unit_price * (a.tva / 100)),
        (aq.quantity * aq.unit_price),
        (aq.quantity * (aq.unit_price + (aq.unit_price * (a.tva / 100))))
    FROM 
        get_supplier_article_quantity(supplier) aq 
    JOIN 
        article a ON a.id_article = aq.id_article
    ;
END;
$$ LANGUAGE plpgsql;

-- TABLE POUR GERER LES BON DE COMMANDE
CREATE SEQUENCE seq_payment_method;
CREATE TABLE payment_method (
    id_payment_method INTEGER PRIMARY KEY DEFAULT nextval('seq_payment_method'),
    payment_method_name VARCHAR(50)
);

ALTER SEQUENCE seq_payment_method RESTART WITH 1;
INSERT INTO payment_method (payment_method_name) VALUES
('Virement bancaire'),
('Cheque bancaire'),
('CASH');


CREATE SEQUENCE seq_purchase_order;
CREATE TABLE purchase_order (
    id_purchase_order INTEGER PRIMARY KEY DEFAULT nextval('seq_purchase_order'),
    date DATE,
    id_supplier INTEGER,
    total_tva DECIMAL(10, 2),
    total_ht DECIMAL(10, 2),
    total_ttc DECIMAL(10, 2),
    delivery_date DATE,
    id_payment_method INTEGER,
    status INTEGER,
    FOREIGN KEY(id_supplier) REFERENCES supplier(id_supplier),
    FOREIGN KEY(id_payment_method) REFERENCES payment_method(id_payment_method)
);

CREATE SEQUENCE seq_purchase_order_line_item;
CREATE TABLE purchase_order_line_item (
    id_purchase_order_line_item INTEGER PRIMARY KEY DEFAULT nextval('seq_purchase_order_line_item'),
    id_purchase_order INTEGER,
    id_article INTEGER,
    quantity DECIMAL(10, 2),
    unit_price DECIMAL(10, 2),
    tva DECIMAL(10, 2),
    tva_amount DECIMAL(10, 2),
    ht_amount DECIMAL(10, 2),
    ttc_amount DECIMAL(10, 2),
    FOREIGN KEY(id_purchase_order) REFERENCES purchase_order(id_purchase_order),
    FOREIGN KEY(id_article) REFERENCES article(id_article)
);

CREATE SEQUENCE seq_payment_condition;
CREATE TABLE payment_condition (
    id_payment_condition INTEGER PRIMARY KEY DEFAULT nextval('seq_payment_condition'),
    id_purchase_order INTEGER,
    percentage DECIMAL(10, 2),
    payment_date DATE,
    FOREIGN KEY(id_purchase_order) REFERENCES purchase_order(id_purchase_order)
);

-- REINITIALISATION DE DONNEES BON DE COMMANDE
DELETE FROM purchase_order_line_item;
DELETE FROM payment_condition;
DELETE FROM purchase_order; 

DELETE FROM article_quantity;
DELETE FROM purchase_request;

UPDATE supplier_article_price SET chosen = false;


-- vue pour avoir les depense
CREATE OR REPLACE VIEW v_purchase_article_request as
 SELECT pr.id_purchase_request,
    pr.sending_date,
    pr.id_utilisateur,
    pr.id_service,
    pr.title,
    pr.description,
    pr.status,
    aq.id_article_quantity,
    aq.id_article,
    aq.quantity,
    aq.id_purchase_order,
    aq.amount,
    aq.status AS article_status
   FROM (purchase_request pr
     JOIN article_quantity aq ON ((aq.id_purchase_request = pr.id_purchase_request)))
  WHERE ((aq.status = 2) AND (pr.status = 2))
  GROUP BY pr.id_service, pr.id_purchase_request, aq.id_article_quantity;

CREATE OR REPLACE VIEW v_depense_mensuel as 
SELECT
    EXTRACT(MONTH FROM sending_date) AS mois,
    EXTRACT(YEAR FROM sending_date) AS annee,
    SUM(amount) AS montant_mensuel
FROM
    v_purchase_article_request
WHERE
    EXTRACT(YEAR FROM sending_date) = '2023' -- annee 2023
AND status = 2
GROUP BY
    EXTRACT(MONTH FROM sending_date), EXTRACT(YEAR FROM sending_date)
ORDER BY
    annee, mois;

