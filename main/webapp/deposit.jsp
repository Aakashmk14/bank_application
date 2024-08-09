<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deposit</title>
    <style>
        body {
            font-family:Georgia, serif;
            background: url('images/new.jpg') no-repeat center center fixed; /* Replace with your wallpaper path */
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
        }
        .container {
            width: 25%;
            margin-width: 200px ;
            background-color:rgba(255, 255, 255, 0.70);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            font-family: Georgia, serif;
            font-size: 1.5em;
        }
        h2 {
            font-size: 2em;
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin: 10px 0 5px;
        }
        input[type="text"],
        input[type="number"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #161d24;
            color: white;
            border: none;
            cursor: pointer;
            padding: 10px;
            border-radius: 4px;
        }
        input[type="submit"]:hover {
            background-color:  #161d24;
        }
        .message {
            margin-top: 20px;
            text-align: center;
            font-size: 16px;
        }
        .message.success {
            color: yellow;
        }
        .message.error {
            color: red;
        }
         .dashboard {
            position: absolute;
            top: 30px;
            right: 20px;
            text-decoration: none;
            color: black;
            padding: 5px 10px;
            background-color:  #a8980a; /* Red */
            border-radius: 8px;
        }
        .dashboard:hover {
            background-color: #f5dd0f; /* Darker Red */
    </style>
</head>
<body>
    <div class="container">
        <a href="customerDashboard.jsp"class="dashboard">Back to Dashboard</a>
        <h2>Deposit Money</h2>
        <form action="DepositServlet" method="post">
            <label for="accountNo">Account Number</label>
            <input type="text" id="accountNo" name="accountNo" required>

            <label for="amount">Deposit Amount</label>
            <input type="number" id="amount" name="amount" min="1" required>

            <input type="submit" value="Deposit">
        </form>
        <div class="message">
            <%= request.getAttribute("message") %>
        </div>
    </div>
</body>
</html>
