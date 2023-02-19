## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
This project runs on JavaFX, and without it, running this will not be possbile.

## The Different Classes
# Account Algo
This Java class is named AccountAlgo and provides a set of functions to encrypt and validate user credentials. The class includes a private variable called saltChars, which is a string containing 72 characters used as a salt for password encryption. The class includes a constructor, setIds function, and various other private helper functions to clear nulls, encrypt passwords, insert salt, and generate salts.

The getEncryption function encrypts the user's password using a combination of the insertSalt and encrypt functions. The tryLogin function validates a user's credentials by verifying if the encrypted password is in the userPassesList array, which contains a set of valid user password pairs.

The getSalt function generates a salt by performing bit manipulation on the user and password pair and then converting the result to a string by selecting characters from the saltChars string. The insertSalt function inserts the salt into the password at every third character to further strengthen the password.

Overall, the AccountAlgo class provides a set of functions to securely encrypt and validate user credentials.

# App
This is the main class of this entire project, because this contains the "main" method.

# Chatbot
This is a Java class called "Chatbot" which includes a method called "getResponse". The class has two fields, a string "chatPerson" and an integer "talkCount".

The "chatPerson" field is initialized with one of two possible character names: "DOOMGUY" or "Johnny Silverhand", chosen randomly using Math.random(). The "talkCount" field is initialized to 0.

The "getResponse" method takes a string parameter called "query". It then uses a switch statement to determine which character's response to use based on the value of the "chatPerson" field. If "chatPerson" is "Johnny Silverhand", the method calls a private method called "getJohnnyResponse" and passes the "query" parameter to it. If "chatPerson" is "DOOMGUY", the method calls a private method called "getDoomResponse" and passes the "query" parameter to it.

The "getJohnnyResponse" and "getDoomResponse" methods contain additional logic to determine a response to return based on the input "query" string. These methods use if statements and switch statements to determine which response to return based on the content of the "query" string.

After a response is determined, the "talkCount" field is incremented and the response is returned as a string.

# CommentViewer
This class exists so that you could view the differnt comments that people might have posted.
This might have some bugs. I wanted to fix all of them, but didn't have enough time.

# Forum
This is the backbone of this entire application.
This is a JavaFX controller class for a simple online forum. The class has a number of fields, including an ArrayList of post links, an index representing the current post, and a Chatbot instance for processing user messages.

The class has a number of annotated methods that are executed in response to user interaction with the forum's graphical user interface (GUI). These methods handle events like clicking buttons, hovering over buttons, and submitting text input. For example, the claimClicked() method is called when the "Claim" button is clicked, and it updates the state of the forum by adding a "Claimed by" line to the post.

Overall, the class provides functionality for creating, and reading posts in the forum. It also provides a chat feature where users can interact with a very primitive Chatbot.

# Login Screen
This is also an important class, as this is the controller for the different components in the loginScreen.fxml file.

This is a Java class LoginScreen which appears to be a controller for a JavaFX GUI application that handles user authentication and new user registration. The class contains various JavaFX GUI components like buttons, text fields, and labels, and defines various event handlers to respond to user actions like clicking the login or sign up button, entering or exiting the button, and so on.

The class has various fields like a winurl object, an AccountAlgo object, a Stage object, and a String object to store the current user's username. The winurl object is used to read and write to various text files like "users.txt" and "passwords.txt" which presumably store the registered user's usernames and passwords respectively. The AccountAlgo object appears to be responsible for handling encryption and decryption of user passwords. The Stage object is used to represent the window in which the GUI is displayed.

The LoginScreen class defines several methods that are used in the various event handlers. For example, the loginClicked method is called when the user clicks the login button, and it gets the entered username and password, checks if they are valid, and either logs in the user or displays an error message if the login was unsuccessful. The signUpClicked method is called when the user clicks the sign up button, and it registers a new user with the entered username and password, or updates the password for an existing user if the username already exists.

Overall, the LoginScreen class appears is a crucial component of a larger GUI application that provides user authentication and registration.

# Poster
This class is the controller for the Post.fxml file, which allows the user to write posts. Each post includes the Title, the name of the poster, the id, and the type.
The id gets updated in realtime.

# winurl
This Java code defines a winurl class with methods for reading and writing files in a GitHub repository, and for opening new JavaFX windows.

The winurl class is a singleton class, which means that there can only be one instance of it in the program. The constructor is private, so the only way to get an instance of the class is to call the getInstance() method.

The class has three public methods:

writeRepoFile(String filePath, String content, String commitMessage): writes a file with the given content to the filePath in the "CG-Collections" repository on GitHub. If a file already exists at the specified path, it is updated with the new content. If the file does not exist, it is created with the new content. The commitMessage parameter is the message that will be associated with the commit on GitHub.

readRepoFile(String filePath): reads the content of the file at the specified filePath in the "CG-Collections" repository on GitHub and returns it as a String. If the file does not exist, it returns the String "Nothing found".

newWindow(String path): loads the FXML file at the specified path and opens a new JavaFX window. The method returns an Object array with the loaded FXML controller and the new stage.

The winurl class also has some private methods:

createRepoFile(String filePath, String content, String commitMessage): creates a new file with the given content at the specified filePath in the "CG-Collections" repository on GitHub. The commitMessage parameter is the message that will be associated with the commit on GitHub.

There are two constructors, one of which takes a parameter, but it is not used in the class.

The class uses the org.kohsuke.github library to connect to the GitHub API using an OAuth token. The token is stored as a private static String variable OAuth. This is not considered a best practice as it exposes sensitive information.

## How to run?
i) Install the "Java Extension Pack" in VSCode.
ii) Press Ctrl + Shift + P, and then create a Java project in VS Code.
iiI) Add the jar files in the repository to your referenced libraries.
iv) Create a folder named: ".vscode"
v) Copy the .json files into it.
vi) Now run the App.java file.

If it doesn't work, then you can download JavaFX from this link: openjfx.io
