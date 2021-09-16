const addEmailField = document.getElementById('add-email-field');
const addPasswordField = document.getElementById('add-password-field');
const emailContainer = document.getElementById('change-email');
const passwordContainer = document.getElementById('change-password');

addEmailField.onclick = function() {

    if (emailContainer.children.length < 1) {
        const emailInput = document.createElement('input');
        const emailLabel = document.createElement('label');

        emailInput.setAttribute('type', 'email');
        emailInput.setAttribute('id', 'email');
        emailInput.setAttribute('name', 'email');
        emailInput.setAttribute('class', 'form-control');
        emailInput.setAttribute('placeholder', 'Edit Email');

        emailLabel.setAttribute('for', 'email');
        emailLabel.setAttribute('class', 'form-label');

        emailContainer.appendChild(emailLabel);
        emailContainer.appendChild(emailInput);
    }
}

addPasswordField.onclick = function() {

    if (passwordContainer.children.length < 1) {
        const passwordInput = document.createElement('input');
        const passwordLabel = document.createElement('label');

        passwordInput.setAttribute('type', 'password');
        passwordInput.setAttribute('id', 'password');
        passwordInput.setAttribute('name', 'password');
        passwordInput.setAttribute('class', 'form-control');
        passwordInput.setAttribute('placeholder', 'Edit Password');

        passwordLabel.setAttribute('for', 'password');
        passwordLabel.setAttribute('class', 'form-label');

        passwordContainer.appendChild(passwordLabel);
        passwordContainer.appendChild(passwordInput);
    }
}