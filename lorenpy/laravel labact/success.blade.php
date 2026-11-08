<!DOCTYPE html>
<html>
<head>
    <title>Registration Success</title>
</head>
<body>
    <h1>Registration Successful</h1>
 
    <p>The student information has been submitted successfully.</p>
 
    <h3>Submitted Information:</h3>
    <ul>
        <li><strong>Full Name:</strong> {{ $full_name }}</li>
        <li><strong>Email:</strong> {{ $email }}</li>
        <li><strong>Age:</strong> {{ $age }}</li>
        <li><strong>Course:</strong> {{ $course }}</li>
    </ul>
 
    <a href="{{ route('register.form') }}">Submit Another Response</a>
</body>
</html>