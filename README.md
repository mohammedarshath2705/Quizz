
# Quiz App API

This project is a simple Quiz Application built using Spring Boot. The application allows users to:

1.Start a new quiz session.  
2.Retrieve a random multiple-choice question from the database.  
3.Submit an answer for a question.  
4.Get a summary of the total questions answered, including details on correct and incorrect submissions.





## Features

- H2 in-memory database for storing questions.
- REST APIs for handling quiz sessions.
- Seeded sample questions using `data.sql`.
- Clear separation of service and controller logic.
## Assumptions

1.The system uses an in-memory H2 database for simplicity.   
2.The database is prepopulated with 10 quiz questions and a single default user (test_user).  
3.No authentication or user management is implemented.  
4.Questions' options are stored as a comma-separated string in the database.
5.APIs only interact with pre-seeded questions and a single user.
## Prerequisites

- Java 17 or higher.
- Maven 3.6 or higher.
- Git installed on your machine.
## Setup Instruction

### 1. Clone the Repository

```bash
  git clone https://github.com/mohammedarshath2705/Quizz.git
```
### 2. Build the Project

Use Maven to build the project:

```bash
  mvn clean install
```

### 3. Run the Application

Start the Spring Boot application:

```bash
  mvn spring-boot:run
```

### 4.Access the APIs

The application will run on http://localhost:8080. You can use tools like Postman or curl to test the APIs.





## API EndPoints

#### 1 .Start a Quiz Session

```http
  POST/quiz/start
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Name of the user starting the session.

#### Example: 

```bash
 POST http://localhost:8080/quiz/start?username=test_user
```


#### 2. Submit an Answer

```http
  POST /quiz/answer
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `sessionId` | `long` | **Required**.  The ID of the current quiz session.
| `questionId` | `long` | **Required**.  The ID of the question being answered.
| `answer` | `string` | **Required**. The user's selected answer.

#### Example: 

```bash
POST http://localhost:8080/quiz/answer?sessionId=550e8400-e29b-41d4-a716-446655440000&questionId=5&answer=Paris
```

#### 3. Get Session Status

```http
 GET /quiz/status
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `sessionId` | `UUID` | **Required**. ID of the current quiz session.

#### Example: 

```bash
 GET http://localhost:8080/quiz/status?sessionId=550e8400-e29b-41d4-a716-446655440000
```



## Database Schema

### `question`  ###  Table

| Column | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. Primary Key
| `question` | `String` | **Required**. The quiz question
| `options` | `String` | **Required**. Comma-separated list of options
| `correctAnswer` | `String` | **Required**. The correct answer for the question

### `quiz_session`  Table
| Column | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `UUID` | **Required**. Primary Key
| `userId` | `String` | **Required**. The ID of the user
| `totalQuestions` | `Integer` | **Required**. 	Total questions answered in the quiz
| `correctAnswers` | `Integer` | **Required**. Total correct answers submitted
| `incorrectAnswers` | `Integer` | **Required**. Total incorrect answers submitted


## Seeded Question


The database is seeded with 10 example questions in the `data.sql`file.   

 Below is an example question:  
**Question**: What is the capital of France?  
**Options**: Paris, London, Berlin, Madrid.  
**Correct Answer**: Paris
## Future Enhancement

- Implement user authentication and session management.  
- Add APIs for creating questions dynamically.
- Enhance the database schema to store question options as JSON instead of a comma-separated string.
- Integrate a frontend using Angular or React.