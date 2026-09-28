document.addEventListener("DOMContentLoaded", function () {

    const sidebarContainer =
        document.getElementById("sidebar-container");

    if (!sidebarContainer) {
        console.error("sidebar-container not found");
        return;
    }

    fetch("components/sidebar.html")
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "Sidebar failed to load: " +
                    response.status
                );
            }

            return response.text();
        })
        .then(html => {

            sidebarContainer.innerHTML = html;

            const logoutButton =
                document.getElementById("logoutButton");

            if (logoutButton) {

                logoutButton.addEventListener(
                    "click",
                    function (event) {

                        event.preventDefault();

                        localStorage.removeItem("loggedIn");
                        localStorage.removeItem("userEmail");
                        localStorage.removeItem("project");

                        window.location.href =
                            "login.html";
                    }
                );

            }

        })
        .catch(error => {

            console.error(
                "Sidebar loading error:",
                error
            );

        });

});