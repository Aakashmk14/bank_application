<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register New Customer</title>
    <style>
        body {
            font-family: Georgia, serif;
            background: url('images/admin.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            height: 100vh;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            background: rgba(255, 255, 255, 0.55);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            	
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
        input, select {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color:  #25475e;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #7fb6db;
        }
        .backlink {
            position: absolute;
            top: 10px;
            right: 20px;
            text-decoration: none;
            color: white;
            padding: 10px 20px;
            background-color: #f21313; /* Red */
            border-radius: 8px;
        }
        .backlink:hover {
            background-color: #752815
         }
    </style>
</head>
<body>
    <div class="container">
        <a href="admindashboard.jsp" class="backlink">Back to Dashboard</a>
        <h2>Register New Customer</h2>
        <form action="registerCustomerServlet" method="post">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required>

            <label for="address">Address</label>
            <input type="text" id="address" name="address" required>

            <label for="mobile_no">Mobile Number</label>
            <input type="text" id="mobile_no" name="mobile_no" required>

            <label for="email_id">Email ID</label>
            <input type="email" id="email_id" name="email_id" required>

            <label for="account_type">Account Type</label>
            <select id="account_type" name="account_type" required>
                <option value="savings">Savings</option>
                <option value="current">Current</option>
            </select>

            <label for="balance">Initial Balance (min 1000)</label>
            <input type="number" id="balance" name="balance" min="1000" required>

            <label for="date_of_birth">Date of Birth</label>
            <input type="date" id="date_of_birth" name="date_of_birth" required>

            <label for="id_proof">ID Proof</label>
            <input type="text" id="id_proof" name="id_proof" required>

            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
