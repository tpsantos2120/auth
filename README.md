# Auth App

This is a backend signup service with email confirmation for activating a new user account.
It is under improvements, no unit or integration tests done so far, though it is in the plans.

# Libraries, Frameworks and Database Used

- Spring Boot(framework)
- Spring Security(to introduce authentication and authorization for users)
- Spring Data JPA and Hibernate(for CREATE,READ,UPDATE,DELETE(CRUD) operations)
- Postgres(database)
- BCrypt(for encrypting passwords)
- Lombok(Libary - Annotations to minimise boilerplate code)
- Validation(Library - validating data coming through requests)
- Java EmailSender(for sending emails)
- Maildev(email client and server for testing)
