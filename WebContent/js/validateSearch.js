   function validateForm() {
        var selectedSpices = document.getElementsByName("selectedSpices");
        var selectedVeggies = document.getElementsByName("selectedveggies");
        var selectedFruits = document.getElementsByName("selectedfriuts");
        var selectedDairy = document.getElementsByName("selectedDairy");
        var selectedGrains = document.getElementsByName("selectedGrains");

        // Check if at least one checkbox is checked in any category
        if (!checkAtLeastOneSelected(selectedSpices) &&
            !checkAtLeastOneSelected(selectedVeggies) &&
            !checkAtLeastOneSelected(selectedFruits) &&
            !checkAtLeastOneSelected(selectedGrains) &&
            !checkAtLeastOneSelected(selectedDairy)) {
            document.getElementById("error-message").style.display = "block"; // Show error message
            return false; // Prevent form submission
        }

        return true; // Allow form submission
    }

    function checkAtLeastOneSelected(elements) {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].checked) {
                return true; // At least one checkbox is checked
            }
        }
        return false; // No checkbox is checked
    }