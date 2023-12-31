--
-- PostgreSQL database dump
--

-- Dumped from database version 12.13
-- Dumped by pg_dump version 12.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: get_supplier_article_quantity(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_supplier_article_quantity(supplier integer) RETURNS TABLE(id_article integer, quantity numeric, unit_price numeric)
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.get_supplier_article_quantity(supplier integer) OWNER TO postgres;

--
-- Name: get_supplier_proforma(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_supplier_proforma(supplier integer) RETURNS TABLE(id_article integer, quantity numeric, unit_price numeric, tva numeric, tva_amount numeric, ht_amount numeric, ttc_amount numeric)
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.get_supplier_proforma(supplier integer) OWNER TO postgres;

--
-- Name: seq_article; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_article
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_article OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: article; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.article (
    id_article integer DEFAULT nextval('public.seq_article'::regclass) NOT NULL,
    code character varying(30),
    description character varying(1000),
    designation character varying(100),
    id_category integer,
    tva numeric(10,2),
    id_unity integer,
    status integer
);


ALTER TABLE public.article OWNER TO postgres;

--
-- Name: seq_article_quantity; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_article_quantity
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_article_quantity OWNER TO postgres;

--
-- Name: article_quantity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.article_quantity (
    id_article_quantity integer DEFAULT nextval('public.seq_article_quantity'::regclass) NOT NULL,
    id_purchase_request integer,
    id_article integer,
    quantity numeric(8,2),
    id_purchase_order integer,
    amount numeric(10,2),
    status integer
);


ALTER TABLE public.article_quantity OWNER TO postgres;

--
-- Name: seq_category; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_category
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_category OWNER TO postgres;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id_category integer DEFAULT nextval('public.seq_category'::regclass) NOT NULL,
    code character varying(30),
    designation character varying(100),
    description character varying(1000),
    status integer
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: seq_payment_condition; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_payment_condition
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_payment_condition OWNER TO postgres;

--
-- Name: payment_condition; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment_condition (
    id_payment_condition integer DEFAULT nextval('public.seq_payment_condition'::regclass) NOT NULL,
    id_purchase_order integer,
    percentage numeric(10,2),
    payment_date date
);


ALTER TABLE public.payment_condition OWNER TO postgres;

--
-- Name: seq_payment_method; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_payment_method
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_payment_method OWNER TO postgres;

--
-- Name: payment_method; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment_method (
    id_payment_method integer DEFAULT nextval('public.seq_payment_method'::regclass) NOT NULL,
    payment_method_name character varying(50)
);


ALTER TABLE public.payment_method OWNER TO postgres;

--
-- Name: seq_purchase_order; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_purchase_order
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_purchase_order OWNER TO postgres;

--
-- Name: purchase_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchase_order (
    id_purchase_order integer DEFAULT nextval('public.seq_purchase_order'::regclass) NOT NULL,
    date date,
    id_supplier integer,
    total_tva numeric(10,2),
    total_ht numeric(10,2),
    total_ttc numeric(10,2),
    delivery_date date,
    id_payment_method integer,
    status integer
);


ALTER TABLE public.purchase_order OWNER TO postgres;

--
-- Name: seq_purchase_order_line_item; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_purchase_order_line_item
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_purchase_order_line_item OWNER TO postgres;

--
-- Name: purchase_order_line_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchase_order_line_item (
    id_purchase_order_line_item integer DEFAULT nextval('public.seq_purchase_order_line_item'::regclass) NOT NULL,
    id_purchase_order integer,
    id_article integer,
    quantity numeric(10,2),
    unit_price numeric(10,2),
    tva numeric(10,2),
    tva_amount numeric(10,2),
    ht_amount numeric(10,2),
    ttc_amount numeric(10,2)
);


ALTER TABLE public.purchase_order_line_item OWNER TO postgres;

--
-- Name: seq_purchase_request; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_purchase_request
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_purchase_request OWNER TO postgres;

--
-- Name: purchase_request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchase_request (
    id_purchase_request integer DEFAULT nextval('public.seq_purchase_request'::regclass) NOT NULL,
    sending_date date,
    id_utilisateur integer,
    id_service integer,
    title character varying(50),
    description character varying(200),
    status integer
);


ALTER TABLE public.purchase_request OWNER TO postgres;

--
-- Name: seq_service; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_service
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_service OWNER TO postgres;

--
-- Name: seq_supplier; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_supplier
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_supplier OWNER TO postgres;

--
-- Name: seq_supplier_article_price; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_supplier_article_price
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_supplier_article_price OWNER TO postgres;

--
-- Name: seq_supplier_category_product; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_supplier_category_product
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_supplier_category_product OWNER TO postgres;

--
-- Name: seq_unity; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_unity
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_unity OWNER TO postgres;

--
-- Name: seq_utilisateur; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_utilisateur
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_utilisateur OWNER TO postgres;

--
-- Name: service; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.service (
    id_service integer DEFAULT nextval('public.seq_service'::regclass) NOT NULL,
    service character varying(50),
    fonction character varying(50),
    creation_date date,
    status integer
);


ALTER TABLE public.service OWNER TO postgres;

--
-- Name: supplier; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.supplier (
    id_supplier integer DEFAULT nextval('public.seq_supplier'::regclass) NOT NULL,
    supplier_name character varying(50),
    supplier_address character varying(50),
    responsable_contact character varying(20),
    mail character varying(30),
    status integer
);


ALTER TABLE public.supplier OWNER TO postgres;

--
-- Name: supplier_article_price; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.supplier_article_price (
    id_supplier_article_price integer DEFAULT nextval('public.seq_supplier_article_price'::regclass) NOT NULL,
    date date,
    id_article integer,
    id_supplier integer,
    unit_price numeric(10,2),
    chosen boolean DEFAULT false,
    status integer DEFAULT 1
);


ALTER TABLE public.supplier_article_price OWNER TO postgres;

--
-- Name: supplier_category_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.supplier_category_product (
    id_supplier_category_product integer DEFAULT nextval('public.seq_supplier_category_product'::regclass) NOT NULL,
    id_supplier integer,
    id_category integer
);


ALTER TABLE public.supplier_category_product OWNER TO postgres;

--
-- Name: unity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.unity (
    id_unity integer DEFAULT nextval('public.seq_unity'::regclass) NOT NULL,
    name character varying(50)
);


ALTER TABLE public.unity OWNER TO postgres;

--
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur (
    id_utilisateur integer DEFAULT nextval('public.seq_utilisateur'::regclass) NOT NULL,
    id_service integer,
    username character varying(50),
    password character varying(20),
    mail character varying(50),
    admin boolean,
    photo character varying(50),
    status integer
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- Name: v_article_quantity_valid; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_article_quantity_valid AS
 SELECT a.id_article_quantity,
    a.id_purchase_request,
    a.id_article,
    a.quantity,
    a.id_purchase_order,
    a.amount,
    a.status
   FROM (public.article_quantity a
     JOIN public.purchase_request p ON ((a.id_purchase_request = p.id_purchase_request)))
  WHERE ((p.status = 2) AND (a.status = 1));


ALTER TABLE public.v_article_quantity_valid OWNER TO postgres;

--
-- Name: v_article_request; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_article_request AS
 SELECT v_article_quantity_valid.id_article,
    sum(v_article_quantity_valid.quantity) AS sum
   FROM public.v_article_quantity_valid
  GROUP BY v_article_quantity_valid.id_article;


ALTER TABLE public.v_article_request OWNER TO postgres;

--
-- Name: v_supplier_article_price_valid; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_supplier_article_price_valid AS
 SELECT supplier_article_price.id_supplier_article_price,
    supplier_article_price.date,
    supplier_article_price.id_article,
    supplier_article_price.id_supplier,
    supplier_article_price.unit_price,
    supplier_article_price.chosen,
    supplier_article_price.status
   FROM public.supplier_article_price
  WHERE ((supplier_article_price.status = 1) AND ((supplier_article_price.date + 30) >= CURRENT_DATE));


ALTER TABLE public.v_supplier_article_price_valid OWNER TO postgres;

--
-- Name: v_chosen_supplier; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_chosen_supplier AS
 SELECT s.id_supplier,
    s.supplier_name,
    s.supplier_address,
    s.responsable_contact,
    s.mail,
    s.status
   FROM (( SELECT v_supplier_article_price_valid.id_supplier
           FROM public.v_supplier_article_price_valid
          WHERE (v_supplier_article_price_valid.chosen IS TRUE)
          GROUP BY v_supplier_article_price_valid.id_supplier) cs
     JOIN public.supplier s ON ((cs.id_supplier = s.id_supplier)));


ALTER TABLE public.v_chosen_supplier OWNER TO postgres;

--
-- Data for Name: article; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.article (id_article, code, description, designation, id_category, tva, id_unity, status) FROM stdin;
2	ART002	Cahiers a spirale avec couverture rigide	Cahiers a spirale	1	20.00	3	1
3	ART003	Classeurs de bureau pour l'organisation des documents	Classeurs de bureau	1	20.00	3	1
4	ART004	Bobine de fil d'acier inoxydable pour la production	Fil d'acier inoxydable	2	20.00	3	1
5	ART005	Tissu en coton de haute qualite	Tissu en coton	2	20.00	1	1
6	ART006	Blocs de bois pour la sculpture et la production	Blocs de bois	2	20.00	1	1
7	ART007	Ensemble de couverts en acier inoxydable	Couverts en acier inoxydable	3	20.00	3	1
8	ART008	Serviettes en papier de haute qualite	Serviettes en papier	3	20.00	3	1
9	ART009	Produits de nettoyage ecologiques	Produits de nettoyage ecologiques	3	20.00	3	1
10	ART010	Filtre a huile de rechange pour voitures	Filtre a huile	4	20.00	3	1
11	ART011	Essuie-glace de remplacement haute performance	Essuie-glace	4	20.00	3	1
12	ART012	Huile moteur synthetique de qualite superieure	Huile moteur synthetique	4	20.00	2	1
1	ART001	Stylos a encre noire de qualite professionnelle	Stylos a encre	1	20.00	3	1
13	ART0030	Appel telephonique	Smartphone	5	10.00	3	0
\.


--
-- Data for Name: article_quantity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.article_quantity (id_article_quantity, id_purchase_request, id_article, quantity, id_purchase_order, amount, status) FROM stdin;
37	13	2	5.00	0	0.00	1
33	11	2	10.00	0	0.00	1
34	11	3	5.00	0	0.00	1
35	11	8	6.00	0	0.00	1
36	12	12	2.00	0	0.00	1
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id_category, code, designation, description, status) FROM stdin;
1	CAT001	Fournitures de bureau	Les fournitures necessaire aux bureaux	1
2	CAT002	Matiere premiere	Matiere premiere pour la production	1
3	CAT003	Fournitures menagere	Fournitures pour le menage	1
4	CAT004	Automobile	Piece, Entretien, carburant des voitures	1
5	CAT0010	Electronique	Materiel et accessoire electronique	1
\.


--
-- Data for Name: payment_condition; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment_condition (id_payment_condition, id_purchase_order, percentage, payment_date) FROM stdin;
\.


--
-- Data for Name: payment_method; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment_method (id_payment_method, payment_method_name) FROM stdin;
1	Virement bancaire
2	Cheque bancaire
3	CASH
4	Omena tanana
\.


--
-- Data for Name: purchase_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.purchase_order (id_purchase_order, date, id_supplier, total_tva, total_ht, total_ttc, delivery_date, id_payment_method, status) FROM stdin;
\.


--
-- Data for Name: purchase_order_line_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.purchase_order_line_item (id_purchase_order_line_item, id_purchase_order, id_article, quantity, unit_price, tva, tva_amount, ht_amount, ttc_amount) FROM stdin;
\.


--
-- Data for Name: purchase_request; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.purchase_request (id_purchase_request, sending_date, id_utilisateur, id_service, title, description, status) FROM stdin;
11	2023-11-22	6	5	Besoin en fourniture de bureau	Comme pour chaque mois, nous avons besoins des fournitures suivant avec leurs quantite respective	2
12	2023-11-22	4	3	Achat de matiere premiere	La production dans notre departement a augmente ces dernier temps, et nous avons besoins de plus de matiere premiere.	1
13	2023-11-22	2	4	Demande d'essaie	Essaie	2
\.


--
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.service (id_service, service, fonction, creation_date, status) FROM stdin;
1	Administration	Responsable de l'administration du societe	2022-01-01	1
2	Ressources humaines	Responsable des ressources humaines	2022-01-01	1
3	Production	Responsable des productions du societe	2022-01-01	1
4	Approvisionnement	Responsable des achats du societe	2022-01-01	1
5	Finance	Responsable du finance du societe	2022-01-01	1
\.


--
-- Data for Name: supplier; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.supplier (id_supplier, supplier_name, supplier_address, responsable_contact, mail, status) FROM stdin;
2	Jumbo Score	Mahamasina	+261 34 238 14	jumboScore@example.com	1
3	Hazo Vato	Ambohidranandriana	+261 20 120 32	hazoVato@example.com	1
4	Magasin U	Analakely	+261 33 125 62	magasinU@example.com	1
1	Leader Price	Tanjombato	+261 32 125 63	leaderPrice@example.com	1
5	IT University	Andoharanofotsy	03215235	ituniversity@gmail.com	0
\.


--
-- Data for Name: supplier_article_price; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.supplier_article_price (id_supplier_article_price, date, id_article, id_supplier, unit_price, chosen, status) FROM stdin;
28	2023-11-22	6	3	20000.00	f	1
24	2023-11-20	2	1	6000.00	f	1
23	2023-11-20	2	2	5000.00	t	1
26	2023-11-20	3	4	8000.00	f	1
25	2023-11-20	3	1	5000.00	f	1
27	2023-11-20	3	2	7000.00	t	1
21	2023-11-20	1	1	700.00	f	1
22	2023-11-20	1	4	900.00	f	1
20	2023-11-20	1	2	800.00	f	1
\.


--
-- Data for Name: supplier_category_product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.supplier_category_product (id_supplier_category_product, id_supplier, id_category) FROM stdin;
3	2	1
4	2	4
5	2	3
6	3	2
7	4	1
8	4	3
9	1	1
10	1	3
11	5	4
\.


--
-- Data for Name: unity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.unity (id_unity, name) FROM stdin;
1	Kg
2	Litres
3	Piece
\.


--
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utilisateur (id_utilisateur, id_service, username, password, mail, admin, photo, status) FROM stdin;
1	1	INSSA Chalman	chalman	inssa.chalman@gmail.com	t	chalman.png	1
2	4	To MAMIARILAZA	to	mamiarilaza.to@gmail.com	t	to.png	1
3	3	Fy Michael	fy	fy.michael@gmail.com	t	fy.png	1
4	3	Finoana RAKOTO	finoana	finoanaRAKOTO@gmail.com	f	finoana.png	1
5	3	Solo RATSIVAHINY	solo	soloRATSIVAHINY@gmail.com	f	solo.png	1
6	5	Mialy RIANTSOA	mialy	mialy.RIANTSOA@gmail.com	t	mialy.png	1
\.


--
-- Name: seq_article; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_article', 13, true);


--
-- Name: seq_article_quantity; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_article_quantity', 37, true);


--
-- Name: seq_category; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_category', 5, true);


--
-- Name: seq_payment_condition; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_payment_condition', 14, true);


--
-- Name: seq_payment_method; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_payment_method', 4, true);


--
-- Name: seq_purchase_order; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_purchase_order', 29, true);


--
-- Name: seq_purchase_order_line_item; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_purchase_order_line_item', 24, true);


--
-- Name: seq_purchase_request; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_purchase_request', 13, true);


--
-- Name: seq_service; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_service', 5, true);


--
-- Name: seq_supplier; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_supplier', 5, true);


--
-- Name: seq_supplier_article_price; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_supplier_article_price', 28, true);


--
-- Name: seq_supplier_category_product; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_supplier_category_product', 11, true);


--
-- Name: seq_unity; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_unity', 1, false);


--
-- Name: seq_utilisateur; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_utilisateur', 6, true);


--
-- Name: article article_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id_article);


--
-- Name: article_quantity article_quantity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_quantity
    ADD CONSTRAINT article_quantity_pkey PRIMARY KEY (id_article_quantity);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id_category);


