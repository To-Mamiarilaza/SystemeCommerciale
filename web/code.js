document.addEventListener("DOMContentLoaded", function () {
    // Function to show loader
    function showLoader() {
        document.querySelector('.loader').style.display = 'block';
    }

    // Function to hide loader
    function hideLoader() {
        document.querySelector('.loader').style.display = 'none';
    }

    function simulateAjaxrequest() {
        showLoader();   //Show loader when the AJAX request starts

        // Simulate AJAX request delay (replace  this with your actual AJAX call)
        setTimeout(function() {
            hideLoader();   //Hide loader when the AJAX request is complete
        }, 3000);   // Simulated 3 secondsdelay, replace with your actual AJAX call
    }

    //Attach the function to your AJAX event (replacethis with your actual AJAX call)
    document.getElementById('buttonId').addEventListener('click', simulateAjaxrequest);
});