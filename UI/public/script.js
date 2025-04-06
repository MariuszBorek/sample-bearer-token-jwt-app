// -------------------------------------------------------------------------------------------------
// Zmienne globalne
let HOSTNAME = "https://localhost:8443/";


let token = "";
// Funkcja do zapisania tokenu
function setToken(newToken) {
    token = newToken;
}

// -------------------------------------------------------------------------------------------------
// ladowanie  csrf token przy ladowaniu strony
// let csrfToken = "";

// // Główna logika, która uruchamia się po załadowaniu strony
// document.addEventListener("DOMContentLoaded", async function () {
//     // Pobieramy token CSRF po załadowaniu strony
//     csrfToken = await fetchCsrfToken();
//     console.log("CSRF Token:", csrfToken);

// });

// // Funkcja do pobrania tokena CSRF z serwera
// async function fetchCsrfToken() {
//     const response = await fetch(`${HOSTNAME}api/v1/auth/csrf/get-csrf-token`, {
//         method: 'GET',
//         credentials: 'include' // Wymagane, jeśli używasz sesji/cookies
//     });
//     const data = await response.json();
//     return data.token; // Zwracamy token CSRF
// }


// -------------------------------------------------------------------------------------------------
// Add User
document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault(); // Zapobiega przeładowaniu strony

    // Pobieramy dane z formularza
    const data = {
        firstname: document.getElementById("firstname").value,
        lastname: document.getElementById("lastname").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    // Wysyłamy dane POST-em do backendu
    fetch(`${HOSTNAME}api/v1/auth/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        credentials: "include", // jeśli używasz cookies
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Rejestracja nie powiodła się");
            }
            return response.json();
        })
        .then(responseData => {
            // Wyświetlamy token w <div id="bearerToken">
            document.getElementById("bearerToken").innerText = "Sukces: " + JSON.stringify(responseData);
        })
        .catch(error => {
            document.getElementById("bearerToken").innerText = "Błąd: " + error.message;
        });
});

// -------------------------------------------------------------------------------------------------
// Get Bearer Token if user exists
document.getElementById("loginForm").addEventListener("submit", function (e) {
    e.preventDefault(); // Zapobiega przeładowaniu strony

    // Pobieramy dane z formularza
    const data = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    // Wysyłamy dane POST-em do backendu
    fetch(`${HOSTNAME}api/v1/auth/authenticate`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        credentials: "include", // jeśli używasz cookies
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Logowanie nie powiodło się");
            }
            return response.json();
        })
        .then(responseData => {
            // Wyświetlamy token w <div id="bearerToken">
            document.getElementById("bearerTokenLogin").innerText = "Sukces: " + JSON.stringify(responseData);
            // Ustawiamy token w zmiennej globalnej
            setToken(responseData.token);
        })
        .catch(error => {
            document.getElementById("bearerTokenLogin").innerText = "Błąd: " + error.message;
        });
});

// -------------------------------------------------------------------------------------------------
// Get Secured Resources
document.getElementById("getDataButton").addEventListener("click", function () {
    fetch(`${HOSTNAME}api/v1/demo-controller`, {
        method: "GET", // Metoda HTTP GET
        headers: {
            "Authorization": `Bearer ${token}`, // Wstawienie tokenu do nagłówka
            "Content-Type": "application/json"
        },
        credentials: "include"  // Jeśli chcesz wysłać ciasteczka (cookies) razem z zapytaniem
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json(); // Parsowanie odpowiedzi jako JSON
        })
        .then(data => {
            // Przetwarzanie danych odpowiedzi
            document.getElementById("securedData").innerText = "Sukces: " + JSON.stringify(data);
        })
        .catch(error => {
            console.error("There was a problem with the fetch operation:", error);
        });
});


// -------------------------------------------------------------------------------------------------
// Formularz z CSRF token
// Obsługa wysyłania formularza

// todo: nie działa, nie wiem dlaczego
document.getElementById("profileForm").addEventListener("submit", function (e) {
    e.preventDefault(); // Zapobiega przeładowaniu strony    

    const data1 = {
        username: document.getElementById("username").value
    };

    //   console.log("Dane do wysłania:", data1);
    //   console.log("Token CSRF:", csrfToken);
    //     console.log("Token:", token);

    fetch(`${HOSTNAME}api/v1/demo-csrf-controller`, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`, // Dodanie tokena (zakładając, że masz zmienną 'token')
            'Content-Type': 'application/json'

            // Wyłaczony CSRF
            // ,'X-CSRF-TOKEN': csrfToken // Dodanie tokena CSRF do nagłówka
        },
        credentials: "include",  // Jeśli chcesz wysłać ciasteczka (cookies) razem z zapytaniem
        body: JSON.stringify(data1) // Przekazywanie danych z formularza
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Przetwarzanie odpowiedzi
            console.log("Odpowiedź z serwera:", data);
            document.getElementById("csrfResponse").innerText = "Sukces: " + JSON.stringify(data);
        })
        .catch(error => {
            console.error('Wystąpił błąd:', error);
            document.getElementById("csrfResponse").innerText = "Błąd: " + error.message;
        });
});





