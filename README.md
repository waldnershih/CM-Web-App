# CM-Web-App(Customer Management Web Application)
Student Assignment

## Overview
The web application aims to facilitate the user to manage the information of their customer(company) and customer contact productively. The application has the basic CRUD (create, retrieve, update, delete) operation designs against two types of users, administrator and staff. Administrators allow managing the details of all customers, their contact members, industry type, and staff. In addition, they can assign customers/ their contact members to staff. On the other hand, staff can manage customers and their contact members assigned by an administrator only

## Development
- Architecture  (MVC)
  - Web pages
  - JSF
  - EJB
  - Java Database(Derby)
- Written in Java

## Features
- Normal User(Staff)
  - Relevant CRUD features for customer management(company, its contact person) which they are assigned
- Admin
  - Relevant CRUD features for customer management(all)
  - able to assign the customer to normal user
  - able to change normal users' details
