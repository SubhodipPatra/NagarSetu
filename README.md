# 🏛 Nagar Setu 

**Nagar Setu** is a public grievance redressal platform that bridges the gap between citizens and administrative authorities.  
It enables **citizens** to raise complaints, **officers** to address them, and **admins** to oversee the process — all secured with **JWT authentication** and automated complaint routing.

---

##  Features

###  User
- Secure registration & login
- Submit complaints with:
  - Category (e.g., Development, Sanitation, Water Supply)
  - Location (pincode)
- Track complaint status in real-time

###  Officer
- Register & await **admin approval**
- Automatically receive complaints based on:
  - Department
  - Location
- Update complaint status & resolution notes

###  Admin
- Approve/reject officer registrations
- Manage all complaints & users
- Monitor complaint resolution timelines

---

##  Automated Complaint Routing
Nagar Setu uses a **DSA-based routing algorithm**:
1. Each complaint has a **type** (e.g., Development, Sanitation).
2. Based on the type → routed to the corresponding **department**.
3. Within that department → complaint goes to the **officer serving the given location**.
4. Officer gets notified instantly.

Example:
- Complaint: "Streetlight not working" → Category: Development → Department: BDO → Officer at pincode `713101`.

---

##  Tech Stack

| Layer          | Technology Used           |
|----------------|---------------------------|
| Backend        | Spring Boot (Java)        |
| Security       | Spring Security + JWT     |
| Database       | PostgreSQL                |
| Frontend       | React.js                  |
| Build Tool     | Maven , Docker            |
| Authentication | Token-based (JWT)         |

