## OAUTH2.0 PROTOCOL IAM SYSTEM SERVICE

🏗️ System Overview

We will create three parts:

PartNamePurpose
✅ Auth Serverauth-serverAuthenticates users, issues tokens (OAuth2)
✅ Resource Serverresource-serverProtects APIs, validates access tokens
✅ Frontend (Optional)frontendAllows users/clients to authenticate and fetch data

⸻

🧱 Technologies
•Java 21
•Spring Boot 3.x
•Spring Security + OAuth2 Authorization Server
•JWT (JSON Web Token)
•Maven
•(Optional DB: H2 or MySQL)
•HTML/JS Frontend

⸻

⚙️ PHASE 1: Authorization Server (Authentication Provider)

This server:
•Verifies username & password
•Issues access tokens (JWTs)
•Knows the client_id and client_secret
•Supports OAuth2 grant types (we’ll use Password Grant for user auth)

⸻

📁 Project Structure

auth-server/
├── config/
│   └── http://AuthorizationServerConfig.java
│   └── http://UserConfig.java
├── http://AuthServerApplication.java
├── application.yml
└── pom.xml

⸻

🔐 PHASE 2: Resource Server (Authorization & Protected APIs)

⸻

📁 Structure

resource-server/
├── controller/
│   └── http://UserController.java
├── config/
│   └── http://SecurityConfig.java
├── http://ResourceServerApplication.java
└── application.yml

⸻
