# Study Plan Manager

## Project Overview

**Study Plan Manager** is a university study plan management application developed as the final project for a Software Engineering degree course. The application allows students to create, modify, and delete their study plans. This project showcases our understanding and application of software engineering principles, following a structured and well-documented development process.

The project was developed by our team, and all stages of the development, including requirements analysis, design, implementation, testing, and maintenance, are detailed in the project report available in this repository.

## Table of Contents

- [Project Overview](#project-overview)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Installation

To install and run the Study Plan Manager application, follow these steps:

1. **Clone the repository:**

   \`\`\`bash
   git clone https://github.com/cris96spa/StudyPlanManager.git
   \`\`\`

2. **Navigate to the project directory:**

   \`\`\`bash
   cd StudyPlanManager/GestionePianiDiStudio/
   \`\`\`

3. **Extract the tar file:**

   The main application is contained within a tar file. Extract it using the following command:

   \`\`\`bash
   tar -xvf StudyPlanManager.tar.gz
   \`\`\`

4. **Compile the Java files:**

   Ensure you have JDK installed on your machine. Then, compile the Java files using:

   \`\`\`bash
   javac -d bin/ src/**/*.java
   \`\`\`

5. **Run the application:**

   After compilation, run the application:

   \`\`\`bash
   java -cp bin/ com.studyplan.Main
   \`\`\`

## Usage

Once the application is up and running, students can interact with it to manage their study plans. The primary features include:

- **Create a Study Plan**: Allows students to create a new study plan by selecting courses offered in their university program.
- **Modify a Study Plan**: Enables modifications to existing study plans, such as adding or removing courses.
- **Delete a Study Plan**: Provides the option to delete an existing study plan.

For detailed usage instructions and user interface guidelines, refer to the user manual provided within the project documentation.

## Project Structure

The project is organized into the following directories:

- **src/**: Contains all Java source code files.
- **bin/**: Contains the compiled bytecode.
- **docs/**: Contains project documentation, including the detailed project report ("Elaborato Progettazione del Software.pdf").

Key files include:

- **Main.java**: The entry point of the application.
- **StudyPlanManager.java**: Core logic for managing study plans.
- **Elaborato Progettazione del Software.pdf**: The complete documentation of the project development process.

## Contributing

Contributions are welcome! To contribute to the Study Plan Manager project, follow these steps:

1. Fork the repository.
2. Create a new branch (\`git checkout -b feature-branch\`).
3. Make your changes and commit them (\`git commit -m 'Add some feature'\`).
4. Push to the branch (\`git push origin feature-branch\`).
5. Open a pull request.

Please ensure your code adheres to the coding standards outlined in the documentation.

## License

This project is licensed under the MIT License. See the \`LICENSE\` file for details.

## Acknowledgments

We would like to thank our professors and mentors for their guidance throughout the development of this project. Special thanks to our team members for their hard work and dedication.