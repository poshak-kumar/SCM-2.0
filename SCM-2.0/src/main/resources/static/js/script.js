console.log("Java Script Loaded");

let currentTheme=getTheme();
changeTheme();

function changeTheme(){
    // Set Current Theme to web page
    document.querySelector('html').classList.add(currentTheme);

    // Setting the Listener to change the Theme Button
    const themeChangeButton = document.querySelector('#theme_button');

    // Changing the text of Theme Button when initialize or start
    themeChangeButton.querySelector('span').textContent = currentTheme == 'light' ? 'Dark' : 'Light';

    themeChangeButton.addEventListener("click", (event) => {

        const oldTheme = currentTheme;
        if (currentTheme === "dark") {
            // Do Theme Light
            currentTheme = "light";
            // Update Theme value on Local Storage
            setTheme(currentTheme);
        } else {
            // Do Theme Dark
            currentTheme = "dark";
             // Update Theme value on Local Storage
            setTheme(currentTheme);
        }

        // Remove the current Theme
        document.querySelector('html').classList.remove(oldTheme);
        // Set the current Theme
        document.querySelector('html').classList.add(currentTheme);

        
        // Changing the text of Theme Button
        themeChangeButton.querySelector('span').textContent = currentTheme == 'light' ? 'Dark' : 'Light';
        
        

    });
}

// Set the Theme to local storage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}

// Get the Theme from Local storage
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}