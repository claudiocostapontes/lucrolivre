1. Back-end (Java 21 + Spring Boot 3.x)

Vamos desenhar a fundação aplicando princípios SOLID e Clean Code desde a primeira interface. 
A divisão em camadas do DDD garante que o coração financeiro fique intocável:

Domain: Onde nascem as entidades (Motorista, Lancamento, CalculoFiscal) e as regras isoladas (isenção de 40% para apps, 
75% para táxi, teto do MEI).

Application: Os casos de uso puros (registrar ganho diário, consolidar mês).

Infrastructure: Os adaptadores para persistir os dados no DynamoDB.

Web/BFF: Controladores REST para expor os endpoints que o Angular vai consumir.

2. O Banco de Dados Local (PostgreSQL)

Para você ter agilidade no desenvolvimento sem depender de conexão com a AWS a cada teste, vamos configurar um docker-compose.yml local. 
Ele vai subir a imagem oficial amazon/dynamodb-local. 
Você codifica e testa as querys na sua própria máquina.

3. Front-end (Angular 19)

O front-end será organizado de forma modular:

Core/Shared: Serviços de autenticação e comunicação com a API.

Features: Tela de lançamento diário com botões rápidos (Uber, Shopee, Combustível) e o Dashboard de Lucro Real.

Otimização: Compilação AOT gerando um pacote estático levíssimo.

4. O Pipeline (GitHub Actions -> Oracle Cloud)

A ideia é não ter trabalho braçal no deploy. Criaremos um workflow no GitHub que, a cada push na main:

Roda os testes do Spring Boot.

Empacota o .jar e constrói a imagem Docker.

Faz o deploy automático no contêiner da sua instância A1 da Oracle Cloud.
