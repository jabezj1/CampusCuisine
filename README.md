# CampusCuisine 

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
Allows target users (students) to select and purchase food and drinks from establishments near their campus. They will recieve food recommendations, be able to set favorites, observe establishments on a map, and complete an ordering process. They can also access a profile page which will track their recent orders, billing information, and also give them to option to become a *BreadMaker*, which is a class of user that is able to deliver the food to those who order it. This app will allow students to make extra money in their free time without going far from their campus.

### App Evaluation
- **Category:** Food
- **Mobile:** This app will primarily target Android users, as it is written in Kotlin, Java, and XML.
- **Story:** Allows students to order food from nearby eatery establishments, as well as gives them the option to deliver food to other users in exchange for money.
- **Market:** This app is mainly for students but could work for ayone who is living/working near a college campus.
- **Habit:** This app is expected to be used at once a week per person, while the special *BreadMaker* class of users will use this app several times a day per person.
- **Scope:** This app will first employ a  restaurant-probing API and a Google Maps API to gather and present data about nearby restauraunts, directions to get to those restaurants, and ability to inspect the menu items of the restauraunts. There will also be a functionality to order food from these establishments, which in the future will likely use a GrubHub, UberEats, or similar API. As usage of the app increases, some users are expected to opt into the *BreakMaker* class, which will allow them to personally deliver orders made by standard users.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Users can create an account.
* Users can log into their account. There will be an option for standard login and another for *BreadMaker* login.
* Users can create an order.
* Users can check over the items in their cart and confirm their order.
* Users can see a profile page with personal information relevant to the app.
* Users can sign up to become a *BreadMaker*.
* *BreakMaker* users will be able to access a list of available jobs for them to take.
* *BreadMaker* users will be able to see the results of their work, such as money earned and previous job history.

**Optional Nice-to-have Stories**

* App signup and login via Google account authentication.

### 2. Screen Archetypes

* Account creation page, where user can input a new username and password, which will then be passed to a Back4App database. 
* Account log in page, where user can use their account credentials to log into the app. Button labeled **Log In as a Breadmaker** will also be there but will only work if user is a registered *BreadMaker*. 
* Main landing page known as **Order** page, where all users can browse restauraunts and menu items, see *Favorites*, find nearby establishments using an embedded Google Maps interface, and start an order.
* Second page known as **Cart** page, where users can see how many items are in the cart, see the total cost of the items in the cart, confirm billing details, confirm orders, and track orders.
* Third page known as **Profile** page, where users can see personal account details, payment methods, recent order history, *Favorites*, and a button to become a **BreadMaker**.
* *BreakMaker* users will have access to a fourth page called **The Bakery** page, where they can see available orders that need to be picked up, examine order receipts, and have access to an embedded Google Maps interface.
* *Breadmaker* users will have access to a fifth page called **Profile** page, where they can access account details, see total money earned, and access a history of orders they delivered. There will be a button to revert the app back into the standard user mode.

## Navigation


## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="https://github.com/jabezj1/CampusCuisine/blob/master/android-project/wireframe.png" width=600>
<img src="https://github.com/jabezj1/CampusCuisine/blob/master/android-project/starting%20page.png" width=600>
<img src="https://github.com/jabezj1/CampusCuisine/blob/master/android-project/log%20in%20page.png" width=600>
<img src="https://github.com/jabezj1/CampusCuisine/blob/master/android-project/sign%20up%20page.png" width=600>
<img src="https://github.com/jabezj1/CampusCuisine/blob/master/android-project/order%20page.png" width=600>
<img src="https://github.com/jabezj1/CampusCuisine/blob/master/android-project/my%20cart%20page.png" width=600>
<img src="https://github.com/jabezj1/CampusCuisine/blob/master/android-project/profile%20page.png" width=600>
<img src="https://github.com/jabezj1/CampusCuisine/blob/master/android-project/bakery%20page.png" width=600>
<img src="https://github.com/jabezj1/CampusCuisine/blob/master/android-project/history%20page.png" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype





## Schema 

### Models

#### POST/GET UserTable

| Property | Type | Description |
| :---: | :---: |  :---: | 
| UserId | *number*| unique id for each user to identify them |
| Username | *string* | username used to logged |
| Password | *string* | password for logging in |
| Email    | *string* | users email to be used in profile sign up |
| Breadmaker| *boolean* | determines if user is going to be delivering or not based off user input|

#### POST/GET OrdersTable
| Property | Type | Description |
| :---: | :---: |  :---: | 
| OrderId | *number* | unique id assigned to each order made |
| CreatedAt | *datetime* |  date when user placed order |
| UpdatedAt | *datetime* |  date when post is updated | 
| OrderComplete | *boolean* | signifies which orders have not been completed that will apear for deliverers| 

### Networking
#### List of network requests by screen

* SignUp Screen 
  * (POST) Query to add user to database
  
* Login Screen
  * (Read/GET) Query to call users information matches one in database
  
* Main Screen
  * (Read/GET) Query to get food suggestions 
  * (Read/GET) Query to get favorited food places 
  * (Read/GET) Query to get locations near user 
  
* Cart/Order Screen
  * (POST) Query to add food to an order to be sent to orders table
  * (Read/GET) Query to get order status for users and bread makers
  * (PUT) Quer to update order status when Breadmaker accepts job

* Profile Screen
  * (Read/GET) Query to get users account details
  * (PUT) Query to update users account details
  * (Read/GET) Query to get orders made by current user
  * (Read/GET) Query to get favorited food places
  * (PUT) Query to make user a breadmaker/deliverer

* Breadmakers
  * (Read/GET) Query to get all orders available
  * (Read/GET) Query to get all orders taken history
