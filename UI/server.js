const express = require("express");
const path = require("path");
const https = require("https");
const fs = require("fs");

const app = express();
const PORT = 3443; // HTTPS zwykle działa na 443, ale lokalnie może być 3443

// Wczytaj certyfikat i klucz SSL
const sslOptions = {
    key: fs.readFileSync(path.join(__dirname, "ssl", "key.pem")),
    cert: fs.readFileSync(path.join(__dirname, "ssl", "cert.pem"))
};

// Middleware do obsługi plików statycznych (HTML, CSS, JS)
app.use(express.static(path.join(__dirname, "public")));

// Endpoint dla GET "/"
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "public", "index.html"));
});

// Uruchomienie serwera HTTPS
https.createServer(sslOptions, app).listen(PORT, () => {
    console.log(`HTTPS server running at https://localhost:${PORT}/`);
});