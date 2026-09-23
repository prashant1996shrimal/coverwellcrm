
// ==========================================
// SIDEBAR
// ==========================================

const menuButton = document.getElementById("menuButton");
const sidebar = document.getElementById("sidebar");

menuButton.addEventListener("click", () => {
    sidebar.classList.toggle("open");
});

const logoutButton =
    document.getElementById("logoutButton");

logoutButton.addEventListener("click", function (event) {

    event.preventDefault();

    localStorage.removeItem("loggedIn");
    localStorage.removeItem("userEmail");
    localStorage.removeItem("project");

    window.location.href = "login.html";

});
// Check whether user is logged in

const loggedIn = localStorage.getItem("loggedIn");

if (loggedIn !== "true") {

    window.location.href = "login.html";

}


// ==========================================
// ADD CUSTOMER MODAL
// ==========================================

const addCustomerButton =
    document.getElementById("addCustomerButton");

const customerModal =
    document.getElementById("customerModal");

const closeModal =
    document.getElementById("closeModal");

const cancelModal =
    document.getElementById("cancelModal");


addCustomerButton.addEventListener("click", () => {
    customerModal.classList.add("show");
});


closeModal.addEventListener("click", () => {
    customerModal.classList.remove("show");
});


cancelModal.addEventListener("click", () => {
    customerModal.classList.remove("show");
});


// Close modal when clicking outside

customerModal.addEventListener("click", (event) => {

    if (event.target === customerModal) {
        customerModal.classList.remove("show");
    }

});


// ==========================================
// CUSTOMER FORM
// ==========================================

const customerForm =
    document.getElementById("customerForm");

customerForm.addEventListener("submit", (event) => {

    event.preventDefault();

    const name =
        document.getElementById("customerName").value;

    alert(
        `Customer "${name}" has been added successfully!`
    );

    customerForm.reset();

    customerModal.classList.remove("show");

});


// ==========================================
// SEARCH
// ==========================================

const searchInput =
    document.getElementById("searchInput");

searchInput.addEventListener("keyup", () => {

    const searchValue =
        searchInput.value.toLowerCase();

    const rows =
        document.querySelectorAll("tbody tr");

    rows.forEach((row) => {

        const text =
            row.textContent.toLowerCase();

        if (text.includes(searchValue)) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }

    });

});


// ==========================================
// TASK CHECKBOX
// ==========================================

const taskCheckboxes =
    document.querySelectorAll(".task input[type='checkbox']");

taskCheckboxes.forEach((checkbox) => {

    checkbox.addEventListener("change", () => {

        const task = checkbox.closest(".task");

        if (checkbox.checked) {

            task.style.opacity = "0.5";

            const title =
                task.querySelector("strong");

            title.style.textDecoration = "line-through";

        } else {

            task.style.opacity = "1";

            const title =
                task.querySelector("strong");

            title.style.textDecoration = "none";

        }

    });

});


// ==========================================
// PERIOD SELECT
// ==========================================

const periodSelect =
    document.getElementById("periodSelect");

periodSelect.addEventListener("change", () => {

    console.log(
        "Selected period:",
        periodSelect.value
    );

});


// ==========================================
// ADD TASK
// ==========================================

const addTaskButton =
    document.getElementById("addTaskButton");

addTaskButton.addEventListener("click", () => {

    const taskName =
        prompt("Enter the new task:");

    if (!taskName) {
        return;
    }

    alert(
        `Task "${taskName}" has been created.`
    );

});


// ==========================================
// KEYBOARD SHORTCUT
// CTRL + K = SEARCH
// ==========================================

document.addEventListener("keydown", (event) => {

    if (
        event.ctrlKey &&
        event.key.toLowerCase() === "k"
    ) {

        event.preventDefault();

        searchInput.focus();

    }

});


// ==========================================
// SIDEBAR MENU
// ==========================================

const menuItems =
    document.querySelectorAll(".menu-item");

menuItems.forEach((item) => {

    item.addEventListener("click", () => {

        menuItems.forEach((menu) => {
            menu.classList.remove("active");
        });

        item.classList.add("active");

    });

});


// ==========================================
// PROFILE CLICK
// ==========================================

const profile =
    document.querySelector(".profile");

profile.addEventListener("click", () => {

    alert("Profile menu clicked");

});