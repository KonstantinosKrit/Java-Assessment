The aim of the assignment is to create a general knowledge assessment application (quiz).
To use the application, the authorization of the user with a unique username and
corresponding password is required. New users can register to the application.
By entering the application, users can answer a 10-question quiz as many times as they
wish. Questions can be multiple choice or true/false. At the end of each quiz, the application
records the score achieved.
A user will be able to view their history in the form of "datetime-score".
Options for statistics will be provided, showing A) Users who have answered more quizzes,
B) Users with the best average score.
Quiz questions will be random each time and will be retrieved from https://opentdb.com/
API.
Data of both, users and scores, can be saved in CSV-type files, or in JSON-type files, or in a
relational database.
The application can be run from the command line or have a graphical interface (GUI).
1. Implement the application by applying the concepts of Data Hiding, Inheritance,
Polymorphism, Composition, Interfaces, Design Patterns, and SOLID Principles, where
necessary or possible.
2. You are asked a new requirement: upon starting a quiz the user will be able to select the
questions category. An option will also be "All". Implement this change so that it appears
with a corresponding commit on GitHub.
3: Apply the Observer pattern so that in case of any error, it will be recorded in an error.log
file.
