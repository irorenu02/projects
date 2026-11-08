<!DOCTYPE html>
<html>
<head>
    <title>Student Registration Form</title>
</head>
<body>
    <h1>Student Registration Form</h1>
 
    <a href="{{ route('home') }}">Back to Home</a>
 
    <br><br>
 
    @if ($errors->any())
        <div style="color: red; border: 1px solid red; padding: 10px; margin-bottom: 15px;">
            <strong>Please fix the following errors:</strong>
            <ul>
                @foreach ($errors->all() as $error)
                    <li>{{ $error }}</li>
                @endforeach
            </ul>
        </div>
    @endif
 
    <form action="{{ route('register.submit') }}" method="POST">
        @csrf
 
        <label for="full_name">Full Name:</label><br>
        <input type="text" name="full_name" id="full_name" value="{{ old('full_name') }}"><br><br>
 
        <label for="email">Email Address:</label><br>
        <input type="text" name="email" id="email" value="{{ old('email') }}"><br><br>
 
        <label for="age">Age:</label><br>
        <input type="number" name="age" id="age" value="{{ old('age') }}"><br><br>
 
        <label for="course">Course:</label><br>
        <select name="course" id="course">
            <option value="">-- Select Course --</option>
            <option value="BSIT" {{ old('course') == 'BSIT' ? 'selected' : '' }}>BSIT</option>
            <option value="BSCS" {{ old('course') == 'BSCS' ? 'selected' : '' }}>BSCS</option>
            <option value="BSEMC" {{ old('course') == 'BSEMC' ? 'selected' : '' }}>BSEMC</option>
            <option value="BSIS" {{ old('course') == 'BSIS' ? 'selected' : '' }}>BSIS</option>
        </select><br><br>
 
        <button type="submit">Submit Registration</button>
    </form>
</body>
</html>