--
-- Name: payment_condition payment_condition_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_condition
    ADD CONSTRAINT payment_condition_pkey PRIMARY KEY (id_payment_condition);


--
-- Name: payment_method payment_method_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT payment_method_pkey PRIMARY KEY (id_payment_method);


--
-- Name: purchase_order_line_item purchase_order_line_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_order_line_item
    ADD CONSTRAINT purchase_order_line_item_pkey PRIMARY KEY (id_purchase_order_line_item);


--
-- Name: purchase_order purchase_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_order
    ADD CONSTRAINT purchase_order_pkey PRIMARY KEY (id_purchase_order);


--
-- Name: purchase_request purchase_request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_request
    ADD CONSTRAINT purchase_request_pkey PRIMARY KEY (id_purchase_request);


--
-- Name: service service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id_service);


--
-- Name: supplier_article_price supplier_article_price_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier_article_price
    ADD CONSTRAINT supplier_article_price_pkey PRIMARY KEY (id_supplier_article_price);


--
-- Name: supplier_category_product supplier_category_product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier_category_product
    ADD CONSTRAINT supplier_category_product_pkey PRIMARY KEY (id_supplier_category_product);


--
-- Name: supplier supplier_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT supplier_pkey PRIMARY KEY (id_supplier);


--
-- Name: unity unity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.unity
    ADD CONSTRAINT unity_pkey PRIMARY KEY (id_unity);


