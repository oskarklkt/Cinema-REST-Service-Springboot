# Cinema REST Service

A Rest Service which could be used for managing Cinema ticket booking. I've created this project to learn SpringBoot basics.

## Authors

- [@oskarklkt](https://github.com/oskarklkt)

## Tech Stack

* Java, SpringBoot, Postman, Git

## Endpoints preview in Postman
* Seat
  
/seat endpoint makes it possible for user to see all available seats in Cinema

![seats](https://github.com/oskarklkt/Cinema-REST-Service-Springboot/assets/117487714/916ca25e-a3b0-4655-b7ed-4204799a82a5)

* Purchase
  
/purchase endpoint with HTTP POST method takes seat row and column from user to buy ticket and returns ticket info plus token which can be used while returning ticket

When seat chosen by user is available:

![purchase](https://github.com/oskarklkt/Cinema-REST-Service-Springboot/assets/117487714/7d18f494-8679-4170-9cf4-b3febb59bbe6)

When seat chosen by user is taken:

![purchase_AlreadyPurchased](https://github.com/oskarklkt/Cinema-REST-Service-Springboot/assets/117487714/80a11af6-9eac-490d-941c-e793e48e58f4)

When seat chosen by user doesn't exists in Cinema:

![purchase_OutOfBounds](https://github.com/oskarklkt/Cinema-REST-Service-Springboot/assets/117487714/d6d9eb96-d6dc-42b4-81d1-07a547f53e68)

* Return

/return endpoint may be used when user changes his plans and decides to return ticket. HTTP POST method takes token given to user while buying ticket.

When token is valid:

![return](https://github.com/oskarklkt/Cinema-REST-Service-Springboot/assets/117487714/8161ef93-f64e-40cd-8724-5fba9bd64825)

When token is not valid:

![return_wrong](https://github.com/oskarklkt/Cinema-REST-Service-Springboot/assets/117487714/71521184-0de7-474a-b058-d6dd6908fe0a)

* Stats

/stats endpoint is only visible to users who know the password, if given password is valid it returns some statistics about cinema

When password is valid:

![stats](https://github.com/oskarklkt/Cinema-REST-Service-Springboot/assets/117487714/a47409d1-4db1-4f64-a9dd-5b1da73cb6b5)

When password is not valid:

![stats_wrongPass](https://github.com/oskarklkt/Cinema-REST-Service-Springboot/assets/117487714/d67da709-d33d-40c2-95db-8d5c925d95bd)



