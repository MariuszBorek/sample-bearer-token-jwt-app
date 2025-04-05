# Bearer token jwt app

## How to use

### 1A. Run App

**run app:**<br>
`./gradlew bootRun`

### 1B. Run App in Debug mode

**a) Add configuration**  
Edit Configuration -> + -> Remote JVM Debug<br>

**b) Run in debug mode:**<br>
`./gradlew bootRun --debug-jvm`

# POSTMAN
### 2. Register User in DB and get JWT token

**POST**:<br>
`localhost:8080/api/v1/auth/register`

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
`localhost:8080/api/v1/auth/authenticate`

**Body**:<br>
`
{
    "email": "name@gmail.com",
    "password": "1234"
}
`

---

# UI - Html & JavaScript
### 3. Use received token in Authentication Header

**GET**:<br>
`localhost:8080/api/v1/demo-controller`

**Headers**:<br>
`Authorization: Bearer <token>`

##### 1. Run UI

**a) open terminal**<br>

**b) Go to UI folder**<br>

**c) run command in terminal:**<br>
`node server.js`


# TODO

- [ ] Add script to run UI and backend
- [ ] Add Dockerfile
- [ ] Add Docker Compose