--
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id_utilisateur);


--
-- Name: article article_id_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_id_category_fkey FOREIGN KEY (id_category) REFERENCES public.category(id_category);


--
-- Name: article article_id_unity_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_id_unity_fkey FOREIGN KEY (id_unity) REFERENCES public.unity(id_unity);


--
-- Name: article_quantity article_quantity_id_article_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_quantity
    ADD CONSTRAINT article_quantity_id_article_fkey FOREIGN KEY (id_article) REFERENCES public.article(id_article);


--
-- Name: article_quantity article_quantity_id_purchase_request_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_quantity
    ADD CONSTRAINT article_quantity_id_purchase_request_fkey FOREIGN KEY (id_purchase_request) REFERENCES public.purchase_request(id_purchase_request);


--
-- Name: payment_condition payment_condition_id_purchase_order_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_condition
    ADD CONSTRAINT payment_condition_id_purchase_order_fkey FOREIGN KEY (id_purchase_order) REFERENCES public.purchase_order(id_purchase_order);


--
-- Name: purchase_order purchase_order_id_payment_method_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_order
    ADD CONSTRAINT purchase_order_id_payment_method_fkey FOREIGN KEY (id_payment_method) REFERENCES public.payment_method(id_payment_method);


--
-- Name: purchase_order purchase_order_id_supplier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_order
    ADD CONSTRAINT purchase_order_id_supplier_fkey FOREIGN KEY (id_supplier) REFERENCES public.supplier(id_supplier);


