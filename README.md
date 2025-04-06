# Bearer token jwt app

## How to use

### 1A. Run App

**a) Build app:**<br>
`./gradlew clean build`

**b) Run app:**<br>
`./gradlew bootRun`

### 1B. Run App in Debug mode

**a) Add configuration**  
Edit Configuration -> + -> Remote JVM Debug<br>

**b) Run in debug mode:**<br>
`./gradlew bootRun --debug-jvm`

---

## POSTMAN
### 2. Register User in DB and get JWT token

**POST**:<br>
`https://localhost:8443/api/v1/auth/register`

**Body**:<br>
`
{
    "firstname": "name",
    "lastname": "name",
    "email": "name@gmail.com",
    "password": "1234"
}
`

**POST**:<br>
`https://localhost:8443/api/v1/auth/authenticate`

**Body**:<br>
`
{
    "email": "name@gmail.com",
    "password": "1234"
}
`

### 3. Use received token in Authentication Header

**GET**:<br>
`https://localhost:8443/api/v1/demo-controller`

**Headers**:<br>
`Authorization: Bearer <token>`

---

## UI - Html & JavaScript
##### 1. Run UI

**a) Open terminal and go to UI folder**<br>

**b) Run command in terminal:**<br>
`node server.js`

**c) Open browser and go to:**<br>
`https://localhost:3443`

---

## TODO

- [ ] Add script to run UI and backend
- [ ] Add Dockerfile
- [ ] Add Docker Compose
