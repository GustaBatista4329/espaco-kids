# 🎒 Espaço Kids — Backend

API REST para gerenciamento de um centro de reforço escolar infantil.

## 🛠️ Tecnologias

- Java + Spring Boot
- Spring Security + JWT
- Hibernate 6 / JPA
- Maven

## 📦 Funcionalidades

- Autenticação com JWT e controle de acesso por perfil (`ADM`, `PROFESSORA`)
- Cadastro e gerenciamento de alunos, responsáveis e professoras
- Atualização de senha restrita ao perfil `ADM`
- Endpoints protegidos por role

## ▶️ Como rodar

```bash
./mvnw spring-boot:run
```

> Configure as variáveis de ambiente de banco de dados e JWT antes de iniciar.

## 🔗 Frontend

[espaco-kids-frontend](https://github.com/GustaBatista4329/espaco-kids-frontend)
