--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

-- Started on 2017-03-14 13:30:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 16550)
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 16385)
-- Name: acl_store; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE acl_store (
    id integer NOT NULL,
    label character varying(100) NOT NULL,
    droit_id integer NOT NULL,
    profil_id integer NOT NULL,
    ressource_id integer NOT NULL,
    lastupdate timestamp without time zone NOT NULL
);


ALTER TABLE acl_store OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16388)
-- Name: acl_store_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE acl_store_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE acl_store_id_seq OWNER TO postgres;

--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 183
-- Name: acl_store_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE acl_store_id_seq OWNED BY acl_store.id;


--
-- TOC entry 195 (class 1259 OID 16492)
-- Name: commande; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE commande (
    reference text NOT NULL,
    id uuid DEFAULT gen_random_uuid() NOT NULL
);


ALTER TABLE commande OWNER TO postgres;

--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 195
-- Name: TABLE commande; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE commande IS 'panier des produits';


--
-- TOC entry 196 (class 1259 OID 16497)
-- Name: commande_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE commande_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE commande_id_seq OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16390)
-- Name: droit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE droit (
    id integer NOT NULL,
    label character varying(20) NOT NULL,
    code character varying(10) NOT NULL,
    lastupdate timestamp without time zone NOT NULL
);


ALTER TABLE droit OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16393)
-- Name: droit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE droit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE droit_id_seq OWNER TO postgres;

--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 185
-- Name: droit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE droit_id_seq OWNED BY droit.id;


--
-- TOC entry 193 (class 1259 OID 16461)
-- Name: produit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE produit (
    label character varying(40),
    reference text,
    status integer,
    quantity integer,
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    price double precision,
    idpicture character(50)
);


ALTER TABLE produit OWNER TO postgres;

--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 193
-- Name: TABLE produit; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE produit IS 'liste des produits';


--
-- TOC entry 194 (class 1259 OID 16483)
-- Name: produit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE produit_id_seq OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16395)
-- Name: profil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE profil (
    id bigint NOT NULL,
    profil character varying(10) DEFAULT ''::character varying NOT NULL,
    label character varying(20),
    lastupdate timestamp without time zone DEFAULT now() NOT NULL,
    version integer NOT NULL
);


ALTER TABLE profil OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16400)
-- Name: profil_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE profil_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profil_id_seq OWNER TO postgres;

--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 187
-- Name: profil_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE profil_id_seq OWNED BY profil.id;


--
-- TOC entry 188 (class 1259 OID 16402)
-- Name: profil_version_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE profil_version_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profil_version_seq OWNER TO postgres;

--
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 188
-- Name: profil_version_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE profil_version_seq OWNED BY profil.version;


--
-- TOC entry 189 (class 1259 OID 16404)
-- Name: ressource; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ressource (
    id integer NOT NULL,
    label character varying(100) NOT NULL,
    code character varying(10) NOT NULL,
    lastupdate timestamp without time zone NOT NULL,
    version integer
);


ALTER TABLE ressource OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16407)
-- Name: ressource_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ressource_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ressource_id_seq OWNER TO postgres;

--
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 190
-- Name: ressource_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ressource_id_seq OWNED BY ressource.id;


--
-- TOC entry 197 (class 1259 OID 16605)
-- Name: utilisateur_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE utilisateur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE utilisateur_id_seq OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16409)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE utilisateur (
    name character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    mail character varying(50) NOT NULL,
    pid integer NOT NULL,
    firstname character varying(20) NOT NULL,
    version integer NOT NULL,
    lastupdate timestamp without time zone DEFAULT now(),
    birthdate date,
    idpicture character(50),
    id bigint DEFAULT nextval('utilisateur_id_seq'::regclass) NOT NULL
);


ALTER TABLE utilisateur OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16415)
-- Name: utilisateur_version_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE utilisateur_version_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE utilisateur_version_seq OWNER TO postgres;

--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 192
-- Name: utilisateur_version_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE utilisateur_version_seq OWNED BY utilisateur.version;


--
-- TOC entry 2060 (class 2604 OID 16417)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acl_store ALTER COLUMN id SET DEFAULT nextval('acl_store_id_seq'::regclass);


--
-- TOC entry 2061 (class 2604 OID 16418)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY droit ALTER COLUMN id SET DEFAULT nextval('droit_id_seq'::regclass);


--
-- TOC entry 2065 (class 2604 OID 16464)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY profil ALTER COLUMN id SET DEFAULT nextval('profil_id_seq'::regclass);


--
-- TOC entry 2064 (class 2604 OID 16420)
-- Name: version; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY profil ALTER COLUMN version SET DEFAULT nextval('profil_version_seq'::regclass);


--
-- TOC entry 2066 (class 2604 OID 16421)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ressource ALTER COLUMN id SET DEFAULT nextval('ressource_id_seq'::regclass);


--
-- TOC entry 2068 (class 2604 OID 16423)
-- Name: version; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utilisateur ALTER COLUMN version SET DEFAULT nextval('utilisateur_version_seq'::regclass);


--
-- TOC entry 2204 (class 0 OID 16385)
-- Dependencies: 182
-- Data for Name: acl_store; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY acl_store (id, label, droit_id, profil_id, ressource_id, lastupdate) FROM stdin;
\.


--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 183
-- Name: acl_store_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('acl_store_id_seq', 1, false);


