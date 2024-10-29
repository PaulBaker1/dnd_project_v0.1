# Dungeons & Dragons Manager

## Overview
Welcome to the **Dungeons & Dragons Manager** project! This tool is designed to simplify campaign management for **Dungeons & Dragons 5th Edition**. The application allows users to create and manage characters, track spells, handle in-game transactions with a built-in currency system, and organize campaigns with real-time session tracking.

The backend is powered by **Spring Boot**, while the frontend is built with **React**. Data is persisted in **PostgreSQL**, and the entire application can be containerized using **Docker** for easy deployment. 

## Installation Instructions

### Prerequisites
- **Java 11+** for the backend
- **Node.js** and **npm** for the frontend
- **PostgreSQL** for database storage
- **Docker** for containerization (optional)

### Backend Setup (Spring Boot)
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/dnd-manager.git
   cd dnd-manager
   ```

2. Install dependencies via Maven:
   ```bash
   ./mvnw install
   ```

3. Set up the PostgreSQL database. Update your `application.yml` configuration:
   ```yaml
   # application.yml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/dndmanager
       username: yourusername
       password: yourpassword
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   ```

4. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend Setup (React)
1. Navigate to the frontend directory:
   ```bash
   cd src/main/resources/static
   ```

2. Install dependencies via npm:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

### Docker Setup (Optional)
To run the project in a containerized environment, use **Docker Compose**.

1. Build the Docker image:
   ```bash
   docker-compose build
   ```

2. Run the application:
   ```bash
   docker-compose up
   ```

This will set up both the **PostgreSQL** database and the **Spring Boot** application inside containers.

## Usage

Once the application is running, you can interact with it through the frontend UI. Below is a simple usage example:

### Creating a Character
1. Go to the **Characters** section.
2. Click **Create Character**.
3. Fill in the details like name, race, class, background, and ability scores.
4. Save the character. You can later level up the character or track their spells and inventory.

### Managing Campaigns
1. Navigate to the **Campaigns** section to start a new campaign.
2. Add characters to the campaign and track resources, sessions, and progress.
3. Use the **Session Logs** to keep a record of each gaming session.

### Real-Time Chat
The application also includes a real-time chat feature for communication between players and the DM during sessions. 

## Features

- **Character Management**: Create and manage characters with built-in support for races, classes, ability scores, spells, and items.
- **Spell Tracking**: Manage spell usage with a daily reset system for each character.
- **Leveling System**: Automatically handle character leveling based on experience points.
- **Inventory and Wallet System**: Manage character inventories and use a detailed currency system (copper, silver, electrum, gold, platinum).
- **Session Tracking**: Log in-game sessions and track progress across campaigns.
- **Real-Time Chat**: Communicate between players and DM via WebSocket-based real-time messaging.
- **Dockerized**: Use Docker for easy deployment with PostgreSQL and Spring Boot services.

## License
This project is licensed under the MIT License. See the [LICENSE](https://github.com/yourusername/dnd-manager/blob/main/LICENSE) file for details.

## Contributing Guidelines

We welcome contributions from developers interested in enhancing the D&D Manager project! To contribute:

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature/your-feature
   ```
3. Make your changes and test them thoroughly.
4. Commit your changes:
   ```bash
   git commit -m 'Add your feature'
   ```
5. Push the branch to your forked repository:
   ```bash
   git push origin feature/your-feature
   ```
6. Open a pull request, and we will review your changes.

### Code Style
- Ensure your code follows the project’s style guidelines.
- Use clear and descriptive commit messages.
- Test your code thoroughly before submitting.

## Contact
For any issues or suggestions, feel free to create an issue or reach out via GitHub.

### Key Updates:
- **Backend Config**: Updated instructions to use `application.yml` (instead of `application.properties`).
# dnd_manager_0.1v
