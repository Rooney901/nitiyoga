document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const user = {
        userName: document.getElementById("userName").value,
        email: document.getElementById("email").value,
        contactNumber: document.getElementById("contactNumber").value,
        dateOfBirth: document.getElementById("dateOfBirth").value,
        gender: document.getElementById("gender").value,
        medicalIssues: document.getElementById("medicalIssues").value,
        feeStatus: "UNPAID",
        active: true
    };

    fetch("http://localhost:8080/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    })
    .then(response => {
        if (response.status === 409) {
            throw new Error("Email already exists");
        }
        if (!response.ok) {
            throw new Error("Registration failed");
        }
        return response.json();
    })
    .then(data => {
        alert("Welcome to Nitiyoga 🧘");
        console.log("Created user:", data);
        document.getElementById("registerForm").reset();
    })
    .catch(error => {
        alert(error.message);
    });
});
