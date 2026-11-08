function calculate(operation) {
    // Get the values entered by the user
    const firstNumber = document.getElementById("number1").value;
    const secondNumber = document.getElementById("number2").value;
 
    // Check if both input fields contain a value
    if (firstNumber === "" || secondNumber === "") {
        document.getElementById("result").textContent =
            "Please enter two numbers.";
        return;
    }
 
    // Convert the input values from text into numbers
    const number1 = Number(firstNumber);
    const number2 = Number(secondNumber);
 
    let answer;
 
    // Select the correct mathematical operation
    if (operation === "add") {
        answer = number1 + number2;
    } else if (operation === "subtract") {
        answer = number1 - number2;
    } else if (operation === "multiply") {
        answer = number1 * number2;
    } else if (operation === "divide") {
        // Prevent division by zero
        if (number2 === 0) {
            document.getElementById("result").textContent =
                "Cannot divide by zero.";
            return;
        }
 
        answer = number1 / number2;
    }
 
    // Display the answer on the webpage
    document.getElementById("result").textContent =
        "Result: " + answer;
}
 
function clearCalculator() {
    // Remove the values from the input fields
    document.getElementById("number1").value = "";
    document.getElementById("number2").value = "";
 
    // Reset the displayed result
    document.getElementById("result").textContent = "Result: 0";
}