--
-- TOC entry 2217 (class 0 OID 16492)
-- Dependencies: 195
-- Data for Name: commande; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY commande (reference, id) FROM stdin;
\.


--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 196
-- Name: commande_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('commande_id_seq', 1, false);


--
-- TOC entry 2206 (class 0 OID 16390)
-- Dependencies: 184
-- Data for Name: droit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY droit (id, label, code, lastupdate) FROM stdin;
\.


--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 185
-- Name: droit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('droit_id_seq', 1, false);


--
-- TOC entry 2215 (class 0 OID 16461)
-- Dependencies: 193
-- Data for Name: produit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY produit (label, reference, status, quantity, id, price, idpicture) FROM stdin;
test	test	1	1	b2837ee1-4458-4c70-9658-55d51bc52596	12.23	\N
\.


--
-- TOC entry 2240 (class 0 OID 0)
-- Dependencies: 194
-- Name: produit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('produit_id_seq', 1, true);


--
-- TOC entry 2208 (class 0 OID 16395)
-- Dependencies: 186
-- Data for Name: profil; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY profil (id, profil, label, lastupdate, version) FROM stdin;
2	Adm	Admin	2016-05-03 00:00:00	1
3	User	Utilisateur	2016-08-07 13:23:22.743	2
\.


--
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 187
-- Name: profil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('profil_id_seq', 3, true);


--
-- TOC entry 2242 (class 0 OID 0)
-- Dependencies: 188
-- Name: profil_version_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('profil_version_seq', 2, true);


--
-- TOC entry 2211 (class 0 OID 16404)
-- Dependencies: 189
-- Data for Name: ressource; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ressource (id, label, code, lastupdate, version) FROM stdin;
\.


--
-- TOC entry 2243 (class 0 OID 0)
-- Dependencies: 190
-- Name: ressource_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ressource_id_seq', 1, false);


--
-- TOC entry 2213 (class 0 OID 16409)
-- Dependencies: 191
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY utilisateur (name, password, mail, pid, firstname, version, lastupdate, birthdate, idpicture, id) FROM stdin;
cesar	admin	test@gmail.com	3	toto2	2	2016-10-06 11:39:56.962	1970-03-03	36bfeaf1-7631-4d50-bb87-c1905aed5545              	3
titi	admin	titi@gmail.com	2	titi	4	2016-10-05 22:24:37.367	1980-07-03	1e6ae088-8d27-4156-9786-07aa048e532f              	2
test2	test	test2@gmail.com	2	test2tttt	4	2016-10-22 09:55:52.4928	1960-07-02	520430e0-1f36-45b4-a367-9eb6bb1dcd6f              	4
fadia2	admin	fadia@gmail.com	2	fadou	2	2016-10-23 20:08:46.632	1976-03-08	a74b9831-3e8d-4e52-8864-41a94b66bbef              	5
test1111	admin	thierry.filleul@gmail.com	2	test	0	2016-12-28 09:40:39.985	1877-02-01	9f0b7ba9-38d2-4928-a378-12aae4133a97              	6
\.


--
-- TOC entry 2244 (class 0 OID 0)
-- Dependencies: 197
-- Name: utilisateur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('utilisateur_id_seq', 6, true);


--
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 192
-- Name: utilisateur_version_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('utilisateur_version_seq', 1, true);


--
-- TOC entry 2073 (class 2606 OID 16425)
-- Name: acl_store_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acl_store
    ADD CONSTRAINT acl_store_pkey PRIMARY KEY (id);


--
-- TOC entry 2085 (class 2606 OID 16627)
-- Name: command_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY commande
    ADD CONSTRAINT command_pkey PRIMARY KEY (id);


--
-- TOC entry 2075 (class 2606 OID 16427)
-- Name: droit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY droit
    ADD CONSTRAINT droit_pkey PRIMARY KEY (id);


--
-- TOC entry 2083 (class 2606 OID 16617)
-- Name: produit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (id);


--
-- TOC entry 2077 (class 2606 OID 16466)
-- Name: profil_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY profil
    ADD CONSTRAINT profil_pkey PRIMARY KEY (id);


--
-- TOC entry 2079 (class 2606 OID 16431)
-- Name: ressource_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ressource
    ADD CONSTRAINT ressource_pkey PRIMARY KEY (id);


--
-- TOC entry 2081 (class 2606 OID 16612)
-- Name: utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- TOC entry 2086 (class 2606 OID 16434)
-- Name: droit_acl_store_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acl_store
    ADD CONSTRAINT droit_acl_store_fk FOREIGN KEY (droit_id) REFERENCES droit(id);


--
-- TOC entry 2088 (class 2606 OID 16467)
-- Name: profil_acl_store_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acl_store
    ADD CONSTRAINT profil_acl_store_fk FOREIGN KEY (profil_id) REFERENCES profil(id);


--
-- TOC entry 2087 (class 2606 OID 16444)
-- Name: ressource_acl_store_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acl_store
    ADD CONSTRAINT ressource_acl_store_fk FOREIGN KEY (ressource_id) REFERENCES ressource(id);


--
-- TOC entry 2089 (class 2606 OID 16472)
-- Name: utilisateur_ibfk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT utilisateur_ibfk_1 FOREIGN KEY (pid) REFERENCES profil(id) ON UPDATE CASCADE;


--
-- TOC entry 2226 (class 0 OID 0)
-- Dependencies: 8
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-03-14 13:30:52

--
-- PostgreSQL database dump complete
--

