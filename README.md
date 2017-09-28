**Instituto Federal de Educação, Ciência e Tecnologia da Paraíba**

**Campus Cajazeiras**

**Curso Superior de Tecnologia em Análise e Desenvolvimento de Sistemas**

**Segurança de Dados**

**Professor**: Saymon

**Aluno**: Natarajan Rodrigues

<h3 align="center">
  Projeto: RSACrypto
</h3>

#### Descrição do projeto:

- criar um aplicativo usando Java Swing que realiza a encriptação e decriptação de um texto utilizando o algoritmo RSA Assíncrono.

- na aplicação proposta, as chaves são geradas com base em dois números primos *p* e *q* que são gerados automaticamente.

- A partir dos números autogerados, são realizados os cálculos que resultam nos números *E*, *D* e *N*.

- A chave privada é composta por *E* e *N*; já a dupla *D* e *N* são chave pública.

#### Para rodar o programa

1. Fazer download do repositório;

2. No seu terminal de linha de comando, navegue até a pasta raiz do projeto.

3. Compile o código com
```
mvn clean install
```

4. Ainda no terminal, execute o arquivo ***JAR*** através do commando
```
java -jar target/rsacripto-1.0-SNAPSHOT.jar
```
