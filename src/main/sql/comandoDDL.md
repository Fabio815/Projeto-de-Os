create database bike_facil;
use bike_facil;
-- bike_facil.bf_cliente definição

CREATE TABLE `bf_cliente` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`nome` varchar(50) NOT NULL,
`telefone` varchar(14) NOT NULL,
`statusCliente` tinyint(4) DEFAULT 1,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- bike_facil.bf_endereco definição

CREATE TABLE `bf_endereco` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`rua` varchar(50) DEFAULT NULL,
`bairro` varchar(100) DEFAULT NULL,
`numero` varchar(10) DEFAULT NULL,
`complemento` varchar(100) DEFAULT NULL,
`id_cliente` int(11) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `id_cliente` (`id_cliente`),
KEY `fk_endereco_cliente` (`id_cliente`),
CONSTRAINT `fk_endereco_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `bf_cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- bike_facil.bf_item_orcamento definição

CREATE TABLE `bf_item_orcamento` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`id_orcamento` int(11) NOT NULL,
`id_produto` int(11) NOT NULL,
`quantidade` int(11) NOT NULL,
`valor_unitario` decimal(10,2) DEFAULT NULL,
`valor_total` decimal(10,2) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `fk_orcamento` (`id_orcamento`),
KEY `fk_produto` (`id_produto`),
CONSTRAINT `bf_item_orcamento_ibfk_1` FOREIGN KEY (`id_orcamento`) REFERENCES `bf_orcamento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `bf_item_orcamento_ibfk_2` FOREIGN KEY (`id_produto`) REFERENCES `bf_produto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- bike_facil.bf_orcamento definição

CREATE TABLE `bf_orcamento` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`valor_servico` decimal(10,2) DEFAULT NULL,
`valor_total` decimal(10,2) DEFAULT NULL,
`observacoes` varchar(100) DEFAULT NULL,
`id_os` int(11) NOT NULL,
PRIMARY KEY (`id`),
KEY `fk_orcamento_os` (`id_os`),
CONSTRAINT `fk_orcamento_os` FOREIGN KEY (`id_os`) REFERENCES `bf_os` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- bike_facil.bf_os definição

CREATE TABLE `bf_os` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`data_emissao` date NOT NULL,
`status` varchar(20) DEFAULT NULL,
`id_cliente` int(11) NOT NULL,
PRIMARY KEY (`id`),
KEY `fk_os_cliente` (`id_cliente`),
CONSTRAINT `fk_os_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `bf_cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- bike_facil.bf_produto definição

CREATE TABLE `bf_produto` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`nome` varchar(80) NOT NULL,
`qtd` int(11) NOT NULL,
`valor_unitario` decimal(10,2) NOT NULL,
`status` tinyint(4) DEFAULT 1,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;