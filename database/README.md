
## Tipos de dado

| Tipo de dado Postgres |  Tipo de dado Java |
|:-------------------:|:------------------:|
| NUMBER(19,0)        |  Long              |
| NUMBER(10,0)        |  Integer           |
| NUMBER(5,0)         |  Short             |
| VARCHAR(*)          |  String            |
| CHAR(*)             |  String            |
| CLOB                |  String @Lob       |
| DATE                |  Date              |

## Nomenclatura

### Tabelas

* **TB_** - Tabela de Sistema
* **RL_** - Tabela de Relacionamento (associativa)

### Colunas

* **CO_NomeDoAtributo** - Código (NUMBER)
* **CO_SEQ_NomeDoAtributo** - Código com Sequence (NUMBER)
* **DT_NomeDoAtributo** - Data (DATE)
* **DS_NomeDoAtributo** - Descrição (VARCHAR/CLOB)
* **NU_NomeDoAtributo** - Número (NUMBER/CHAR/VARCHAR2) 
* **QT_NomeDoAtributo** - Quantidade (NUMBER(s,p))
* **SG_NomeDoAtributo** - Sigla (CHAR/VARCHAR2)
* **VL_NomeDoAtributo** - Valor monetário (NUMBER(6,2))

### Objetos

* **CK_ SiglaDaTabela_NomeColuna** - Check Constraint (constraint de verificação/domínio)
* **IN_ SiglaDaTabela_NomeColuna** - Index
* **IE_NomeFK sem o prefixo** - Index Chave Estrangeira
* **MVW_NomeDaViewMaterializada** - View Materilizada
* **PK_SiglaDaTabela** - Primary key (cria índice automaticamente)
* **SQ_ SiglaDaTabela_NomeColuna** - Sequence
* **UC_SiglaDaTabela_SiglaColuna** - Index Único
* **VW_NomeDaView** - View