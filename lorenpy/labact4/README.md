# Login, Registration, and Dashboard activity
A simple static HTML application containing a login page, a user registration page and a student dashboard

# Files
- `login.html`: Login page. Submitting the form opens the dashboard.
- `register.html`: Registration form for full name, username, email, password and password confirmation. The Register link redirects to `login.html`.
- `dashboard.html`: Sample student dashboard with a logout link.
- `style.css`: Shared styles for all pages.

# User Manual
Open the application

1. Open `register.html` in a web browser to create an account, or open `login.html` to sign in.
2. On the registration page, enter your full name, username, email, password, and confirm password.
3. Select "Register here". The page redirects to `login.html`.
4. Enter a username and password on the login page, then select "Login".
5. The dashboard displays the sample student records.
6. Select "Logout" to return to the login page.

This is a front-end activity only. It does not save users or authenticate credentials because it has no back-end database

## Submit Through GitHub
Create a new GitHub repository, then run these commands from this project folder:

```bash
git init
git add login.html register.html dashboard.html style.css README.md
git commit -m "Add registration page and user manual"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
git push -u origin main
```

Replace the remote URL with the link to your own GitHub repository before running the final command. Submit that repository URL for the activity.