--
-- Name: purchase_order_line_item purchase_order_line_item_id_article_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_order_line_item
    ADD CONSTRAINT purchase_order_line_item_id_article_fkey FOREIGN KEY (id_article) REFERENCES public.article(id_article);


--
-- Name: purchase_order_line_item purchase_order_line_item_id_purchase_order_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_order_line_item
    ADD CONSTRAINT purchase_order_line_item_id_purchase_order_fkey FOREIGN KEY (id_purchase_order) REFERENCES public.purchase_order(id_purchase_order);


--
-- Name: purchase_request purchase_request_id_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_request
    ADD CONSTRAINT purchase_request_id_service_fkey FOREIGN KEY (id_service) REFERENCES public.service(id_service);


--
-- Name: purchase_request purchase_request_id_utilisateur_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_request
    ADD CONSTRAINT purchase_request_id_utilisateur_fkey FOREIGN KEY (id_utilisateur) REFERENCES public.utilisateur(id_utilisateur);


--
-- Name: supplier_article_price supplier_article_price_id_article_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier_article_price
    ADD CONSTRAINT supplier_article_price_id_article_fkey FOREIGN KEY (id_article) REFERENCES public.article(id_article);


--
-- Name: supplier_article_price supplier_article_price_id_supplier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier_article_price
    ADD CONSTRAINT supplier_article_price_id_supplier_fkey FOREIGN KEY (id_supplier) REFERENCES public.supplier(id_supplier);


--
-- Name: supplier_category_product supplier_category_product_id_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier_category_product
    ADD CONSTRAINT supplier_category_product_id_category_fkey FOREIGN KEY (id_category) REFERENCES public.category(id_category);


--
-- Name: supplier_category_product supplier_category_product_id_supplier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier_category_product
    ADD CONSTRAINT supplier_category_product_id_supplier_fkey FOREIGN KEY (id_supplier) REFERENCES public.supplier(id_supplier);


--
-- Name: utilisateur utilisateur_id_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_id_service_fkey FOREIGN KEY (id_service) REFERENCES public.service(id_service);


--
-- PostgreSQL database dump complete
--

