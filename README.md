# Bearer token jwt app

## How to use

### 1. Register User in DB and get JWT token

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
---
**POST**:<br>
`localhost:8080/api/v1/auth/authenticate`

**Body:**<br>
`
{
    "email": "name@gmail.com",
    "password": "1234"
}
`

### 2. Use received token in Authentication Header

**GET**:<br>
`localhost:8080/api/v1/demo-controller`

**Headers**:<br>
`Authorization: Bearer <token>`
