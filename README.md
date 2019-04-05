# ShoeChatBot2
A3-Andrew Tardif

Hi this is my ReadMe File for A3

Things I already did in the previous iteration:
  Design the Gui and send the code over to teammate for implimentation
  
Things i Changed

1. Added Client and Server Classes to use sockets with

        -Adds additional Functionality beyond regular shoe chatbot Normaly functionality
 
2. Attempt at hyperlinking code for Html's(failed and commented out)
3. Changes to Answer Generator for utilizing both the shoechatbot functionality and Socket conversation
4. Add psudo switch statement to switch between modes at the begining

        -Helps user decide what they want to use the program for


Snippet of normal use with first line changed to choose between "Normal Functionality" and "Chatbot talking to itself via Sockets"

Normal Functionality

20:01:46] Bot: Press 1 for Normal Functionality, Press 2 for two chatbot Convo

[20:01:49] You: 1

[20:01:49] Bot: I'm not sure what you meant. Try "I want to buy running shoes"

[20:01:59] You: hi

[20:01:59] Bot: Hello sir/madam, how are you doing today?

[20:02:02] You: good

[20:02:02] Bot: Great, how may I help you today?

[20:02:12] You: i would like to buy shoes

[20:02:12] Bot: What type are you looking for?

[20:02:17] You: soccer

[20:02:18] Bot: Here are the soccer shoes that we have: 
1) Shoe: Copa Mundial Cleats, Price: 200
URL: https://www.adidas.ca/en /copa-mundial-cleats/015110.html
2) Shoe: Predator 19.3 Firm Ground Cleats, Price: 130
URL: https://www.adidas.ca/en /predator-19.3-firm-ground-cleats/D97942.html
3) Shoe: X 18.1 Firm Ground Cleats, Price: 300
URL: https://www.adidas.ca/en /x-18.1-firm-ground-cleats/BB9346.html
4) Shoe: Predator 19+ Firm Ground Cleats, Price: 400
URL: https://www.adidas.ca/en /predator-19-plus-firm-ground-cleats/BC0549.html

 Please type the shoe number that you want to purchase.

[20:02:22] You: 1

[20:02:22] Bot: Alright, selected Copa Mundial Cleats for $200. Would you like to checkout?

[20:02:26] You: yes

[20:02:26] Bot: Successfully checked out your shoe: Copa Mundial Cleats.
Would you like to purchase another one?

[20:02:31] You: no

[20:02:31] Bot: Thank you for buying from us! See you later!

-----------------------------------------------------------------------------------------------------
Chatbot Talking to itself via sockets(as was accepted through email by the teacher)

[20:05:54] Bot: Press 1 for Normal Functionality, Press 2 for two chatbot Convo

[20:05:56] Client: Do you like waffles?

[20:05:56] Client: Yes I like waffles!

[20:05:56] Client: Amazing! Are you good at cooking them?

[20:05:56] Client: Yeah I am, how about you?

[20:05:56] Client: I'm pretty good at it, gonna go make some, see you later.

[20:05:56] Client: Okay, see you later :)

[20:05:56] Client: Do you like waffles?

[20:05:56] Client: Yes I like waffles!

[20:05:56] Client: Amazing! Are you good at cooking them?

[20:05:56] Client: Yeah I am, how about you?
