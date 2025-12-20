console.log("attendance.js loaded");

/* ------------------------------
   Global state
--------------------------------*/
let selectedUserIds = new Set();
const usersMap = {}; // id -> user, filled by suggestUsers()

/* ------------------------------
   Enable submit only if date and admin selected
--------------------------------*/
function toggleSubmit() {
    const date = document.getElementById("attendanceDate").value;
    const admin = document.getElementById("adminUser").value;
    document.getElementById("submitBtn").disabled = !(date && admin);
}

// Initialize UI on page load
window.addEventListener('DOMContentLoaded', () => {
    toggleSubmit();
    updateSelectedList();
    document.getElementById("adminUser").addEventListener('change', toggleSubmit);
});

/* ------------------------------
   Autocomplete users
--------------------------------*/
function suggestUsers() {

    const keyword = document.getElementById("searchUser").value.trim();
    const suggestions = document.getElementById("suggestions");

    if (keyword.length < 2) {
        suggestions.innerHTML = "";
        return;
    }

    fetch(`/api/attendance/users/suggest?keyword=${keyword}`)
        .then(res => res.json())
        .then(users => {

            suggestions.innerHTML = "";

            if (users.length === 0) {
                suggestions.innerHTML = "<div>No users found</div>";
                return;
            }

            users.forEach(user => {
                usersMap[user.id] = user;

                suggestions.innerHTML += `
                    <div class="user-option">
                        <input type="checkbox"
                            id="user-checkbox-${user.id}"
                            onchange="toggleUser(${user.id})">
                        <span class="user-name">${user.userName}</span>
                    </div>
                `;
            });

            // reflect current selected state
            Array.from(selectedUserIds).forEach(id => {
                const cb = document.getElementById(`user-checkbox-${id}`);
                if (cb) cb.checked = true;
            });

            updateSelectedList();
        })
        .catch(err => console.error(err));
}

/* ------------------------------
   Track selected users and UI
--------------------------------*/
function toggleUser(userId) {
    if (selectedUserIds.has(userId)) {
        selectedUserIds.delete(userId);
        const cb = document.getElementById(`user-checkbox-${userId}`);
        if (cb) cb.checked = false;
    } else {
        selectedUserIds.add(userId);
        const cb = document.getElementById(`user-checkbox-${userId}`);
        if (cb) cb.checked = true;
    }
    updateSelectedList();
}

function updateSelectedList() {
    const container = document.getElementById("selectedList");
    container.innerHTML = "";
    if (selectedUserIds.size === 0) {
        container.innerHTML = "<em>No users selected</em>";
        return;
    }
    Array.from(selectedUserIds).forEach(id => {
        const user = usersMap[id];
        const text = user ? `${user.userName}` : `User ${id}`;
        container.innerHTML += `<div>${text}</div>`;
    });
}

/* ------------------------------
   Submit attendance
--------------------------------*/
function submitAttendance() {

    const date = document.getElementById("attendanceDate").value;
    if (!date) {
        alert("Please select a date");
        return;
    }

    const adminSelect = document.getElementById("adminUser");
    if (!adminSelect) {
        alert("Admin select not found!");
        return;
    }

    const adminUser = adminSelect.value;
    if (!adminUser) {
        alert("Please select an admin");
        return;
    }

    fetch("/api/attendance/mark", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            date: date,
            presentUserIds: Array.from(selectedUserIds),
            adminUser: adminUser
        })
    })
    .then(res => {
        if (!res.ok) throw new Error(`Server returned ${res.status}`);
        return res.text();
    })
    .then(msg => {
    showToast(msg); // or alert(msg) if you prefer

    // Refresh page after short delay so user sees success message
    setTimeout(() => {
        window.location.reload();
    }, 1500);
})
    .catch(err => {
        console.error(err);
        alert('Failed to submit attendance: ' + err.message);
    });
}

function showToast(message) {
    const toast = document.getElementById("toast");
    toast.textContent = message;
    toast.classList.add("show");

    setTimeout(() => {
        toast.classList.remove("show");
    }, 3000);
}

window.addEventListener("DOMContentLoaded", () => {
    if (localStorage.getItem("theme") === "dark") {
        document.body.classList.add("dark");
    }
});
