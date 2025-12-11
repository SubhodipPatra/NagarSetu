# NagarSetu — Smart Grievance System

> A smart grievance management system with **role-based access control (RBAC)**.
> Complaints are algorithmically routed to the appropriate officer and department. Users can track complaint status, and officers manage and update complaints efficiently.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Roles & Permissions](#roles--permissions)
4. [Data Model](#data-model)
5. [Algorithmic Complaint Routing](#algorithmic-complaint-routing)
6. [User Workflow](#user-workflow)
7. [Setup & Installation](#setup--installation)
8. [Future Enhancements](#future-enhancements)

---

## Project Overview

**NagarSetu** is a smart system that simplifies grievance submission and resolution.

* Users submit complaints, which are automatically routed to the relevant officer and department.
* Officers can view complaints assigned to them, update status, and provide remarks.
* Admins manage officers, departments, and assign authorities.
* Users can track the status of their complaints in real-time.

---

## Features

* **Role-Based Access Control (RBAC)**: Separate dashboards and permissions for Admin, Officer, and User.
* **Algorithmic Complaint Routing**: Automatically assigns complaints to the correct officer and department based on pincode and complaint type.
* **Complaint Tracking**: Users can view updates and remarks on their complaints.
* **Authority Management**: Admins control officer permissions and assignments.
* **Audit Trail**: Track all actions on complaints for accountability.

---

## Roles & Permissions

|    Role | Permissions                                                             |
| ------: | ----------------------------------------------------------------------- |
|   ADMIN | Manage officers, departments, and assign authority; view all complaints |
| OFFICER | View assigned complaints; update status and add remarks                 |
|    USER | Submit complaints; track status and remarks of their own complaints     |

---

## Data Model

**Users**

* `id`, `name`, `email`, `password_hash`, `role` (`USER`, `OFFICER`, `ADMIN`), `created_at`

**Departments**

* `id`, `name`, `description`

**Officers**

* `id` (FK → users.id), `department_id`, `pincode`, `authority_level`

**Complaints**

* `id`, `user_id`, `title`, `description`, `department_id`, `officer_id`, `pincode`, `status` (`PENDING`, `IN_PROGRESS`, `RESOLVED`), `created_at`, `updated_at`

**Complaint Status Logs**

* `id`, `complaint_id`, `status`, `remarks`, `updated_by`, `updated_at`

---

## Algorithmic Complaint Routing

1. User submits a complaint with title, description, department, and pincode.
2. System automatically determines the responsible officer based on department and pincode.
3. Complaint is assigned to the officer’s dashboard.
4. Officer updates status and adds remarks.
5. User sees updates in real-time.

---

## User Workflow

**For Users**

* Submit complaints specifying type and location (pincode).
* Track the complaint status and read officer remarks.

**For Officers**

* View complaints assigned based on department and pincode.
* Update complaint status and provide remarks.

**For Admins**

* Add and manage officers and departments.
* Assign authority and manage officer roles.
* View all complaints for oversight.

---

## Setup & Installation

**Backend**

* Use a backend framework like **Spring Boot** with RBAC.
* Entities: `User`, `Officer`, `Department`, `Complaint`, `ComplaintStatusLog`.
* Implement algorithm to automatically route complaints to the correct officer.
* JWT-based authentication for secure login and role management.

**Frontend**

* Separate dashboards for Admin, Officer, and User.
* Users: Submit and track complaints.
* Officers: View and update complaints assigned to them.
* Admins: Manage officers, departments, and authorities.

---

## Future Enhancements

* Notifications (email/SMS) for complaint updates.
* Analytics dashboard for complaint resolution metrics.
* Attachments for complaint evidence.
* Mobile app for easier grievance submission.
* Multi-level escalation and workflow management.

---

