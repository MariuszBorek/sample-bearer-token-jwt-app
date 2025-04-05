const express = require("express");
const path = require("path");

const app = express();
const PORT = 3000;

// Middleware do obsługi plików statycznych (HTML, CSS, JS)
app.use(express.static(path.join(__dirname, "public")));

// Endpoint dla GET "/"
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "public", "index.html"));
});

// Uruchomienie serwera
app.listen(PORT, () => {
    console.log(`Server running at http://localhost:${PORT}/`);
});
