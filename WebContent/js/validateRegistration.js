 


    document.getElementById('register-form').addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent form submission

        // Get form values
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('pass').value;
        const confirmPassword = document.getElementById('re_pass').value;
        const contact = document.getElementById('contact').value;

        // Password validation regex
        const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

        // Validation
        if (name.trim() === '') {
            alert('Please enter your name.');
            return;
        }

        if (email.trim() === '' || !email.includes('@')|| !email.includes('.')) {
            alert('Please enter a valid email address.');
            return;
        }

        if (!password.match(passwordRegex)) {
            alert('Password must contain at least 1 uppercase letter, 1 number, 1 special character, and be at least 8 characters long.');
            return;
        }

        if (password !== confirmPassword) {
            alert('Passwords do not match.');
            return;
        }

        if (contact.trim().length !== 10 || isNaN(contact)) {
            alert('Contact number should be a 10-digit number.');
            return;
        }

        // If all validations pass, you can submit the form
        this.submit();
    });

