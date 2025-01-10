# Database Schema Description

This document describes the database schema for a learning management system. The schema supports functionalities such as user management, course creation, task assignments, discussions, messaging, and notifications. Each table in the schema serves a specific purpose and is interconnected to ensure data consistency and integrity.

## Tables

### 1. **User_**
Stores user-related data, such as email, name, birthdate, role, and account details. Users can manage courses and participate in various activities within the system.

| Field         | Type         | Description                                            |
|---------------|--------------|--------------------------------------------------------|
| userid        | SERIAL       | Primary key, unique identifier for the user           |
| email         | VARCHAR(255) | User's email address (unique)                         |
| name          | VARCHAR(255) | User's full name                                      |
| birthdate     | DATE         | User's birthdate                                      |
| phonenumber   | VARCHAR(20)  | User's phone number (unique)                          |
| creationdate  | DATE         | Date of account creation                              |
| role          | VARCHAR(20)  | User's role (admin, teacher, student)                |
| isdeleted     | BOOLEAN      | Indicates if the account is deleted (default: FALSE) |
| password      | VARCHAR(255) | User's hashed password                                |

---

### 2. **Course**
Stores information about courses, including their titles, descriptions, start and end dates, and statuses.

| Field        | Type         | Description                                          |
|--------------|--------------|------------------------------------------------------|
| courseid     | SERIAL       | Primary key, unique identifier for the course       |
| title        | VARCHAR(255) | Course title                                        |
| description  | TEXT         | Detailed course description                         |
| startdate    | DATE         | Start date of the course                            |
| enddate      | DATE         | End date of the course                              |
| status       | VARCHAR(50)  | Course status (active, archived)                    |
| userid       | INT          | Foreign key referencing User_ (creator)             |

---

### 3. **Task**
Represents individual tasks or assignments within a course.

| Field        | Type         | Description                                          |
|--------------|--------------|------------------------------------------------------|
| taskid       | SERIAL       | Primary key, unique identifier for the task         |
| title        | VARCHAR(255) | Task title                                          |
| description  | TEXT         | Task description                                   |
| duedate      | DATE         | Task due date                                      |
| status       | VARCHAR(50)  | Task status (pending, completed, etc.)             |
| courseid     | INT          | Foreign key referencing Course                     |

---

### 4. **Discussion**
Facilitates discussions related to a course.

| Field         | Type         | Description                                         |
|---------------|--------------|-----------------------------------------------------|
| discussionid  | SERIAL       | Primary key, unique identifier for the discussion  |
| title         | VARCHAR(255) | Discussion title                                   |
| description   | TEXT         | Discussion description                             |
| courseid      | INT          | Foreign key referencing Course                     |

---

### 5. **Message**
Stores messages exchanged within discussions.

| Field         | Type         | Description                                         |
|---------------|--------------|-----------------------------------------------------|
| messageid     | SERIAL       | Primary key, unique identifier for the message     |
| content       | TEXT         | Message content                                    |
| timestamp     | TIMESTAMP    | Time when the message was sent                     |
| userid        | INT          | Foreign key referencing User_ (sender)            |
| discussionid  | INT          | Foreign key referencing Discussion                 |

---

### 6. **File_**
Handles file attachments across the system.

| Field       | Type         | Description                                          |
|-------------|--------------|------------------------------------------------------|
| fileid      | SERIAL       | Primary key, unique identifier for the file         |
| path        | VARCHAR(255) | Path to the file on the server                      |
| timestamp   | TIMESTAMP    | Time of file upload                                 |
| userid      | INT          | Foreign key referencing User_ (uploader)           |
| courseid    | INT          | Foreign key referencing Course                     |
| taskid      | INT          | Foreign key referencing Task                       |
| messageid   | INT          | Foreign key referencing Message                    |

---

### 7. **CalendarEvent**
Represents events scheduled for a course.

| Field        | Type         | Description                                          |
|--------------|--------------|------------------------------------------------------|
| eventnumber  | SERIAL       | Primary key, unique identifier for the event        |
| courseid     | INT          | Foreign key referencing Course                      |
| title        | VARCHAR(255) | Event title                                         |
| description  | TEXT         | Event description                                  |
| starttime    | TIMESTAMP    | Event start time                                   |
| endtime      | TIMESTAMP    | Event end time                                     |

---

### 8. **EngagesIn**
Tracks the relationship between users and courses they are enrolled in.

| Field        | Type         | Description                                          |
|--------------|--------------|------------------------------------------------------|
| userid       | INT          | Foreign key referencing User_                       |
| courseid     | INT          | Foreign key referencing Course                     |
| joindate     | DATE         | Date the user joined the course                    |

---

### 9. **UserDoesTask**
Tracks associations between users and tasks assigned to them.

| Field        | Type         | Description                                          |
|--------------|--------------|------------------------------------------------------|
| userid       | INT          | Foreign key referencing User_                       |
| taskid       | INT          | Foreign key referencing Task                       |

---

### 10. **Notification**
Handles notifications sent to users.

| Field           | Type         | Description                                      |
|------------------|--------------|--------------------------------------------------|
| notificationid   | SERIAL       | Primary key, unique identifier for the notification |
| title            | VARCHAR(255) | Notification title                             |
| content          | TEXT         | Notification content                          |
| timestamp        | TIMESTAMP    | Time when the notification was sent           |
| userid           | INT          | Foreign key referencing User_                 |
